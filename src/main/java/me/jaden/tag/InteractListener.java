package me.jaden.tag;

import javafx.scene.layout.Priority;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Jaden on 7/3/2015.
 */
public class InteractListener implements Listener {

    @EventHandler (priority = EventPriority.HIGH)
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();

            if (player.getInventory().getItemInHand().getType().equals(Material.POTATO_ITEM)) {
                if (player.getInventory().getItemInHand().getAmount() > 1) {
                    player.getInventory().setItemInHand(new ItemStack(Material.POTATO_ITEM, player.getInventory().getItemInHand().getAmount()-1));
                }
                else {
                    player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5, 1));
                }
                e.setCancelled(true);
            }
        }
    }

}
