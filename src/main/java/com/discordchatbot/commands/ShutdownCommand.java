package com.discordchatbot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ShutdownCommand extends Command {

    public ShutdownCommand()
    {
        this.name = "shutdown";
        this.help = "safely shuts off the bot";
        this.guildOnly = false;
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
    	System.out.println("shutting down");
    	event.getChannel().sendMessage("shutting down").queue();
        event.reactWarning();
        event.getJDA().shutdown();
        System.exit(0);
    }
    
}