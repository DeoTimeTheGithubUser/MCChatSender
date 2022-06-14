package me.deotime.mcchatsender.event

import me.deotime.mcchatsender.MinecraftWebhook
import me.deotime.mcchatsender.getListeningWebhook
import net.minecraftforge.event.ServerChatEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import tv.twitch.chat.ChatEvent
import java.io.IOException

class ChatListener {

    @SubscribeEvent
    fun onChat(event: ServerChatEvent) {
        val message = event.message
        getListeningWebhook(message)?.sendMessage()
    }

}