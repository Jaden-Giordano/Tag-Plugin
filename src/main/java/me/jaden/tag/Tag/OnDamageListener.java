package me.jaden.tag.Tag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamageListener implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}

}
