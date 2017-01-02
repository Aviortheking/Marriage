package net.DeltaWings.Minecraft.Marriage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Delta Wings on 02/01/2017 at.20:37
 */
public class PlayerJoin implements Listener {

    private Main pl;
    private FileConfiguration config;

    PlayerJoin(Main main) {
        this.pl = main;
        this.config = pl.getConfig();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String Base = "partners."+p.getName();
        if(!p.hasPlayedBefore()) {
            config.createSection(Base);
            config.createSection(Base+".who");
            config.set(Base+".who", "none");
            config.createSection(Base+".propositions");

        } else if (!config.isSet(Base)) {
                config.createSection(Base);
                config.createSection(Base+".who");
                config.set(Base+".who", "none");
                config.createSection(Base+".propositions");
        }
        pl.saveConfig();
    }
}
