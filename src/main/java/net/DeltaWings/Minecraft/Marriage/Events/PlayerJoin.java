package net.DeltaWings.Minecraft.Marriage.Events;

import net.DeltaWings.Minecraft.Marriage.Main;
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

    public PlayerJoin(Main main) {
        this.pl = main;
        this.config = pl.getConfig();
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String Base = "partners."+p.getName();
        if (!config.isSet(Base)) {
	        config.set(Base+".who", "none");
	        config.createSection(Base+".propositions");
	        pl.saveConfig();
        }
    }
}
