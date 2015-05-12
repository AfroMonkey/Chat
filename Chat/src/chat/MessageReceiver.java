/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Moy
 */
public class MessageReceiver implements Runnable{
    ClientConversation conversation;
    
    public MessageReceiver(ClientConversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public void run() {
        Message message;
        ObjectInputStream objectInputStream;
        do {
            try {
                objectInputStream = new ObjectInputStream(conversation.socket.getInputStream());
                message = (Message)objectInputStream.readObject();
                conversation.txtMessages.setText(conversation.txtMessages.getText() + "\n" + message.getSender() + ": " + message.getContent());
            } catch (IOException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
    }
}
