package de.parti.tut.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor{
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 1) {
            	if(cmd.getName().equalsIgnoreCase("gm")) {
            		p.setGameMode(GameMode.CREATIVE);
            		p.sendMessage(ChatColor.GRAY + "Gamemode wurde gewechselt.");
                }
            }
        }
        return false;
    }
}