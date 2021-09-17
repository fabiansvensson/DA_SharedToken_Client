import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class main {
    private static final String REQUEST_TOKEN = "REQ";
    private static final int RELEASE = 2;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 4321)){
            //reading the input from server
            BufferedReader input = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);

            //taking the user input
            //Scanner scanner = new Scanner(System.in);

            //Client clientRun = new Client(socket);

            //new Thread(clientRun).start();
            //loop closes when user enters exit command

            int serverResponse = 0;
            System.out.println("Client connecting and sending request to server");
            while(serverResponse >= 0) {
                output.println(REQUEST_TOKEN);
                serverResponse = Integer.parseInt(input.readLine());
                int newToken = serverResponse + 1;
                System.out.println("New token value is: " + newToken);
                output.println(newToken);
            }
        } catch (Exception e) {
            System.out.println("Exception occured in client main: " + e.getStackTrace());
        }
    }
}