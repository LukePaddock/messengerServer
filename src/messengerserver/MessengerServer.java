package messengerserver;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author lukepaddock
 */
public class MessengerServer {
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        ServerSocket SRVSOCK = new ServerSocket(4444);
        System.out.println("Welcome to the Server Application! (created and developed by: Luke Paddock)");
        System.out.println("Server Online!");
        while (true) {
            Socket SOCK = SRVSOCK.accept();
            ConnectionArray.add(SOCK);
            
            ClientConnection CC = new ClientConnection(SOCK);
            Thread newClient = new Thread(CC);
            newClient.start();
            System.out.println("New Client Connected!: " +SOCK.getRemoteSocketAddress().toString());
            publishMessage("New Client Connected!: " +SOCK.getRemoteSocketAddress().toString());
            
        }
  
    }
    public static void publishMessage(String msg) throws Exception {
        for (int i = 1; i <= MessengerServer.ConnectionArray.size(); i++) {
            Socket TEMP_SOCK = MessengerServer.ConnectionArray.get(i-1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println(msg);
            OUT.flush();
        }
    }
}
