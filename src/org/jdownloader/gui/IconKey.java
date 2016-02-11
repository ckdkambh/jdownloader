package org.jdownloader.gui;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.appwork.utils.Files;
import org.appwork.utils.Files.Handler;
import org.appwork.utils.os.CrossSystem;

public class IconKey {

    public static void main(String[] args) throws URISyntaxException {
        URL url = IconKey.class.getResource(IconKey.class.getSimpleName() + ".class");
        File file = new File(url.toURI());
        while (!file.getName().equals("bin")) {
            file = file.getParentFile();
        }
        final File images;
        if (CrossSystem.isWindows()) {
            images = new File(file.getParentFile(), "themes\\themes\\standard\\org\\jdownloader\\images");
        } else {
            images = new File(file.getParentFile(), "themes/themes/standard/org/jdownloader/images");
        }
        Files.walkThroughStructure(new Handler<RuntimeException>() {
            protected File root = null;

            @Override
            public void intro(File f) throws RuntimeException {
                root = f;
            }

            @Override
            public void onFile(File f) throws RuntimeException {
                if (f.getName().endsWith(".png")) {
                    String rel = Files.getRelativePath(root, f);
                    rel = rel.substring(0, rel.length() - 4);

                    String name = rel.toUpperCase().replaceAll("\\W", "_");
                    System.out.println("public static final String ICON_" + name + " = \"" + rel + "\";");

                }
            }

            @Override
            public void outro(File f) throws RuntimeException {
            }

        }, images);
        System.out.println(url);
    }

    public static final String ICON_PLUGIN                      = "plugin";
    public static final String ICON_GO_NEXT                     = "go-next";
    public static final String ICON_FOLDER_ADD                  = "folder_add";
    public static final String ICON_UNDO                        = "undo";
    public static final String ICON_VIDEO                       = "video";
    public static final String ICON_POPDOWNSMALL                = "popDownSmall";
    public static final String ICON_PROXY_ROTATE                = "proxy_rotate";
    public static final String ICON_MEDIA_PLAYBACK_START_FORCED = "media-playback-start_forced";
    public static final String ICON_BASICAUTH                   = "basicauth";
    public static final String ICON_DESKTOP                     = "desktop";
    public static final String ICON_WIZARD                      = "wizard";
    public static final String ICON_DLC                         = "dlc";
    public static final String ICON_LOG                         = "log";
    public static final String ICON_LOGOUT                      = "logout";
    public static final String ICON_EXTENSIONMANAGER            = "extensionmanager";
    public static final String ICON_OK                          = "ok";
    public static final String ICON_PROXY                       = "proxy";
    public static final String ICON_SPEED                       = "speed";
    public static final String ICON_CONFIRMALL                  = "confirmAll";
    public static final String ICON_HARDDISK                    = "harddisk";
    public static final String ICON_CLOSE                       = "close";
    public static final String ICON_POPUPLARGE                  = "popUpLarge";
    public static final String ICON_ERROR                       = "error";
    public static final String ICON_TEXT                        = "text";
    public static final String ICON_UPDATERICON100              = "updaterIcon100";
    public static final String ICON_ADD                         = "add";
    public static final String ICON_WARNING_RED                 = "warning_red";
    public static final String ICON_MOBILE                      = "mobile";
    public static final String ICON_BROWSE                      = "browse";
    public static final String ICON_POPDOWNLARGE                = "popDownLarge";
    public static final String ICON_HEART                       = "heart";
    public static final String ICON_OCR                         = "ocr";
    public static final String ICON_SORT                        = "sort";
    public static final String ICON_REMOVE_OK                   = "remove_ok";
    public static final String ICON_POPUPSMALL                  = "popUpSmall";
    public static final String ICON_FILTER                      = "filter";
    public static final String ICON_RENEW                       = "renew";
    public static final String ICON_UPLOAD                      = "upload";
    public static final String ICON_STOP                        = "stop";
    public static final String ICON_PREMIUM                     = "premium";
    public static final String ICON_GO_DOWN                     = "go-down";
    public static final String ICON_DOCUMENT                    = "document";
    public static final String ICON_GO_BOTTOM                   = "go-bottom";
    public static final String ICON_RUN                         = "run";
    public static final String ICON_FOLDER                      = "folder";
    public static final String ICON_QUESTION                    = "question";
    public static final String ICON_REMOVE_DISABLED             = "remove_disabled";
    public static final String ICON_THUMBS_UP                   = "thumbs-up";
    public static final String ICON_TREE_PACKAGE_CLOSED         = "tree_package_closed";
    public static final String ICON_REGEXSTAR                   = "regexStar";
    public static final String ICON_TOPBAR                      = "topbar";
    public static final String ICON_CONTRIBUTER                 = "contributer";
    public static final String ICON_MEDIAPLAYER                 = "mediaplayer";
    public static final String ICON_MINIMIZE                    = "minimize";
    public static final String ICON_UPDATE                      = "update";
    public static final String ICON_CLOSE_ON                    = "close.on";
    public static final String ICON_CHANGELOG                   = "changelog";
    public static final String ICON_MONEY                       = "money";
    public static final String ICON_NETWORK_ERROR               = "network-error";
    public static final String ICON_SERVER                      = "server";
    public static final String ICON_MEDIA_PLAYBACK_START        = "media-playback-start";
    public static final String ICON_CLIPBOARD                   = "clipboard";
    public static final String ICON_PASSWORD                    = "password";
    public static final String ICON_DIALOGOCR                   = "dialogOCR";
    public static final String ICON_BOX                         = "box";
    public static final String ICON_STOP_CONDITIONAL            = "stop_conditional";
    public static final String ICON_RECORD                      = "record";
    public static final String ICON_SKIPPED                     = "skipped";
    public static final String ICON_PRIO_3                      = "prio_3";
    public static final String ICON_FLAGS_DE                    = "flags/de";
    public static final String ICON_FLAGS_EN                    = "flags/en";
    public static final String ICON_FILEICON                    = "fileIcon";
    public static final String ICON_EXTRACTION_TRUE             = "extraction_true";
    public static final String ICON_FAV_LARGE_NETLOAD_IN        = "fav/large.netload.in";
    public static final String ICON_FAV_BIG_NETLOAD_IN          = "fav/big.netload.in";
    public static final String ICON_FAV_LARGE_UPLOADED_TO       = "fav/large.uploaded.to";
    public static final String ICON_FAV_BIG_SHARE_ONLINE_BIZ    = "fav/big.share-online.biz";
    public static final String ICON_FAV_BIG_UPLOADED_TO         = "fav/big.uploaded.to";
    public static final String ICON_FAV_LARGE_SHARE_ONLINE_BIZ  = "fav/large.share-online.biz";
    public static final String ICON_FAV_FOOTER_UPLOADED_TO      = "fav/footer.uploaded.to";
    public static final String ICON_UPDATERICON0                = "updaterIcon0";
    public static final String ICON_TEST                        = "test";
    public static final String ICON_FALSE_ORANGE                = "false-orange";
    public static final String ICON_LIST                        = "list";
    public static final String ICON_TREE_PACKAGE_OPEN           = "tree_package_open";
    public static final String ICON_RIGHT                       = "right";
    public static final String ICON_LOAD                        = "load";
    public static final String ICON_BITCOIN                     = "bitcoin";
    public static final String ICON_IMAGE                       = "image";
    public static final String ICON_FIND                        = "find";
    public static final String ICON_IMPORT                      = "import";
    public static final String ICON_ORDER                       = "order";
    public static final String ICON_STOPSIGN                    = "stopsign";
    public static final String ICON_BOTTY_HEART                 = "botty/heart";
    public static final String ICON_BOTTY_STOP                  = "botty/stop";
    public static final String ICON_BOTTY_ROBOT_DEL             = "botty/robot_del";
    public static final String ICON_BOTTY_ROBOT                 = "botty/robot";
    public static final String ICON_BOTTY_ROBOT_SOS             = "botty/robot_sos";
    public static final String ICON_BOTTY_ROBOT_INFO            = "botty/robot_info";
    public static final String ICON_PRIO_2                      = "prio_2";
    public static final String ICON_BAD                         = "bad";
    public static final String ICON_REMOVE_DUPES                = "remove_dupes";
    public static final String ICON_DOWNLOADPASSWORD            = "downloadpassword";
    public static final String ICON_EXTRACTION_TRUE_FAILED      = "extraction_true_failed";
    public static final String ICON_PLAY                        = "play";
    public static final String ICON_HOME                        = "home";
    public static final String ICON_TREE_ARCHIVE                = "tree_archive";
    public static final String ICON_RESTART                     = "restart";
    public static final String ICON_URL                         = "url";
    public static final String ICON_BUBBLE                      = "bubble";
    public static final String ICON_COMPRESS                    = "compress";
    public static final String ICON_CLEAR                       = "clear";
    public static final String ICON_WRENCH                      = "wrench";
    public static final String ICON_ABOUT                       = "about";
    public static final String ICON_CANCEL                      = "cancel";
    public static final String ICON_SIDEBAR                     = "sidebar";
    public static final String ICON_COPY                        = "copy";
    public static final String ICON_NETWORK_IDLE                = "network-idle";
    public static final String ICON_MENU                        = "menu";
    public static final String ICON_TRASH                       = "trash";
    public static final String ICON_BATCH                       = "batch";
    public static final String ICON_WARNING                     = "warning";
    public static final String ICON_GO_UP                       = "go-up";
    public static final String ICON_PRIO__1                     = "prio_-1";
    public static final String ICON_FILE                        = "file";
    public static final String ICON_PACKAGIZER                  = "packagizer";
    public static final String ICON_TREE_ARCHIVE_OPEN           = "tree_archive_open";
    public static final String ICON_MODEM                       = "modem";
    public static final String ICON_RECONNECT                   = "reconnect";
    public static final String ICON_HASHSUM                     = "hashsum";
    public static final String ICON_SAVETO                      = "saveto";
    public static final String ICON_GUI                         = "gui";
    public static final String ICON_CLOUD_SYNC                  = "cloud_sync";
    public static final String ICON_START                       = "start";
    public static final String ICON_EDIT                        = "edit";
    public static final String ICON_LOGO_DLC                    = "logo/dlc";
    public static final String ICON_LOGO_CNL                    = "logo/cnl";
    public static final String ICON_LOGO_LOGO_128X128           = "logo/logo-128x128";
    public static final String ICON_LOGO_NZB                    = "logo/nzb";
    public static final String ICON_LOGO_ANDROID                = "logo/android";
    public static final String ICON_LOGO_UPNP                   = "logo/upnp";
    public static final String ICON_LOGO_9KW                    = "logo/9kw";
    public static final String ICON_LOGO_ENDCAPTCHA             = "logo/endCaptcha";
    public static final String ICON_LOGO_LOGO_19_19             = "logo/logo_19_19";
    public static final String ICON_LOGO_CHROME                 = "logo/chrome";
    public static final String ICON_LOGO_JD_LOGO_54_54          = "logo/jd_logo_54_54";
    public static final String ICON_LOGO_BITCOIN_LARGE          = "logo/bitcoin_large";
    public static final String ICON_LOGO_CBH                    = "logo/cbh";
    public static final String ICON_LOGO_LOGO_20_20             = "logo/logo_20_20";
    public static final String ICON_LOGO_LOGO_15_15             = "logo/logo_15_15";
    public static final String ICON_LOGO_IMAGE_TYPERZ           = "logo/image_typerz";
    public static final String ICON_LOGO_PAYPAL                 = "logo/paypal";
    public static final String ICON_LOGO_LOGO_14_14             = "logo/logo_14_14";
    public static final String ICON_LOGO_FFMPEG                 = "logo/ffmpeg";
    public static final String ICON_LOGO_CHEAPCAPTCHA           = "logo/cheapCaptcha";
    public static final String ICON_LOGO_WINDOWS                = "logo/windows";
    public static final String ICON_LOGO_MYJDOWNLOADER          = "logo/myjdownloader";
    public static final String ICON_LOGO_IOS                    = "logo/ios";
    public static final String ICON_LOGO_JD_LOGO_64_64          = "logo/jd_logo_64_64";
    public static final String ICON_LOGO_LOGO_18_18             = "logo/logo_18_18";
    public static final String ICON_LOGO_JD_LOGO_128_128        = "logo/jd_logo_128_128";
    public static final String ICON_LOGO_FIREFOX                = "logo/firefox";
    public static final String ICON_LOGO_JD_LOGO_54_54_TRANS    = "logo/jd_logo_54_54_trans";
    public static final String ICON_LOGO_PAYPAL_LARGE           = "logo/paypal_large";
    public static final String ICON_LOGO_CAPTCHASOLUTIONS       = "logo/CaptchaSolutions";
    public static final String ICON_LOGO_LOGO_48X48             = "logo/logo-48x48";
    public static final String ICON_LOGO_DBC                    = "logo/dbc";
    public static final String ICON_LOGO_PAYSAFECARD_LARGE      = "logo/paysafecard_large";
    public static final String ICON_LOGO_LOGO_17_17             = "logo/logo_17_17";
    public static final String ICON_LOGO_JD_LOGO_256_256        = "logo/jd_logo_256_256";
    public static final String ICON_LOGO_LOGO_16_16             = "logo/logo_16_16";
    public static final String ICON_SELECT                      = "select";
    public static final String ICON_LOCK                        = "lock";
    public static final String ICON_PASTE                       = "paste";
    public static final String ICON_RESET                       = "reset";
    public static final String ICON_TRUE                        = "true";
    public static final String ICON_THUMBS_DOWN                 = "thumbs-down";
    public static final String ICON_INFO                        = "info";
    public static final String ICON_BUY                         = "buy";
    public static final String ICON_SETTINGS                    = "settings";
    public static final String ICON_WAIT                        = "wait";
    public static final String ICON_LOGIN                       = "login";
    public static final String ICON_GO_TOP                      = "go-top";
    public static final String ICON_TRUE_ORANGE                 = "true-orange";
    public static final String ICON_PACKAGE_OPEN                = "package_open";
    public static final String ICON_AUDIO                       = "audio";
    public static final String ICON_UPDATERICON                 = "updatericon";
    public static final String ICON_ADVANCEDCONFIG              = "advancedConfig";
    public static final String ICON_BUG                         = "bug";
    public static final String ICON_RAR                         = "rar";
    public static final String ICON_SEARCH                      = "search";
    public static final String ICON_EXIT                        = "exit";
    public static final String ICON_SAVE                        = "save";
    public static final String ICON_WARNING_BLUE                = "warning_blue";
    public static final String ICON_DIALOG_ERROR                = "dialog/error";
    public static final String ICON_DIALOG_FIND                 = "dialog/find";
    public static final String ICON_DIALOG_CANCEL               = "dialog/cancel";
    public static final String ICON_DIALOG_WARNING              = "dialog/warning";
    public static final String ICON_DIALOG_INFO                 = "dialog/info";
    public static final String ICON_DIALOG_LOGIN                = "dialog/login";
    public static final String ICON_DIALOG_HELP                 = "dialog/help";
    public static final String ICON_EVENT                       = "event";
    public static final String ICON_CHUNKS                      = "chunks";
    public static final String ICON_CHAT                        = "chat";
    public static final String ICON_PACKAGE_NEW                 = "package_new";
    public static final String ICON_REMOVE                      = "remove";
    public static final String ICON_PRIO_3_CLEAR                = "prio_3_clear";
    public static final String ICON_PRIO_0                      = "prio_0";
    public static final String ICON_DOWNLOADPATH                = "downloadpath";
    public static final String ICON_MEDIA_PLAYBACK_STOP         = "media-playback-stop";
    public static final String ICON_ADDCONTAINER                = "addContainer";
    public static final String ICON_SILENTMODE                  = "silentmode";
    public static final String ICON_EXTRACT                     = "extract";
    public static final String ICON_DELETE                      = "delete";
    public static final String ICON_DOWNLOADMANAGMENT           = "downloadmanagment";
    public static final String ICON_EXPORT                      = "export";
    public static final String ICON_EXTENSION                   = "extension";
    public static final String ICON_LINKGRABBER                 = "linkgrabber";
    public static final String ICON_BOTTOMBAR                   = "bottombar";
    public static final String ICON_BACKUP                      = "backup";
    public static final String ICON_LOGINS                      = "logins";
    public static final String ICON_PRIO_1                      = "prio_1";
    public static final String ICON_EXTTABLE_SORT               = "exttable/sort";
    public static final String ICON_EXTTABLE_WIDTHLOCKED        = "exttable/widthLocked";
    public static final String ICON_EXTTABLE_FIND               = "exttable/find";
    public static final String ICON_EXTTABLE_FINDMENU           = "exttable/findmenu";
    public static final String ICON_EXTTABLE_SORTDESC           = "exttable/sortDesc";
    public static final String ICON_EXTTABLE_COLUMNBUTTON       = "exttable/columnButton";
    public static final String ICON_EXTTABLE_SORTASC            = "exttable/sortAsc";
    public static final String ICON_EXTTABLE_RESETCOLUMNS       = "exttable/resetColumns";
    public static final String ICON_EXTTABLE_LOCKCOLUMN         = "exttable/lockColumn";
    public static final String ICON_HELP                        = "help";
    public static final String ICON_LINK                        = "link";
    public static final String ICON_DOWNLOAD                    = "download";
    public static final String ICON_WARNING_GREEN               = "warning_green";
    public static final String ICON_UPLOADED_SIGN_UP_NOW_EN     = "uploaded/sign_up_now_en";
    public static final String ICON_UPLOADED_GET_PREMIUM_DE     = "uploaded/get_premium_de";
    public static final String ICON_UPLOADED_PREMIUM_EN         = "uploaded/premium_en";
    public static final String ICON_UPLOADED_GET_PREMIUM_EN     = "uploaded/get_premium_en";
    public static final String ICON_AUTO_RECONNECT              = "auto-reconnect";
    public static final String ICON_REMOVE_FAILED               = "remove_failed";
    public static final String ICON_PUZZLE                      = "puzzle";
    public static final String ICON_CONFIRMSELECTEDLINKS        = "confirmSelectedLinks";
    public static final String ICON_PROFILE                     = "profile";
    public static final String ICON_CONSOLE                     = "console";
    public static final String ICON_REMOVE_OFFLINE              = "remove_offline";
    public static final String ICON_LANGUAGE                    = "language";
    public static final String ICON_DEFAULTPROXY                = "defaultProxy";
    public static final String ICON_BOARD                       = "board";
    public static final String ICON_FALSE                       = "false";
    public static final String ICON_SPLIT_PACKAGES              = "split_packages";
    public static final String ICON_MEDIA_PLAYBACK_PAUSE        = "media-playback-pause";
    public static final String ICON_RESUME                      = "resume";
    public static final String ICON_CUT                         = "cut";
    public static final String ICON_UPDATE_B                    = "update_b";
    public static final String ICON_PARALELL                    = "paralell";
    public static final String ICON_REFRESH                     = "refresh";
    public static final String ICON_FOLDER_OPEN                 = "folder_open";
    public static final String ICON_MORE                        = "more";
}
