package com.discordchatbot.commands;

import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class DeleteMessageCommand extends Command{

	public DeleteMessageCommand() {
		this.name="delete";
		this.help="deletes X number of messages";
		this.arguments= "[number of msg to delete between 1-100]";
	}
	
	@Override
	protected void execute(CommandEvent event){
		//gets argument
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		try {
			//get number of msg to delete
			int numMsgDelete = Integer.parseInt(args[1]);
			//gets the current channel to delete msg from
			TextChannel channel = event.getTextChannel();
			//gets the message to delete and put it in a list
			List<Message> msg = channel.getHistory().retrievePast(numMsgDelete).complete();
			//deletes all msg in the list
			channel.purgeMessages(msg);
		}
		catch (IndexOutOfBoundsException e) {
			event.reply("Enter the number of messages to delete.");
		}
		catch (NumberFormatException e) {
			event.reply("Enter a valid integer from 1-100.");
		}
		catch (IllegalArgumentException e) {
			event.reply("Enter a number between 1-100.");
		}

		
	}
	
	

}
