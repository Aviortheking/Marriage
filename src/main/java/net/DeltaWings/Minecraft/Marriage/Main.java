package net.DeltaWings.Minecraft.Marriage;

import net.DeltaWings.Minecraft.Marriage.Events.PlayerJoin;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.DeltaWings.Minecraft.Marriage.Commands.*;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		// Plugin startup logic
		getCommand("Marry").setExecutor(new Marry(this));
		getCommand("Partner").setExecutor(new Partner(this));
		getCommand("divorce").setExecutor(new Divorce(this));

		//load the config
		getConfig().options().copyDefaults(true);
		saveConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	public Boolean hasPermission(Command command, String[] arguments, Player player) {
		List<String> perms = new ArrayList<>();
		perms.add("deltawings.marriage.all");
		// divorce command
		if( command.getName().equalsIgnoreCase("divorce") ) perms.add("deltawings.marriage.divorce");
		// marry command
		if( command.getName().equalsIgnoreCase("marry") ) perms.add("deltawings.marriage.marry");
		//partner command
		if( command.getName().equalsIgnoreCase("partner") ) {
			perms.add("deltawings.marriage.partner.all");
			if ( arguments[0].equalsIgnoreCase("info") ) perms.add("deltawings.marriage.info");
			if ( arguments[0].equalsIgnoreCase("join") ) perms.add("deltawings.marriage.join");
			if ( arguments[0].equalsIgnoreCase("sethome") ) perms.add("deltawings.marriage.sethome");
			if ( arguments[0].equalsIgnoreCase("home") ) perms.add("deltawings.marriage.home");
			if ( arguments[0].equalsIgnoreCase("delhome") ) perms.add("deltawings.marriage.delhome");
			if ( arguments[0].equalsIgnoreCase("message") ) perms.add("deltawings.marriage.message");
			if ( arguments[0].equalsIgnoreCase("gift") ) perms.add("deltawings.marriage.gift");
			if ( arguments[0].equalsIgnoreCase("help") ) perms.add("deltawings.marriage.help");
		}
		for ( String permission : perms ) if ( player.hasPermission(permission) ) return true;
		return true;
	}
}
