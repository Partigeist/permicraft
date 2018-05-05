package de.parti.tut.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.parti.tut.main.Main;
import de.parti.tut.main.Plots;

public class PlotsSetHomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("tutorial.setspawn")) {
				if(args.length == 1) {
					String gsname = args[0];
					Plots.sethome(gsname, p);
				} else
					p.sendMessage("Bitte benutze /home <gsname>");				
			} else
				p.sendMessage("Dazu hast du keine Rechte");
		}
		return false;
	}

}
