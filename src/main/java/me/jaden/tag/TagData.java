package me.jaden.tag;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by Jaden on 7/2/2015.
 */
public class TagData {

    private boolean it;
    private boolean canTag;
    private int tags;

    public TagData() {
        this.it = false;
        this.canTag = false;
        this.tags = 0;
    }

    public boolean isIt() {
        return this.it;
    }

    public boolean isCanTag() {
        return this.canTag;
    }

    public void tagged() {
        this.it = true;

        Tag.instance.getServer().getScheduler().scheduleSyncDelayedTask(Tag.instance, new Runnable() {

            public void run() {
                canTag = true;
            }

        }, 20 * 3);
    }

    public void unTag() {
        this.it = false;
        this.canTag = false;
        this.tags++;
    }

    public void setIt(boolean it) {
        this.it = it;
    }

    public int getTags() {
        return this.tags;
    }

    public void spend(int amt) {
        this.tags -= amt;
    }
}
