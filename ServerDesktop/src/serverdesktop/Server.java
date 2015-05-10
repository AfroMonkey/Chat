package serverdesktop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private ServerSocket server;
    private Socket socket;
    private final int PORT;

    public Server(int PORT) {
        this.PORT = PORT;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(PORT);
            while(true) {
                System.out.println("Esperando clientes");
                socket = server.accept();
                if(socket.getInetAddress().getHostName().equals("Internet")) {
                    break;
                }
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
                Thread clientListener = new Thread(new ClientListener(socket));
                clientListener.start();
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                socket.close();
                server.close();
                System.out.println("Conexiones terminadas");
            } catch (IOException ex) {
                System.out.println("Error al cerrar las conexiones");
            }
        }
    }
}
