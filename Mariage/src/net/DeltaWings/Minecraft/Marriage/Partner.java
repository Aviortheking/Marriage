package net.DeltaWings.Minecraft.Marriage;

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
                 * infos/info
                 * join
                 * sethome/shome
                 * home
                 * delhome/dhome
                 * message/msg
                 * sendgift/gift
                 */
                if (args.length == 1 && (args[0].equalsIgnoreCase("infos") || args[0].equalsIgnoreCase("info"))) {
                    // /partner infos
                    // check si joueur est marrié et si autre joueur en ligne
                        // donner la vie, faim, exp de l'autre joueur
                } else if (args.length == 1 && args[0].equalsIgnoreCase("join")) {
                    // /partner join
                    // check si marrié et si autre joueur est en ligne
                        // tp le joueur a lautre joueur
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("sethome") || args[0].equalsIgnoreCase("shome"))) {
                    // /partner sethome/shome
                    // check si marrie
                        // mettre le point de home
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("home"))) {
                    // /partner home
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("delhome") || args[0].equalsIgnoreCase("dhome"))) {
                    // /partner delhome/dhome
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("message") || args[0].equalsIgnoreCase("msg"))) {
                    // /partner message/msg
                } else if (args.length == 1 && (args[0].equalsIgnoreCase("sendgift") || args[0].equalsIgnoreCase("gift"))) {
                    // /partner sendgift/gift
                }
            }
        }
        return true;
    }
}
