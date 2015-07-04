package me.jaden.tag;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaden on 7/3/2015.
 */
public class BuyExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("buy")) {
            if (sender instanceof Player) {
                if (args.length < 1) {
                    return false;
                }

                if (args[0].equalsIgnoreCase("potato")) {
                    Player player = (Player) sender;
                    TagData tag = (TagData) Tag.instance.getMetadata(player, "TagData");
                    if (tag.getTags() > 5) {
                        player.sendMessage("You bought a "+ ChatColor.GREEN+"Potato "+ChatColor.WHITE+" for 5 tags.");
                        ItemStack potato = new ItemStack(Material.POTATO_ITEM);
                        ItemMeta im = potato.getItemMeta();
                        List<String> lores = new ArrayList<String>();
                        lores.add(ChatColor.ITALIC + "A quick speed boost!");
                        player.getInventory().addItem(potato);
                        tag.spend(5);
                        return true;
                    }
                    else {
                        player.sendMessage("You do not have enough tags...");
                        return true;
                    }
                }
                else if (args[0].equalsIgnoreCase("bow")) {
                    Player player = (Player) sender;
                    TagData tag = (TagData) Tag.instance.getMetadata(player, "TagData");
                    if (tag.getTags() > 15) {
                        player.sendMessage("You bought a " + ChatColor.GREEN + "Bow " + ChatColor.WHITE + " for 15 tags.");
                        ItemStack bow = new ItemStack(Material.BOW);
                        ItemMeta im = bow.getItemMeta();
                        List<String> lores = new ArrayList<String>();
                        lores.add(ChatColor.ITALIC+"Tag from a distance!");
                        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        bow.setDurability((short) 1);
                        player.getInventory().addItem(bow);
                        tag.spend(15);
                        return true;
                    }
                    else {
                        player.sendMessage("You do not have enough tags...");
                        return true;
                    }
                }
                else {
                    sender.sendMessage("Item "+args[0]+" not found.");
                    return true;
                }
            }
            else if (args[0].equalsIgnoreCase("carrot")) {
                Player player = (Player) sender;
                TagData tag = (TagData) Tag.instance.getMetadata(player, "TagData");
                if (tag.getTags() > 25) {
                    player.sendMessage("You bought a " + ChatColor.GREEN + "Carrot " + ChatColor.WHITE + " for 25 tags.");
                    ItemStack carrot = new ItemStack(Material.CARROT_ITEM);
                    ItemMeta im = carrot.getItemMeta();
                    List<String> lores = new ArrayList<String>();
                    lores.add(ChatColor.ITALIC+"Speed Buff");
                    im.setLore(lores);
                    player.getInventory().addItem(carrot);
                    tag.spend(25);
                    return true;
                }
                else {
                    player.sendMessage("You do not have enough tags...");
                    return true;
                }
            }
            else if (args[0].equalsIgnoreCase("gold")) {
                Player player = (Player) sender;
                TagData tag = (TagData) Tag.instance.getMetadata(player, "TagData");
                if (tag.getTags() > 50) {
                    player.sendMessage("You bought a " + ChatColor.GREEN + "Gold Bar " + ChatColor.WHITE + " for 50 tags.");
                    ItemStack gold = new ItemStack(Material.GOLD_INGOT);
                    ItemMeta im = gold.getItemMeta();
                    List<String> lores = new ArrayList<String>();
                    lores.add(ChatColor.ITALIC+"Congrats your rich...");
                    im.setLore(lores);
                    player.getInventory().addItem(gold);
                    tag.spend(50);
                    return true;
                }
                else {
                    player.sendMessage("You do not have enough tags...");
                    return true;
                }
            }
            else {
                sender.sendMessage("You must be a player.");
                return true;
            }
        }
        return false;
    }

}
