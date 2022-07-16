package ru.mcore.improvedmobs;


import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractMob implements Listener {
    @Getter private final String NAME;
    private final ImprovedMobs plugin;

    private boolean initialized = false;

    public AbstractMob(@NotNull String NAME, @NotNull ImprovedMobs plugin){
        this.NAME = NAME;
        this.plugin = plugin;
    }

    public void initialize(){
        if(this.initialized) {
            return;
        }
        this.initialized = true;

        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }
}
