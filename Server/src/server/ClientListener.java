package server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientListener implements Runnable{
    private final Socket socket;
    private final int port = 5000;

    public ClientListener(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        System.out.println("Listening the client: " + socket.getRemoteSocketAddress());
        String message;
        String receiver;
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            do {
                receiver = dataInputStream.readUTF();
                message = dataInputStream.readUTF();
                Thread clientTalker = new Thread(new ClientTalker(receiver, message));
                clientTalker.start();
            } while(!message.equals("Sax") && socket.isConnected());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
