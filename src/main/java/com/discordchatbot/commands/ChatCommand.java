package com.discordchatbot.commands;

import javax.annotation.Nonnull;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatCommand extends ListenerAdapter{


	public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		if (ToggleChatCommand.chatEnable) {
			//event.getChannel().sendMessage("hello this is a text test!").queue();
			String message = event.getMessage().getContentRaw();
			String response = ToggleChatCommand.chatSession.multisentenceRespond(message);
			event.getChannel().sendMessage(response).queue();
			
			System.out.println("you: " + message);
			System.out.println("bot: " + response);
			
		}
	}
	

}
