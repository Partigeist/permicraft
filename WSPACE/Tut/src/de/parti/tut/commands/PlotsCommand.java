package de.parti.tut.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.parti.tut.main.Main;
import de.parti.tut.main.Plots;

public class PlotsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("tutorial.plotscreate")) {
				if(args.length == 1) {
					try {
						String gsname = args[0];
						if(gsname.length() < 20) {
							Location loc = p.getLocation();						
							Plots baum = new Plots(gsname, loc.getBlockX(), loc.getBlockY(), loc.getBlockZ(), loc.getChunk(), loc.getWorld().getName(), p.getName());
							p.sendMessage("Dein GS wurde erstellt :)");
						} else 
							p.sendMessage("Der GS-Name darf aus maximal 20 Zeichen bestehen.");
					} catch (Exception e) {
						p.sendMessage("Der GS-Name darf nur aus erlaubten Satzzeichen bestehen");
						e.printStackTrace();
					}
				} else
					p.sendMessage("Bitte gib /gscreate <name> ein");
			}
		}
		return false;
	}

}
