package com.reuxertz.gaia.items;

import com.google.common.collect.Sets;
import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.api.items.IBaseItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseHammer extends ItemTool implements IBaseItem {

    public BaseHammer(String name, Item.ToolMaterial material, float damage, float speed, CreativeTabs tab)
    {
        super(damage, speed, material, Sets.newHashSet());

        setRegistryName(name);
        setUnlocalizedName(Genesis.MODID + "." + name);
        setCreativeTab(tab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(this, 0, normal);
    }
}