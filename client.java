import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 3228)) {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a number to check prime: ");
            int number = scanner.nextInt();
            outStream.writeObject(number);

            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            boolean isPrime = (boolean) inStream.readObject();

            System.out.println(number + " is" + (isPrime ? "" : " not") + " a prime number.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error with server: " + e.getMessage());
        }
    }
}
