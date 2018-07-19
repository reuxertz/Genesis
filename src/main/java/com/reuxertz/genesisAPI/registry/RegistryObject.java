package com.reuxertz.genesisAPI.registry;

import com.reuxertz.genesisAPI.organics.GeneData;
import com.reuxertz.genesisAPI.organics.Organism;
import com.reuxertz.genesisAPI.organics.SpeciesFeature;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

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

    public boolean isItemRegistered;
    public boolean isBlockRegistered;
    public boolean isModelInitialized;
    public boolean isEntityRegistered;
    public boolean autoRegister = false;
    public Set<RegistryObject> registryGroupSet = new HashSet<>();

    public String name;
    public String modId;
    public IGenesisRegistry registry;

    public EntityEntry entityEntry;
    public Map<String, LayerResourceLocation> entityLayerResourceMap = new HashMap<>();
    public ModelBase entityModel;
    public int primaryEggColor = Color.BLACK.getRGB();
    public int secondaryEggColor = Color.WHITE.getRGB();

    public Block block;
    public Class tileEntityClass;
    public Item item;

    public RegistryObject autoRegister()
    {
        return autoRegister(true);
    }
    public RegistryObject autoRegister(boolean includeGroup)
    {
        this.autoRegister = true;

        if (includeGroup) {
            Object[] groupArray = registryGroupSet.toArray();
            for (int i = 0; i < groupArray.length; i++)
                ((RegistryObject)groupArray[i]).autoRegister(false);
        }

        return this;
    }
    public void groupWith(List<RegistryObject> registryObjects)
    {
        for (int i = 0; i < registryObjects.size(); i++)
            groupWith(registryObjects.get(i));
    }
    public void groupWith(RegistryObject registryObject)
    {
        List<RegistryObject> groupedRegistryObjects = new ArrayList<RegistryObject>();
        groupedRegistryObjects.addAll(this.registryGroupSet);
        groupedRegistryObjects.add(this);

        for (int i = 0; i < groupedRegistryObjects.size(); i++)
        {
            RegistryObject groupedRegistryObject = groupedRegistryObjects.get(i);

            if (!groupedRegistryObject.registryGroupSet.contains(registryObject))
                groupedRegistryObject.registryGroupSet.add(registryObject);
            else
                i = i;


            if (!registryObject.registryGroupSet.contains(groupedRegistryObject))
                registryObject.registryGroupSet.add(groupedRegistryObject);
            else
                i = i;
        }
    }

    public List<RegistryObject> getGroup()
    {
        List<RegistryObject> result = new ArrayList<>();
        result.add(this);
        result.addAll(this.registryGroupSet);
        return result;
    }
    public boolean isItemRegistered()
    {
        return isItemRegistered;
    }
    public boolean isBlockRegistered()
    {
        return isBlockRegistered;
    }
    public boolean isModelInitialized()
    {
        return isModelInitialized;
    }

    public String getUnlocalizedString()
    {
        return modId + "." + name;
    }

    //public void setRender(RenderGenesisLiving renderGenesisLiving) { this.renderGenesisLiving = renderGenesisLiving; }

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

    public RegistryObject registerOverlay(String overlayName, int zIndex)
    {
        RegistryObject.LayerResourceLocation re = new RegistryObject.LayerResourceLocation(modId,  "textures/entities/" + name + "/" + name + "_" + overlayName + ".png", zIndex);
        this.entityLayerResourceMap.put(overlayName, re);

        return this;
    }

    //Events
    public RegistryObject registerEventHandler(IEventHandler eventHandler)
    {
        EventRegistry.registerEventHandler(name, eventHandler);
        return this;
    }

    //Ecosystem
    public RegistryObject registerBreed(String subspecies, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, subspecies, genes);
        return this;

    }
    public RegistryObject registerSpecies(List<SpeciesFeature> speciesData)
    {
        SpeciesRegistry.registerSpecies(name, speciesData);
        return this;

    }
    public RegistryObject registerSpeciesState(String stateName)
    {
        SpeciesRegistry.registerSpeciesState(name, stateName, organism -> { });
        return this;
    }
    public RegistryObject registerSpeciesState(String stateName, Consumer<Organism> stateConstructor)
    {
        SpeciesRegistry.registerSpeciesState(name, stateName, stateConstructor);
        return this;
    }
    public RegistryObject registerSpawnEggColor(int primaryEggColor, int secondaryEggColor)
    {
        this.primaryEggColor = primaryEggColor;
        this.secondaryEggColor = secondaryEggColor;
        return this;
    }

    //Render

}
