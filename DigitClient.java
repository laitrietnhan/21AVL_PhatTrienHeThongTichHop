import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class DigitClient {
    public static void main(String[] args) throws IOException {
        String host = args.length > 0 ? args[0] : "localhost";
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 5000;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            System.out.println("Da ket noi. Nhap 1 chu so (0-9), hoac QUIT de thoat.");
            String line;
            while ((line = console.readLine()) != null) {
                out.write(line + "\n");
                out.flush();
                String reply = in.readLine();
                if (reply == null) {
                    System.out.println("Server da dong ket noi.");
                    break;
                }
                System.out.println("Server: " + reply);
                if (line.equals("QUIT")) break;
            }
        }
    }
}
