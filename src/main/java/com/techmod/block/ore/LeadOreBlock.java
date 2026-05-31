package com.techmod.block.ore;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LeadOreBlock extends Block {
    public LeadOreBlock() {
        super(BlockBehaviour.Properties.of().strength(4.0F, 5.0F).requiresCorrectToolForDrops());
    }
}
