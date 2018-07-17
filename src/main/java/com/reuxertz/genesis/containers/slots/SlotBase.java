package com.reuxertz.genesis.containers.slots;

import com.reuxertz.genesis.containers.ContainerBase;
import com.reuxertz.genesis.containers.IGuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SlotBase extends Slot
{
    protected IGuiContainer guiContainer;
    protected ContainerBase container;

    public SlotBase(IGuiContainer guiContainer, ContainerBase container, IInventory inventory, int index, int xPosition, int yPosition)
    {
        super(inventory, index, xPosition, yPosition);
        this.guiContainer = guiContainer;
        this.container = container;
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack)
    {
        return true;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn)
    {
        ItemStack s1 = inventory.getStackInSlot(getSlotIndex());
        if (s1.hasTagCompound() && s1.getTagCompound().getBoolean("isOpen"))
            return false;

        return super.canTakeStack(playerIn);
    }
}