package com.discordchatbot.commands;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ToggleChatCommand extends Command{
	private static final boolean TRACE_MODE = false;
	static String botName = "testbot";
	
	public static boolean chatEnable = false;
	public static String resourcesPath;
	public static Bot bot;
	public static Chat chatSession;

	public ToggleChatCommand() {
		this.name = "togglechat";
		this.help = "turns the chat function on or off";
	}
	
	@Override
	protected void execute(CommandEvent event) {
		
		if(chatEnable) {
			event.reply("turning chat function off");
			bot = null;
			chatSession = null;
			System.gc();
			chatEnable = false;
		}
		else {
			event.reply("turning chat function on");
			resourcesPath = getResourcesPath();
			bot = new Bot(botName, resourcesPath);
			chatSession = new Chat(bot);
			bot.brain.nodeStats();
			chatEnable = true;
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
