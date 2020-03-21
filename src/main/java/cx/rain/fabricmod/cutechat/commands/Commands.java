package cx.rain.fabricmod.cutechat.commands;

import net.fabricmc.fabric.api.registry.CommandRegistry;

public class Commands {
    public Commands() {
        CommandRegistry.INSTANCE.register(true, dispatcher -> {
            CommandCuteChatVersion.register(dispatcher);
            CommandCuteChatReconnect.register(dispatcher);
        });
    }
}
