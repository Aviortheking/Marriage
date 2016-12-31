package net.DeltaWings.Minecraft.Marriage;

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

    private FileConfiguration config;

    Marry(Main main) {
        this.config = main.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(cmd.getName().equalsIgnoreCase("marry")) {
                if(args.length == 1 && !args[0].equalsIgnoreCase("accept")) {
                    Boolean found = false;
                    for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if(player.getName().equalsIgnoreCase(args[0])) {
                            found = true;
                            if(!config.getBoolean("partners."+player.getName()+".married")) {
                                //config messages.proposition
                                player.sendMessage(config.getString("messages.proposition".replace("%sender%",p.getName()).replace("%receiver%", player.getName()).replace("%time%",Integer.toString(config.getInt("config.timeout"))).replace("&", "§")));
                            }
                        }
                    }
                    if(!found) {
                        //send message "Player isn't online or is not correctly typed"
                        p.sendMessage(args[0]+" isn't online or you haven't correctly typed his/her name !");
                    }
                } else if (args.length == 2 && args[0].equalsIgnoreCase("accept")) {
                    if(config.getInt("partners."+p.getName()+".propositions."+args[1]+".timeout", 0) > 0) {
                        //message you have accepted the proposition
                        //bd %couple has been married !
                    } else {
                        //message the other player haven't send a proposition or the proposition is timed out !
                    }
                }
            }
        }
        return true;
    }
}
