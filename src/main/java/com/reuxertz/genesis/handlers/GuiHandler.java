package com.reuxertz.genesis.handlers;


import com.reuxertz.genesis.containers.IGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        ItemStack heldItemStack = player.getHeldItem(EnumHand.MAIN_HAND);
        Item heldItem = heldItemStack.getItem();
        if (heldItem instanceof IGuiContainer)
            return ((IGuiContainer)heldItem).getContainer(player, world, heldItemStack);

        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof IGuiContainer)
            return ((IGuiContainer)tileEntity).getContainer(player, world, tileEntity);

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        ItemStack heldItemStack = player.getHeldItem(EnumHand.MAIN_HAND);
        Item heldItem = heldItemStack.getItem();
        if (heldItem instanceof IGuiContainer)
            return ((IGuiContainer)heldItem).getGuiContainer(player, world, heldItemStack);

        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof IGuiContainer)
            return ((IGuiContainer)tileEntity).getGuiContainer(player, world, tileEntity);

        return null;
    }

}