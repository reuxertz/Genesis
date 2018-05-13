package com.reuxertz.genesis.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityGroundItem extends TileEntity {
    private ItemStack stack;
    private int rotation;

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        //this.markDirty();
        this.stack = stack;
        //world.markBlockForUpdate(pos);
    }

//    public void update() {
//        if (this.world.getBlockState(this.getPos().add(0, -1, 0)) == Blocks.AIR.getDefaultState() && !this.world.isRemote) {
//            this.world.setBlockState(this.getPos(), Blocks.AIR.getDefaultState());
//            EntityItem entityItem = new EntityItem(this.world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), this.getStack());
//            this.world.spawnEntity(entityItem);
//        }
//    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        NBTTagCompound item = new NBTTagCompound();

        if (stack != null) {
            stack.writeToNBT(item);
        }

        nbt.setTag("GroundItemBlock", item);
        nbt.setInteger("Rotation", rotation);

        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagCompound item = nbt.getCompoundTag("GroundItemBlock");

        stack = new ItemStack(item);
        rotation = nbt.getInteger("Rotation");
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);

        return new SPacketUpdateTileEntity(pos, this.getBlockMetadata(), compound);
    }

    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.getNbtCompound();
        this.readFromNBT(compound);
    }


}