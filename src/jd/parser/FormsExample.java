//    jDownloader - Downloadmanager
//    Copyright (C) 2008  JD-Team jdownloader@freenet.de
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

package jd.parser;

import jd.plugins.RequestInfo;

public class FormsExample {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Suche auf Glück bei Google
        System.out.println("Suche auf Glück bei Google");
        Form[] forms = Form.getForms("http://www.google.de/");
        for (Form element : forms) {
            System.out.println(element.toString());
        }
        Form form = Form.getForms("http://www.google.de/")[0];
        form.vars.remove("btnG");
        form.vars.put("q", "jDownloader");
        System.out.println(form.getRequestInfo().getLocation());
        RequestInfo req = form.getRequestInfo();

        System.out.println(req.getConnection().getURL().toString());
        System.out.println(req.getLocation() + "\n\n");

        // Eintrag auf Pastebin
        System.out.println("Eintrag auf Pastebin");
        forms = Form.getForms("http://jdownloader.pastebin.com/");
        for (Form element : forms) {
            System.out.println(element.toString());
        }
        form = forms[forms.length - 1];
        String str = form.toString();
        form.vars.put("format", "java");
        form.vars.put("code2", str);
        form.vars.put("poster", "jDownloader");
        req = form.getRequestInfo();
        System.out.println(req.getConnection().getURL().toString());

    }

}
