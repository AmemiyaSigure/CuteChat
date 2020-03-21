package cx.rain.fabricmod.cutechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import cx.rain.fabricmod.cutechat.CuteChat;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class CommandCuteChatVersion {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("cutechat")
                .executes(context -> {
                    context.getSource().sendError(
                            new LiteralText("§b服务端MOD版本：" + CuteChat.VERSION));
                    return 1;
                })
                .then(CommandManager.literal("version")
                        .executes(context -> {
                            context.getSource().sendError(
                                    new LiteralText("§b服务端MOD版本：" + CuteChat.VERSION));
                            return 1;
                        }))
        );
    }
}
