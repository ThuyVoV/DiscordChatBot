package com.discordchatbot.commands;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.MagicBooleans;

import com.discordchatbot.DiscordChatBot.Config;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.TextChannel;

public class ToggleChatCommand extends Command{
	private static final boolean TRACE_MODE = true;
//	static String botName = "super";
	static String botName = "testbot";
	
	public static boolean chatEnable = false;
	public static String resourcesPath;
	public static Bot bot;
	public static Chat chatSession;

	public ToggleChatCommand() {
		this.name = "chat";
		this.help = "turns the chat function on or off";
		this.arguments = "[on/off]";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
		
		if (args.length < 2) {
			event.reply("```Please provide an argument:\n" + 
					Config.get("PREFIX") + name + " " + getArguments() +"```");
			return;
		}
			
		if(args[1].equalsIgnoreCase("off")) {
			bot = null;
			chatSession = null;
			System.gc();
			chatEnable = false;
			
			System.out.println("chat off");
			event.reply("```Chat function off```");
		}
		else if (args[1].equalsIgnoreCase("on")){

			resourcesPath = getResourcesPath();
			bot = new Bot(botName, resourcesPath);
			chatSession = new Chat(bot);
			bot.brain.nodeStats();
			MagicBooleans.trace_mode = TRACE_MODE;
			chatEnable = true;
			
			event.reply("```Chat function on```");
			System.out.println("chat on");
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
