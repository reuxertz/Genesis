package com.reuxertz.genesis.entities;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.handlers.packets.PacketOrganismNBT;
import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.registry.RegistryObject;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public abstract class EntityOrganism extends EntityCreature implements IOrganismContainer, IEntityAdditionalSpawnData
        //,IEntityAdditionalSpawnData
{
    protected Organism organism;
    protected RegistryObject registryObject;

    public World getWorld() {
        return this.world;
    }
    public Organism getOrganism() {
        return organism;
    }
    public RegistryObject getRegistryObject() {
        return registryObject;
    }
    public abstract String getRegistryName();

    public EntityOrganism(World world, RegistryObject registryObject, String subspecies, Double age, Double mass)
    {
        super(world);
        NBTTagCompound nbt = this.getEntityData();
        this.registryObject = registryObject;
//
//        String name = registryObject.name;
//        if (!(subspecies == null || subspecies == ""))
//            name += "_" + subspecies;

        if (this.registryObject != null) {
            Genome genome = SpeciesRegistry.getSpeciesGenome(this.registryObject.name);
            organism = new Organism(this.registryObject.name, genome, age, mass);
            organism.setOrganismContainer(this);
        }
        return;
    }

    @Override
    public void onEntityUpdate()
    {
        super.onEntityUpdate();

        if (world.isRemote)
            return;

        organism.tick(world);
    }

    public void handleGrowth()
    {
        return;
    }
    public boolean handleReproduction()
    {
        return false;
    }
    public void refreshState()
    {
        return;
    }

    public void writeSpawnData(ByteBuf buffer) {

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeEntityToNBT(nbtTagCompound);

        if (registryObject == null)
            registryObject = Genesis.registry.getRegistryObject(nbtTagCompound.getString("name"));

        nbtTagCompound.setString("name", registryObject.name);
        ByteBufUtils.writeTag(buffer, nbtTagCompound);
    }
    public void readSpawnData(ByteBuf additionalData) {
        NBTTagCompound nbtTagCompound = ByteBufUtils.readTag(additionalData);
        readEntityFromNBT(nbtTagCompound);
        registryObject = Genesis.registry.getRegistryObject(nbtTagCompound.getString("name"));
    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound = organism.writeToNBT(compound);

        super.writeEntityToNBT(compound);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        readOrganismFromNBT(compound);
    }
    public void readOrganismFromNBT(NBTTagCompound compound)
    {
        organism = Organism.readFromNBT(this, compound);
//        if (organism == null) {
//            Genome genome = SpeciesRegistry.getSpeciesGenome(this.getName());
//            organism = new Organism(this.getName(), genome);
//            organism.writeToNBT(compound);
//        }
        //NetworkHandler.sendToPlayersInWorld(world, new PacketOrganismNBT(this));
}



}
