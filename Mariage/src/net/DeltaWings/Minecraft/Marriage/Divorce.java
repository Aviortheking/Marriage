package net.DeltaWings.Minecraft.Marriage;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 09/01/2017 at.20:35
 */
public class Divorce implements CommandExecutor {

	private Main pl;
	private FileConfiguration config;

	public Divorce(Main main) {
		this.pl = main;
		this.config = pl.getConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if(cmd.getName().equalsIgnoreCase("divorce")) {
			if (sender instanceof Player ) {
				Player p = (Player)sender;
				if ( pl.hasPermission(cmd, args, p) ) {
					p.sendMessage( config.getString("messages.divorced".replace("&", "£").replace("%otherplayer%", config.getString("partners."+p.getName()+".who"))));
					for ( Player player : Bukkit.getServer().getOnlinePlayers() ) {
						if ( player.getName().equalsIgnoreCase(config.getString( "partners."+p.getName()+".who")) ) player.sendMessage(config.getString("messages.other-divorced".replace("&", "£").replace("%otherplayer%", config.getString(p.getName()))));
					}
					config.set("partners."+config.getString( "partners."+p.getName()+".who")+".who", "none");
					config.set("partners."+config.getString( "partners."+p.getName()+".who")+".home", null);
					config.set("partners."+p.getName()+".who", "none");
					config.set("partners."+p.getName()+".home", null);
					pl.saveConfig();
				}
			}
		}
		return false;
	}
}
