package com.techmod.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class SilverOreBlock extends Block {
    public SilverOreBlock() {
        super(BlockBehaviour.Properties.of().strength(3.8F, 4.0F).requiresCorrectToolForDrops());
    }
}
