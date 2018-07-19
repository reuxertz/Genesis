package com.reuxertz.genesis.render;

import com.reuxertz.genesisAPI.internal.GenesisAPI;
import com.reuxertz.genesisAPI.registry.RegistryObject;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;

import java.util.ArrayList;
import java.util.List;

public abstract class RenderGenesisLiving extends RenderLiving {

    public RenderGenesisLiving(RenderManager renderManager, String name, ModelBase modelBase, float size)
    {
        super(renderManager, modelBase, size);

        RegistryObject regobj = GenesisAPI.registry.getRegistryObject(name);
        List<LayerGenesisLiving> layers = new ArrayList<>();

        List<String> keys = new ArrayList<>(regobj.entityLayerResourceMap.keySet());
        for (int i = 0; i < keys.size(); i++)
        {
            String key = keys.get(i);
            RegistryObject.LayerResourceLocation resourceLocation = regobj.entityLayerResourceMap.get(key);

            LayerGenesisLiving layerGenesisLiving = new LayerGenesisLiving(this, key, resourceLocation);

            if (layers.size() == 0) {
                layers.add(layerGenesisLiving);
                continue;
            }

            for (int j = 0; j < layers.size(); j++) {
                if (layerGenesisLiving.getZIndex() <= layers.get(j).getZIndex()) {
                    layers.add(j, layerGenesisLiving);
                break;
            }
                layers.add(layerGenesisLiving);
                break;
            }
        }

        for (int i = 0; i < layers.size(); i++)
            addLayer(layers.get(i));
    }




}
