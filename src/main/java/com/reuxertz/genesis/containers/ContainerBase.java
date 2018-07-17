package com.reuxertz.genesis.containers;

import com.reuxertz.genesis.containers.slots.SlotBase;
import com.reuxertz.genesis.items.base.ItemBase;
import com.reuxertz.genesis.tileentities.TileEntityContainerBase;
import com.reuxertz.genesis.util.NBTHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
        if (guiContainer instanceof TileEntityContainerBase && guiContainerObject instanceof ItemStack) {
            ((TileEntityContainerBase) guiContainerObject).readFromNBT(nbt);
            ((TileEntityContainerBase) guiContainerObject).markDirty();
        }

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
        this.construct();

        entityPlayer.inventory.openInventory(entityPlayer);
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
        super.onContainerClosed(playerIn);
        setNBT(nbt -> nbt.setBoolean("isOpen", false));

        if (guiContainerObject instanceof ItemStack)
            setNBT(nbt -> NBTHelper.writeInventory(nbt, inventory));

        if (guiContainerObject instanceof TileEntityContainerBase) {
            TileEntityContainerBase tileEntityContainerBase = ((TileEntityContainerBase) guiContainerObject);
            IBlockState blockState = entityPlayer.world.getBlockState(tileEntityContainerBase.getPos());
            if (!playerIn.world.isRemote) {
                playerIn.world.notifyBlockUpdate(tileEntityContainerBase.getPos(), blockState, blockState, 3);
                playerIn.world.notifyNeighborsOfStateChange(
                        tileEntityContainerBase.getPos(),
                        entityPlayer.world.getBlockState(tileEntityContainerBase.getPos()).getBlock(), true);
            }
        }

        entityPlayer.inventory.closeInventory(entityPlayer);
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