package de.parti.tut.main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Plots {
	private String gsname;
	private int anzahlChunks;
	private float x, y, z;
	private ArrayList<Chunk> chunks = new ArrayList();
	private ArrayList<String> members = new ArrayList();
	private ArrayList<String> admins = new ArrayList();
	private String owner;
	
	public Plots(String name, float x, float y, float z, Chunk chunk, String world, String owner) {
		gsname = name;
		this.x = x;
		this.y = y;
		this.z = z;
		anzahlChunks = 1;
		chunks.add(chunk);
		this.owner = owner;
		members.add(owner);
		
		FileConfiguration gscfg = Main.getPlugin().getConfig();
		gscfg.set("GS." + gsname + ".gsname", gsname);
		gscfg.set("GS." + gsname + ".owner", owner.toLowerCase());
		gscfg.set("GS." + gsname + ".chunks", chunk.getX() + "," + chunk.getZ() + ";");
		gscfg.set("GS." + gsname + ".home.x", x);
		gscfg.set("GS." + gsname + ".home.y", y);
		gscfg.set("GS." + gsname + ".home.z", z);
		gscfg.set("GS." + gsname + ".home.world", world);
		gscfg.set("GS." + gsname + ".members", owner + ";");
		StringBuilder plots = new StringBuilder();
		plots.append(gscfg.get("GS.plotlist"));
		plots.append(gsname + ";");
		gscfg.set("GS.plotlist", plots.toString());
		Main.getPlugin().saveConfig();
	}
	
	public static void sethome(String plotname, Player player) {
		if (isOnPlot(plotname, player.getName())) {
			FileConfiguration gscfg = Main.getPlugin().getConfig();
			String owner = gscfg.getString("GS." + plotname + ".owner");
			if(player.getName().equals(owner)) {
				Location loc = player.getLocation();
				gscfg.set("GS." + plotname + ".home.x", loc.getX());
				gscfg.set("GS." + plotname + ".home.y", loc.getY());
				gscfg.set("GS." + plotname + ".home.z", loc.getZ());
				player.sendMessage("Dein Home wurde gesetzt.");
			} else {
				player.sendMessage("Du kannst nur auf deinen eigenen Grundstücken das Home setzen.");
			}
		} else
			player.sendMessage("Du befindest dich auf keinem Grundstück.");
	}
	
	public static boolean isOnPlot(String plotname, String playername) {
		Player p = Bukkit.getPlayer(playername);
		Location loc = p.getLocation();
		String playerchunk = loc.getChunk().getX() + "," + loc.getChunk().getZ() + ";";
		FileConfiguration gscfg = Main.getPlugin().getConfig();
		String chunkslist = gscfg.getString("GS." + plotname + ".chunks");
		if(chunkslist.contains(playerchunk)) {
			return true;
		} else 
			return false;
	}
	
	public static ArrayList<String> getMemberList(String plotname) {
		FileConfiguration gscfg = Main.getPlugin().getConfig();
		ArrayList<String> membersa = new ArrayList();
		if(gscfg.contains("GS." + plotname)) {
			String memberss = gscfg.getString("GS." + plotname + ".members");
			int ind1 = 0;
			int ind2 = 0;
			StringBuilder onemember = new StringBuilder();
			//System.out.println(memberss);					//DB
			char c;
			for(int i = 0; i < memberss.length(); i++) {
				c = memberss.charAt(i);
				//System.out.println(c);
				if(c == ';') {
					//System.out.println(i);
					for(int i1 = ind1; i1 < i; i1++) {
						onemember.append(memberss.charAt(i1));
					}
					ind1 = i+1;
					//System.out.println(onemember);
					membersa.add(onemember.toString());
					onemember.delete(0, onemember.length());
					//System.out.println(membersa);
				}
			}
		}
		return membersa;
	}
	
	public static void setMemberList(String plotname, ArrayList<String> memberlist) {
		FileConfiguration cfg = Main.getPlugin().getConfig();
		StringBuilder memberss = new StringBuilder();
		for(int i = 0; i < memberlist.size(); i++) {
			//System.out.println("Joa" + memberlist.get(i));
			memberss.append(memberlist.get(i) + ";");
			//System.out.println("Joa2" + memberss);
		}
		cfg.set("GS." + plotname + ".members", memberss.toString().toLowerCase());
		Main.getPlugin().saveConfig();
	}
	
	public static ArrayList<String> getGSlist() {
		FileConfiguration gscfg = Main.getPlugin().getConfig();
		ArrayList<String> lista = new ArrayList();
		String lists = gscfg.getString("GS.plotlist");
		int ind1 = 0;
		int ind2 = 0;
		StringBuilder onegs = new StringBuilder();
		//System.out.println(lists);					//DB
		char c;
		for(int i = 0; i < lists.length(); i++) {
			c = lists.charAt(i);
			//System.out.println(c);
			if(c == ';') {
				//System.out.println(i);
				for(int i1 = ind1; i1 < i; i1++) {
					onegs.append(lists.charAt(i1));
				}
				ind1 = i+1;
				//System.out.println(onegs);
				lista.add(onegs.toString());
				onegs.delete(0, onegs.length());
				//System.out.println(lista);
			}
		}
		
		return lista;
	}
	
	public static ArrayList<String> getOverallChunkslist() {
		ArrayList<String> gslist = getGSlist();
		System.out.println(gslist.toString());
		ArrayList<String> overallchunkslist = new ArrayList<String>();
		for(int i = 0; i < gslist.size(); i++) {
			String plot = gslist.get(i);
			ArrayList<String> onechunkl = getChunksList(plot);
			for(int i1 = 0; i1 < onechunkl.size(); i1++) {
				overallchunkslist.add(onechunkl.get(i1) + ";");
			}
		}
		
		return overallchunkslist;
		
	}
	
	public static ArrayList<String> getChunksList(String plotname) {
		FileConfiguration gscfg = Main.getPlugin().getConfig();
		ArrayList<String> chunksa = new ArrayList<String>();
		if(gscfg.contains("GS." + plotname)) {
			String chunkss = gscfg.getString("GS." + plotname + ".chunks");
			int ind1 = 0;
			StringBuilder onechunk = new StringBuilder();
			System.out.println(chunkss);					//DB
			char c;
			for(int i = 0; i < chunkss.length(); i++) {
				c = chunkss.charAt(i);
				System.out.println(c);
				if(c == ';') {
					System.out.println(i);
					for(int i1 = ind1; i1 < i+1; i1++) {
						onechunk.append(chunkss.charAt(i1));
					}
					ind1 = i+1;
					System.out.println(onechunk);
					chunksa.add(onechunk.toString());
					onechunk.delete(0, onechunk.length()); 
					System.out.println(chunksa);
				}
			}
		}
		return chunksa;
	}
	
	public static void AddChunk(String plotname, String chunk) {
		ArrayList<String> chunksa = getChunksList(plotname);
		chunksa.add(chunk);
		FileConfiguration cfg = Main.getPlugin().getConfig();
		StringBuilder chunks = new StringBuilder();
		for(int i = 0; i < chunksa.size(); i++) {
			//System.out.println("Joa" + chunksa.get(i));
			chunks.append(chunksa.get(i));
			//System.out.println("Joa2" + chunks);
		}
		cfg.set("GS." + plotname + ".chunks", chunks.toString().toLowerCase());
		Main.getPlugin().saveConfig();
	}
	
	public static ArrayList<String> getChunksOfPlayerList(String playername) {
		ArrayList<String> chunksa = new ArrayList<String>();
		ArrayList<String> gslist = getGSlist();
		System.out.println(gslist.toString());
		for(int i = 0; i < gslist.size(); i++) {
			String plot = gslist.get(i);
			if(isMember(playername, plot)) {
				ArrayList<String> onechunkl = getChunksList(plot);
				for(int i1 = 0; i1 < onechunkl.size(); i1++) {
					chunksa.add(onechunkl.get(i1));
				}
			}
		}
		
		return chunksa;
	}
	
	public static boolean isMember(String playername, String plotname) {
		ArrayList<String> memberlist = getMemberList(plotname);
		if (memberlist.contains(playername)) {
			return true;
		} else
			return false;
	}
	
	public static boolean isOwner(String playername, String plotname) {
		FileConfiguration cfg = Main.getPlugin().getConfig();
		if(cfg.contains("GS." + plotname)) {
			if(cfg.getString("GS." + plotname + ".owner") == playername) {
				return true;
			} else 
				return false;
		}
		return false;
	}
	
	public static ArrayList<String> getNeighbors(int x, int z) {
		ArrayList<String> neighbors = new ArrayList<String>();
		String chunkS = (x + "," + z + ";");
		neighbors.add((x+1) + "," + z + ";");	
		neighbors.add((x-1) + "," + z + ";");	
		neighbors.add(x + "," + (z+1) + ";");	
		neighbors.add(x + "," + (z-1) + ";");
		System.out.println("Debug: Neighbors of " + chunkS + " are: " + neighbors.toString());
		return neighbors;
	}
	
	public static String getPlotofChunk(int x, int z) {
		String plotname;
		ArrayList<String> plotnamesa = getGSlist();
		String chunk = (x + "," + z + "");
		System.out.println("Chunk: db: " + chunk);
		for(int i = 0; i < plotnamesa.size(); i++) {
			if(getChunksList(plotnamesa.get(i)).contains(chunk)) {
				return plotnamesa.get(i);
			}
		}
		return null;
	}
	
	public static String getPlotofChunk(String chunkS) {
		String plotname;
		ArrayList<String> plotnamesa = getGSlist();
		String chunk = chunkS;
		System.out.println("Chunk: db: " + chunk);
		for(int i = 0; i < plotnamesa.size(); i++) {
			if(getChunksList(plotnamesa.get(i)).contains(chunk)) {
				return plotnamesa.get(i);
			}
		}
		return null;
	}
	
	public static String ChunktoString(Chunk c) {
		String chunkS = (c.getX() + "," + c.getZ() + ";");
		return chunkS;
		
	}
	
	public static int getXofChunkS(String chunkS) {
		char c;
		int x;
		StringBuilder xS = new StringBuilder();
		for(int i = 0; i < chunkS.length(); i++) {
			c = chunkS.charAt(i);
			if(c == ',') {
				x = Integer.parseInt(xS.toString());
				return x;
			} else
				xS.append(c);				
		}
		return 0;
	}
	
	public static int getZofChunkS(String chunkS) {
		char c;
		int z;
		int k;
		StringBuilder zS = new StringBuilder();
		for(int i = 0; i < chunkS.length(); i++) {
			c = chunkS.charAt(i);
			if(c == ',') {
				for(int i1 = i+1; i1 < chunkS.length(); i1++) {
					c = chunkS.charAt(i1);
					if(c != ';') {
						zS.append(c);
						System.out.println("zS appended: " + c);
					} else {
						return Integer.parseInt(zS.toString());
					}
				}
			}
		}
		return 0;
	}
	
	public static boolean isChunkUsed(String chunkS) {
		ArrayList<String> allchunks = getOverallChunkslist();
		if(allchunks.contains(chunkS)) {
			return true;
		}
		return false;
	}
	
	public static void RemoveChunk(String plotname, String chunkS) {
		ArrayList<String> chunksa = getChunksList(plotname);
		chunksa.remove(chunkS);
		FileConfiguration cfg = Main.getPlugin().getConfig();
		StringBuilder chunks = new StringBuilder();
		for(int i = 0; i < chunksa.size(); i++) {
			//System.out.println("Joa" + chunksa.get(i));
			chunks.append(chunksa.get(i));
			//System.out.println("Joa2" + chunks);
		}
		cfg.set("GS." + plotname + ".chunks", chunks.toString().toLowerCase());
		Main.getPlugin().saveConfig();
	}
}
