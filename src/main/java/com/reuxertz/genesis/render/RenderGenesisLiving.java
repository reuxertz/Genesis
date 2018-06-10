package com.reuxertz.genesis.render;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.registry.RegistryObject;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import sun.plugin.javascript.navig4.Layer;

import java.util.Map;

public abstract class RenderGenesisLiving extends RenderLiving {

    public RenderGenesisLiving(RenderManager renderManager, String name, ModelBase modelBase, float size)
    {
        super(renderManager, modelBase, size);

        RegistryObject regobj = Genesis.registry.getRegistryObject(name);
        for (Map.Entry<String, ResourceLocation> entry : regobj.entityLayerResourceMap.entrySet())
        {
            String key = entry.getKey();
            ResourceLocation resourceLocation = entry.getValue();

            LayerGenesisLiving layerGenesisLiving = new LayerGenesisLiving(this, resourceLocation);
            addLayer(layerGenesisLiving);
        }
    }




}
