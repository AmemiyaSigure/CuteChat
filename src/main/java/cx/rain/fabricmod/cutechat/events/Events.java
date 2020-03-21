package cx.rain.fabricmod.cutechat.events;

import cx.rain.fabricmod.cutechat.CuteChat;
import net.fabricmc.fabric.api.event.server.ServerStartCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;

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

        ServerStartCallback.EVENT.register(server -> {
            CuteChat.Server = server;
        });
    }
}
