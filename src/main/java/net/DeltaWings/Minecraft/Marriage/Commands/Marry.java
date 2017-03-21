package net.DeltaWings.Minecraft.Marriage.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.DeltaWings.Minecraft.Marriage.Main;

/**
 * Created by Floflo on 21/12/2016.
 */
public class Marry implements CommandExecutor {

    private Main pl;
    private FileConfiguration config;

    public Marry(Main main) {
        this.pl = main;
        this.config = pl.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("marry")) {
            if(sender instanceof Player) {
                Player p = (Player)sender;
	            if ( pl.hasPermission(cmd, args, p) ) {
		            // /marry <player> to ask a player to marry him/her
		            if(args.length == 1 && !args[0].equalsIgnoreCase("accept")) {
			            Boolean found = false;
			            for(Player player : Bukkit.getServer().getOnlinePlayers()) {
				            if(player.getName().equalsIgnoreCase(args[0])) {
					            found = true;
					            if(!config.getBoolean("partners."+player.getName()+".married")) {
						            //config messages.proposition
						            if ( config.getBoolean("partners."+player.getName()+".propositions."+p.getName()+".isblocked", false) ) p.sendMessage(config.getString("messages.blocked").replace("&", "§"));
						            else {
						            	config.set("partners."+player.getName()+".propositions", p.getName());
							            pl.saveConfig();
							            player.sendMessage(config.getString("messages.proposition".replace("%sender%",p.getName()).replace("%receiver%", player.getName()).replace("%time%",Integer.toString(config.getInt("config.timeout"))).replace("&", "§")));
						            }
					            }
				            }
			            }
			            if(!found) {
				            //send message "Player isn't online or is not correctly typed"
				            p.sendMessage(args[0]+" isn't online or you haven't correctly typed his/her name !");
			            }
			            // /marry accept <player>
		            } else if (args.length == 2 && args[0].equalsIgnoreCase("accept")) {
			            if (!config.getString("partners." + p.getName() + ".who").equalsIgnoreCase("none"))
				            p.sendMessage((config.getString("messages.already-married").replace("&", "§")));
			            else if (config.getConfigurationSection("partners." + p.getName() + ".propositions").contains(args[1])) {
				            String conf = "partners." + p.getName() + ".";
				            config.set(conf + "who", args[1]);
				            config.set("partners." + args[1] + ".who", p.getName());
				            config.set(conf + "propositions", null);
				            config.set("partners." + args[1] + ".propositions", null);
				            pl.saveConfig();
				            p.sendMessage(config.getString("messages.accepted"));
				            if (config.getBoolean("config.isbroadcasted"))
					            Bukkit.broadcastMessage(config.getString("messages.broadcast").replace("&", "§").replace("%p1%", p.getName()).replace("%p2%", args[1]));
			            } else p.sendMessage(config.getString("messages.no-proposition").replace("%player%", args[1]).replace("&", "§"));
			            //check si marrié
			            //msg t'es déjà marrié sale con !
			            //sinon check si le joueur lui a proposée
			            //message you have accepted the proposition
			            //bd %p1 and %p2 has been married !
			            //sinon message le joueur ne t'a pas proposée de te marrié avc lui/elle
		            } else {
			            p.sendMessage(config.getString("messages.miss-spell").replace("&", "§"));
		            }
	            }
            }
        }
        return true;
    }
}
