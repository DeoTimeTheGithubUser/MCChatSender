package me.deotime.mcchatsender

import me.deotime.webhook.DiscordWebhook
import me.deotime.webhook.WebhookMessage
import java.net.URL

class MinecraftWebhook(val url: String, val username: String, val avatarUrl: String, val listenedMessage: String, val writtenMessage: String) {
    fun sendMessage() {
        Thread {

            val connection = URL(url)
            val discordWebhook = DiscordWebhook.createWebhook(connection)

            val webhookMessage = WebhookMessage()
            webhookMessage.content = writtenMessage
            webhookMessage.username = username
            webhookMessage.avatarURL = avatarUrl

            discordWebhook.sendMessage(webhookMessage)

        }.start()
    }
}

fun getListeningWebhook(message: String): MinecraftWebhook? {
    return Config.registeredWebhooks.firstOrNull { message.contains(it.listenedMessage) }
}