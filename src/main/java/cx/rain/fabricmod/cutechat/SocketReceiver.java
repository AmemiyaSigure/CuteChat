package cx.rain.fabricmod.cutechat;

public class SocketReceiver {
    public static void OnReceive(String str) {
        CuteChat.CHAT_BUFFER.add(str);
    }
}
