package com.reuxertz.genesis.render;

import com.reuxertz.genesis.entities.EntityGenesisAnimal;
import com.reuxertz.genesisAPI.organics.GeneData;
import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerGenesisLiving<E extends EntityGenesisAnimal> implements LayerRenderer<E>
{
    private final RenderGenesisLiving renderer;
    private final String layerName;
    private final RegistryObject.LayerResourceLocation resourceLocation;

    public int getZIndex()
    {
        return resourceLocation.zIndex;
    }

    public LayerGenesisLiving(RenderGenesisLiving renderer, String layerName, RegistryObject.LayerResourceLocation resourceLocation)
    {
        this.renderer = renderer;
        this.layerName = layerName;
        this.resourceLocation = resourceLocation;
    }

    @Override
    public void doRenderLayer(EntityGenesisAnimal entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale)
    {
        if (!entity.isInvisible())
        {
            RegistryObject.LayerResourceLocation texture = resourceLocation;
            if (texture != null)
            {
                ITextureObject textureObject = Minecraft.getMinecraft().getTextureManager().getTexture(texture);
                if (textureObject != TextureUtil.MISSING_TEXTURE)
                {
                    Organism organism = entity.getOrganism();

                    if (organism == null)
                        return;

                    Genome genome = entity.getOrganism().getGenome();
                    if (genome == null)
                        return;

                    GeneData geneData = entity.getOrganism().getGenome().getGene(layerName + "layer");

                    if (geneData != null) {
                        double red = geneData.values.get(0);
                        double green = geneData.values.get(1);
                        double blue = geneData.values.get(2);
                        GlStateManager.color((float)red, (float)green, (float)blue);
                    }

                    this.renderer.bindTexture(texture);
                    this.renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
                    this.renderer.setLightmap(entity);



                    GlStateManager.color(1f, 1f, 1f);
                }
            }
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}
