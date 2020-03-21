package cx.rain.fabricmod.cutechat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

public class ChatSocket {
    private Socket SOCKET = new Socket();
    private Thread THREAD = null;
    private boolean IS_RUNNING = false;

    public void init(String host, int port) throws IOException {
        SOCKET.connect(new InetSocketAddress(host, port), 10);
        SOCKET.setKeepAlive(true);
    }

    public void beginReceive() {
        if (!IS_RUNNING) {
            IS_RUNNING = true;
            THREAD = new Thread(() -> {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(SOCKET.getInputStream(), StandardCharsets.UTF_8));
                    while (IS_RUNNING && SOCKET.isConnected()) {
                        String str = br.readLine();
                        if (!str.isEmpty()) {
                            SocketReceiver.OnReceive(str);
                        }
                    }
                    IS_RUNNING = false;
                } catch (IOException ignored) {
                }
            });
            THREAD.start();
        }
    }

    public void send(String str) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(SOCKET.getOutputStream(), StandardCharsets.UTF_8));
            bw.write(str);
            bw.flush();
        } catch (IOException ignored) {
        }
    }

    public void reconnect(String host, int port) throws IOException {
        if (SOCKET.isConnected() && (!SOCKET.isClosed())) {
            SOCKET.close();
        }
        SOCKET = new Socket();
        SOCKET.connect(new InetSocketAddress(host, port));
    }

    public void dispose() throws IOException {
        IS_RUNNING = false;
        SOCKET.close();
    }
}
