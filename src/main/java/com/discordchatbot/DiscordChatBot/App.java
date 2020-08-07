package com.discordchatbot.DiscordChatBot;

import javax.security.auth.login.LoginException;

import com.discordchatbot.commands.ChatCommand;
import com.discordchatbot.commands.DeleteMessageCommand;
import com.discordchatbot.commands.ShutdownCommand;
import com.discordchatbot.commands.ToggleChatCommand;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class App {
//    private static final boolean TRACE_MODE = false;
//    static String botName = "testbot";

	public static void main(String[] args) throws LoginException {

		JDA jda = JDABuilder.createDefault(Config.get("TOKEN")).build();

		EventWaiter waiter = new EventWaiter();
		CommandClientBuilder builder = new CommandClientBuilder();

		builder.setOwnerId(Config.get("OWNER_ID"));
		builder.setPrefix(Config.get("PREFIX"));
		builder.setHelpWord(Config.get("HELP_WORD"));

		builder.setActivity(Activity.playing("OINK OINK GOTTA CODE!!"));

		builder.addCommand(new ShutdownCommand());
		builder.addCommand(new ToggleChatCommand());
		builder.addCommand(new DeleteMessageCommand());
		CommandClient client = builder.build();

		jda.addEventListener(client);
		jda.addEventListener(new ChatCommand());
	}
}