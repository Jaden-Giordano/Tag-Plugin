package me.jaden.tag;

import org.bukkit.entity.Player;

/**
 * Created by Jaden on 7/3/2015.
 */
public class TagContainer {

    private Player tagger;
    private Player tagged;
    private int amt;

    public TagContainer(Player tagger, Player tagged) {
        this.tagger = tagger;
        this.tagged = tagged;
        this.amt = 1;
    }

    public void addTag() {
        this.amt++;
    }

    public void setTags(int amt) {
        this.amt = amt;
    }

    public int getTags() {
        return this.amt;
    }

    public Player getTagger() {
        return this.tagger;
    }

    public Player getTagged() {
        return this.tagged;
    }

    public void setTagger(Player tagger) {
        this.tagger = tagger;
    }

    public void setTagged(Player tagged) {
        this.tagged = tagged;
    }
}
