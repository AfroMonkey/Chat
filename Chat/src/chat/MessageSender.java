/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moy
 */
public class MessageSender implements Runnable{
    private Socket socket;
    private String user;
    private String message;

    MessageSender(Socket socket, String user, String message) {
        this.socket = socket;
        this.user = user;
        this.message = message;
    }

    @Override
    public void run() {
        sendMessage();
    }
    
    private void sendMessage() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(MessageSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
