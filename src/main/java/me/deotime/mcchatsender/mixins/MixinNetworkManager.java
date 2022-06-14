package me.deotime.mcchatsender.mixins;

import io.netty.channel.ChannelHandlerContext;
import me.deotime.mcchatsender.Config;
import me.deotime.mcchatsender.MCChatSenderKt;
import me.deotime.mcchatsender.MinecraftWebhook;
import me.deotime.mcchatsender.MinecraftWebhookKt;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Inject(method = "channelRead0*", at = @At("HEAD"))
    private void onChannelReal(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        if(!(packet instanceof S02PacketChat)) return;
        S02PacketChat chatPacket = (S02PacketChat) packet;
        String message = chatPacket.getChatComponent().getUnformattedText();
        MinecraftWebhook webhook = MinecraftWebhookKt.getListeningWebhook(message);
        if(webhook == null) return;
        MCChatSenderKt.sendPrefixMessage("&aWebhook &e\"" + webhook.getUsername() + "\"&a triggered!");
        webhook.sendMessage();
    }

}
