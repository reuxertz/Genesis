package com.reuxertz.genesis.render;

import com.reuxertz.genesis.api.entities.EntityGenesisAnimal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerGenesisLiving<E extends EntityGenesisAnimal> implements LayerRenderer<E>
{
    private final RenderGenesisLiving renderer;
    private final ResourceLocation resourceLocation;

    public LayerGenesisLiving(RenderGenesisLiving renderer, ResourceLocation resourceLocation)
    {
        this.renderer = renderer;
        this.resourceLocation = resourceLocation;
    }

    @Override
    public void doRenderLayer(EntityGenesisAnimal entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale)
    {
        if (!entity.isInvisible())
        {
            ResourceLocation texture = resourceLocation;
            if (texture != null)
            {
                ITextureObject textureObject = Minecraft.getMinecraft().getTextureManager().getTexture(texture);
                if (textureObject != TextureUtil.MISSING_TEXTURE)
                {
                    this.renderer.bindTexture(texture);

                    this.renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
                    this.renderer.setLightmap(entity);
                }
            }
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
