package com.discordchatbot.commands;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

import com.discordchatbot.DiscordChatBot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ChatCommand extends Command {

	private static final boolean TRACE_MODE = false;
	static String botName = "testbot";
	private final EventWaiter waiter;

	public ChatCommand(EventWaiter waiter) {
		this.waiter = waiter;
		this.name = "chat";
		this.help = "Talks with the bot";
		this.guildOnly = false;
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply("hello there");

		try {
			String resourcesPath = getResourcesPath();
			// System.out.println(resourcesPath);
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot = new Bot(botName, resourcesPath);
			Chat chatSession = new Chat(bot);
			bot.brain.nodeStats();
			// String textLine = "";

			

				waiter.waitForEvent(MessageReceivedEvent.class,

						// make sure it's by the same user, and in the same channel, and for safety, a
						// different message
						e -> e.getAuthor().equals(event.getAuthor()) && e.getChannel().equals(event.getChannel())
								&& !e.getMessage().equals(event.getMessage()) && !e.getAuthor().isBot(),
						// respond, inserting the name they listed into the response
						e -> event.reply(chatSession.multisentenceRespond(event.getMessage().toString())),
						// if the user takes more than a minute, time out
						1, TimeUnit.MINUTES, () -> event.reply("Sorry, you took too long."));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//	                //System.out.print("You : ");
//	            	textLine = event.getMessage().getContentRaw();
//	                textLine = IOUtils.readInputTextLine();
//	                if ((textLine == null) || (textLine.length() < 1))
//	                    textLine = MagicStrings.null_input;
//	                
//	                if (textLine.equals(Config.get("PREFIX")+"chat stop")) {
//	                    break;
//	                	//System.exit(0);
//	                } 
//	                else if (textLine.equals(Config.get("PREFIX")+"wqwq")) {
//	                    bot.writeQuit();
//	                    break;
//	                    //System.exit(0);
//	                } 
//	                else {
//	                    String request = textLine;
////	                    if (MagicBooleans.trace_mode)
////	                        System.out.println("STATE=" + request + ":THAT=" + 
////	                        		((History) chatSession.thatHistory.get(0)).get(0) + 
////	                        		":TOPIC=" + chatSession.predicates.get("topic"));
//	                    String response = chatSession.multisentenceRespond(request);
//	                    while (response.contains("&lt;"))
//	                        response = response.replace("&lt;", "<");
//	                    while (response.contains("&gt;"))
//	                        response = response.replace("&gt;", ">");
//	                    //System.out.println("Robot : " + response);
//	                    event.getChannel().sendMessage(response).queue();
//	                }
	}

	private static String getResourcesPath() {

		// getting base directory of the project
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		// taking off the "/." at the end of the string
		path = path.substring(0, path.length() - 2);
		// System.out.println(path);
		// append the path to resources folder
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}

}
