package ru.mcore.improvedmobs.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.jetbrains.annotations.NotNull;
import ru.mcore.improvedmobs.ImprovedMobs;

public class TickEvent extends Event implements Listener {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public TickEvent(){
        super();
    }

    @EventHandler
    public void startTaskTimer(PluginEnableEvent event){
        Bukkit.getScheduler().runTaskTimer(ImprovedMobs.getInstance(), () -> {
            Bukkit.getPluginManager().callEvent(new TickEvent());
        }, 1L, 1L);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
