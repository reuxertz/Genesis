package com.reuxertz.genesis.organics;

import com.reuxertz.genesisAPI.organics.GeneData;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class Genome
{
    public static final String sequence1Tag = "sequence1";
    public static final String sequence2Tag = "sequence2";

    protected String sequence1;
    protected String sequence2;
    protected List<GeneData> sequence1Genes;
    protected List<GeneData> sequence2Genes;
    protected List<GeneData> expressedGenes;
    protected Map<String, GeneData> expressedGenesMap = new HashMap<>();

    public String getSequence1()
    {
        return sequence1;
    }
    public String getSequence2()
    {
        return sequence1;
    }
    public GeneData getGene(String type)
    {
        return expressedGenesMap.get(type);
    }

    public GeneData getGene(GeneData.GeneType type)
    {
        return getGene(type.toString().toLowerCase());
    }

    public void setSequence(String sequence1, String sequence2)
    {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        translate();
    }
    public void setSequence(String sequence) {
        setSequence(sequence, sequence);
    }

    public boolean isValid()
    {
        boolean hasEmptySequence = sequence1.isEmpty() || sequence1.isEmpty();

        if (hasEmptySequence)
            return false;

        return true;
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

    public Genome clone()
    {
        return new Genome(sequence1, sequence2);
    }

    public void translate()
    {
        sequence1Genes = GenomeHelper.Translate(sequence1);
        sequence2Genes = GenomeHelper.Translate(sequence2);

        List<GeneData> allGenes = new ArrayList<>(sequence1Genes);
        allGenes.addAll(sequence2Genes);

        expressedGenes = GenomeHelper.FilterDominantGenes(allGenes);

        for (int i = 0; i < expressedGenes.size(); i++)
            expressedGenesMap.put("" + expressedGenes.get(i).geneType.toString().toLowerCase(), expressedGenes.get(i));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString(sequence1Tag, this.sequence1);
        nbt.setString(sequence2Tag, this.sequence2);

        return nbt;
    }

    public static Genome readFromNBT(NBTTagCompound nbt)
    {
        return new Genome(nbt.getString(sequence1Tag), nbt.getString(sequence2Tag));
    }
}
