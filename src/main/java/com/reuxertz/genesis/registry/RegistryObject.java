package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.blocks.IBaseBlock;
import com.reuxertz.genesis.api.items.IBaseItem;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.render.LayerGenesisLiving;
import com.reuxertz.genesis.render.RenderGenesisLiving;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import sun.plugin.javascript.navig4.Layer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistryObject {

    public static class LayerResourceLocation extends ResourceLocation
    {
        public int zIndex;

        public LayerResourceLocation(String modID, String resourcePath, int zIndex)
        {
            super(modID, resourcePath);
            this.zIndex = zIndex;
        }
    }

    protected boolean _isItemRegistered;
    protected boolean _isBlockRegistered;
    protected boolean _isModelInitialized;
    protected boolean _isEntityRegistered;
    protected boolean autoRegister = false;

    public String name;
    public String modId;
    public IGenesisRegistry registry;

    public EntityEntry entityEntry;
    public Map<String, LayerResourceLocation> entityLayerResourceMap = new HashMap<>();
    public RenderGenesisLiving renderGenesisLiving;
    public ModelBase entityModel;
    public ModelResourceLocation entityModelResourceLocation;

    public Block block;
    public Class tileEntityClass;

    public Item item;

    public void setAutoRegister(boolean autoRegister)
    {
        this.autoRegister = autoRegister;
    }

    public boolean isItemRegistered()
    {
        return _isItemRegistered;
    }
    public boolean isBlockRegistered()
    {
        return _isBlockRegistered;
    }
    public boolean isModelInitialized()
    {
        return _isModelInitialized;
    }

    public String getUnlocalizedString()
    {
        return modId + "." + name;
    }

    public void setRender(RenderGenesisLiving renderGenesisLiving) { this.renderGenesisLiving = renderGenesisLiving; }

    protected RegistryObject(IGenesisRegistry registry, String modId, String name) {
        this.modId = modId;
        this.name = name;
    }
    public RegistryObject(IGenesisRegistry registry, String modId, String name, Item item)
    {
        this(registry, modId, name);
        this.item = item;

        item.setUnlocalizedName(getUnlocalizedString());
        item.setRegistryName(modId, name);
    }
    public RegistryObject(IGenesisRegistry registry, String modId, String name, Block block)
    {
        this(registry, modId, name);
        this.block = block;
        block.setUnlocalizedName(getUnlocalizedString());
        block.setRegistryName(modId, name);
    }
    public RegistryObject(IGenesisRegistry registry, String modId, String name, Block block, Class tileEntityClass)
    {
        this(registry, modId, name, block);
        this.tileEntityClass = tileEntityClass;
    }
    public RegistryObject(IGenesisRegistry registry, String modId, String name, EntityEntry entry, ModelBase modelbase)
    {
        this(registry, modId, name);
        this.entityEntry = entry;
        this.entityModel = modelbase;
    }
    public RegistryObject(IGenesisRegistry registry, String modId, String name, EntityEntry entry, ModelResourceLocation modelResourceLocation)
    {
        this(registry, modId, name);
        this.entityEntry = entry;
        this.entityModelResourceLocation = modelResourceLocation;
    }

    public RegistryObject autoRegister(boolean autoRegister)
    {
        this.autoRegister = autoRegister;
        return this;
    }
    public RegistryObject registerOverlay(String name, String overlayName, int zIndex)
    {
        RegistryObject.LayerResourceLocation re = new RegistryObject.LayerResourceLocation(modId,  "textures/entities/" + name + "/" + name + "_" + overlayName + ".png", zIndex);
        this.entityLayerResourceMap.put(overlayName, re);

        return this;
    }
    //Ecosystem
    public RegistryObject registerBreed(String name, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, "", genes);
        return this;
    }
    public RegistryObject registerBreed(String name, String subspecies, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, subspecies, genes);
        return this;

    }
    public RegistryObject registerSpecies(String name, List<SpeciesFeature> speciesData)
    {
        SpeciesRegistry.registerSpecies(name, speciesData);
        return this;

    }

    public void initModel()
    {
        if (isModelInitialized())
            return;

        if (item != null && item instanceof IBaseItem)
            ((IBaseItem)item).initModel();

        if (block != null && block instanceof IBaseBlock)
            ((IBaseBlock)block).initModel();

        _isModelInitialized = true;
    }
}
