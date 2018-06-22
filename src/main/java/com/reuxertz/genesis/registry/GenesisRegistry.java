package com.reuxertz.genesis.registry;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.api.blocks.BaseBlock;
import com.reuxertz.genesis.api.blocks.BaseBlockMetal;
import com.reuxertz.genesis.api.blocks.BaseBlockOre;
import com.reuxertz.genesis.api.items.BaseIngot;
import com.reuxertz.genesis.api.items.BaseNugget;
import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.blocks.BaseBlockGrowable;
import com.reuxertz.genesis.items.BaseCropSeed;
import com.reuxertz.genesis.api.items.BaseItem;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
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

import static com.reuxertz.genesis.Genesis.registry;

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
    public RegistryObject registerItem(String name)
    {
        return registerContent(new RegistryObject(registry, modId, name, new BaseItem(name)));
        
    }
    public RegistryObject registerItem(String name, Item item)
    {
        return registerContent(new RegistryObject(registry, modId, name, item));
    }

    //Environment
    public RegistryObject registerOre(String name)
    {
        return registerContent(new RegistryObject(registry, modId, "ore_" + name, new BaseBlockOre("ore_" + name)));
        
    }
    public RegistryObject registerMetal(String name)
    {
        return this.registerMetal(name, true, true, true);
    }
    public RegistryObject registerMetal(String name, boolean isAlloy, boolean hasNuggetIngot, boolean enableArmorSet)
    {
        RegistryObject result;
        if (!isAlloy) {
            registerOre(name);
        }

        result = registerMetalBlock(name);

        if (hasNuggetIngot)
        {
            result = registerIngot(name);
            registerNugget(name);
        }

        if (enableArmorSet) {
            registerContent(new RegistryObject(registry, modId, "chain_" + name, new BaseItem("chain_" + name)));
            registerArmorSet(name);
        }

        return result;
    }
    public RegistryObject registerMetalBlock(String name)
    {
        return registerContent(new RegistryObject(registry, modId, "block_" + name, new BaseBlockMetal("block_" + name)));
        
    }
    public RegistryObject registerIngot(String name)
    {
        return registerContent(new RegistryObject(registry, modId, "ingot_" + name, new BaseIngot("ingot_" + name)));
        
    }
    public RegistryObject registerNugget(String name)
    {
        return registerContent(new RegistryObject(registry, modId, "nugget_" + name, new BaseNugget("nugget_" + name)));
        
    }

    //Player
    public RegistryObject registerArmor(String name, String type)
    {
        if (type != null)
            type = type + "_";

        if (type == null)
            type = "";


        RegistryObject result = registerContent(new RegistryObject(registry, modId, "chestplate_" + type + name, new BaseBlockMetal("chestplate_" + name)));
        registerContent(new RegistryObject(registry, modId, "boots_" + type + name, new BaseBlockMetal("boots_" + name)));
        registerContent(new RegistryObject(registry, modId, "leggings_" + type + name, new BaseBlockMetal("leggings_" + name)));
        registerContent(new RegistryObject(registry, modId, "helmet_" + type + name, new BaseBlockMetal("helmet_" + name)));

        return result;
    }
    public RegistryObject registerArmorSet(String name)
    {
        RegistryObject result = registerArmor(name, null);
        registerArmor(name, "chain");
        registerArmor(name, "studded");

        return result;
    }

    //Plants
    public RegistryObject registerCrop(String name)
    {
        BaseBlockGrowable blockCrop = new BaseBlockGrowable("crop_" + name);
        BaseCropSeed crop = new BaseCropSeed(name, blockCrop);
        BaseCropSeed seed = new BaseCropSeed("seed_" + name, blockCrop);

        RegistryObject result = registerContent(new RegistryObject(registry, modId, name, crop));
        registerContent(new RegistryObject(registry, modId, "seed_" + name, seed));

        registerContent(new RegistryObject(registry, modId, "crop_" + name, blockCrop));

        seed.setBlockCrop(blockCrop);
        crop.setBlockCrop(blockCrop);
        blockCrop.setSeed(seed).setCrop(crop);

        return result;
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
//    public RegistryObject registerSpecies(String name, List<SpeciesFeature> speciesData, List<GeneData> geneData)
//    {
//        SpeciesRegistry.registerSpecies(name, speciesData, geneData);
//        
//    }
}
