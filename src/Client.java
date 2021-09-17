import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private String message = "";
    private Integer token;
    private int port;
    private static final int LIMIT = 10;
    // private PrintWriter output;

    public Client(Socket s, ObjectOutputStream oos, ObjectInputStream ois) throws IOException {
        this.socket = s;
        this.port = socket.getPort();
        this.oos = oos;
        this.ois = ois;
    }
    @Override
    public void run() {
        try {
            int ctr = 0;
            while(ctr < LIMIT) {
                oos.writeObject("REQ");
                //read the server response message
                token = (Integer) ois.readObject();
                token++;
                oos.writeObject(token);
                System.out.println("New Token Value: " + token);
                ctr++;
            }
            oos.writeObject("EXIT");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                ois.close();
                System.out.println("Socket closed.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}