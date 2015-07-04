package me.jaden.tag;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Jaden on 7/2/2015.
 */
public class OnJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Tag.instance.getServer().broadcastMessage(ChatColor.YELLOW+"Welcome "+ChatColor.GREEN+player.getName()+ChatColor.YELLOW+" to Tag!!!");

        Tag.instance.setMetadata(player, "TagData", new TagData());
        player.getInventory().clear();
    }

}
