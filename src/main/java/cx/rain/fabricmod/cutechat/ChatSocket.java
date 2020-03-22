package cx.rain.fabricmod.cutechat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatSocket {
    private Socket SOCKET = new Socket();

    public void init(String host, int port) throws IOException {
        SOCKET.connect(new InetSocketAddress(host, port), 10);
        SOCKET.setKeepAlive(true);
    }

    public void beginReceive() {
        Thread thread = new Thread(() -> {
            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(SOCKET.getInputStream(), StandardCharsets.UTF_8));
                while (true) {
                    String str = br.readLine();
                    CuteChat.CHAT_BUFFER.add(str);
                }
            } catch (IOException ignored) {
            }
        });
        thread.start();
    }

    public void send(String str) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(SOCKET.getOutputStream(), StandardCharsets.UTF_8));
            bw.write(str);
            bw.flush();
        } catch (IOException ignored) {
        }
    }

    public void reconnect(String host, int port) throws IOException {
        if (!SOCKET.isClosed()) {
            SOCKET.close();
        }
        SOCKET = new Socket();
        SOCKET.connect(new InetSocketAddress(host, port));
    }

    public void dispose() throws IOException {
        SOCKET.close();
    }
}
