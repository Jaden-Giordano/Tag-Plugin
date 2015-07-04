package me.jaden.tag;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jaden on 7/2/2015.
 */
public class Tag extends JavaPlugin {

    public static Tag instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getLogger().info(this.getName()+ " enabled...");

        this.getServer().getPluginManager().registerEvents(new OnHitListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnDamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new OnLeaveListener(), this);
        this.getServer().getPluginManager().registerEvents(new InteractListener(), this);

        this.getCommand("buy").setExecutor(new BuyExecutor());
        this.getCommand("tags").setExecutor(new TagsExecutor());

        this.getServer().getWorld("Tag").setSpawnLocation(258, 110, 249);

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {
                Collection<? extends Player> players = Tag.instance.getServer().getOnlinePlayers();

                boolean itCheck = false;
                for (Player i : players) {
                    TagData data = (TagData) Tag.instance.getMetadata(i, "TagData");
                    if (data != null) {
                        if (data.isIt()) {
                            i.setDisplayName(ChatColor.RED + "[IT]" + ChatColor.WHITE + i.getName());
                            i.setPlayerListName(ChatColor.RED + "[IT]" + ChatColor.WHITE + i.getName());
                            i.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
                            itCheck = true;
                        } else {
                            i.setDisplayName(ChatColor.BLUE + "[SAFE]" + ChatColor.WHITE + i.getName());
                            i.setPlayerListName(ChatColor.BLUE + "[SAFE]" + ChatColor.WHITE + i.getName());
                            i.getInventory().setHelmet(new ItemStack(Material.AIR));
                        }
                    } else {
                        Tag.instance.setMetadata(i, "TagData", new TagData());
                    }

                    if (i.getLocation().getBlockY() < 98) {
                        i.teleport(new Location(i.getWorld(), 258, 101, 249));
                    }

                    if (i.getInventory().contains(Material.CARROT_ITEM)) {
                        i.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 0));
                    }
                }
                if (!itCheck) {
                    Tag.instance.randomIt();
                }
            }

        }, 10, 10);

        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {
                Tag.instance.getServer().broadcastMessage(ChatColor.DARK_BLUE+"You are playing Tag by "+ChatColor.ITALIC+ChatColor.BLUE+"Greymouth"+ChatColor.RESET+ChatColor.DARK_BLUE+"...");
            }

        }, 20*60, 20*60);
    }

    @Override
    public void onDisable() {
        this.getLogger().info(this.getName() + " disabled...");
    }

    public void reset() {
        Collection<? extends Player> players = this.getServer().getOnlinePlayers();
        for (Player p : players) {
            TagData data = (TagData) this.getMetadata(p, "TagData");
            if (data != null) {
                data.setIt(false);
            }
        }
    }

    public void randomIt() {
        Collection<? extends Player> players = this.getServer().getOnlinePlayers();
        int r = (int) Math.floor(Math.random()*players.size());
        int i = 0;
        for (Player p : players) {
            if (i == r) {
                TagData data = (TagData) this.getMetadata(p, "TagData");
                if (data != null) {
                    this.reset();
                    data.tagged();
                    break;
                }
            }
            i++;
        }
    }

    public void setMetadata(Metadatable object, String key, Object value) {
        object.setMetadata(key, new FixedMetadataValue(Tag.instance, value));
    }

    public Object getMetadata(Metadatable object, String key) {
        List<MetadataValue> values = object.getMetadata(key);
        for (MetadataValue value : values) {
            // Plugins are singleton objects, so using == is safe here
            if (value.getOwningPlugin() == Tag.instance) {
                return value.value();
            }
        }
        return null;
    }

}
