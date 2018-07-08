package com.reuxertz.genesis.api.gui;

import com.reuxertz.genesis.api.containers.IGuiContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerBase extends GuiContainer
{
    private ResourceLocation texture;

        public GuiContainerBase(EntityPlayer player, IGuiContainer guiContainer, Object guiContainerObject)
        {
            super(guiContainer.getContainer(player, player.world, guiContainerObject));
            this.texture = guiContainer.getTexture();
        }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        //this.drawTexturedModalRect(x + 77, y + 37, 176, 0, tileEntityContainerBase.getCurrentTime() * 21 / 100, 16);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
