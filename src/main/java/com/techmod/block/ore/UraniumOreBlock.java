package com.techmod.block.ore;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;

public class UraniumOreBlock extends Block {
    public UraniumOreBlock() {
        super(BlockBehaviour.Properties.of().strength(4.5F, 6.0F).requiresCorrectToolForDrops().lightLevel(state -> 3));
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide && entity instanceof Player player && !player.isCreative() && !player.isSpectator()) {
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 0));
        }

        super.stepOn(level, pos, state, entity);
    }
}
