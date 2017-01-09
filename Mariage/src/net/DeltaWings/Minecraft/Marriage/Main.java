package net.DeltaWings.Minecraft.Marriage;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Delta Wings on 19/12/2016 at.18:15
 */
public class Main extends JavaPlugin implements Listener{

    public void onEnable(){
        getCommand("Marry").setExecutor(new Marry(this));
        getCommand("Partner").setExecutor(new Partner(this));
        getCommand("divorce").setExecutor(new Divorce(this));

        //load the config
        getConfig().options().copyDefaults(true);
        saveConfig();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
    }

    public void onDisable() {

    }

    public Boolean hasPermission(Command command, String[] arguments, Player player) {
    	Command cmd = command;
    	String[] args = arguments;
    	Player p = player;
	    List<String> perms = new ArrayList<>();
    	perms.add("deltawings.marriage.all");
    	// divorce command
    	if( cmd.getName().equalsIgnoreCase("divorce") ) perms.add("deltawings.marriage.divorce");
	    // marry command
    	if( cmd.getName().equalsIgnoreCase("marry") ) perms.add("deltawings.marriage.marry");
	    //partner command
	    if( cmd.getName().equalsIgnoreCase("partner") ) {
		    perms.add("deltawings.marriage.partner.all");
		    if ( args[0].equalsIgnoreCase("info") ) perms.add("deltawings.marriage.info");
		    if ( args[0].equalsIgnoreCase("join") ) perms.add("deltawings.marriage.join");
		    if ( args[0].equalsIgnoreCase("sethome") ) perms.add("deltawings.marriage.sethome");
		    if ( args[0].equalsIgnoreCase("home") ) perms.add("deltawings.marriage.home");
		    if ( args[0].equalsIgnoreCase("delhome") ) perms.add("deltawings.marriage.delhome");
		    if ( args[0].equalsIgnoreCase("message") ) perms.add("deltawings.marriage.message");
		    if ( args[0].equalsIgnoreCase("gift") ) perms.add("deltawings.marriage.gift");
		    if ( args[0].equalsIgnoreCase("help") ) perms.add("deltawings.marriage.help");
	    }
	    for ( String permission : perms ) if ( p.hasPermission(permission) ) return true;
    	return true;
    }

}
