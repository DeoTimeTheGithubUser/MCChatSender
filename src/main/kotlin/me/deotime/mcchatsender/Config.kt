package me.deotime.mcchatsender

import com.google.gson.*
import java.io.*

object Config {

    private const val FILE_NAME = "config/minecraft_webhooks.json"
    private val GSON = GsonBuilder().setPrettyPrinting().create()

    lateinit var configFile: File
    val registeredWebhooks = HashSet<MinecraftWebhook>()

    fun addNewWebhook(webhook: MinecraftWebhook) {
        registeredWebhooks.add(webhook)
        save()
    }

    private fun save() {
        val writer = FileWriter(configFile)
        GSON.toJson(registeredWebhooks, writer)
        writer.flush()
        writer.close()
    }

    fun init() {
        val configFile = File(FILE_NAME)
        if(!configFile.exists()) configFile.createNewFile()

        this.configFile = configFile

        val parser = JsonParser()
        val inputStreamReader = InputStreamReader(FileInputStream(configFile))
        val read = parser.parse(inputStreamReader)
        if(read is JsonNull) return
        val minecraftWebhooks = read.asJsonArray
        for(webhook in minecraftWebhooks) registeredWebhooks.add(GSON.fromJson(webhook, MinecraftWebhook::class.java))
    }

}

