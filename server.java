import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3228)) {
            while (true) {
                System.out.println("Awaiting client connection...");
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connected successfully");

                    ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream());
                    int number = (int) inStream.readObject();

                    boolean isPrime = checkPrime(number);

                    ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    outStream.writeObject(isPrime);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
