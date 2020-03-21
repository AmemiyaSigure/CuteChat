package cx.rain.fabricmod.cutechat;

import net.minecraft.server.MinecraftServer;
import net.minecraft.text.LiteralText;

public class SocketReceiver {
    public static void OnReceive(String str) {
        MinecraftServer server = CuteChat.Server;
        if (server != null) {
            server.getPlayerManager().broadcastChatMessage(new LiteralText(str), true);
        }
    }
}
