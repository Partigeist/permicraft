package de.parti.tut.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage("§7Der Spieler §a" + p.getName() + " §7ist auf Mittelerde erschienen");
		p.sendMessage("§dHey " + p.getName());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		e.setQuitMessage("§7Der Spieler §a\" + p.getName() + \" §7ist von Mittelerde verschwunden");
	}

}
