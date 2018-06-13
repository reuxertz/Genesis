package com.reuxertz.genesis.entities;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.handlers.NetworkHandler;
import com.reuxertz.genesis.handlers.packets.PacketOrganismNBT;
import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.registry.RegistryObject;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityOrganism extends EntityCreature implements IOrganismContainer
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

    public EntityOrganism(World world) {
        super(world);
        registryObject = Genesis.registry.getRegistryObject(this.getOrganismName());
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


//    public void writeSpawnData(ByteBuf buffer) {
//        //GenomeHelper.validateGenome(getOrganismName(), this.getOrganism().getGenome());
//        NBTTagCompound nbtTagCompound = organism.writeToNBT(new NBTTagCompound());
//        ByteBufUtils.writeTag(buffer, nbtTagCompound);
//    }
//    public void readSpawnData(ByteBuf additionalData) {
//        NBTTagCompound nbtTagCompound = ByteBufUtils.readTag(additionalData);
//    }

    public void writeEntityToNBT(NBTTagCompound compound)
    {
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
        if (organism == null) {
            Genome genome = SpeciesRegistry.getSpeciesGenome(this.getName());
            organism = new Organism(this.getName(), genome);
            organism.writeToNBT(compound);
        }
        NetworkHandler.sendToPlayersInWorld(world, new PacketOrganismNBT(this));
}



}
