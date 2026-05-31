package com.techmod.blockentity;

import com.techmod.CoreTechMod;
import com.techmod.menu.CoreWorkbenchMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CoreWorkbenchBlockEntity extends BlockEntity implements MenuProvider {
    public CoreWorkbenchBlockEntity(BlockPos pos, BlockState state) {
        super(CoreTechMod.CORE_WORKBENCH_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.techmod.core_workbench");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new CoreWorkbenchMenu(containerId, playerInventory, getBlockPos());
    }
}
