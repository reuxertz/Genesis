package com.reuxertz.fauna;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.GenesisPlugin;
import com.reuxertz.genesis.api.IGenesisPlugin;
import com.reuxertz.genesis.api.IGenesisRegistry;
import com.reuxertz.genesis.entities.EntityHuman;
import net.minecraft.block.material.MapColor;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

import static com.reuxertz.genesis.Genesis.registry;

@Mod(modid = Fauna.MODID, name = Fauna.NAME, version = Fauna.VERSION, dependencies = "required-after:forge@[14.23.3.2655,)", useMetadata = true)
public class Fauna implements IGenesisPlugin
{
    public static final String MODID = "fauna";
    public static final String NAME = "Fauna";
    public static final String VERSION = "1.0";

    @GenesisPlugin
    public Fauna()
    {
        return;
    }

    public String getModID() { return Fauna.MODID; }
    public void register(IGenesisRegistry registry)
    {
//        registry.registerEntity("human",
//                new EntityEntry(EntityHuman.class, "human"));

//        EntityEntry registree = EntityEntryBuilder.create()
//                .entity(EntityHuman.class)
//                .id(new ResourceLocation(Fauna.MODID, "human"), 0)
//                .name("human")
//                .tracker(80, 3, false)
//                .egg(MapColor.BLUE.colorValue, MapColor.YELLOW.colorValue)
//                .spawn(EnumCreatureType.CREATURE, 20, 1, 5, BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
//                .build();


        //registry.registerEntity("human", registree);

        return;
    }
}