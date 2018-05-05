package de.parti.tut.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.parti.tut.main.Main;

public class ItemsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("tutorial.items")) {
				if(args.length == 0) {
					Inventory inv = Bukkit.createInventory(null, 2 * 9, "§l§6Rucksack");  //arg1: n * 9
					
					ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
					ItemMeta imeta = item.getItemMeta();
					imeta.setDisplayName("§7Hauapfel");
					imeta.addEnchant(Enchantment.DAMAGE_ALL, 7, true);
					item.setItemMeta(imeta);
					item.setAmount(13);
					inv.setItem(13, item);
					
					
					p.openInventory(inv);
				} else
					p.sendMessage("§cBitte benutze /backpack");
			} else
				p.sendMessage("§cDazu hast du keine Rechte!");
		}
		return false;
	}

}
