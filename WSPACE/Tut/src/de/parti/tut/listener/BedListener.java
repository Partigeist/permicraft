package de.parti.tut.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedListener implements Listener {
	
	@EventHandler
	public void onBedEnter(PlayerBedEnterEvent e) {
		e.getPlayer().sendMessage("§1Gute Nacht");		//2ez
	}

}
