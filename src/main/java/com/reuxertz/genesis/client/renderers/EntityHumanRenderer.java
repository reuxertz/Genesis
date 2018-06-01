package com.reuxertz.genesis.client.renderers;

import com.reuxertz.genesis.entities.EntityHuman;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityHumanRenderer extends RenderLiving<EntityHuman>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation("genesis:textures/entities/human/human.png");

    public EntityHumanRenderer(RenderManager renderManager)
    {
        super(renderManager, new ModelBiped(), 1.0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityHuman entity)
    {
        return TEXTURES;
    }
}