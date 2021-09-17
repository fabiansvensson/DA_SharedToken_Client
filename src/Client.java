import java.io.*;
import java.net.Socket;

public class Client implements Runnable {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    // private PrintWriter output;

    public Client(Socket s) throws IOException {
        this.socket = s;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }
    @Override
    public void run() {

        try {
            while(true) {
                oos.writeObject("REQ");
                String response = (String)ois.readObject();
                System.out.println(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}