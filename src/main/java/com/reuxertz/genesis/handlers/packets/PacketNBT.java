package com.reuxertz.genesis.handlers.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.UUID;

public abstract class PacketNBT extends PacketBase {

    public UUID persistentID;
    public NBTTagCompound nbtTagCompound;

    public PacketNBT(UUID persistentID, NBTTagCompound nbtTagCompound)
    {
        this.persistentID = persistentID;
        this.nbtTagCompound = nbtTagCompound;
    }

    public void serialize(PacketBuffer buffer)
    {
        buffer.writeCompoundTag(nbtTagCompound);
    }

    public void deserialize(PacketBuffer buffer) throws IOException
    {
        nbtTagCompound = buffer.readCompoundTag();
    }
}
