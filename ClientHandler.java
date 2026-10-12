import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;
    private String currentRoom = null;  

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void send(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);

            send("Nhập tên của bạn:");
            name = in.readLine();
            if (name == null) return;
            name = name.trim();
            if (name.isEmpty()) name = "Khach";
            System.out.println(name + " da dang nhap.");

            boolean running = true;
            while (running) {
                send("");
                send("===== DANH SÁCH PHÒNG =====");
                for (int i = 0; i < Server.ROOMS.length; i++) {
                    send((i + 1) + ". " + Server.ROOMS[i]);
                }
                send("Chọn phòng (nhập số) hoặc /exit để thoát:");

                String choice = in.readLine();
                if (choice == null) break;
                choice = choice.trim();

                if (choice.equals("/exit")) break;

                int index;
                try {
                    index = Integer.parseInt(choice) - 1;
                } catch (NumberFormatException e) {
                    send("Lựa chọn không hợp lệ, hãy nhập số!");
                    continue;
                }
                if (index < 0 || index >= Server.ROOMS.length) {
                    send("Không có phòng này!");
                    continue;
                }

                currentRoom = Server.ROOMS[index];
                Server.joinRoom(currentRoom, this);
                send(">>> Bạn đã vào phòng " + currentRoom + ". Gõ /quit để rời phòng, /exit để thoát.");
                Server.broadcast(currentRoom, "*** " + name + " đã vào phòng ***", this);
                System.out.println(name + " vao phong " + currentRoom);

                String line;
                while ((line = in.readLine()) != null) {
                    line = line.trim();
                    if (line.equals("/quit")) break;                    
                    if (line.equals("/exit")) { running = false; break; } 
                    if (line.isEmpty()) continue;
                    Server.broadcast(currentRoom, "[" + name + "]: " + line, this);
                }
                if (line == null) running = false;   

                leaveCurrentRoom();
            }
        } catch (IOException e) {
        } finally {
            leaveCurrentRoom();
            try { socket.close(); } catch (IOException e) { }
            System.out.println((name == null ? "Client" : name) + " da ngat ket noi.");
        }
    }

    private void leaveCurrentRoom() {
        if (currentRoom != null) {
            Server.leaveRoom(currentRoom, this);
            Server.broadcast(currentRoom, "*** " + name + " đã rời phòng ***", this);
            System.out.println(name + " roi phong " + currentRoom);
            currentRoom = null;
        }
    }
}
