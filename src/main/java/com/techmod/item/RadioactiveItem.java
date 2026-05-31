package com.techmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RadioactiveItem extends Item {
    public RadioactiveItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (level.isClientSide || !(entity instanceof Player player) || player.isCreative() || player.isSpectator()) {
            return;
        }

        if (level.getGameTime() % 80L != 0L) {
            return;
        }

        int count = stack.getCount();
        int duration = Math.min(160, 40 + count * 4);
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, 0));
        if (count >= 16) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
        }
    }
}
