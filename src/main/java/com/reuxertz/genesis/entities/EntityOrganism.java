package com.reuxertz.genesis.entities;

import com.reuxertz.genesis.Genesis;
import com.reuxertz.genesis.organics.Genome;
import com.reuxertz.genesis.organics.IOrganismContainer;
import com.reuxertz.genesis.organics.Organism;
import com.reuxertz.genesis.registry.GenesisRegistry;
import com.reuxertz.genesis.registry.RegistryObject;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.tools.nsc.transform.patmat.Logic;

import java.util.UUID;

public abstract class EntityOrganism extends EntityCreature implements IOrganismContainer {

    protected Organism organism;
    protected RegistryObject registryObject;

    public World getWorld() { return this.world; }
    public Organism getOrganism() { return organism; }
    public RegistryObject getRegistryObject() { return registryObject; }

    public EntityOrganism(World world)
    {
        super(world);

        return;
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


    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        registryObject = Genesis.registry.getRegistryObject(this.getName());

        organism = Organism.readFromNBT(this, compound);
        if (organism == null) {
            Genome genome = SpeciesRegistry.getSpeciesGenome(this.getName());
            organism = new Organism(this.getName(), genome);
            organism.writeToNBT(compound);
        }

    }



}
