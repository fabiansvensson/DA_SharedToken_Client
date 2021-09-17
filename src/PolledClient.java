import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.Thread.sleep;


public class PolledClient {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        String message = "";

        try {
            for(int i = 0; i < 2; i++) {
                socket = new Socket("localhost", 4321);
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                Client clientRun = new Client(socket, oos, ois);
                new Thread(clientRun).start();
                sleep(3000);
            }
            while(true) {}
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
