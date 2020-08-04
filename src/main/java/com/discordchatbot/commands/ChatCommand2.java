package com.discordchatbot.commands;

import java.io.File;

import javax.annotation.Nonnull;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;

import com.discordchatbot.DiscordChatBot.Config;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatCommand2 extends ListenerAdapter{
	private static final boolean TRACE_MODE = false;
	static String botName = "testbot";

	public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
		if(event.getAuthor().isBot())
			return;
			
		String[] msg = event.getMessage().getContentRaw().split(" ");
		Boolean chatEnable = false;
		
		if ( msg[0].equalsIgnoreCase(Config.get("PREFIX")+ "chat")) {
			
			String resourcesPath;
			MagicBooleans.trace_mode = TRACE_MODE;
			Bot bot;
			Chat chatSession;
			
			
			
			if(msg[1].equalsIgnoreCase("on")) {
				chatEnable = true;
				resourcesPath = getResourcesPath();
				bot = new Bot(botName, resourcesPath);
				chatSession = new Chat(bot);
				bot.brain.nodeStats();
				
			}
			else if (msg[1].equalsIgnoreCase("off")) {
				chatEnable = false;
				bot = null;
				chatSession = null;
				System.gc();
				
			}
			
			if(chatEnable) {
				
			}
			
		}
	}
	
	private static String getResourcesPath() {

		// getting base directory of the project
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		// taking off the "/." at the end of the string
		path = path.substring(0, path.length() - 2);
		// append the path to resources folder
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}
}
