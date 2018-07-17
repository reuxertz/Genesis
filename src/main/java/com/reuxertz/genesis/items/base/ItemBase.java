package com.reuxertz.genesis.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements IItemBase {

    public ItemBase(String name) {
        this(name, CreativeTabs.MISC);
    }
    public ItemBase(String name, CreativeTabs tab) {
        super();
        setCreativeTab(tab);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {

        ModelResourceLocation normal = new ModelResourceLocation(getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(this, 0, normal);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float x, float y, float z) {

        return EnumActionResult.FAIL;
    }
}