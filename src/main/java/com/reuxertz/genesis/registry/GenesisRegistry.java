package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.api.IEventHandler;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.blocks.BaseBlock;
import com.reuxertz.genesis.render.RenderGenesisLiving;
import com.reuxertz.genesis.tileentities.TileEntityBaseCrop;
import com.reuxertz.genesis.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

    public GenesisRegistry() {
    }

    //@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

//        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
//
//            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);
//
//                if (!regobj.autoRegister)
//                    continue;
//
//                if (regobj.block != null && regobj.item == null &&
//                    regobj.block instanceof BaseBlock && ((BaseBlock)regobj.block).isSimple)
//            {
//                Block block =(Block)regobj.block;
//
//                ItemBlock bl = new ItemBlock(block);
//                bl.setRegistryName(block.getUnlocalizedName());
//                bl.setUnlocalizedName(block.getUnlocalizedName());
//                regobj.item = bl;
//            }
//
//            if (regobj.item != null) {
//                Item item = GenesisRegistry.registryObjectList.get(i).item;
//                event.getRegistry().register(item);
//            }
//        }
        registerModItems(event, null);

        return;
    }
    public static void registerModItems(RegistryEvent.Register<Item> event, String modId) {

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {

            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
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

    //@SubscribeEvent
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
    public static void registerModBlocks(RegistryEvent.Register<Block> event, String modId) {

        GameRegistry.registerTileEntity(TileEntityBaseCrop.class, "genesis:tileEntityBaseCrop");

        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject regobj = GenesisRegistry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
                continue;

            if (regobj.block != null) {
                if (regobj.isBlockRegistered())
                    continue;

                event.getRegistry().register((Block) regobj.block);
            }
        }
    }

    //@SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject registryObject = GenesisRegistry.registryObjectList.get(i);

            if (!registryObject.autoRegister)
                continue;

            if (registryObject.entityEntry != null) {
                RegistryObject regobj = registry.registryObjectList.get(i);
                event.getRegistry().register(regobj.entityEntry);

            }
        }
        return;
    }
    public static void registerModEntities(RegistryEvent.Register<EntityEntry> event, String modId)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++) {
            RegistryObject registryObject = GenesisRegistry.registryObjectList.get(i);

            if (registryObject.autoRegister)
                continue;

            if (modId != null && registryObject.modId != modId)
                continue;

            if (registryObject.entityEntry != null) {
                RegistryObject regobj = registry.registryObjectList.get(i);
                event.getRegistry().register(regobj.entityEntry);

            }
        }
        return;
    }


    //@SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

        GenesisRegistry.registerEntityRenderers(null);
        GenesisRegistry.initModels(null);
    }
    @SuppressWarnings("unchecked")
    public static void registerEntityRenderers(String modId)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
        {
            RegistryObject regobj = registry.registryObjectList.get(i);

            if (modId != null && regobj.autoRegister)
                continue;

            if (modId == null && !regobj.autoRegister)
                continue;

            if (modId != null && regobj.modId != modId)
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
                        //regobj.setRender(renderGenesisLiving);
                        return renderGenesisLiving;
                    }
                );
            }
        }
    }
    public static void initModels(String modId)
    {
        for (int i = 0; i < GenesisRegistry.registryObjectList.size(); i++)
            if ((!GenesisRegistry.registryObjectList.get(i).autoRegister && GenesisRegistry.registryObjectList.get(i).modId == modId) ||
                    (GenesisRegistry.registryObjectList.get(i).autoRegister && GenesisRegistry.registryObjectList.get(i).modId == null))
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
    public RegistryObject registerItem(String name, String modId, Item item)
    {
        return registerContent(new RegistryObject(registry, modId, name, item));
    }
    public RegistryObject registerBlock(String name, String modId, Block block) {
        return Genesis.registry.registerContent(new RegistryObject(registry, modId, name, block));
    }

    //Entities
    public RegistryObject registerEntity(String name, String modId, EntityEntry entityEntry, ModelBase modelBase)
    {
        return registerContent(new RegistryObject(registry, modId, name, entityEntry, modelBase));
        
    }
    public RegistryObject registerEntity(String name, String modId, EntityEntry entityEntry, ModelResourceLocation modelResourceLocation)
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
