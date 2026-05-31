package com.techmod.menu;

import com.techmod.CoreTechMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class CoreWorkbenchMenu extends AbstractContainerMenu {
    private static final int CRAFT_GRID_X = 35;
    private static final int CRAFT_GRID_Y = 22;
    private static final int RESULT_X = 125;
    private static final int RESULT_Y = 40;
    private static final int PLAYER_INVENTORY_X = 8;
    private static final int PLAYER_INVENTORY_Y = 102;
    private static final int HOTBAR_Y = 160;
    private static final int CRAFT_SLOT_COUNT = 9;
    private static final int RESULT_SLOT_INDEX = 9;

    private final SimpleContainer craftGrid = new SimpleContainer(CRAFT_SLOT_COUNT);
    private final SimpleContainer resultSlot = new SimpleContainer(1);
    private final BlockPos pos;

    public CoreWorkbenchMenu(int containerId, Inventory playerInventory, BlockPos pos) {
        super(CoreTechMod.CORE_WORKBENCH_MENU.get(), containerId);
        this.pos = pos;
        addCraftingGrid();
        addResultSlot();
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = slots.get(index);
        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = slot.getItem();
        ItemStack copy = sourceStack.copy();
        int playerInventoryStart = RESULT_SLOT_INDEX + 1;
        int playerInventoryEnd = playerInventoryStart + 36;

        if (index < playerInventoryStart) {
            if (!moveItemStackTo(sourceStack, playerInventoryStart, playerInventoryEnd, true)) {
                return ItemStack.EMPTY;
            }
        } else if (!moveItemStackTo(sourceStack, 0, CRAFT_SLOT_COUNT, false)) {
            return ItemStack.EMPTY;
        }

        if (sourceStack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, craftGrid);
        clearContainer(player, resultSlot);
    }

    private void addCraftingGrid() {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 3; ++column) {
                addSlot(new Slot(craftGrid, column + row * 3,
                        CRAFT_GRID_X + column * 18, CRAFT_GRID_Y + row * 18));
            }
        }
    }

    private void addResultSlot() {
        addSlot(new Slot(resultSlot, 0, RESULT_X, RESULT_Y) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                addSlot(new Slot(playerInventory, column + row * 9 + 9,
                        PLAYER_INVENTORY_X + column * 18, PLAYER_INVENTORY_Y + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int column = 0; column < 9; ++column) {
            addSlot(new Slot(playerInventory, column, PLAYER_INVENTORY_X + column * 18, HOTBAR_Y));
        }
    }
}
