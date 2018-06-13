package com.reuxertz.genesis.handlers.packets;

import com.reuxertz.genesis.entities.EntityOrganism;
import com.reuxertz.genesis.organics.Organism;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketOrganismNBT extends PacketNBT {

    public PacketOrganismNBT(EntityOrganism entityOrganism)
    {
        super(entityOrganism.getPersistentID(), entityOrganism.getOrganism().writeToNBT(new NBTTagCompound()));
    }

    public IMessage process(MessageContext context, EntityPlayer player)
    {
        if (context.side == Side.CLIENT)
            for (int i = 0; i < player.world.loadedEntityList.size(); i++)
                if (player.world.loadedEntityList.get(i).getPersistentID().equals(persistentID) && player.world.loadedEntityList.get(i) instanceof EntityOrganism)
                    ((EntityOrganism) player.world.loadedEntityList.get(i)).readOrganismFromNBT(nbtTagCompound);

        return null;
    }
}
