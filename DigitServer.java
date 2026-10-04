import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DigitServer {

    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 5000;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("DigitServer dang lang nghe cong " + port);
            while (true) {
                Socket socket = server.accept();
                new Thread(() -> phucVu(socket)).start();  
            }
        }
    }

    static void phucVu(Socket socket) {
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

            String line;
            while ((line = in.readLine()) != null) {
                String reply;
                if (line.equals("QUIT")) {
                    send(out, "BYE");
                    break;
                }
                reply = doiSoThanhChu(line);
                send(out, reply);
            }
        } catch (IOException e) {
            System.out.println("Client ngat ket noi: " + e.getMessage());
        }
    }

    static String doiSoThanhChu(String line) {
    if (line.length() == 1) {
        switch (line.charAt(0)) {
            case '0': return "không";
            case '1': return "một";
            case '2': return "hai";
            case '3': return "ba";
            case '4': return "bốn";
            case '5': return "năm";
            case '6': return "sáu";
            case '7': return "bảy";
            case '8': return "tám";
            case '9': return "chín";
        }
    }
    return "ERR INVALID_DIGIT";
}

    static void send(BufferedWriter out, String msg) throws IOException {
        out.write(msg);
        out.write("\n");
        out.flush();
    }
}
