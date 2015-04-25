package server;

import java.util.Scanner;

/**
 * @version 1.0
 * @author MJDPCode
 */
public class Server {
    public static void main(String[] args) {
        Thread getClients = new Thread(new GetClients());
        getClients.start();
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.print(">");
            command = scanner.nextLine();
        } while (!command.equals("close"));
    }
}
