package me.jaden.tag.Tag;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnHitListener implements Listener {

	@EventHandler
	public void OnEntityHit(EntityDamageByEntityEvent e) {	
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player damager = (Player) e.getDamager();
			Player damaged = (Player) e.getEntity();

			GameData dr_data = (GameData) App.instance.getMetadata(damager, "gamedata");
			GameData dd_data = (GameData) App.instance.getMetadata(damaged, "gamedata");

			if (dr_data.isIt()) {
				dr_data.setIt(false);
				dd_data.tagged();
			}
			else {
				e.setCancelled(true);
			}
		}
	}

}
