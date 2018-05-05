package de.parti.tut.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.parti.tut.main.Main;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 0) {
				if(p.hasPermission("tutorial.heal") || p.hasPermission("tutorial.*")) {
					p.setHealth(20);
					p.setFoodLevel(20);
					p.sendMessage("§aDu wurdest geheilt :)");
				}
				else if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						target.setHealth(20);
						target.setFoodLevel(20);
						target.sendMessage("§aDu wurdest von §6" + p.getName() + "§a geheilt geheilt");
						p.sendMessage("Du hast " + args[0] + " geheilt");
					} else 
						p.sendMessage("§cDer Spieler " + args[0] + " ist nicht online");
				} else
					p.sendMessage("Dazu hast du keine Rechte");			
			} else 
				p.sendMessage("§cBitte benutze /heal <PLAYER>");
		}
		return false;
	}

}
