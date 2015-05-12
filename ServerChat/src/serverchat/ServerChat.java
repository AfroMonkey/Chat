/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan
 */
public class ServerChat 
{
    public static void main(String[] args)
    {
        int port = 5001;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            do {
                Socket socket = serverSocket.accept();
                Thread listener = new Thread(new ClientListener(socket));
                listener.start();
            } while(true);
        } catch (IOException ex) {
            
        }
        
        
        
    }
    
}
