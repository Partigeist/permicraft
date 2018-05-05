package de.parti.tut.commands;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.parti.tut.main.Plots;

public class PlotsChunkAdd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			Location loc = p.getLocation();
			int x = loc.getChunk().getX();
			int z = loc.getChunk().getZ();
			int ind = 0;
			String chunk = x + "," + z + ";";
			if(Plots.isChunkUsed(chunk) == false) {
				ArrayList<String> chunks = Plots.getChunksOfPlayerList(p.getName());
				System.out.println(chunks.toString());
				ArrayList<String> n = Plots.getNeighbors(x, z);
				for(int i = 0; i < n.size(); i++) {
					if(chunks.contains(n.get(i))) {
						p.sendMessage("Chunk added!");
						Plots.AddChunk(Plots.getPlotofChunk(n.get(i)), chunk);
					} else
						ind++;
				}
				if(ind == 4) {
					p.sendMessage("Kein Grundst�ck in der N�he.");
				}
			} else
				p.sendMessage("Chunk geh�rt bereits zu einem Grundst�ck.");
		}
		return false;
	}

}
