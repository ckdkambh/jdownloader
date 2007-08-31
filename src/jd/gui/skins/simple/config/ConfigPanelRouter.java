package jd.gui.skins.simple.config;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jd.Configuration;
import jd.JDFileFilter;
import jd.JDUtilities;
import jd.controlling.interaction.HTTPReconnect;
import jd.plugins.Plugin;
import jd.router.RouterData;
import jd.router.RouterParser;
/**
 * Hier können Routereinstellungen vorgenommen werden
 * 
 * @author astaldo
 */
class ConfigPanelRouter extends JPanel implements ItemListener, ActionListener{
    private static String GET = "GET";
    private static String POST = "POST";
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4338544183688967675L;
    private JPanel panel;
    private Logger logger = Plugin.getLogger();
    private Configuration configuration;
    private RouterData routerData;

    private JButton btnImport;
    private JButton btnDisconnect;
    
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblRouterIP;
    private JLabel lblRouterPort;
    private JLabel lblRouterName;
    private JLabel lblLogin;
    private JLabel lblLoginType;
    private JLabel lblLoginRequestProperties;
    private JLabel lblLoginPostParams;
    private JLabel lblLogoff;
    private JLabel lblConnect;
    private JLabel lblDisconnect;
    private JLabel lblDisconnectType;
    private JLabel lblDisconnectRequestProperties;
    private JLabel lblDisconnectPostParams;
    private JLabel lblIPAddressRegEx;
    private JLabel lblIPAddressSite;
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtRouterIP;
    private JTextField txtRouterPort;
    private JTextField txtRouterName;
    private JTextField txtLogin;
    private JComboBox  cboLoginType;
    private JTextField txtLoginRequestProperties;
    private JTextField txtLoginPostParams;
    private JTextField txtLogoff;
    private JTextField txtConnect;
    private JTextField txtDisconnect;
    private JComboBox  cboDisconnectType;
    private JTextField txtDisconnectRequestProperties;
    private JTextField txtDisconnectPostParams;
    private JTextField txtIPAddressRegEx;
    private JTextField txtIPAddressSite;
    
    ConfigPanelRouter(Configuration configuration){
        this.configuration = configuration;
        this.routerData = configuration.getRouterData();
        panel = new JPanel(new GridBagLayout());
        String types[] = new String[]{GET,POST};
        setLayout(new GridBagLayout());
        lblUsername                    = new JLabel("Benutzername",JLabel.RIGHT);
        lblPassword                    = new JLabel("Password",JLabel.RIGHT);
        lblRouterIP                    = new JLabel("Hostname/IP des Routers",JLabel.RIGHT);
        lblRouterPort                  = new JLabel("Port des Routers",JLabel.RIGHT);
        lblRouterName                  = new JLabel("Name des Routers",JLabel.RIGHT);
        lblLogin                       = new JLabel("Anmeldestring",JLabel.RIGHT);
        lblLoginType                   = new JLabel("Art der Anmeldung",JLabel.RIGHT);
        lblLoginRequestProperties      = new JLabel("RequestProperties fürs Login",JLabel.RIGHT);
        lblLoginPostParams             = new JLabel("POST Parameter fürs Login",JLabel.RIGHT);
        lblLogoff                      = new JLabel("Anmeldestring",JLabel.RIGHT);
        lblConnect                     = new JLabel("Verbindungsaufbau",JLabel.RIGHT);
        lblDisconnect                  = new JLabel("Verbindungsabbruch",JLabel.RIGHT);
        lblDisconnectType              = new JLabel("Art des Verbindungsabbruchs",JLabel.RIGHT);
        lblDisconnectRequestProperties = new JLabel("RequestProperties für den Verbindungsabbruch",JLabel.RIGHT);
        lblDisconnectPostParams        = new JLabel("POST Parameter für den Verbindungsabbruch",JLabel.RIGHT);
        lblIPAddressRegEx              = new JLabel("RegEx ausdruck zum Lesen der IP",JLabel.RIGHT);
        lblIPAddressSite               = new JLabel("RouterPage für die IPAdresse",JLabel.RIGHT);
        
        txtUsername                    = new JTextField(configuration.getRouterUsername(),10);
        txtPassword                    = new JPasswordField(configuration.getRouterPassword(),10);
        txtRouterIP                    = new JTextField(configuration.getRouterIP(),10);
        txtRouterPort                  = new JTextField(Integer.toString(configuration.getRouterPort()),10);
        txtRouterName                  = new JTextField(50);
        txtLogin                       = new JTextField(50);
        cboLoginType                   = new JComboBox(types);
        txtLoginRequestProperties      = new JTextField(50);
        txtLoginPostParams             = new JTextField(50);
        txtLogoff                      = new JTextField(50);
        txtConnect                     = new JTextField(50);
        txtDisconnect                  = new JTextField(50);
        cboDisconnectType              = new JComboBox(types);
        txtDisconnectRequestProperties = new JTextField(50);
        txtDisconnectPostParams        = new JTextField(50);
        txtIPAddressRegEx              = new JTextField(50);
        txtIPAddressSite               = new JTextField(50);

        btnImport = new JButton("Import");
        btnDisconnect = new JButton("DisconnectTest");
        
        txtLogin.setToolTipText("Als Platzhalter für den Benutzernamen "+HTTPReconnect.VAR_USERNAME+" und für das Password "+HTTPReconnect.VAR_PASSWORD+" nehmen");
        txtConnect.setToolTipText("Hiermit wird die Verbindung wiederaufgebaut (zb http://www.google.de)");
        txtLoginRequestProperties.setToolTipText("<HTML>Die Werte werden folgendermaßen eingegeben:<BR>key1==value1;;key2==value2;;key3==value3.1=\"value3.2\"</HTML>");
        txtDisconnectRequestProperties.setToolTipText("<HTML>Die Werte werden folgendermaßen eingegeben:<BR>key1==value1;;key2==value2;;key3==value3.1=\"value3.2\"</HTML>");

        load();
        cboLoginType.addItemListener(this);
        cboDisconnectType.addItemListener(this);
        btnImport.addActionListener(this);
        btnDisconnect.addActionListener(this);
        
        Insets insets = new Insets(1,5,1,5);
        
        int row=0;
        JDUtilities.addToGridBag(panel, btnImport,                      0, row++, 2, 1, 1, 1, insets, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
        JDUtilities.addToGridBag(panel, btnDisconnect,                  0, row++, 2, 1, 1, 1, insets, GridBagConstraints.CENTER, GridBagConstraints.CENTER);
        JDUtilities.addToGridBag(panel, lblUsername,                    0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblPassword,                    0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblRouterIP,                    0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblRouterPort,                  0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblRouterName,                  0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblLogin,                       0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblLoginType,                   0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblLoginRequestProperties,      0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblLoginPostParams,             0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblLogoff,                      0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblConnect,                     0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblDisconnect,                  0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblDisconnectType,              0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblDisconnectRequestProperties, 0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblDisconnectPostParams,        0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblIPAddressSite,               0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);
        JDUtilities.addToGridBag(panel, lblIPAddressRegEx,              0, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.EAST);

        row=2;
        JDUtilities.addToGridBag(panel, txtUsername,                    1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtPassword,                    1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtRouterIP,                    1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtRouterPort,                  1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtRouterName,                  1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtLogin,                       1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, cboLoginType,                   1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtLoginRequestProperties,      1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtLoginPostParams,             1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtLogoff,                      1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtConnect,                     1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtDisconnect,                  1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, cboDisconnectType,              1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtDisconnectRequestProperties, 1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtDisconnectPostParams,        1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtIPAddressSite,               1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        JDUtilities.addToGridBag(panel, txtIPAddressRegEx,              1, row++, 1, 1, 1, 1, insets, GridBagConstraints.NONE, GridBagConstraints.WEST);
        
        add(panel);
        checkComboBoxes();

    }
    /**
     * Lädt alle Informationen 
     */
    void load(){
        txtRouterName.setText(routerData.getRouterName());
        txtLogin.setText(routerData.getLogin());
        txtLoginPostParams.setText(routerData.getLoginPostParams());
        txtLogoff.setText(routerData.getLogoff());
        txtConnect.setText(routerData.getConnect());
        txtDisconnect.setText(routerData.getDisconnect());
        txtDisconnectPostParams.setText(routerData.getDisconnectPostParams());
        txtIPAddressSite.setText(routerData.getIpAddressSite());

        if(routerData.getIpAddressRegEx() != null)
            txtIPAddressRegEx.setText(routerData.getIpAddressRegEx().toString()); 
        else
            txtIPAddressRegEx.setText(null);
        if(routerData.getLoginType() == RouterData.TYPE_WEB_GET)
            cboLoginType.setSelectedItem(GET);
        else
            cboLoginType.setSelectedItem(POST);
        if(routerData.getDisconnectType() == RouterData.TYPE_WEB_GET)
            cboDisconnectType.setSelectedItem(GET);
        else
            cboDisconnectType.setSelectedItem(POST);
        if(routerData.getLoginRequestProperties()!=null)
            txtLoginRequestProperties.setText(mergeHashMap(routerData.getLoginRequestProperties()));
        else
            txtLoginRequestProperties.setText(null);
        if(routerData.getDisconnectRequestProperties()!=null)
            txtDisconnectRequestProperties.setText(mergeHashMap(routerData.getDisconnectRequestProperties()));
        else
            txtDisconnectRequestProperties.setText(null);
    }
    /**
     * Speichert alle Änderungen auf der Maske
     */
    void save(){
        configuration.setRouterData(routerData);
        configuration.setRouterUsername(txtUsername.getText().trim());
        configuration.setRouterPassword(new String(txtPassword.getPassword()).trim());
        configuration.setRouterIP(txtRouterIP.getText().trim());
        routerData.setRouterName(txtRouterName.getText().trim());
        routerData.setLogin(txtLogin.getText().trim());
        routerData.setLoginPostParams(txtLoginPostParams.getText().trim());
        routerData.setLogoff(txtLogoff.getText().trim());
        routerData.setConnect(txtConnect.getText().trim());
        routerData.setDisconnect(txtDisconnect.getText().trim());
        routerData.setDisconnectPostParams(txtDisconnectPostParams.getText().trim());
        routerData.setIpAddressSite(txtIPAddressSite.getText().trim());

        try {
            configuration.setRouterPort(Integer.parseInt(txtRouterPort.getText()));
        }
        catch (NumberFormatException e) { logger.severe("routerPort wrong"); }

        if(cboLoginType.getSelectedItem().equals(GET))
            routerData.setLoginType(RouterData.TYPE_WEB_GET);
        else
            routerData.setLoginType(RouterData.TYPE_WEB_POST);
        if(cboDisconnectType.getSelectedItem().equals(GET))
            routerData.setDisconnectType(RouterData.TYPE_WEB_GET);
        else
            routerData.setDisconnectType(RouterData.TYPE_WEB_POST);
        try {
            String txtForPattern = txtIPAddressRegEx.getText().trim();
            Pattern.compile(txtForPattern);
            routerData.setIpAddressRegEx(txtForPattern);
        }
        catch (Exception e) { logger.severe("RegEx for IPAddress wrong"); }
        routerData.setLoginRequestProperties(splitString(txtLoginRequestProperties.getText().trim()));
        routerData.setDisconnectRequestProperties(splitString(txtDisconnectRequestProperties.getText().trim()));
    }
    /**
     * Gibt eine Hashmap als String zurück. Das Format ist so;
     * key1=value1;key2=value2;"key3;k"="value;value"...
     * 
     * @param map Die Map, die ausgegeben werden soll
     * @return Ein String, der die Werte der HashMap darstellt
     */
    private String mergeHashMap(HashMap<String, String>map){
        StringBuffer buffer = new StringBuffer();
        Iterator<String> iterator = map.keySet().iterator();
        String key;
        while(iterator.hasNext()){
            key = iterator.next();
            buffer.append(key);
            buffer.append("==");
            buffer.append(map.get(key));
            if(iterator.hasNext())
                buffer.append(";;");
        }
        return buffer.toString();
    }
    private HashMap<String, String> splitString(String properties){
        HashMap<String, String> map = new HashMap<String, String>();
        if(properties == null || properties.equals(""))
            return map;
        String[] items = properties.split(";;");
        String[] keyValuePair;
        for(int i=0;i<items.length;i++){
            keyValuePair = items[i].split("==");
            if(keyValuePair.length==2){
                map.put(keyValuePair[0], keyValuePair[1]);
            }
            else{
                logger.severe("something's wrong with the key/value pair."+keyValuePair[0]);
            }
        }
        return map;
    }
    /**
     * Initiiert die ComboBoxen
     */
    private void checkComboBoxes(){
        if(cboLoginType.getSelectedItem().equals(GET)){
            lblLoginPostParams.setEnabled(false);
            txtLoginRequestProperties.setEnabled(false);
            txtLoginPostParams.setEnabled(false);
        }
        else{
            lblLoginPostParams.setEnabled(true);
            txtLoginRequestProperties.setEnabled(true);
            txtLoginPostParams.setEnabled(true);
            
        }
        if(cboDisconnectType.getSelectedItem().equals(GET)){
            lblDisconnectPostParams.setEnabled(false);
            txtDisconnectRequestProperties.setEnabled(false);
            txtDisconnectPostParams.setEnabled(false);
        }
        else{
            lblDisconnectPostParams.setEnabled(true);
            txtDisconnectRequestProperties.setEnabled(true);
            txtDisconnectPostParams.setEnabled(true);
            
        }
    }
    private void importFromRoutersDat(){
        JFileChooser fileChooser = new JFileChooser();
        JDFileFilter fileFilter = new JDFileFilter("Routers",".dat",true);
        File fileRoutersDat;
        Vector<RouterData> routerData;
        
        
        fileChooser.setFileFilter(fileFilter);
        fileChooser.showOpenDialog(this);
        fileRoutersDat = fileChooser.getSelectedFile();
        if(fileRoutersDat != null){
            RouterParser parser = new RouterParser();
            routerData = parser.parseFile(fileRoutersDat);
            Object selected = JOptionPane.showInputDialog(
                    this, 
                    "Bitte wähle deinen Router aus", 
                    "Router importieren", 
                    JOptionPane.INFORMATION_MESSAGE, 
                    null, 
                    routerData.toArray(), 
                    null);
            if(selected != null){
                this.routerData = (RouterData)selected;
                load();
            }
        }
    }
    public void itemStateChanged(ItemEvent e) {
        checkComboBoxes();
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnImport)
            importFromRoutersDat();
        else if(e.getSource() == btnDisconnect){
            int button = JOptionPane.showConfirmDialog(
                    this, 
                    "Sollen die Daten gespeichert und der Router getrennt werden?",
                    "Reconnect-Test",
                    JOptionPane.YES_NO_OPTION);
            if(button == JOptionPane.YES_OPTION){
                save();
                boolean success = new HTTPReconnect().interact();
                if (success)
                    JOptionPane.showMessageDialog(this, "RouterTrennung erfolgreich");
                else
                    JOptionPane.showMessageDialog(this, "RouterTrennung fehlgeschlagen");
            }
        }
            
    }
}
