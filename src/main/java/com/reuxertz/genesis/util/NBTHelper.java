package com.reuxertz.genesis.util;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NBTHelper {

    public static void writeInventory(NBTTagCompound nbtTagCompound, InventoryBasic inventoryBasic)
    {
        NBTTagCompound inventoryNBT = new NBTTagCompound();

        for (int i = 0; i < inventoryBasic.getSizeInventory(); i++)
        {
            ByteBufUtils.writeItemStack(null, inventoryBasic.getStackInSlot(i));
        }

    }

    public static InventoryBasic readInventory(NBTTagCompound nbtTagCompound, InventoryBasic inventoryBasic)
    {
        return null;
    }

}
