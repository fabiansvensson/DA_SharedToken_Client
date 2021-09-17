import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientThread extends Thread {
    private static final int REQUEST_TOKEN = 1;
    private static final int RELEASE = 2;

    private PrintWriter output;
    private String response;
    private BufferedReader input;

    public ClientThread() {
        try (Socket socket = new Socket("localhost", 4321)) {
            //reading the input from server
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

            //taking the user input
            Scanner scanner = new Scanner(System.in);

            //Client clientRun = new Client(socket);

            //new Thread(clientRun).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            int serverResponse = 0;
            while(serverResponse > 0)
            output.print(REQUEST_TOKEN);
            //output.println(REQUEST_TOKEN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
