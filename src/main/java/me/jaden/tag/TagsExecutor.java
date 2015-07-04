package me.jaden.tag;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Jaden on 7/3/2015.
 */
public class TagsExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("tags")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                TagData data = (TagData) Tag.instance.getMetadata(p, "TagData");
                if (data != null) {
                    p.sendMessage("You have "+ ChatColor.GREEN+data.getTags()+ChatColor.WHITE+" tags.");
                }
            }
            else {
                sender.sendMessage("You must be a player.");
            }
        }
        return false;
    }

}
