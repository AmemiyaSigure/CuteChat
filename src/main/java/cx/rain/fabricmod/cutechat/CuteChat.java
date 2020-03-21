package cx.rain.fabricmod.cutechat;

import cx.rain.fabricmod.cutechat.commands.Commands;
import cx.rain.fabricmod.cutechat.events.Events;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CuteChat implements ModInitializer {
    public static final ChatSocket SOCKET = new ChatSocket();
    public static final Logger LOGGER = LogManager.getLogger("CuteChat");
    public static final String VERSION = "1.0.0";

    public static MinecraftServer Server = null;

    @Override
    public void onInitialize() {
        LOGGER.info("[CuteChat] Loading...");

        String host = "localhost";
        int port = 35423;

        try {
            SOCKET.init(host, port);
            SOCKET.beginReceive();
        } catch (IOException ex) {
            LOGGER.error("[CuteChat] Can't connect to " + host + ":" + port + "!");
            ex.printStackTrace();
        }

        new Events();
        new Commands();
    }

}
