package cx.rain.fabricmod.cutechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import cx.rain.fabricmod.cutechat.CuteChat;
import cx.rain.fabricmod.cutechat.commands.suggests.SuggestCuteChat;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.io.IOException;
import java.util.ArrayList;

public class CommandCuteChatReconnect {
    private static final ArrayList<String> ARGUMENT_SUGGEST = new ArrayList<>();

    static {
        ARGUMENT_SUGGEST.add("version");
        ARGUMENT_SUGGEST.add("reconnect");
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("cutechat")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("reconnect"))
                .executes(context -> {
                    try {
                        CuteChat.SOCKET.reconnect();
                    } catch (IOException ex) {
                        context.getSource().sendError(new LiteralText("[CuteChat] 重连失败！请参考后台报错。"));
                        ex.printStackTrace();
                        return 0;
                    }
                    return 1;
                })
        );
    }
}
