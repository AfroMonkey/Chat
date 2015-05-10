package chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Juan
 */
public class LoginWindow extends JFrame{
        
        //Declaración de variables
    private JPanel cardPanel, login, register;
    private CardLayout cardLayout = new CardLayout();
    
    private JTextField userTxt, userRegTxt;
    private JPasswordField passTxt, passRegTxt, confirmRegTxt;
    private JLabel userLbl, passLbl, regLbl, logLbl, userRegLbl, passRegLbl, confirmRegLbl;
    private JButton loginBtn, regBtn, logBtn, registerBtn;
    
    public LoginWindow() {
        
        //Ventana
        super("JMDP");
        this.setSize(300, 215);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Componentes Login
        userTxt = new JTextField(25);
        passTxt = new JPasswordField(25);
        userLbl = new JLabel("Usuario: ");
        passLbl = new JLabel("Contraseña: ");
        loginBtn = new JButton("Iniciar sesión");
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
                LoginWindow.this.setSize(300, 260);
                userTxt.setText(null);
                passTxt.setText(null);
            }
        });
        
        logBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "1");
                LoginWindow.this.setSize(300, 205);
                userRegTxt.setText(null);
                passRegTxt.setText(null);
                confirmRegTxt.setText(null);
            }
        });
        
        add(cardPanel);
        
    }
}