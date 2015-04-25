package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTalker implements Runnable{
    private final int port = 5000; 
    private final String message;
    private final String receiver;

    public ClientTalker(String receiver, String message) {
        this.message = message;
        this.receiver = receiver;
    }
    
    @Override
    public void run() {
        try {
            Socket socketReceiver = new Socket(receiver, port);
            DataOutputStream dataOutputStream = new DataOutputStream(socketReceiver.getOutputStream());
            dataOutputStream.writeUTF(message);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
