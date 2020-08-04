package com.discordchatbot.DiscordChatBot;

import java.io.File;

import javax.security.auth.login.LoginException;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.History;
import org.alicebot.ab.MagicBooleans;
import org.alicebot.ab.MagicStrings;
import org.alicebot.ab.utils.IOUtils;

import com.discordchatbot.commands.ChatCommand;
import com.discordchatbot.commands.ChatCommand2;
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
 
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) throws LoginException {
    	
    	JDA jda = JDABuilder.createDefault(Config.get("TOKEN")).build();
    	
    	EventWaiter waiter = new EventWaiter();
        CommandClientBuilder builder = new CommandClientBuilder();

        builder.setOwnerId(Config.get("OWNER_ID"));
        builder.setPrefix(Config.get("PREFIX"));
        builder.setHelpWord(Config.get("HELP_WORD"));
        
        builder.setActivity(Activity.playing("OINK OINK GOTTA CODE!!"));

        //builder.addCommand(new ChatCommand(waiter));
        builder.addCommand(new ShutdownCommand());
        builder.addCommand(new ToggleChatCommand());
        CommandClient client = builder.build();
        

        jda.addEventListener(client);
        jda.addEventListener(waiter);
        jda.addEventListener(new ChatCommand2());
    	
//        try {
// 
//            String resourcesPath = getResourcesPath();
//            System.out.println(resourcesPath);	
//            MagicBooleans.trace_mode = TRACE_MODE;
//            Bot bot = new Bot(botName, resourcesPath);
//            Chat chatSession = new Chat(bot);
//            bot.brain.nodeStats();
//            String textLine = "";
// 
//            while(true) {
//                System.out.print("You : ");
//                textLine = IOUtils.readInputTextLine();
//                if ((textLine == null) || (textLine.length() < 1))
//                    textLine = MagicStrings.null_input;
//                
//                if (textLine.equals("q")) {
//                    System.exit(0);
//                } 
//                else if (textLine.equals("wqwq")) {
//                    bot.writeQuit();
//                    System.exit(0);
//                } 
//                else {
//                    String request = textLine;
//                    if (MagicBooleans.trace_mode)
//                        System.out.println("STATE=" + request + ":THAT=" + 
//                        		((History) chatSession.thatHistory.get(0)).get(0) + 
//                        		":TOPIC=" + chatSession.predicates.get("topic"));
//                    String response = chatSession.multisentenceRespond(request);
//                    while (response.contains("&lt;"))
//                        response = response.replace("&lt;", "<");
//                    while (response.contains("&gt;"))
//                        response = response.replace("&gt;", ">");
//                    System.out.println("Robot : " + response);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
 
//    private static String getResourcesPath() {
//    	
//    	//getting base directory of the project
//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
//        //taking off the "/." at the end of the string
//        path = path.substring(0, path.length() - 2);
//        System.out.println(path);
//        //append the path to resources folder
//        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
//        return resourcesPath;
//    }
}