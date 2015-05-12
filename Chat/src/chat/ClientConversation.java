package chat;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientConversation extends JFrame{
    public JTextArea txtMessages;
    private final JTextField txtMessage;
    private final JScrollPane scrMessages;
    private final JButton btnSend;
   
    public String user;
    public Socket socket;
    
    public ClientConversation(String user, Socket socket) {
        super(user);
        this.setSize(500, 500);
        
        this.user = user;
        this.socket = socket;
        
        txtMessages = new JTextArea();
        txtMessages.setLineWrap(true);
        txtMessages.setWrapStyleWord(true);
        txtMessages.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        scrMessages = new JScrollPane(txtMessages);
                
        txtMessage = new JTextField();
        
        btnSend = new JButton("Send");
        btnSend.addActionListener((ActionEvent e) -> {
            Thread messageSender = new Thread(new MessageSender(socket, user, txtMessage.getText()));
            messageSender.start();
            txtMessages.setText(txtMessages.getText() + "\n" + user + ":" + txtMessage.getText());
            txtMessage.setText("");
        });
        
        GroupLayout layout = new GroupLayout(this.getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                    .addComponent(scrMessages)
                    .addGroup(
                        layout.createSequentialGroup()
                            .addComponent(txtMessage)
                            .addComponent(btnSend, 1, 1, 100)
                    )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                    .addComponent(scrMessages)
                    .addGroup(
                            layout.createParallelGroup()
                                .addComponent(txtMessage, 1, 1, 30)
                                .addComponent(btnSend, 1, 1, 30)
                    )
        );
        this.setLayout(layout);
        Thread messageReceiver = new Thread(new MessageReceiver(this));
        messageReceiver.start();
    }
}
