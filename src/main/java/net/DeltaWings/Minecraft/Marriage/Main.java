package net.DeltaWings.Minecraft.Marriage;

import net.DeltaWings.Minecraft.Marriage.Custom.Config;
import net.DeltaWings.Minecraft.Marriage.Events.PlayerJoin;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.DeltaWings.Minecraft.Marriage.Commands.*;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

	public static Main getInstance() {
		return instance;
	}

	private static Main instance;

	@Override
	public void onEnable() {
		instance = this;
		// Plugin startup logic
		getCommand("Marry").setExecutor(new Marry(this));
		getCommand("Partner").setExecutor(new Partner(this));
		getCommand("divorce").setExecutor(new Divorce(this));

		//load the config
		getConfig().options().copyDefaults(true);
		saveConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);

		config();
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}

	private void config() {
		Config c = new Config("", "config");
		if(!c.exist()) {
			c.create();
			c.set("prefix", "&8[&bMarriage&8]&f");
		}
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
/*
couples.yml

Aviortheking:
	marry: LePhoenixArdent
	home:
		world:
		x:
		y:
		z:
	requests:
		tepenny: blocked
		seb23100: {time remaining}

fonctionnement:
/marry <(i)invite (a)accept or (h)help or null> <playername or null>
/partner <null or (he)help or (ho)home or (sh, shome)sethome or (dh, dhome)delhome or (tp)teleport>

/divorce <playername or null or (h)help>

/divorce help || /divorce
send message to sender message config.intro
send message to sender message (desc of /marry fonctions)
send message to sneder message config.outro

/divorce player
if sender.marry != player
	send message to sender your are not married with player
else
	set home to null
	set marry to null
	if player is online
		send message to player sender have divorced with you
	send message to sender you have divorced with player



/marry invite player
if player.marry == null
    if player.requests.sender is blocked
        send message to sender you are blocked
    else if player.requests.sender have remaining remaining
        send message to sender time is remaining before you can re ask
    else
        send message to sender you have invited player
        send message to player you have been invited by sender
        set player.invitations.sender time + 60s
else
	send message to sender you are already married



/marry accept player
if sender.invitations.player < time && sender.married == none
	set sender.marry to player
	set sender.requests to null
	set player.marry to sender
	set player.requests to null
	send message to player message you and sender are now married
	send message to sender message you and player are now married
	if config.broadcast
		send message to server message sender and player are now married
else
	send message to sender you don't answered in time or didn't received any request

/marry help || /marry
send message to sender message config.intro
send message to sender message (desc of /marry fonctions)
send message to sender message config.outro

/partner || /partner help
send message to sender message config.intro
send message to sender message (desc of /partner fonctions)
send message to sneder message config.outro

/partner sethome
if sender.home.y is not set
	sender.home.world = sender.world
	sender.home.x = sender.x
	sender.home.y = sender.y
	sender.home.z = sender.z
	etc etc
	sender.marry.home.world = sender.world
	sender.marry.home.x = sender.x
	sender.marry.home.y = sender.y
	sender.marry.home.z = sender.z
	send message to sender && sender.marry your home has been set
else
	send message to sender you already have a home

/partner home
if sender.home is set
	sender.teleport to sender.home
	send message to sender you have been teleported to your home

/partner delhome
if sender.home.y is set
	set sender.home to null
	send message to sender && sender.marry your home has been deleted
else
	send message to sender you don't have any homes

/partner tp
if sender.marry is online
	sender.teleport to sender.marry
else
	send message to sender your partner isn't online
 */
