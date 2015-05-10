package clientdesktop;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client 
{
    private String user;
    private String host;
    private int port;
    
    private Socket socket;

    public Client(String user, String host, int port) {
        this.user = user;
        this.host = host;
        this.port = port;
    }
    
    public void connect() {
        try {
            socket = new Socket(host, port);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendMessage(Message message) {
        try {
            Thread clientTalker = new Thread(new ClientTalker(new DataOutputStream(socket.getOutputStream()), message));
            clientTalker.run();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


