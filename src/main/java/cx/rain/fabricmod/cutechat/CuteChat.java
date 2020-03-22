package cx.rain.fabricmod.cutechat;

import cx.rain.fabricmod.cutechat.commands.Commands;
import cx.rain.fabricmod.cutechat.events.Events;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CuteChat implements ModInitializer {
    public static final ChatSocket SOCKET = new ChatSocket();
    public static final Logger LOGGER = LogManager.getLogger("CuteChat");
    public static final String VERSION = "1.0.2";

    public static final String HOST = "localhost";
    public static final int PORT = 35423;

    public static final List<String> CHAT_BUFFER = new ArrayList<>();

    @Override
    public void onInitialize() {
        LOGGER.info("[CuteChat] Loading...");

        try {
            SOCKET.init(HOST, PORT);
            SOCKET.beginReceive();
        } catch (IOException ex) {
            LOGGER.error("[CuteChat] Can't connect to " + HOST + ":" + PORT + "!");
            ex.printStackTrace();
        }

        new Events();
        new Commands();
    }

}
