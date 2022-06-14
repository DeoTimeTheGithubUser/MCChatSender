package me.deotime.mcchatsender.command

import me.deotime.mcchatsender.Config
import me.deotime.mcchatsender.gui.GuiCreateWebhook
import me.deotime.mcchatsender.sendPrefixMessage
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

class CommandMCChatSender : CommandBase() {

    override fun canCommandSenderUseCommand(sender: ICommandSender?) = true

    override fun getCommandName() = "mcchatsender"

    override fun getCommandUsage(sender: ICommandSender?) = "/mcchatsender"

    override fun processCommand(sender: ICommandSender?, args: Array<out String>?) {
        val gui = GuiCreateWebhook()
        gui.createAction = {
            Config.addNewWebhook(it)
            sendPrefixMessage("&aCreated new me.deotime.webhook.")
        }
    }
}