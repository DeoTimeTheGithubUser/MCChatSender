package me.deotime.mcchatsender

import me.deotime.mcchatsender.command.CommandMCChatSender
import net.minecraft.client.Minecraft
import net.minecraft.util.ChatComponentText
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import org.apache.logging.log4j.LogManager

@SideOnly(Side.CLIENT)
@Mod(modid = MCChatSender.MOD_ID)
class MCChatSender {
    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        Config.init()
        ClientCommandHandler.instance.registerCommand(CommandMCChatSender())
        LOGGER.info("MCChatSender initialized")
    }

    companion object {
        const val MOD_ID = "mcchatsender"
        const val PREFIX = "\u00a7c[MCChatSender]"
        @get:JvmName("getLogger")
        @JvmStatic
        val LOGGER = LogManager.getLogger(MOD_ID)!!
    }
}

fun String.clr() = this.replace("&", "\u00a7")

fun sendPrefixMessage(msg: String) {
    Minecraft.getMinecraft().thePlayer?.addChatMessage(ChatComponentText("${MCChatSender.PREFIX} \u00a7r${msg.clr()}"))
}