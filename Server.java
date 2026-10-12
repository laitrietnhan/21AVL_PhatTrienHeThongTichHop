import java.net.*;
import java.util.*;

public class Server {
    static final int PORT = 5000;

    static final String[] ROOMS = {"Study", "Game", "TraiNghiem"};

    static final Map<String, List<ClientHandler>> rooms = new HashMap<>();

    public static void main(String[] args) throws Exception {
        for (String r : ROOMS) {
            rooms.put(r, new ArrayList<>());
        }

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server dang chay tai cong " + PORT + " ...");

        while (true) {
            Socket socket = serverSocket.accept();           
            System.out.println("Client moi ket noi: " + socket.getInetAddress());
            new Thread(new ClientHandler(socket)).start();   
        }
    }

    static void broadcast(String room, String msg, ClientHandler except) {
        synchronized (rooms) {
            for (ClientHandler c : rooms.get(room)) {
                if (c != except) {
                    c.send(msg);
                }
            }
        }
    }

    static void joinRoom(String room, ClientHandler c) {
        synchronized (rooms) {
            rooms.get(room).add(c);
        }
    }

    static void leaveRoom(String room, ClientHandler c) {
        synchronized (rooms) {
            rooms.get(room).remove(c);
        }
    }
}
