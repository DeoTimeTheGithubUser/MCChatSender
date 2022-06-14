package me.deotime.mcchatsender

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.*

object Config {

    private const val FILE_NAME = "config/minecraft_webhooks.json"
    private val GSON = GsonBuilder().setPrettyPrinting().create()

    lateinit var configFile: File
    private val registeredWebhooks = HashSet<MinecraftWebhook>()

    init {
        init()
    }

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

    private fun init() {
        val configFile = File(FILE_NAME)
        if(!configFile.exists()) configFile.createNewFile()

        this.configFile = configFile

        val parser = JsonParser()
        val inputStreamReader = InputStreamReader(FileInputStream(configFile))
        val obj = parser.parse(inputStreamReader)?.let { it as JsonObject }?:return
        val minecraftWebhooks = obj.getAsJsonArray("webhooks") as JsonArray
        for(webhook in minecraftWebhooks) registeredWebhooks.add(GSON.fromJson(webhook, MinecraftWebhook::class.java))
    }

}

