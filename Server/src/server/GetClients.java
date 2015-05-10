package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GetClients implements Runnable{
    final private int port = 5050;

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        Thread clienListener;
        do {
            try {
                serverSocket = new ServerSocket(port);
                serverSocket.setSoTimeout(5000);
                socket = serverSocket.accept();
                clienListener = new Thread(new ClientListener(socket));
                clienListener.start();
                System.out.println("Client connected: " + socket.getRemoteSocketAddress());     
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } while(true);
    }
}
