package serverdesktop;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerDesktop {
    public static void main(String[] args) {
        int PORT = 5050;
        Scanner scanner = new Scanner(System.in);
        String command;
        Thread server = new Thread(new Server(PORT));
        server.start();
        do {
            System.out.print(">");
            command = scanner.nextLine();
        } while(!command.equals("close"));
        try {
            Socket socket = new Socket("192.168.0.100", PORT);
            socket.close();
        } catch (IOException ex) {
            
        }
        System.out.println("Server cerrado");
    }

}
