package chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
public class ClientTalker implements Runnable
{
    private final ObjectOutputStream objectOutputStream;
    private final Message message;
    
    public ClientTalker(ObjectOutputStream objectOutputStream, Message message)
    {
        this.objectOutputStream = objectOutputStream;
        this.message = message;
    }
    
    @Override
    public void run() {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(ClientTalker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
