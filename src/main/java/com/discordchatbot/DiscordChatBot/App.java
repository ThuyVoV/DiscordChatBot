package com.discordchatbot.DiscordChatBot;

import javax.security.auth.login.LoginException;

import com.discordchatbot.commands.ChatCommand;
import com.discordchatbot.commands.DeleteMessageCommand;
import com.discordchatbot.commands.ShutdownCommand;
import com.discordchatbot.commands.ToggleChatCommand;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {

	public static void main(String[] args) throws LoginException {

		JDA jda = JDABuilder.createDefault(Config.get("TOKEN")).build();

		CommandClientBuilder builder = new CommandClientBuilder();

		builder.setOwnerId(Config.get("OWNER_ID"));
		builder.setPrefix(Config.get("PREFIX"));
		builder.setHelpWord(Config.get("HELP_WORD"));

		builder.setActivity(Activity.playing("OINK OINK GOTTA CODE!!"));

		builder.addCommand(new ToggleChatCommand());
		builder.addCommand(new DeleteMessageCommand());
		CommandClient client = builder.build();
		
		jda.addEventListener(client);
		jda.addEventListener(new ChatCommand());
		jda.addEventListener(new ShutdownCommand());
	}
}