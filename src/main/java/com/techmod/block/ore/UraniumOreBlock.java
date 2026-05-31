package com.techmod.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class UraniumOreBlock extends Block {
    public UraniumOreBlock() {
        super(BlockBehaviour.Properties.of().strength(4.0F, 5.0F).requiresCorrectToolForDrops());
    }
}
