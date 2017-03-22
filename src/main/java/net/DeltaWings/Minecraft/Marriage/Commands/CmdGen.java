package net.DeltaWings.Minecraft.Marriage.Commands;

import net.DeltaWings.Minecraft.Marriage.Custom.Config;
import org.bukkit.entity.Player;

/**
 * Created by Delta Wings on 22/03/2017 at.00:52
 */
public class CmdGen {
	private Config c = new Config("", "config");
	private Config d = new Config("", "data");


	public void sendMessage(Player Player, String Message) {
		Player.sendMessage((c.getString("prefix") + Message).replace("&", "§"));
	}

	public Boolean isMarried(String Player1, String Player2) {
		return d.getString(Player1 + ".marry").equalsIgnoreCase(Player2);
	}
}
