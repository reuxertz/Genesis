package com.reuxertz.genesisAPI.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

    public static void writeInventory(NBTTagCompound nbtTagCompound, IInventory inventoryBasic)
    {
        NBTTagCompound inventoryNBT = new NBTTagCompound();

        for (int i = 0; i < inventoryBasic.getSizeInventory(); i++)
        {
            ItemStack itemStack = inventoryBasic.getStackInSlot(i);
            NBTTagCompound itemStackNBT = itemStack.serializeNBT();
//            NBTTagCompound itemStackNBT = new NBTTagCompound();
//            itemStack.writeToNBT(itemStackNBT);
            inventoryNBT.setTag("item_" + i, itemStackNBT);
        }

        nbtTagCompound.setTag("inventory", inventoryNBT);
    }

    public static IInventory readInventory(NBTTagCompound nbtTagCompound, int inventorySize)
    {
        if (!nbtTagCompound.hasKey("inventory"))
            return new InventoryBasic("inventory", false, inventorySize);

        NBTTagCompound inventoryNBT = nbtTagCompound.getCompoundTag("inventory");
        InventoryBasic inventoryBasic = new InventoryBasic("inventory", false, inventorySize);

        int i = 0;
        while (true)
        {
            if (inventoryNBT.hasKey("item_" + i))
            {
                NBTTagCompound itemStackNBT = inventoryNBT.getCompoundTag("item_" + i);
                ItemStack itemStack = new ItemStack(itemStackNBT);
                inventoryBasic.setInventorySlotContents(i, itemStack);
                i++;
                continue;
            }
            break;
        }

        return inventoryBasic;
    }

}
