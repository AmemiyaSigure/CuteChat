package cx.rain.fabricmod.cutechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import cx.rain.fabricmod.cutechat.CuteChat;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.io.IOException;

public class CommandCuteChatReconnect {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("cutechat")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("reconnect")
                        .executes(context -> {
                            try {
                                CuteChat.SOCKET.reconnect(CuteChat.HOST, CuteChat.PORT);
                                context.getSource().sendError(new LiteralText("§c[CuteChat] 重连成功！"));
                            } catch (IOException ex) {
                                context.getSource().sendError(new LiteralText("§c[CuteChat] 重连失败！请参考后台报错。"));
                                ex.printStackTrace();
                            }
                            return 1;
                        }))
        );
    }
}
