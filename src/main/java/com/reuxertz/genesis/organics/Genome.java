package com.reuxertz.genesis.organics;

import com.reuxertz.genesis.api.organisms.GeneData;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class Genome
{
    public String sequence1;
    public String sequence2;
    public List<GeneData> sequence1Genes;
    public List<GeneData> sequence2Genes;
    public List<GeneData> expressedGenes;
    public Map<GeneData.GeneType, GeneData> expressedGenesMap = new HashMap<>();

    public void setSequence(String sequence1, String sequence2)
    {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        Translate();
    }
    public void setSequence(String sequence) {
        setSequence(sequence, sequence);
    }
    public void setSequence(NBTTagCompound nbtTagCompound)
    {
        String s1 = nbtTagCompound.getString("sequence1");
        String s2 = nbtTagCompound.getString("sequence2");
        setSequence(s1, s2);
    }

    public Genome(String sequence)
    {
        this(sequence, sequence);
    }
    public Genome(String sequence1, String sequence2)
    {
        setSequence(sequence1, sequence2);
    }
    public Genome(List<GeneData> genes)
    {
        String sequence = "";
        for (int i = 0; i < genes.size(); i++)
            sequence += GeneHelper.getGeneString(genes.get(i));

        setSequence(sequence);
    }

    public void Translate()
    {
        sequence1Genes = GenomeHelper.Translate(sequence1);
        sequence2Genes = GenomeHelper.Translate(sequence2);

        List<GeneData> allGenes = new ArrayList<>(sequence1Genes);
        allGenes.addAll(sequence2Genes);

        expressedGenes = GenomeHelper.FilterDominantGenes(allGenes);

        for (int i = 0; i < expressedGenes.size(); i++)
            expressedGenesMap.put(expressedGenes.get(i).geneType, expressedGenes.get(i));
    }

    public GeneData getGene(GeneData.GeneType type)
    {
        return expressedGenesMap.get(type);
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString("sequence1", this.sequence1);
        nbt.setString("sequence2", this.sequence2);
    }

    public static Genome readFromNBT(NBTTagCompound nbt)
    {
        return new Genome(nbt.getString("sequence1"), nbt.getString("sequence2"));
    }
}
