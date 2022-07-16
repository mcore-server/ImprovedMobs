package ru.mcore.improvedmobs;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ImprovedMobs extends JavaPlugin {
    @Getter private static ImprovedMobs instance;
    private static final String LISTENERS_PATH = "ru.mcore.improvedmobs.listeners";

    @Override
    public void onEnable() {
        instance = this;
        loadListeners();
    }

    @Override
    public void onDisable() {}

    private void loadListeners(){
        Reflections reflections = new Reflections(LISTENERS_PATH);
        Set<Class<?>> modules = reflections.getSubTypesOf(Listener.class).stream().filter(v -> v.getPackage().getName().equals(LISTENERS_PATH)).collect(Collectors.toSet());

        for (Class<?> cls : modules) {
            try {
                Class<Listener> module = (Class<Listener>) cls;
                Listener listener = module.getDeclaredConstructor(Listener.class).newInstance();

                Bukkit.getPluginManager().registerEvents(listener, this);
            } catch (Exception ex) {
                ex.printStackTrace();
                getLogger().warning(ex.getMessage());
            }
        }
    }
}
