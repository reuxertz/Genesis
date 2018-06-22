package com.reuxertz.genesis.handlers.packets;

import com.reuxertz.genesis.mod.Genesis;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.io.IOException;

public abstract class PacketBase implements IMessage {

    @Override
    public final void toBytes(ByteBuf buffer) {
        serialize(new PacketBuffer(buffer));
    }

    @Override
    public final void fromBytes(ByteBuf buffer) {
        try {
            deserialize(new PacketBuffer(buffer));
        } catch (IOException err) {
            Genesis.logger.catching(err);
        }
    }

    public abstract void serialize(PacketBuffer buffer);

    public abstract void deserialize(PacketBuffer buffer) throws IOException;

    public abstract IMessage process(MessageContext context, EntityPlayer player);
}