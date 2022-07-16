package ru.mcore.improvedmobs;

import lombok.Getter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ImprovedMobs extends JavaPlugin {
    private static final String MOB_LISTENERS_PATH = "ru.mcore.improvedmobs.mobs";

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    private void loadMobs(){
        Set<AbstractMob> abstractMobSet = new HashSet<>();

        Reflections reflections = new Reflections(MOB_LISTENERS_PATH);
        Set<Class<?>> modules = reflections.getSubTypesOf(AbstractMob.class).stream().filter(v -> v.getPackage().getName().equals(MOB_LISTENERS_PATH)).collect(Collectors.toSet());

        for (Class<?> cls : modules) {
            try {
                Class<AbstractMob> module = (Class<AbstractMob>) cls;
                AbstractMob mob = module.getDeclaredConstructor(AbstractMob.class).newInstance(this);

                mob.initialize();
            } catch (Exception ex) {
                ex.printStackTrace();
                getLogger().warning(ex.getMessage());
            }
        }
    }
}
