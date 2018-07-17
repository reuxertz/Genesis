package com.reuxertz.genesis.tileentities;

import com.reuxertz.genesis.containers.ContainerBase;
import com.reuxertz.genesis.containers.IGuiContainer;
import com.reuxertz.genesis.gui.GuiContainerBase;
import com.reuxertz.genesis.util.NBTHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityContainerBase extends TileEntityBase implements IGuiContainer
{
    protected ResourceLocation texture;
    protected boolean isOpen = false;
    public IInventory inventory;

    @Override
    public int getInventorySize()
    {
        return 27;
    }

    @Override
    public boolean hasPlayerInventory()
    {
        return true;
    }

    @Override
    public void construct(ContainerBase containerBase) {

        containerBase.inventory = inventory;
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 9; x++)
                containerBase.addSlot(new Slot(containerBase.inventory, x + y * 9, 8 + x * 18, 18 + y * 18));
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        return writeToNBT(new NBTTagCompound());
    }

    public TileEntityContainerBase(ResourceLocation texture)
    {
        super();

        this.texture = texture;
        this.inventory = new InventoryBasic("inventory", false, getInventorySize());
    }

    @Override
    public Container getContainer(EntityPlayer player, World world, Object guiContainerObject)
    {
        return new ContainerBase(player, this, guiContainerObject);
    }

    @Override
    public GuiContainer getGuiContainer(EntityPlayer player, World world, Object guiContainerObject)
    {
        return new GuiContainerBase(player, this, guiContainerObject);
    }

    @Override
    public ResourceLocation getTexture()
    {
        return texture;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)    {

        compound.setBoolean("isOpen", isOpen);
        NBTHelper.writeInventory(compound, inventory);

        NBTTagCompound nbt = super.writeToNBT(compound);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        //this.inventory.deserializeNBT(nbt.getCompoundTag("Inventory"));
        if (nbt.hasKey("isOpen"))
            isOpen = nbt.getBoolean("isOpen");
        inventory = NBTHelper.readInventory(nbt, getInventorySize());
    }
}
