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
			
//		String[] msg = event.getMessage().getContentRaw().split(" ");
//		Boolean chatEnable;
//		
//		if ( msg[0].equalsIgnoreCase(Config.get("PREFIX")+ "chat")) {
//			
//			String resourcesPath;
//			MagicBooleans.trace_mode = TRACE_MODE;
//			Bot bot;
//			Chat chatSession;
//			
//			
//			//create objects
//			if(msg[1].equalsIgnoreCase("on")) {
//				event.getChannel().sendMessage("chat is now on!").queue();
//				chatEnable = true;
//				resourcesPath = getResourcesPath();
//				bot = new Bot(botName, resourcesPath);
//				chatSession = new Chat(bot);
//				bot.brain.nodeStats();
//				
//			}
//			else if (msg[1].equalsIgnoreCase("off")) {
//				event.getChannel().sendMessage("chat is now off!").queue();
//				chatEnable = false;
//				bot = null;
//				chatSession = null;
//				System.gc();
//				
//			}
//
//		}
		
		if (ToggleChatCommand.chatEnable) {
			//event.getChannel().sendMessage("hello this is a text test!").queue();
			String message = event.getMessage().getContentRaw();
			String response = ToggleChatCommand.chatSession.multisentenceRespond(message);
			event.getChannel().sendMessage(response).queue();
			
		}
	}
	

}
