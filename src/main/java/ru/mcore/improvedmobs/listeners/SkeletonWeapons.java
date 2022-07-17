package ru.mcore.improvedmobs.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import ru.mcore.improvedmobs.AbstractMob;

import java.util.List;

public class SkeletonWeapons extends AbstractMob {
    private static final double HORIZONTAL_BOX_WIGHT = 12;
    private static final double VERTICAL_BOX_WIGHT = 4;

    public SkeletonWeapons(){ }

    @EventHandler
    public void checkNearPlayers(TickEvent event){
        lambdaForMobsByEntityType(this::processEntity, EntityType.SKELETON);
    }

    private void processEntity(LivingEntity livingEntity){
        List<Entity> nearbyEntities = livingEntity.getNearbyEntities(HORIZONTAL_BOX_WIGHT * 0.5d, VERTICAL_BOX_WIGHT * 0.5d, HORIZONTAL_BOX_WIGHT * 0.5d);

        boolean isPlayerNearby = nearbyEntities.stream().anyMatch(entity -> entity.getType()==EntityType.PLAYER);

        updateItemInHand(livingEntity, isPlayerNearby);
    }

    private void updateItemInHand(LivingEntity livingEntity, boolean isPlayerNearby){
        ItemStack itemStack = livingEntity.getEquipment().getItemInMainHand();
        Material newMaterial = isPlayerNearby ? Material.WOODEN_SWORD : Material.BOW;

        if(itemStack.getType() != newMaterial) {
            livingEntity.getEquipment().setItemInMainHand(new ItemStack(newMaterial), true);
        }
    }
}
