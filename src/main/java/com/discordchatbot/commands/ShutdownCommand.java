package com.discordchatbot.commands;

import com.discordchatbot.DiscordChatBot.Config;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ShutdownCommand extends ListenerAdapter{
	
	public void onMessageReceived(MessageReceivedEvent event) throws IllegalStateException{
		if(event.getAuthor().isBot())
			return;
		
		String msg = event.getMessage().getContentRaw();
		
		if(msg.equalsIgnoreCase(Config.get("PREFIX") + "shutdown")){
		
			if(event.getAuthor().getId().contentEquals(Config.get("OWNER_ID"))) {
				System.out.println("Shutting down!");
				event.getChannel().sendMessage("Shutting down!").queue();
				event.getJDA().shutdown();
				System.exit(0);
			}
		}
	}
}