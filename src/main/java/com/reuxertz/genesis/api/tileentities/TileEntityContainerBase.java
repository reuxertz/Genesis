package com.reuxertz.genesis.api.tileentities;

import com.reuxertz.genesis.api.containers.ContainerBase;
import com.reuxertz.genesis.api.containers.IGuiContainer;
import com.reuxertz.genesis.api.gui.GuiContainerBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityContainerBase extends TileEntityBase implements IGuiContainer
{
    protected ResourceLocation texture;
    protected boolean isOpen = false;

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

        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 9; x++)
                containerBase.addSlot(new Slot(containerBase.inventory, x + y * 9, 8 + x * 18, 18 + y * 18));
    }

    public TileEntityContainerBase(ResourceLocation texture)
    {
        this.texture = texture;
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
        NBTTagCompound nbt = super.writeToNBT(compound);

        //compound.setTag("Inventory", this.inventory.serializeNBT());
        nbt.setBoolean("isOpen", isOpen);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        //this.inventory.deserializeNBT(nbt.getCompoundTag("Inventory"));
        if (nbt.hasKey("isOpen"))
            isOpen = nbt.getBoolean("isOpen");
    }
}
