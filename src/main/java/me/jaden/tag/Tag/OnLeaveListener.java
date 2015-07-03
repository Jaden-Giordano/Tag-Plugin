package me.jaden.tag.Tag;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		GameData data = (GameData) App.instance.getMetadata(p, "gamedata");
		if (data != null) {
			if (data.isIt()) {
				App.instance.randomIt();
			}
		}
	}

}
