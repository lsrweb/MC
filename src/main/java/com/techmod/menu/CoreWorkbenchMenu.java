package com.techmod.menu;

import com.techmod.CoreTechMod;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

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

    private final CraftingContainer craftGrid = new TransientCraftingContainer(this, 3, 3);
    private final ResultContainer resultSlot = new ResultContainer();
    private final BlockPos pos;
    private final Player player;

    public CoreWorkbenchMenu(int containerId, Inventory playerInventory, BlockPos pos) {
        super(CoreTechMod.CORE_WORKBENCH_MENU.get(), containerId);
        this.pos = pos;
        this.player = playerInventory.player;
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

        if (index == RESULT_SLOT_INDEX) {
            sourceStack.getItem().onCraftedBy(sourceStack, player.level(), player);
            if (!moveItemStackTo(sourceStack, playerInventoryStart, playerInventoryEnd, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(sourceStack, copy);
        } else if (index < playerInventoryStart) {
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
        if (!player.level().isClientSide) {
            clearContainer(player, craftGrid);
        }
    }

    @Override
    public void slotsChanged(net.minecraft.world.Container container) {
        updateCraftingResult(this, player.level(), player, craftGrid, resultSlot);
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
        addSlot(new ResultSlot(player, craftGrid, resultSlot, 0, RESULT_X, RESULT_Y));
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

    private static void updateCraftingResult(AbstractContainerMenu menu, Level level, Player player,
                                             CraftingContainer craftGrid, ResultContainer resultSlot) {
        if (level.isClientSide) {
            return;
        }

        ServerPlayer serverPlayer = (ServerPlayer) player;
        ItemStack result = ItemStack.EMPTY;
        Optional<CraftingRecipe> recipe = level.getServer().getRecipeManager()
                .getRecipeFor(RecipeType.CRAFTING, craftGrid, level);
        if (recipe.isPresent() && resultSlot.setRecipeUsed(level, serverPlayer, recipe.get())) {
            ItemStack assembled = recipe.get().assemble(craftGrid, level.registryAccess());
            if (assembled.isItemEnabled(level.enabledFeatures())) {
                result = assembled;
            }
        }

        resultSlot.setItem(0, result);
        menu.setRemoteSlot(RESULT_SLOT_INDEX, result);
        serverPlayer.connection.send(new ClientboundContainerSetSlotPacket(
                menu.containerId, menu.incrementStateId(), RESULT_SLOT_INDEX, result));
    }
}
