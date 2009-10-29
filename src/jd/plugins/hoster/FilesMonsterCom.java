//    jDownloader - Downloadmanager
//    Copyright (C) 2009  JD-Team support@jdownloader.org
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package jd.plugins.hoster;

import jd.PluginWrapper;
import jd.nutils.encoding.Encoding;
import jd.parser.Regex;
import jd.parser.html.Form;
import jd.plugins.Account;
import jd.plugins.AccountInfo;
import jd.plugins.DownloadLink;
import jd.plugins.HostPlugin;
import jd.plugins.LinkStatus;
import jd.plugins.PluginException;
import jd.plugins.PluginForHost;
import jd.plugins.DownloadLink.AvailableStatus;

@HostPlugin(revision = "$Revision$", interfaceVersion = 2, names = { "filesmonster.com" }, urls = { "http://[\\w\\.\\d]*?filesmonster\\.com/download.php\\?id=.+" }, flags = { 2 })
public class FilesMonsterCom extends PluginForHost {

    public FilesMonsterCom(PluginWrapper wrapper) {
        super(wrapper);
        // Premium isn't finished yet
        this.enablePremium("http://filesmonster.com/service.php");
    }

    public void login(Account account) throws Exception {
        this.setBrowserExclusive();
        br.setFollowRedirects(true);
        br.postPage("http://filesmonster.com/login.php", "act=login&user=" + Encoding.urlEncode(account.getUser()) + "&pass=" + Encoding.urlEncode(account.getPass()));
        if (!br.containsHTML(">Your membership type</span></td>.*?<td>Premium</td>") || !br.containsHTML(">Expired\\?</span></td>.*?<td>No <a") || br.containsHTML("Username/Password can not be found in our database") || br.containsHTML("Try to recover your password by 'Password reminder'")) { throw new PluginException(LinkStatus.ERROR_PREMIUM); }
    }

    public AccountInfo fetchAccountInfo(Account account) throws Exception {
        AccountInfo ai = new AccountInfo();
        try {
            login(account);
        } catch (PluginException e) {
            account.setValid(false);
            return ai;
        }
        String hostedFiles = br.getRegex(">Hosted Files</span></td>.*?<td>(\\d+).*?<a").getMatch(0);
        if (hostedFiles != null) ai.setFilesNum(Long.parseLong(hostedFiles));
        String trafficleft = br.getRegex("id=\"info_credit\">.*?<strong>(.*?)</strong>").getMatch(0);
        if (trafficleft != null) {
            ai.setTrafficLeft(Regex.getSize(trafficleft));
        }
        String expires = br.getRegex(">Membership expiration</span></td>.*?<td>([0-9]{1,2}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2})</td>").getMatch(0);
        if (expires != null) {
            ai.setValidUntil(Regex.getMilliSeconds(expires, "MM/dd/yy HH:mm", null));
            account.setValid(true);
            return ai;
        } else {
            expires = br.getRegex("<span style=color:#[0-9a-z]+>.*?([0-9]{1,2}/[0-9]{1,2}/[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}).*?</span>").getMatch(0);
            if (expires != null) {
                ai.setValidUntil(Regex.getMilliSeconds(expires, "MM/dd/yy HH:mm", null));
                account.setValid(true);
                return ai;
            } else {
                account.setValid(false);
                return ai;
            }
        }
    }

    public void handlePremium(DownloadLink downloadLink, Account account) throws Exception {
        requestFileInformation(downloadLink);
        login(account);
        br.setDebug(true);
        br.getPage(downloadLink.getDownloadURL());
        String premlink = br.getRegex("\"(http://filesmonster\\.com/get/.*?\\./)\"").getMatch(0);
        if (premlink == null) throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT);
        br.getPage(premlink);
        if (br.containsHTML("but it has exceeded the daily limit download in total")) throw new PluginException(LinkStatus.ERROR_PREMIUM, PluginException.VALUE_ID_PREMIUM_TEMP_DISABLE);
        Form DLForm1 = br.getForm(0);
        DLForm1.setMethod(Form.MethodType.POST);
        DLForm1.setAction("http://filesmonster.com/ajax.php");
        br.submitForm(DLForm1);
        String ticketID = br.getRegex("text\":\"(.*?)\"").getMatch(0);
        if (ticketID == null) throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT);
        DLForm1 = new Form();
        DLForm1.setMethod(Form.MethodType.POST);
        DLForm1.setAction("http://filesmonster.com/ajax.php");
        DLForm1.put("act", "getdl");
        DLForm1.put("data", ticketID);
        br.submitForm(DLForm1);
        String dllink = br.getRegex("url\":\"(http:.*?)\"").getMatch(0);
        if (dllink == null) throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT);
        dllink = dllink.replaceAll("\\\\/", "/");
        /* max chunks to 1 , because each chunk gets calculated full size */
        dl = jd.plugins.BrowserAdapter.openDownload(br, downloadLink, dllink, true, 1);
        if (!(dl.getConnection().isContentDisposition())) {
            br.followConnection();
            if (premlink == null) throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT);
        }
        dl.startDownload();
    }

    public int getMaxSimultanPremiumDownloadNum() {
        return -1;
    }

    @Override
    public AvailableStatus requestFileInformation(DownloadLink downloadLink) throws Exception {
        br.setFollowRedirects(true);
        br.getPage(downloadLink.getDownloadURL());
        String filesize = br.getRegex("File size: <span class=\"em\">(.*?)</span>").getMatch(0);
        String filename = br.getRegex("File name: <span class=\"em\">(.*?)</span>").getMatch(0);
        if (filename == null) { throw new PluginException(LinkStatus.ERROR_FILE_NOT_FOUND); }
        downloadLink.setName(Encoding.htmlDecode(filename.trim()));
        if (filesize != null) {
            downloadLink.setDownloadSize(Regex.getSize(filesize.trim()));
        }
        return AvailableStatus.TRUE;

    }

    @Override
    public void handleFree(DownloadLink downloadLink) throws Exception {
        br.setFollowRedirects(true);
        br.getPage(downloadLink.getDownloadURL());
        String wait = br.getRegex("You can wait for the start of downloading (\\d+) minute").getMatch(0);
        if (wait != null) { throw new PluginException(LinkStatus.ERROR_IP_BLOCKED, Long.parseLong(wait) * 60 * 1000l); }
        wait = br.getRegex("is already in use (\\d+) free download").getMatch(0);

        if (wait != null) { throw new PluginException(LinkStatus.ERROR_IP_BLOCKED, 10 * 60 * 1000l); }
        /* get file id */

        String fileID = br.getRegex("<input type=\"hidden\" name=\"t\" value=\"(.*?)\"").getMatch(0);
        String fmurl = br.getRegex("(http://[\\w\\.\\d]*?filesmonster\\.com/)").getMatch(0);

        if (fileID == null || fmurl == null) {
            if (br.containsHTML("UNDER MAINTENANCE")) {
                throw new PluginException(LinkStatus.ERROR_TEMPORARILY_UNAVAILABLE, "Maintenance", 60 * 1000l);
            } else if (fmurl == null) { /* no filesmonsterpage, retry */
                throw new PluginException(LinkStatus.ERROR_TEMPORARILY_UNAVAILABLE, "Invalid page", 20 * 1000l);
            }

            String isPay = br.getRegex("<input type=\"hidden\" name=\"showpayment\" value=\"(1)").getMatch(0);
            Boolean isFree = br.containsHTML("slowdownload");
            if (!isFree && isPay != null) {
                throw new PluginException(LinkStatus.ERROR_FATAL, "Only downloadable via premium");
            } else {
                throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT);
            }

        }

        br.postPage(fmurl + "get/free/", "t=" + fileID);
        /* now we have the data page, check for wait time and data id */

        String data = br.getRegex("name='data' value='(.*?)'>").getMatch(0);
        wait = br.getRegex("'wait_sec'>(\\d+)<").getMatch(0);
        if (data == null || wait == null) { throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT); }

        /* request ticket for this file */

        br.postPage(fmurl + "ajax.php", "act=rticket&data=" + data);
        data = br.getRegex("\\{\"text\":\"(.*?)\"").getMatch(0);
        /* wait */
        sleep(1000l * (Long.parseLong(wait) + 2), downloadLink);
        /* request download information */
        br.postPage(fmurl + "ajax.php", "act=getdl&data=" + data);
        String url = br.getRegex("\\{\"url\":\"(.*?)\"").getMatch(0);
        data = br.getRegex("\"file_request\":\"(.*?)\"").getMatch(0);
        if (data == null || url == null) { throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT); }

        url = url.replaceAll("\\\\/", "/");
        /* start download */
        dl = jd.plugins.BrowserAdapter.openDownload(br, downloadLink, url, "X-File-Request=" + data);
        if (!dl.getConnection().isContentDisposition()) {
            br.followConnection();
            throw new PluginException(LinkStatus.ERROR_PLUGIN_DEFEKT);
        }
        dl.setFilenameFix(true);
        dl.startDownload();

    }

    @Override
    public int getMaxSimultanFreeDownloadNum() {
        return 1;
    }

    @Override
    public void reset() {
    }

    @Override
    public void resetDownloadlink(DownloadLink link) {
    }

    @Override
    public String getAGBLink() {

        return "http://filesmonster.com/rules.php";

    }

}
