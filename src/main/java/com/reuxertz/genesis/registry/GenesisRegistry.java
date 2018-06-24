package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.IEventHandler;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.blocks.BaseBlock;
import com.reuxertz.genesis.api.blocks.BaseBlockMetal;
import com.reuxertz.genesis.api.blocks.BaseBlockOre;
import com.reuxertz.genesis.api.items.BaseIngot;
import com.reuxertz.genesis.api.items.BaseNugget;
import com.reuxertz.genesis.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.items.BaseCropSeed;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.render.RenderGenesisLiving;
import com.reuxertz.genesis.tileentity.TileEntityBaseCrop;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.reuxertz.genesis.mod.Genesis.registry;

@Mod.EventBusSubscriber(modid = Genesis.MODID)
public class GenesisRegistry implements IGenesisRegistry
{
    public interface IRegistryObjectAction {

        void onAction(RegistryObject registryObject);
    }

    private static Map<String, RegistryObject> registryObjectHashMap = new HashMap<>();
    private static Map<Item, String> registryItemHashMap = new HashMap<>();
    private static List<RegistryObject> registryObjectList = new ArrayList<>();
    private static List<String> registeredModIds = new ArrayList<>();

    private final String modId;

    public void iterate(IRegistryObjectAction action)
    {
        for (int i = 0 ; i < registryObjectList.size(); i++)
            action.onAction(registryObjectList.get(i));
    }

    public RegistryObject getRegistryObject(String name)
    {
        return registryObjectHashMap.get(name);
    }
    public RegistryObject getRegistryObject(Item item)
    {
        return registryObjectHashMap.get(registryItemHashMap.get(item));
    }

    public GenesisRegistry(String modId) {
        this.modId = modId;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {

            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

                if (!regobj.autoRegister)
                    continue;

                if (regobj.block != null && regobj.item == null &&
                    regobj.block instanceof BaseBlock && ((BaseBlock)regobj.block).isSimple)
            {
                Block block =(Block)regobj.block;

                ItemBlock bl = new ItemBlock(block);
                bl.setRegistryName(block.getUnlocalizedName());
                bl.setUnlocalizedName(block.getUnlocalizedName());
                regobj.item = bl;
            }

            if (regobj.item != null) {
                Item item = GenesisRegistry.registryObjectList.get(i).item;
                event.getRegistry().register(item);
            }
        }

        return;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        GameRegistry.registerTileEntity(TileEntityBaseCrop.class, "genesis:tileEntityBaseCrop");

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject registryObject = GenesisRegistry.registryObjectList.get(i);

            if (!registryObject.autoRegister)
                continue;

            if (GenesisRegistry.registryObjectList.get(i).block != null) {
                if (GenesisRegistry.registryObjectList.get(i).isBlockRegistered())
                    continue;

                RegistryObject regobj = registry.registryObjectList.get(i);
                event.getRegistry().register((Block) regobj.block);
            }
        }
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject registryObject = GenesisRegistry.registryObjectList.get(i);

            if (!registryObject.autoRegister)
                continue;

            if (registryObject.entityEntry != null) {
//                if (registryObjectList.get(i).isEntityRegistered())
//                    continue;

                RegistryObject regobj = registry.registryObjectList.get(i);
                event.getRegistry().register(regobj.entityEntry);

            }
        }
        return;
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent event)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {

            RegistryObject registryObject = GenesisRegistry.registryObjectList.get(i);
            if (registryObject.entityModelResourceLocation == null)
                continue;

            try {
                IBakedModel teBasic = event.getModelRegistry().getObject(registryObject.entityModelResourceLocation);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void registerEntityRenderers()
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
        {
            RegistryObject regobj = registry.registryObjectList.get(i);

            if (!regobj.autoRegister)
                continue;

            if (regobj.entityEntry != null)
            {
                RenderingRegistry.registerEntityRenderingHandler(regobj.entityEntry.getEntityClass(),
                        manager -> {
                            RenderGenesisLiving renderGenesisLiving = new RenderGenesisLiving(manager, regobj.name, regobj.entityModel, 1f) {
                                @Override
                                protected ResourceLocation getEntityTexture(Entity entity) {
                                    return new ResourceLocation(regobj.modId + ":" + "textures/entities/" + regobj.name + "/" + regobj.name + ".png");
                                }
                        };
                        regobj.setRender(renderGenesisLiving);
                        return renderGenesisLiving;
                    }
                );
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
            GenesisRegistry.registryObjectList.get(i).initModel();

        return;
    }

    public RegistryObject registerContent(RegistryObject registryObject)
    {
        if (registryObjectHashMap.keySet().contains(registryObject.name) ||
            registryObjectHashMap.values().contains(registryObject)  ||
            registryObjectList.contains(registryObject))
            return registryObject;

        registryObjectHashMap.put(registryObject.name, registryObject);
        registryObjectList.add(registryObject);

        if (!registeredModIds.contains(registryObject.modId))
            registeredModIds.add(registryObject.modId);

        if (registryObject.item != null)
            registryItemHashMap.put(registryObject.item, registryObject.name);

        return registryObject;
    }
    public RegistryObject registerItem(String name, Item item)
    {
        return registerContent(new RegistryObject(registry, modId, name, item));
    }
    public RegistryObject registerBlock(String name, Block block) {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, name, block));
    }

    //Entities
    public RegistryObject registerEntity(String name, EntityEntry entityEntry, ModelBase modelBase)
    {
        return registerContent(new RegistryObject(registry, modId, name, entityEntry, modelBase));
        
    }
    public RegistryObject registerEntity(String name, EntityEntry entityEntry, ModelResourceLocation modelResourceLocation)
    {
        return registerContent(new RegistryObject(registry, modId, name, entityEntry, modelResourceLocation));
        
    }

    //Genetics
    public void registerEventHandler(String name, IEventHandler eventHandler)
    {
        EventRegistry.registerEventHandler(name, eventHandler);
    }
    public void registerBreed(String name, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, "", genes);
    }
    public void registerBreed(String name, String subspecies, List<GeneData> genes)
    {
        SpeciesRegistry.registerBreed(name, subspecies, genes);
    }
    public void registerSpecies(String name, List<SpeciesFeature> speciesData)
    {
        SpeciesRegistry.registerSpecies(name, speciesData);

    }

}
