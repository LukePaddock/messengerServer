package messengerserver;
import java.io.*;
import java.net.*;

/**
 *
 * @author lukepaddock
 */
public class ClientConnection implements Runnable {
    public Socket SOCK;

    ClientConnection(Socket X) {
        this.SOCK = X;
    }
    public void run() {
        try {
        InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);
        while (true) {
            String MESSAGE = BR.readLine();
            if (MESSAGE != null) {
                MessengerServer.publishMessage(MESSAGE);
                System.out.println("(" +SOCK.getRemoteSocketAddress() +"): " +MESSAGE);
            }
            
            
        }
        }
        
        
        //Just the stupid catch thingamajig :(
        catch (Exception sucks) {
            
            System.out.println(sucks);
        }
        
    }
    
}
