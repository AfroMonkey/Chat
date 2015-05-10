package serverdesktop;

import java.net.Socket;

public class ClientListener implements Runnable{
    private final Socket socket;

    ClientListener(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        
    }
}
