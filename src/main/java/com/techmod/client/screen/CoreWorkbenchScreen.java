package com.techmod.client.screen;

import com.techmod.menu.CoreWorkbenchMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CoreWorkbenchScreen extends AbstractContainerScreen<CoreWorkbenchMenu> {
    public CoreWorkbenchScreen(CoreWorkbenchMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        imageWidth = 176;
        imageHeight = 184;
        titleLabelX = 8;
        titleLabelY = 6;
        inventoryLabelX = 8;
        inventoryLabelY = 90;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int left = leftPos;
        int top = topPos;

        drawPanel(graphics, left, top, imageWidth, imageHeight, 0xFF182024, 0xFF2B3539);
        graphics.fill(left + 6, top + 16, left + 170, top + 82, 0xFF101619);
        graphics.fill(left + 8, top + 18, left + 168, top + 80, 0xFF222C30);
        graphics.fill(left + 8, top + 84, left + 168, top + 85, 0xFF526469);

        drawCraftingGrid(graphics, left + 34, top + 21);
        drawArrow(graphics, left + 93, top + 42);
        drawSlot(graphics, left + 124, top + 39, 0xFF45D3C9);

        drawInventoryFrame(graphics, left + 7, top + 101, 9, 3);
        drawInventoryFrame(graphics, left + 7, top + 159, 9, 1);
    }

    private void drawCraftingGrid(GuiGraphics graphics, int x, int y) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                drawSlot(graphics, x + column * 18, y + row * 18, 0xFF354247);
            }
        }
    }

    private void drawInventoryFrame(GuiGraphics graphics, int x, int y, int columns, int rows) {
        graphics.fill(x - 1, y - 1, x + columns * 18 + 1, y + rows * 18 + 1, 0xFF11181B);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                drawSlot(graphics, x + column * 18, y + row * 18, 0xFF2D383C);
            }
        }
    }

    private void drawPanel(GuiGraphics graphics, int x, int y, int width, int height, int borderColor, int fillColor) {
        graphics.fill(x, y, x + width, y + height, borderColor);
        graphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, fillColor);
        graphics.fill(x + 2, y + 2, x + width - 2, y + 12, 0xFF344348);
    }

    private void drawSlot(GuiGraphics graphics, int x, int y, int accentColor) {
        graphics.fill(x - 1, y - 1, x + 17, y + 17, 0xFF0D1214);
        graphics.fill(x, y, x + 16, y + 16, 0xFF182023);
        graphics.fill(x + 1, y + 1, x + 15, y + 15, 0xFF273136);
        graphics.fill(x + 2, y + 2, x + 14, y + 14, 0xFF1A2225);
        graphics.fill(x + 2, y + 2, x + 14, y + 3, accentColor);
    }

    private void drawArrow(GuiGraphics graphics, int x, int y) {
        graphics.fill(x, y + 5, x + 22, y + 11, 0xFF6D8588);
        graphics.fill(x + 17, y, x + 22, y + 16, 0xFF6D8588);
        graphics.fill(x + 22, y + 3, x + 26, y + 13, 0xFF6D8588);
        graphics.fill(x + 26, y + 6, x + 30, y + 10, 0xFF45D3C9);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
