package serverchat;

import chat.Message;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.DataOutputStream;

public class ClientListener implements Runnable
{
    
    private Socket socket;
    private static HashMap<String, Socket> connectedUsers = new HashMap<String, Socket>();
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private String user;
    
    public ClientListener(Socket socket) {
        this.socket = socket;
        user = null;
        
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            try {
                socket.close();
            } catch (IOException e) {}
            Thread.currentThread().stop();
        }
    }

    public String getUser() {
        return user;
    }
    
    @Override
    public void run() 
    {
        loginAttempt();
    }
    
    public void ConnectedUsers(Message receiver) {
        Message usersConnected;
        String users = "";
        
        for (String u : connectedUsers.keySet()) {
            users = users + "," + u;
        }
        usersConnected = new Message("Server", receiver.getSender(), users);
        try {
            objectOutputStream.writeObject(usersConnected);
        } catch (IOException ex) {
            Logger.getLogger(ClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loginAttempt()
    {
        Message logAttempt;        
        try {
            logAttempt = (Message) objectInputStream.readObject();
            DBHandler handler = new DBHandler();
            boolean exists = handler.LoginUser(logAttempt.getSender(), logAttempt.getContent());
            
            Message status;
            if (exists)
            {
                user = logAttempt.getSender();
                status = new Message("Server", logAttempt.getSender(), "Accepted");
                objectOutputStream.writeObject(status);
                System.out.println("Conectado: " + user);
                connectedUsers.put(user, socket);
            } else {                
                status = new Message("Server", logAttempt.getSender(), "Rejected");
                objectOutputStream.writeObject(status);
                Thread.currentThread().stop();
            }
            //TODO
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + " Aqui");
            Thread.currentThread().stop();
        } catch(ClassNotFoundException ex) {
            System.out.println("Class");
        }
        finally 
        {
            try {
                socket.close();
            } catch (IOException ex) {}
        }
    }
}
