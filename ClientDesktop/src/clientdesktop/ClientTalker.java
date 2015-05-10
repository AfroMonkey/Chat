package clientdesktop;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTalker implements Runnable{
    private final DataOutputStream dataOutputStream;
    private final Message message;

    public ClientTalker(DataOutputStream dataOutputStream, Message message) {
        this.dataOutputStream = dataOutputStream;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            dataOutputStream.writeUTF(message.getSender());
            dataOutputStream.writeUTF(message.getReceiver());
            dataOutputStream.writeUTF(message.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ClientTalker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
