package interfazcliente;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientConversation extends JFrame implements Runnable{
    private JTextArea txtMessages;
    private JTextField txtMessage;
    private JScrollPane scrMessages;
    private JButton btnSend;
    
    public ClientConversation(String user) {
        super(user);
        this.setSize(500, 500);
        
        txtMessages = new JTextArea();
        txtMessages.setLineWrap(true);
        txtMessages.setWrapStyleWord(true);
        txtMessages.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        scrMessages = new JScrollPane(txtMessages);
                
        txtMessage = new JTextField();
        
        btnSend = new JButton("Send");
        
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
    }

    
    
    @Override
    public void run() {
        this.show();
    }

}
