package com.reuxertz.genesis.api.block;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.registry.BlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseBlock extends Block {

    public boolean isSimple = true;

    public BaseBlock(String name, Material material) {
        this(name, material, CreativeTabs.MISC);
    }
    public BaseBlock(String name, Material material, CreativeTabs tab) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(Genesis.MODID + "." + name);
        setCreativeTab(tab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, normal);
    }
}