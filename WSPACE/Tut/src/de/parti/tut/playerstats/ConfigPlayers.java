package de.parti.tut.playerstats;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigPlayers {
	
	public static File allplayers = new File("plugins/Parti", "allplayers.yml");
	public static FileConfiguration allplayerscfg = YamlConfiguration.loadConfiguration(allplayers);
	
	public void save() throws IOException {
		allplayerscfg.save(allplayers);
	}
	
	

}
