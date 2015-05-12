/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moy
 */
public class ContactListener implements Runnable
{
    private final Socket socket;
    private final String user;
    
    public ContactListener(Socket socket, String user) {
        this.socket = socket;
        this.user = user;
    }
    
    @Override
    public void run() {
        do {
            ClientHome.setUsersConnected(getConnectedUsers());
            try {
                wait(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ContactListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while(true);
    }

    private String[] getConnectedUsers() {
        String[] namesArray = null;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            Message requestConnecteds = new Message(user, "Server", "GetConnecteds");
            objectOutputStream.writeObject(requestConnecteds);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message connecteds = (Message) objectInputStream.readObject();
            String names = connecteds.getContent();
            namesArray = names.split(",");
        } catch (IOException ex) {
            Logger.getLogger(ContactListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return namesArray;
    }
    
}
