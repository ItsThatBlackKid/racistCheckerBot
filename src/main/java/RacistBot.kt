import com.google.common.util.concurrent.FutureCallback
import de.btobastian.javacord.DiscordAPI
import de.btobastian.javacord.Javacord
import de.btobastian.javacord.entities.message.Message
import de.btobastian.javacord.listener.message.MessageCreateListener
import de.btobastian.sdcf4j.handler.JavacordHandler

class RacistBot {

    val clientID = "345832259586097163"
    val username = "discord-tut#0449"
    val token = "MzQ1ODMyMjU5NTg2MDk3MTYz.DHBLgQ.wjTpYP-8cE3kGQagEhvHxqOnloM"
    val api: DiscordAPI = Javacord.getApi(token, true)

    companion object {
        val listOfRacistWords: ArrayList<String> = arrayListOf(
                "Nigger",
                "Gook",
                "Ape",
                "Coon",
                "Jigaboo",
                "Negro",
                "Sambo",
                "Sooty",
                "Teapot",
                "Cracker",
                "Gringo",
                "Gweilo",
                "gwailo",
                "kwai lo",
                "Honky",
                "Redskin",
                "White Trash")

        var doCheck = false
    }


    init {

        val handler = JavacordHandler(api)

        api.connect(object : FutureCallback<DiscordAPI> {
            override fun onSuccess(p0: DiscordAPI?) {
                api.registerListener(listener)

            }

            override fun onFailure(p0: Throwable?) {
                p0!!.printStackTrace()
            }
        })

        handler.registerCommand(BotCommands())

    }

    val listener = object : MessageCreateListener {
        override fun onMessageCreate(api: DiscordAPI?, message: Message?) {
            if (!doCheck) {
                return
            }

            val content = message!!.content
            if (content.isRacist()) {
                val author = message.author
                message.reply("Racist message detected from ${author.name}")
            }
        }
    }


    fun listRacistWords() {
        val builder = StringBuilder()
        for (word in RacistBot.listOfRacistWords) {
            builder.append(word + System.getProperty("line.separator"))
        }
        println(builder.toString())
    }

    fun String.isRacist(): Boolean {
        /*var condition = false
         val thisLower = this.toLowerCase()
         for (badWord in BadwordChecker().getBadWords()) {
             val badWordLower = badWord.toLowerCase()
             if(badWordLower in thisLower) {
                 println("condition: $condition")
                 condition = true
                 break
             }
         }*/

        /*val stream = BadwordChecker().getBadWords().asSequence().any {
            this.toLowerCase().contains(it.toLowerCase())}*/


        val check = listOfRacistWords.asSequence().any {
            this.toLowerCase().contains(it.toLowerCase())
        }

        println("checked: $check")

        if (check == false) {
            return false
        }
        return check
    }
}

fun main(args: Array<String>) {
    RacistBot()
}