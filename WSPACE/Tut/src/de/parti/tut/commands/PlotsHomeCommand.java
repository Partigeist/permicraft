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

public class PlotsHomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("tutorial.setspawn")) {
				if(args.length == 1) {
					String gsname = args[0];
					FileConfiguration gscfg = Main.getPlugin().getConfig();
					String members = gscfg.getString("GS." + args[0] + ".members");
					p.sendMessage(members);
					if(Plots.getMemberList(gsname).contains(p.getName())) {
						float x = Float.parseFloat(gscfg.getString("GS." + gsname + ".home.x"));
						float y = Float.parseFloat(gscfg.getString("GS." + gsname + ".home.y"));
						float z = Float.parseFloat(gscfg.getString("GS." + gsname + ".home.z"));
						World world = Bukkit.getWorld(gscfg.getString("GS." + gsname + ".home.world"));
						Location loc = new Location(world, x, y, z, 0, 0);
						p.teleport(loc);
					} else
						p.sendMessage("Du musst Member eines Grundstücks sein, um dich dorthin zu teleportieren");
				} else
					p.sendMessage("Bitte benutze /home <gsname>");				
			} else
				p.sendMessage("Dazu hast du keine Rechte");
		}
		return false;
	}

}
