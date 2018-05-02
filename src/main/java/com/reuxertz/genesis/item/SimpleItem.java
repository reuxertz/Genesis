package com.reuxertz.genesis.item;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.api.item.BaseItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SimpleItem extends BaseItem {

    public SimpleItem(String name) {
        super(name, CreativeTabs.MISC);
    }

//    public SimpleItem() {
//        setRegistryName("simpleitem");        // The unique name (within your mod) that identifies this item
//        setUnlocalizedName(Genesis.MODID + ".simpleitem");     // Used for localization (en_US.lang)
//        setCreativeTab(CreativeTabs.MISC);   // the item will appear on the Miscellaneous tab in creative
//    }
//
//    @SideOnly(Side.CLIENT)
//    public void initModel() {
//
//        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
//        ModelLoader.setCustomModelResourceLocation(this, 0, normal);
//    }

//    @SideOnly(Side.CLIENT)
//    public void initModel() {
//        ModelResourceLocation blueModel = new ModelResourceLocation(getRegistryName() + "_blue", "inventory");
//        ModelResourceLocation redModel = new ModelResourceLocation(getRegistryName() + "_red", "inventory");
//
//        ModelBakery.registerItemVariants(this, blueModel, redModel);
//
//        ModelLoader.setCustomMeshDefinition(this, stack -> {
//            if (isBlue(stack)) {
//                return blueModel;
//            } else {
//                return redModel;
//            }
//        });
//    }
}