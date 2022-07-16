package ru.mcore.improvedmobs;


import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class AbstractMob implements Listener {
    @Getter
    private final String NAME;

    public AbstractMob(String NAME){
        this.NAME = NAME;
        
    }
}
