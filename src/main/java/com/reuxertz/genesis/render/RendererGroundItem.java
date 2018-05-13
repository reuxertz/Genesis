package com.reuxertz.genesis.render;

import com.reuxertz.genesis.tileentity.TileEntityGroundItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RendererGroundItem extends TileEntitySpecialRenderer {
    public RendererGroundItem() {

    }

    @Override
    public void render(TileEntity tileentity, double x, double y, double z, float f, int d, float a) {
        TileEntityGroundItem te = (TileEntityGroundItem) tileentity;
        ItemStack stack = te.getStack();
        if (stack == null) {
            return;
        }

        GL11.glPushMatrix();

        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GL11.glDisable(GL11.GL_LIGHTING);

        //renderer.setBrightness(15728880); todo

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.translate(x + 0.5, y + 0.025, z + 0.5);
        GlStateManager.rotate(te.getRotation(), 0, 1, 0);
        GlStateManager.scale(1.5F, 1.5F, 1.5F);
        GlStateManager.rotate(90, 1, 0, 0);

        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        renderItem.renderItem(stack, renderItem.getItemModelMesher().getItemModel(stack));

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}