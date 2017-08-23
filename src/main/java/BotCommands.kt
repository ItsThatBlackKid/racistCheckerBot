import de.btobastian.javacord.entities.message.Message
import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor

class BotCommands : CommandExecutor {
    @Command(aliases = arrayOf("--add"), description = "Add word to list")
    fun onCommand(message: Message) {
        RacistBot.doCheck = false
        val content = message.content
        val newWord = content.substring(content.lastIndexOf("add") + 3).trimStart()
                .capitalize()
        RacistBot.listOfRacistWords.add(newWord)
        message.reply("Added word: $newWord")
    }

    @Command(aliases = arrayOf("--list", "-l"), description = "Get list of racist words")
    fun listMessage(message: Message) {
        val builder = StringBuilder()
        for (word in RacistBot.listOfRacistWords) {
            builder.append(word + System.getProperty("line.separator"))
        }

        message.reply(builder.toString())
    }
}