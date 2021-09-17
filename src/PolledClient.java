import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class PolledClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321)) {
            Client clientRun = new Client(socket);

            new Thread(clientRun).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
