package cx.rain.fabricmod.cutechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import cx.rain.fabricmod.cutechat.CuteChat;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class CommandCuteChatVersion {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralCommandNode<ServerCommandSource> root = dispatcher.register(CommandManager.literal("cutechat")
                .executes(context -> {
                    context.getSource().sendError(
                            new LiteralText("§b服务端MOD版本：" + CuteChat.VERSION));
                    return 1;
                })
        );
        
        dispatcher.register(CommandManager.literal("cutechat")
                .then(CommandManager.literal("version"))
                    .redirect(root)
        );
    }
}
