package ru.mcore.improvedmobs;


import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class AbstractMob implements Listener {

    public AbstractMob(){

    }

    protected void lambdaForMobsByEntityType(Consumer<LivingEntity> consumer, EntityType entityType){
        Bukkit.getWorlds().forEach(world -> lambdaForMobsByEntityType(consumer, world, entityType));
    }

    protected void lambdaForMobsByEntityType(Consumer<LivingEntity> consumer, World world, EntityType entityType){
        world.getLivingEntities().stream().filter(entity -> entity.getType()==entityType).forEach(consumer);
    }
}
