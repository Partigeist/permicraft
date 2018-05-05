package de.parti.tut.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChunkInfoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("tutorial.chunkinfo")) {
				int chunkx = p.getLocation().getChunk().getX();
				int chunkz = p.getLocation().getChunk().getZ();
				p.sendMessage("§1Du befindest dich in Chunk X: §3" + chunkx + "§1 Z: §3" + chunkz);
			}
		}
		return false;
	}

}
