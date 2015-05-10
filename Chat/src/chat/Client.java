package chat;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author  MJDPCode
 * 
 *          Moises Navarro
 *          Juan Jimenez
 *          Diego Celada
 *          Jose Gomez
 */
public class Client 
{
    private String user;
    private String host;
    private int port;
    private Socket socket;

    public Client(String user)
    {
        this.user = user;
    }
    
    public boolean connect(String host, int port)
    {
        this.host = host;
        this.port = port;
        
        try 
        {
            socket = new Socket(host, port);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean disconnect()
    {
        try 
        {
            socket.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public void sendMessage(Message message)
    {
        
    }
}
