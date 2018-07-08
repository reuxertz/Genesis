package com.reuxertz.genesis.api.containers;

import com.reuxertz.genesis.api.containers.slots.SlotBase;
import com.reuxertz.genesis.api.items.ItemBase;
import com.reuxertz.genesis.api.tileentities.TileEntityContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Consumer;

public class ContainerBase extends Container
{
    public EntityPlayer entityPlayer;
    public IGuiContainer guiContainer;
    public Object guiContainerObject;
    public IInventory inventory;

    public NBTTagCompound getNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        if (guiContainer instanceof ItemBase && guiContainerObject instanceof ItemStack)
            nbt = ((ItemStack)guiContainerObject).getTagCompound();
        if (guiContainer instanceof TileEntityContainerBase && guiContainerObject instanceof ItemStack)
            ((TileEntityContainerBase)guiContainerObject).writeToNBT(nbt);

        if (nbt == null)
            nbt = new NBTTagCompound();

        return nbt;
    }
    public void setNBT(Consumer<NBTTagCompound> nbtFunction)
    {
        NBTTagCompound nbt = getNBT();

        nbtFunction.accept(nbt);

        if (guiContainer instanceof ItemBase && guiContainerObject instanceof ItemStack)
            ((ItemStack)guiContainerObject).setTagCompound(nbt);
        if (guiContainer instanceof TileEntityContainerBase && guiContainerObject instanceof ItemStack)
            ((TileEntityContainerBase)guiContainerObject).readFromNBT(nbt);


    }

    public Slot addSlot(Slot slotIn) {

        return this.addSlotToContainer(slotIn);
    }

    public ContainerBase(EntityPlayer entityPlayer)
    {
        this.entityPlayer = entityPlayer;
    }

    public ContainerBase(EntityPlayer entityPlayer, IGuiContainer guiContainer, Object guiContainerObject)
    {
        this(entityPlayer);
        this.guiContainer = guiContainer;
        this.guiContainerObject = guiContainerObject;
        this.inventory = new InventoryBasic("inventory", false, guiContainer.getInventorySize());
        this.construct();

    }

    public void construct()
    {
//        this.addSlotToContainer(new SlotItemHandler(blockEntity.getInventory(), 0, 51, 38));
//        this.addSlotToContainer(new SlotOutput(blockEntity.getInventory(), 1, 109, 38));

        //Container Inventory
        guiContainer.construct(this);

        //Player's stored inventory
        if (guiContainer.hasPlayerInventory())
            for (int y = 0; y < 3; y++)
                for (int x = 0; x < 9; x++) {
                    int index = x + y * 9 + 9;
                    this.addSlotToContainer(new Slot(entityPlayer.inventory, index, 8 + x * 18, 86 + y * 18));
                }

        //Players active inventory
        for (int x = 0; x < 9; x++)
            this.addSlotToContainer(new SlotBase(guiContainer, this, entityPlayer.inventory, x, 8 + x * 18, 144));

        setNBT(nbt -> nbt.setBoolean("isOpen", true));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        setNBT(nbt -> nbt.setBoolean("isOpen", false));



    }

    //Shift Click
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack transferred = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(slotIndex);

        int otherSlots = this.inventorySlots.size() - 36;

        if (slot != null && slot.getHasStack())
        {
            ItemStack current = slot.getStack();
            transferred = current.copy();

            if (slotIndex < otherSlots)
            {
                if (!this.mergeItemStack(current, otherSlots, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(current, 0, otherSlots, false))
            {
                return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            } else
            {
                slot.onSlotChanged();
            }
        }

        return transferred;
    }
}