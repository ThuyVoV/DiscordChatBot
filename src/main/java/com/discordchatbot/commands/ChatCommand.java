package com.discordchatbot.commands;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ChatCommand extends Command{
	
    private static final boolean TRACE_MODE = false;
    static String botName = "testbot";

	public ChatCommand() {
		this.name = "chat";
		this.help = "Talks with the bot";
		this.guildOnly=false;
	}
	
	@Override
	protected void execute(CommandEvent event) {
		event.getChannel().sendMessage("hello!").queue();
		
		 try {
			 
	            String resourcesPath = getResourcesPath();
	            System.out.println(resourcesPath);	
	            MagicBooleans.trace_mode = TRACE_MODE;
	            Bot bot = new Bot(botName, resourcesPath);
	            Chat chatSession = new Chat(bot);
	            bot.brain.nodeStats();
	            String textLine = "";
	 
	            while(true) {
	                System.out.print("You : ");
	                textLine = IOUtils.readInputTextLine();
	                if ((textLine == null) || (textLine.length() < 1))
	                    textLine = MagicStrings.null_input;
	                
	                if (textLine.equals("?shutdown")) {
	                    break;
	                	//System.exit(0);
	                } 
	                else if (textLine.equals("wqwq")) {
	                    bot.writeQuit();
	                    break;
	                    //System.exit(0);
	                } 
	                else {
	                    String request = textLine;
	                    if (MagicBooleans.trace_mode)
	                        System.out.println("STATE=" + request + ":THAT=" + 
	                        		((History) chatSession.thatHistory.get(0)).get(0) + 
	                        		":TOPIC=" + chatSession.predicates.get("topic"));
	                    String response = chatSession.multisentenceRespond(request);
	                    while (response.contains("&lt;"))
	                        response = response.replace("&lt;", "<");
	                    while (response.contains("&gt;"))
	                        response = response.replace("&gt;", ">");
	                    System.out.println("Robot : " + response);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	
    private static String getResourcesPath() {
    	
    	//getting base directory of the project
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        //taking off the "/." at the end of the string
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        //append the path to resources folder
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }

}