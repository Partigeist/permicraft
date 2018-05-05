package de.parti.tut.listener;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.mysql.fabric.xmlrpc.Client;

import net.minecraft.server.v1_12_R1.ChatBaseComponent;
import net.minecraft.server.v1_12_R1.PlayerChunkMap;

public class TestListener implements Listener {
	
	@EventHandler
	public void onTest(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		
		Chunk c = loc.getChunk();
		//p.sendMessage("Du befindest dich jetzt: " + c.getX() + " " + c.getZ());
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChattest(PlayerChatEvent ec) {
		Player p = ec.getPlayer();
		String msg = ec.getMessage();
		Player p1 = ec.getPlayer();
		p1.setCustomName("");
		p1.setCustomNameVisible(true);
		ec.setPlayer(p1);
		ec.setFormat("§2" + p.getName() + "§f: " + msg);
		
	}

}
