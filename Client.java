import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String host = (args.length > 0) ? args[0] : "localhost";
        int port = 5000;

        Socket socket = new Socket(host, port);
        System.out.println("Đã kết nối tới server " + host + ":" + port);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

        Thread receiver = new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                }
            } catch (IOException e) { }
            System.out.println("Đã ngắt kết nối khỏi server.");
            System.exit(0);
        });
        receiver.start();

        Scanner sc = new Scanner(System.in, "UTF-8");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            out.println(line);
            if (line.trim().equals("/exit")) {
                Thread.sleep(300); 
                break;
            }
        }
        socket.close();
        System.exit(0);
    }
}
