package com.discordchatbot.DiscordChatBot;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
	
	//load config file and store it to a map
	private static final Dotenv dotenv = Dotenv.load(); 

	//get the variable from the .env file
	public static String get(String key) {
		return dotenv.get(key);
	}
}
