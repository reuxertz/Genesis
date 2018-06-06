package com.reuxertz.genesis.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;

public abstract class RenderGenesisLiving extends RenderLiving {

    public RenderGenesisLiving(RenderManager renderManager, ModelBase modelBase, float size)
    {
        super(renderManager, modelBase, size);

        //if(dinosaur.getOverlays()[i].equalsIgnoreCase("mouth"))
        //    this.addLayer(new LayerGenesisLiving(this));
    }




}
