package me.jaden.tag.Tag;

import java.util.Collection;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

	public static App instance;

	@Override
	public void onEnable() {
		instance = this;
		this.getLogger().info(this.getName()+ " enabled...");

		this.getServer().getPluginManager().registerEvents(new OnHitListener(), this);
		this.getServer().getPluginManager().registerEvents(new OnDamageListener(), this);

		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			public void run() {
				Collection<? extends Player> players = App.instance.getServer().getOnlinePlayers();

				boolean itCheck = false;
				for (Player i : players) {
					GameData data = (GameData) App.instance.getMetadata(i, "gamedata");
					if (data != null) {
						if (data.isIt()) {
							i.setDisplayName(ChatColor.RED+"IT-"+ChatColor.WHITE+i.getName());
							itCheck = true;
						}
						else {
							i.setDisplayName(i.getName());
						}
					}
					else {
						App.instance.setMetadata(i, "gamedata", new GameData());
					}
				}
				if (!itCheck) {
					App.instance.randomIt();
				}
			}

		}, 5, 5);
	}

	@Override
	public void onDisable() {
		this.getLogger().info(this.getName() + " disabled...");
	}

	public void refresh() {
		Collection<? extends Player> players = this.getServer().getOnlinePlayers();
		for (Player p : players) {
			GameData data = (GameData) this.getMetadata(p, "gamedata");
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
				GameData data = (GameData) this.getMetadata(p, "gamedata");
				if (data != null) {
					this.refresh();
					data.tagged();
					break;
				}
			}
			i++;
		}
	}

	public void setMetadata(Metadatable object, String key, Object value) {
		object.setMetadata(key, new FixedMetadataValue(App.instance, value));
	}

	public Object getMetadata(Metadatable object, String key) {
		List<MetadataValue> values = object.getMetadata(key);  
		for (MetadataValue value : values) {
			// Plugins are singleton objects, so using == is safe here
			if (value.getOwningPlugin() == App.instance) {
				return value.value();
			}
		}
		return null;
	}

}
