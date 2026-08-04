package com.daichan.firstPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CollectCommand implements CommandExecutor {

    private final FirstPlugin plugin;

    public CollectCommand(FirstPlugin plugin){

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {
        Collector collector = plugin.getCollector();
        if (args.length == 0) {

            sender.sendMessage("Usage");

            return true;
        }
        if(args[0].equalsIgnoreCase("start")){
            label = args[1];
            sender.sendMessage("Start!");
            collector.start(label);
        }
        if(args[0].equalsIgnoreCase("stop")){
            sender.sendMessage("Stop!");
            collector.stop();
        }
        return true;
    }

}