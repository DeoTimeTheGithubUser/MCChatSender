package me.deotime.mcchatsender.gui

import me.deotime.mcchatsender.MinecraftWebhook
import java.awt.Component
import java.awt.FlowLayout
import javax.swing.*

class GuiCreateWebhook : JFrame("Create Webhook") {

    var createAction: ((webhook: MinecraftWebhook) -> Unit)? = null

    init {
        setSize(500, 800)
        initGui()
        isVisible = true
    }

    private fun initGui() {
        val container = JPanel().yBox()

        val title = JLabel("Create Webhook").centerAlign()
        container.add(title)

        val optionsContainer = JPanel().yBox().centerAlign()

        val urlOption = createOption(optionsContainer, "URL")
        val usernameOption = createOption(optionsContainer, "Username")
        val avatarURLOption = createOption(optionsContainer, "Avatar URL")
        val listenOption = createOption(optionsContainer, "Listen")
        val sendOption = createOption(optionsContainer, "Send")

        container.add(optionsContainer)

        val createButton = JButton("Create")
        createButton.addActionListener {
            createAction?.let {
                val minecraftWebhook = MinecraftWebhook(
                    urlOption(),
                    usernameOption(),
                    avatarURLOption(),
                    listenOption(),
                    sendOption()
                )
                it(minecraftWebhook)
            }
        }


    }

    private fun createOption(container: JPanel, optionName: String): () -> String {
        val optionPanel = JPanel().centerAlign()
        optionPanel.layout = FlowLayout()
        val label = JLabel("$optionName: ")
        val field = JTextField()
        field.columns = 15
        optionPanel.add(label)
        optionPanel.add(field)
        container.add(optionPanel)
        return {field.text}
    }

    private fun <T : JComponent> T.yBox(): T {
        this.layout = BoxLayout(this, BoxLayout.Y_AXIS)
        return this
    }

    private fun <T : JComponent> T.centerAlign(): T {
        this.alignmentX = JComponent.CENTER_ALIGNMENT
        return this
    }

}