package me.jaden.tag;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;

/**
 * Created by Jaden on 7/2/2015.
 */
public class OnHitListener implements Listener {

    ArrayList<TagContainer> tags = new ArrayList<TagContainer>();

    @EventHandler
    public void OnEntityHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() == e.getEntity())) {
            if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
                Player damager = (Player) e.getDamager();
                Player damaged = (Player) e.getEntity();

                TagData dr_data = (TagData) Tag.instance.getMetadata(damager, "TagData");
                TagData dd_data = (TagData) Tag.instance.getMetadata(damaged, "TagData");

                if (dr_data.isIt()) {
                    if (dr_data.isCanTag()) {
                        tagEvent(damager, damaged);
                        dr_data.unTag();
                        dd_data.tagged();
                        Tag.instance.getServer().broadcastMessage(ChatColor.RED + damaged.getName() + ChatColor.WHITE + " has been tagged you have 3 seconds to " + ChatColor.YELLOW + "RUN" + ChatColor.WHITE + "!!!");
                    } else {
                        damager.sendMessage("You cannot tag anyone until the 3 seconds has passed...");
                    }
                } else {
                    e.setCancelled(true);
                }
            }
            if (e.getDamager() instanceof Arrow && e.getEntity() instanceof Player) {
                Player damager = (Player) ((Arrow) e.getDamager()).getShooter();
                Player damaged = (Player) e.getEntity();

                TagData dr_data = (TagData) Tag.instance.getMetadata(damager, "TagData");
                TagData dd_data = (TagData) Tag.instance.getMetadata(damaged, "TagData");

                if (dr_data.isIt()) {
                    if (dr_data.isCanTag()) {
                        tagEvent(damager, damaged);
                        dr_data.unTag();
                        dd_data.tagged();
                        Tag.instance.getServer().broadcastMessage(ChatColor.RED + damaged.getName() + ChatColor.WHITE + " has been tagged you have 3 seconds to " + ChatColor.YELLOW + "RUN" + ChatColor.WHITE + "!!!");
                    } else {
                        damager.sendMessage("You cannot tag anyone until the 3 seconds has passed...");
                    }
                } else {
                    e.setCancelled(true);
                }
            }

            if (e.getDamager() instanceof FishHook && e.getEntity() instanceof Player) {
                Player damager = (Player) ((FishHook) e.getDamager()).getShooter();
                Player damaged = (Player) e.getEntity();

                TagData dr_data = (TagData) Tag.instance.getMetadata(damager, "TagData");
                TagData dd_data = (TagData) Tag.instance.getMetadata(damaged, "TagData");

                if (dr_data.isIt()) {
                    if (dr_data.isCanTag()) {
                        tagEvent(damager, damaged);
                        dr_data.unTag();
                        dd_data.tagged();
                        Tag.instance.getServer().broadcastMessage(ChatColor.RED + damaged.getName() + ChatColor.WHITE + " has been tagged you have 3 seconds to " + ChatColor.YELLOW + "RUN" + ChatColor.WHITE + "!!!");
                    } else {
                        damager.sendMessage("You cannot tag anyone until the 3 seconds has passed...");
                    }
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    public void tagEvent(Player tagger, Player tagged) {
        for (TagContainer i : tags) {
            if (i.getTagged() == tagger && i.getTagger() == tagged) {
                i.setTagger(tagger);
                i.setTagged(tagged);
                if (i.getTags() > 4) {
                    Tag.instance.getServer().broadcastMessage(tagger.getName()+" has gotten "+ChatColor.BOLD+ChatColor.ITALIC+ChatColor.RED+"REVENGE"+ChatColor.RESET+"!!!");
                }
                i.setTags(1);
                return;
            }
            else if (i.getTagger() == tagger && i.getTagged() == tagged) {
                i.addTag();
                if (i.getTags() > 4) {
                    Tag.instance.getServer().broadcastMessage(tagger.getName() + " is " + ChatColor.BOLD + ChatColor.ITALIC + ChatColor.RED + "DOMINATING " + ChatColor.RESET + tagged.getName()+"!!!");
                }
                return;
            }
        }
        tags.add(new TagContainer(tagger, tagged));
    }

}
