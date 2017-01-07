package net.DeltaWings.Minecraft.Marriage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 02/01/2017 at.23:35
 */
public class Partner implements CommandExecutor {

    private Main pl;
    private FileConfiguration config;

    Partner(Main main) {
        this.pl = main;
        this.config = pl.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("partner")) {
                /**
                 * infos/info TODO
                 * join DONE
                 * sethome/shome DONE
                 * home DONE
                 * delhome/dhome DONE
                 * message/msg DONE
                 * sendgift/gift TODO
                 */
                if (args.length == 1 && (args[0].equalsIgnoreCase("infos") || args[0].equalsIgnoreCase("info"))) {
                    // /partner infos
					p.sendMessage("Work in Progress");
                } else if (args.length == 1 && args[0].equalsIgnoreCase("join")) {
                    // /partner join
                    String partner = config.getString("partners."+p.getName()+".who");
                    if (!partner.equalsIgnoreCase("none")) {
                        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                            if (player.getName().equalsIgnoreCase(partner)) {
                                p.teleport(player);
                                // message tp to
                                p.sendMessage(config.getString("messages.tp-to").replace("&", "§"));
	                            // message tp from
                                player.sendMessage(config.getString("messages.tp-from").replace("&", "§"));
                            }
                        }
                    }
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("sethome") || args[0].equalsIgnoreCase("shome"))) {
                    // /partner sethome/shome

                    String partner = config.getString("partners."+p.getName()+".who");

                    if (!partner.equalsIgnoreCase("none")) {




	                    String playerPath = "partner."+p.getName()+".home";

                        if (!config.isSet("partner."+p.getName()+".home")) {
                            config.createSection(playerPath+".home");
                            config.createSection(playerPath+".home.x");
                            config.createSection(playerPath+".home.y ");
                            config.createSection(playerPath+".home.z");
                            config.createSection(playerPath+".home.world");
                        }

	                    Location playerLoc = p.getLocation();

                        config.set(playerPath+".home.x", playerLoc.getX());
                        config.set(playerPath+".home.y", playerLoc.getY());
                        config.set(playerPath+".home.z", playerLoc.getZ());
                        config.set(playerPath+".home.world", playerLoc.getWorld());

	                    String partnerPath = "partner."+partner+".home";

                        if (!config.isSet(partnerPath)) {
                            config.createSection(partnerPath);
                            config.createSection(partnerPath+".x");
                            config.createSection(partnerPath+".y ");
                            config.createSection(partnerPath+".z");
                            config.createSection(partnerPath+".world");
                        }
                        config.set(partnerPath+".x", playerLoc.getX());
                        config.set(partnerPath+".y", playerLoc.getY());
                        config.set(partnerPath+".z", playerLoc.getZ());
                        config.set(partnerPath+".world", playerLoc.getWorld());

                        //message home mis
                        p.sendMessage(config.getString("messages.sethome"));
	                    pl.saveConfig();
                    }
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("home"))) {
                    // /partner home
					if ((!config.getString("partners."+p.getName()+".who").equalsIgnoreCase("none")) && config.isSet("partner."+p.getName()+".home")) {
						// teleport
						String playerPath = "partner."+p.getName()+".home";
						p.teleport(new Location(Bukkit.getServer().getWorld(config.getString(playerPath+".world")), config.getDouble(playerPath+".x"), config.getDouble(playerPath+".y"), config.getDouble(playerPath+".z")));
						// message joueur tp home
						p.sendMessage(config.getString("messages.tp-home"));
					} else if (!config.getString("partners."+p.getName()+".who").equalsIgnoreCase("none")) {
						// pas marrié
						p.sendMessage(config.getString("messages.notMarried"));
					}
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("delhome") || args[0].equalsIgnoreCase("dhome"))) {
                    // /partner delhome/dhome
	                if (!config.getString("partners."+p.getName()+".who").equalsIgnoreCase("none") && config.isSet("partner."+p.getName()+".home")) {
		                config.set("partner."+p.getName()+".home", null);
		                config.set("partner."+config.getString("partner."+p.getName()+".who")+".home", null);
		                pl.saveConfig();
		                // message home supprimé
		                p.sendMessage("messages.homedeleted");
	                } else if (!config.isSet("partner."+p.getName()+".home")) {
		                // tu n'as pas de home
		                p.sendMessage(config.getString("messages.noHome"));
	                } else {
	                	//tu n'es pas marrié
	                	p.sendMessage(config.getString("messages.notMarried"));
	                }
                } else if (args.length > 1 && (args[0].equalsIgnoreCase("message") || args[0].equalsIgnoreCase("msg"))) {
                    // /partner message/msg
	                if (config.getString("partners."+p.getName()+".who").equalsIgnoreCase("none")) {
	                	for (Player player : Bukkit.getServer().getOnlinePlayers()) {
	                		if (player.getName().equalsIgnoreCase(config.getString("partner."+p.getName()+".who"))) {
	                			String msg = "";
	                			for (int a = 1; args.length-1 < a; a++) {
	                				msg += " "+args[a];
				                }
				                // message send message to other player
	                			player.sendMessage(msg.replace("&", "§"));
			                }
		                }
	                }
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("sendgift") || args[0].equalsIgnoreCase("gift"))) {
                    // /partner sendgift/gift
	                p.sendMessage("Work In Progress");
                } else {
					// message miss-spell
	                p.sendMessage(config.getString("messages.miss-spell").replace("&", "§"));
                }
            }
        }
        return true;
    }
}