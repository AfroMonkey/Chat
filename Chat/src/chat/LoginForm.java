package chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Juan
 */
public class LoginForm extends JFrame{
    private JPanel cardPanel, login, register;
    private CardLayout cardLayout = new CardLayout();
    private final JMenuBar menuBar = new JMenuBar();
    private JMenu menuPreferences = new JMenu("Preferencias");
    private JMenuItem menuItemPort = new JMenuItem("Puerto");
    private JMenuItem menuItemServer = new JMenuItem("Servidor");
    private JTextField userTxt, userRegTxt;
    private JPasswordField passTxt, passRegTxt, confirmRegTxt;
    private JLabel userLbl, passLbl, regLbl, logLbl, userRegLbl, passRegLbl, confirmRegLbl;
    private JButton loginBtn, regBtn, logBtn, registerBtn;
    
    private int port;
    private String serverIP;
    private final String serverName = "Server";
    private String user;
    
    public LoginForm() {
        //Ventana
        super("JMDP");
        this.setSize(300, 230);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        port = 5001;
        
        menuItemPort.addActionListener((ActionEvent ae) -> {
            try {
                String strPort = JOptionPane.showInputDialog(null, "Ingresa un puerto valido", Integer.toString(port));
                if(strPort != null && !"".equals(strPort)) {
                    port = Integer.valueOf(strPort);
                }
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingresa un puerto valido", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
            }
        });
        menuPreferences.add(menuItemPort);
        menuItemServer.addActionListener((ActionEvent ae) -> {
            String strServerIP = JOptionPane.showInputDialog(null, "Ingresa la IP del servidor", serverIP);
            if(strServerIP != null && !"".equals(strServerIP)) {
                serverIP = strServerIP;
            }
        });
        menuPreferences.add(menuItemServer);
        menuBar.add(menuPreferences);
        this.setJMenuBar(menuBar);
        
        //Componentes Login
        userTxt = new JTextField(25);
        passTxt = new JPasswordField(25);
        userLbl = new JLabel("Usuario: ");
        passLbl = new JLabel("Contraseña: ");
        loginBtn = new JButton("Iniciar sesión");
        loginBtn.addActionListener((ActionEvent ae) -> {
            if(!userTxt.equals("")) {
                user = userTxt.getText();
                loginIntent();
            }
        });
        regBtn = new JButton("Registrarse");
        regLbl = new JLabel("¿No tienes una cuenta?");
        
        //Componentes Register
        userRegTxt = new JTextField(25);
        passRegTxt = new JPasswordField(25);
        confirmRegTxt = new JPasswordField(25);
        userRegLbl = new JLabel("Usuario:");
        passRegLbl = new JLabel("Contraseña:");
        confirmRegLbl = new JLabel("Confirmar contraseña:");
        registerBtn = new JButton("Registrarse");
        logLbl = new JLabel("¿Ya tienes una cuenta?");
        logBtn = new JButton("Iniciar sesión");
        
        //Login panel
        login = new JPanel();
        
        GroupLayout loginComp = new GroupLayout(this.login);
        loginComp.setAutoCreateContainerGaps(true);
        loginComp.setAutoCreateGaps(true);
        
        loginComp.setHorizontalGroup(
                loginComp.createParallelGroup()
                .addComponent(userLbl)
                .addComponent(userTxt)
                .addComponent(passLbl)
                .addComponent(passTxt)
                .addComponent(loginBtn)
                .addGroup(
                        loginComp.createSequentialGroup()
                                .addGap(22)
                                .addComponent(regLbl)
                                .addComponent(regBtn)
                )
        );
        loginComp.setVerticalGroup(
                loginComp.createSequentialGroup()
                .addComponent(userLbl)
                .addComponent(userTxt)
                .addComponent(passLbl)
                .addComponent(passTxt)
                .addComponent(loginBtn)
                .addGroup(
                        loginComp.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(regLbl)
                                .addComponent(regBtn)
                )
        );
        
        login.setLayout(loginComp);
        
        //Register panel
        register = new JPanel();
        
        GroupLayout regComp = new GroupLayout(this.register);
        regComp.setAutoCreateContainerGaps(true);
        regComp.setAutoCreateGaps(true);
        
        regComp.setHorizontalGroup(
                regComp.createParallelGroup()
                .addComponent(userRegLbl)
                .addComponent(userRegTxt)
                .addComponent(passRegLbl)
                .addComponent(passRegTxt)
                .addComponent(confirmRegLbl)
                .addComponent(confirmRegTxt)
                .addComponent(registerBtn)
                .addGroup(
                        regComp.createSequentialGroup()
                                .addGap(15)
                                .addComponent(logLbl)
                                .addComponent(logBtn)
                )
        );
        regComp.setVerticalGroup(
                regComp.createSequentialGroup()
                .addComponent(userRegLbl)
                .addComponent(userRegTxt)
                .addComponent(passRegLbl)
                .addComponent(passRegTxt)
                .addComponent(confirmRegLbl)
                .addComponent(confirmRegTxt)
                .addComponent(registerBtn)
                .addGroup(
                        regComp.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(logLbl)
                                .addComponent(logBtn)
                )
        );
        
        register.setLayout(regComp);
        
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(login, "1");
        cardPanel.add(register, "2");
        
        
        regBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "2");
                LoginForm.this.setSize(300, 260);
                userTxt.setText(null);
                passTxt.setText(null);
            }
        });
        
        logBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "1");
                LoginForm.this.setSize(300, 205);
                userRegTxt.setText(null);
                passRegTxt.setText(null);
                confirmRegTxt.setText(null);
            }
        });  
        super.
        add(cardPanel);        
    }
    
    public void setServerIP() {
        String strServerIP; 
        do {
            strServerIP = JOptionPane.showInputDialog(null, "Ingresa la IP del servidor", serverIP);
            if(strServerIP != null && !"".equals(strServerIP)) {
                serverIP = strServerIP;
            }
        } while(strServerIP == null || "".equals(strServerIP));
    }

    private void loginIntent() {
        try {
            Socket socket = new Socket(serverIP, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            String pass = String.valueOf(passTxt.getPassword());
            pass = DigestUtils.sha256Hex(pass);
            Message logAttempt = new Message(user, serverName, pass);
            objectOutputStream.writeObject(logAttempt);
            try {
                Message logResult = (Message)objectInputStream.readObject();
                if(logResult.getSender().equals(serverName) && logResult.getContent().equals("Accepted")) {
                    ClientHome clientHome = new ClientHome(logResult.getContent());
                    clientHome.setVisible(true);
                    this.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexion a servidor", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
}