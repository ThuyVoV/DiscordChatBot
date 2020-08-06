package com.discordchatbot.commands;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nonnull;

import com.discordchatbot.DiscordChatBot.Config;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatCommand extends ListenerAdapter{


	public void onMessageReceived(@Nonnull MessageReceivedEvent event) throws IllegalArgumentException{
		
		if(event.getAuthor().isBot()) {
			return;
		}
		
		if ( event.getMessage().getContentRaw().startsWith(Config.get("PREFIX")) )
			return;
		
		try {
			if (ToggleChatCommand.chatEnable) {
				//splits sentences ending with . ? !
				String[] msg = event.getMessage().getContentRaw().split("\\.|\\?|\\!");
				String message;
				String response;

				for(int i = 0; i < msg.length; i++) {
					message = msg[i];
					response = ToggleChatCommand.chatSession.multisentenceRespond(message);
					event.getChannel().sendMessage(response).queue();
					
					System.out.println("you: " + message);
					System.out.println("bot: " + response);
					
					TimeUnit.SECONDS.sleep(1);
				}
			}
		}
		catch (IllegalArgumentException e) {
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
