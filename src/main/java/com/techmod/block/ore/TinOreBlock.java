package com.techmod.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TinOreBlock extends Block {
    public TinOreBlock() {
        super(BlockBehaviour.Properties.of().strength(3.2F, 3.0F).requiresCorrectToolForDrops());
    }
}
