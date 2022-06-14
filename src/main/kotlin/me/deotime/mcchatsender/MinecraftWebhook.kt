package me.deotime.mcchatsender

import webhook.DiscordWebhook
import webhook.WebhookMessage
import java.net.URL

class MinecraftWebhook(val url: String, val username: String, val avatarUrl: String, val listenedMessage: String, val writtenMessage: String) {
    fun sendMessage() {
        Thread {

            val connection = URL(url)
            val discordWebhook = DiscordWebhook.createWebhook(connection)

            val webhookMessage = WebhookMessage
                .builder()
                .content(writtenMessage)
                .username(username)
                .avatarURL(avatarUrl)
                .build()

            discordWebhook.sendMessage(webhookMessage)

        }.start()
    }
}

fun getListeningWebhook(message: String): MinecraftWebhook? {
    return Config.registeredWebhooks.firstOrNull { message.contains(it.listenedMessage) }
}