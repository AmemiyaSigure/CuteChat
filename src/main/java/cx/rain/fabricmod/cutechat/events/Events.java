package cx.rain.fabricmod.cutechat.events;

import cx.rain.fabricmod.cutechat.CuteChat;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.text.LiteralText;

import java.io.IOException;

public class Events {
    public Events() {
        ServerStopCallback.EVENT.register(server -> {
            try {
                CuteChat.SOCKET.dispose();
            } catch (IOException ex) {
                CuteChat.LOGGER.error("[CuteChat] Can't close socket!");
                ex.printStackTrace();
            }
        });

        ServerTickCallback.EVENT.register(server -> {
            for (String chat : CuteChat.CHAT_BUFFER) {
                server.getPlayerManager().broadcastChatMessage(new LiteralText(chat), true);
            }
        });
    }
}
