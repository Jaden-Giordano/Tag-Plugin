package me.jaden.tag;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Jaden on 7/2/2015.
 */
public class OnLeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        TagData data = (TagData) Tag.instance.getMetadata(p, "TagData");
        if (data != null) {
            if (data.isIt()) {
                Tag.instance.randomIt();
            }
        }
    }

}
