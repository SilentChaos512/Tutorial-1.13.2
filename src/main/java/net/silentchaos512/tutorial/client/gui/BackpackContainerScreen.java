package net.silentchaos512.tutorial.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.silentchaos512.tutorial.inventory.BackpackContainer;

public class BackpackContainerScreen extends ContainerScreen<BackpackContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");

    private final PlayerInventory playerInventory;
    private final int inventoryRows;

    public BackpackContainerScreen(BackpackContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.playerInventory = inv;
        this.inventoryRows = screenContainer.getInventoryRows();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(title.getFormattedText(), 8, 6, 4210752);
        this.font.drawString(playerInventory.getDisplayName().getFormattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) return;

        // Render the GUI texture
        GlStateManager.color4f(1, 1, 1, 1);
        minecraft.getTextureManager().bindTexture(TEXTURE);
        int posX = (this.width - this.xSize) / 2;
        int posY = (this.height - this.ySize) / 2;
        // blit(posX, posY, minU, minV, maxU, maxV)
        blit(posX, posY, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
        blit(posX, posY + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
    }
}
