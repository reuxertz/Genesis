package com.reuxertz.gaia.items;

import com.reuxertz.genesis.mod.Genesis;
import com.reuxertz.genesis.api.items.IBaseItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasePickaxe extends ItemPickaxe implements IBaseItem {

    public BasePickaxe(String name, Item.ToolMaterial material, CreativeTabs tab)
    {
        super(material);

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
