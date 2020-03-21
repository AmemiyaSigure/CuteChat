package cx.rain.fabricmod.cutechat.mixins;

import cx.rain.fabricmod.cutechat.CuteChat;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class MixinPlayerChat {
    @Shadow public ServerPlayerEntity player;

    @Inject(at = @At(value = "TAIL"), method = "onChatMessage")
    public void onChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        String str = "<" + player.getDisplayName() + "> " + packet.getChatMessage();
        CuteChat.SOCKET.send(str);
    }
}
