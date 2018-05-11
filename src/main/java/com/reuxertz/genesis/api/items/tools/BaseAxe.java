package com.reuxertz.genesis.api.items.tools;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.items.IBaseItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseAxe extends ItemAxe implements IBaseItem {

    public BaseAxe(String name, Item.ToolMaterial material, float damage, float speed, CreativeTabs tab)
    {
        super(material, damage, speed);

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
