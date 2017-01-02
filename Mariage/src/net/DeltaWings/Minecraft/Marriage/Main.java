package net.DeltaWings.Minecraft.Marriage;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Delta Wings on 19/12/2016 at.18:15
 */
public class Main extends JavaPlugin implements Listener{

    public void onEnable(){
        getCommand("Marry").setExecutor(new Marry(this));
        getCommand("Partner").setExecutor(new Partner(this));

        //load the config
        getConfig().options().copyDefaults(true);
        saveConfig();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
    }

    public void onDisable() {

    }

}
