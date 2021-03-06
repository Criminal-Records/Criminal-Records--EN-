package me.fred50.criminal;

import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CriminalRecords extends JavaPlugin {

	public final static HashMap<String,Integer> counts = new HashMap<>();
	
	
	@Override
	public void onDisable() {
		
		System.out.println("[Criminal Records] CR v"
				+ getDescription().getVersion() + " disabled!");
				
		
		saveConfig();
		
	}
	
	@Override
	public void onEnable() {
		
		loadConfiguration();		
						
		System.out.println("[Criminal Records] CR v"
				+ getDescription().getVersion() + " Enabled!");
		
	}
	
	
	public void loadConfiguration() {
		
		
	    if (!getConfig().contains("Players")) {
		String[] players43 = {};
		
		getConfig().addDefault("Players",
				Arrays.asList(players43));
		
	    }
	    
		int list056;
		list056 = 0;

		int limit;
		limit = getConfig().getStringList("Players").size();
		while (limit > list056) {
			
			String loadplayer = getConfig().getStringList(
					"Players").get(list056);
			
			int playerammount = getConfig().getInt("CR." + loadplayer + ".Ammount");
			
			CriminalRecords.counts.put(loadplayer, playerammount);
			
			list056++;
		}
	    
	    
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	    if (!getConfig().contains("cfg")) {
		String[] config = {};
		
		getConfig().addDefault("cfg",
				Arrays.asList(config));
		
	    }
	    
		int list055;
		list055 = 0;

		int limit2;
		limit2 = getConfig().getStringList("cfg").size();
		while (limit2 > list055) {
			
			String loadplayer = getConfig().getStringList(
					"cfg").get(list055);
			
			int playerammount = getConfig().getInt("CR." + loadplayer + ".Ammount");
			
			CriminalRecords.counts.put(loadplayer, playerammount);
			
			list055++;
		}
	    
	    
		getConfig().options().copyDefaults(true);
		saveConfig();
		
	}
	
	
    @Override
	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String args[]) {
		String commandName = command.getName().toLowerCase();
		final CommandSender player;
		if (sender instanceof CommandSender) {
			player = (CommandSender) sender;
		} else {
			return true;
		}
		
		if (commandName.equals("cr")) {

			if (args.length == 0) {
				if(player.hasPermission("criminal.menu")){
					player.sendMessage(ChatColor.WHITE + "----" + ChatColor.GREEN + "Criminal Records" + ChatColor.WHITE + "----");
					player.sendMessage(ChatColor.GREEN + "/Cr add <name>");
					player.sendMessage(ChatColor.GREEN + "/Cr view <name>");
					player.sendMessage(ChatColor.GREEN + "/Cr clear <name>");
				}else{
					player.sendMessage(ChatColor.RED + "DONT HAVE ACCESS!");
				}

			}

			if (args.length == 1) {
				if(player.hasPermission("criminal.menu")){
					player.sendMessage(ChatColor.WHITE + "----" + ChatColor.GREEN + "Criminal Records" + ChatColor.WHITE + "----");
					player.sendMessage(ChatColor.GREEN + "/Cr add <name>");
					player.sendMessage(ChatColor.GREEN + "/Cr view <name>");
					player.sendMessage(ChatColor.GREEN + "/Cr clear <name>");
				}
			}

			if (args.length == 2) {
				boolean correct1 = false;
				String whatisit1;
				Player targetPlayer = player.getServer().getPlayer(args[1]);
				whatisit1 = args[0];

				if (whatisit1.equalsIgnoreCase("view")) {
					if(player.hasPermission("criminal.menu.view")){
						correct1 = true;
						if(!getConfig().contains("Players." + targetPlayer.getName() + ".Infractions")){
							getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "That player has " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + "!");
						}else{
							getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "That player has " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + "!");
						}
					}else{
						player.sendMessage(ChatColor.RED + "DONT HAVE ACCESS!");
					}
				}


				if (whatisit1.equalsIgnoreCase("clear")) {
					if(player.hasPermission("criminal.menu.clear")){
						correct1 = true;
						
						if (!getConfig().contains("Players." + targetPlayer.getName() + ".Infractions")) {
						    getConfig().addDefault("Players." + targetPlayer.getName() + ".Infractions", 0);
							getConfig().options().copyDefaults(true);
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "You have successfully cleared " + targetPlayer.getName() + "'s account!");
							saveConfig();
						} else {
							getConfig().set("Players." + targetPlayer.getName() + ".Infractions", 0);
							getConfig().options().copyDefaults(true);
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "You have successfully cleared " + targetPlayer.getName() + "'s account!");
							saveConfig();
						}
					}else{
						player.sendMessage(ChatColor.RED + "DONT HAVE ACCESS!");
					}
				}



				if (whatisit1.equalsIgnoreCase("add")) {
					if(player.hasPermission("criminal.menu.add")){
						correct1 = true;
						
						if (!getConfig().contains("Players." + targetPlayer.getName() + ".Infractions")) {
						    getConfig().addDefault("Players." + targetPlayer.getName() + ".Infractions", 1);
						    player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "You have successfully added a infraction to " + targetPlayer.getName() + "'s account!");
						    getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
						    player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "That player now has " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions") + "!");
							getConfig().options().copyDefaults(true);
							saveConfig();
						} else {
							int current = getConfig().getInt("Players." + targetPlayer.getName() + ".Infractions");
							getConfig().set("Players." + targetPlayer.getName() + ".Infractions", current + 1);
							getConfig().options().copyDefaults(true);
							saveConfig();
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "You have successfully added a infraction to " + targetPlayer.getName() + "'s account!");
							getConfig().get("Players." + targetPlayer.getName() + ".Infractions");
							player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.GOLD + "That player now has " + getConfig().getInt("Players." + targetPlayer.getName() +".Infractions"));
						}
					}else{
						player.sendMessage(ChatColor.DARK_PURPLE + "[CR] " + ChatColor.RED + "DONT HAVE ACCESS!");
					}
				}

				if (correct1 == false) {
					if(player.hasPermission("criminal.menu")){
						player.sendMessage(ChatColor.WHITE + "----" + ChatColor.GREEN + "Criminal Records" + ChatColor.WHITE + "----");
						player.sendMessage(ChatColor.GREEN + "/Cr add <name>");
						player.sendMessage(ChatColor.GREEN + "/Cr view <name>");
						player.sendMessage(ChatColor.GREEN + "/Cr clear <name>");
					}
				}

			}

		}
		return false;

	}  
    

}