package com.reuxertz.genesis.genetics;

import com.reuxertz.genesis.api.genes.GeneData;
import com.reuxertz.genesis.genetics.genes.GeneHelper;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class Genome
{
    public static Random Random = new Random();

    public static List<GeneData> Translate(String genomeSequence)
    {
        List<GeneData> result = new ArrayList<>();
        for (int i = 0; i < genomeSequence.length() - (GeneHelper.CodonLength - 1); i++)
        {
            String nextThree = genomeSequence.substring(i, i+3);
            if (!nextThree.equals(GeneHelper.StartCodon))
                continue;

            int remainingLength = genomeSequence.length() - i;
            if (remainingLength < GeneHelper.GeneLength)
                break;

            String geneSubString = genomeSequence.substring(i, GeneHelper.GeneLength);
            GeneData newGeneData = GeneHelper.Translate(geneSubString);

            if (newGeneData == null)
                continue;

            result.add(newGeneData);
        }

        return result;
    }
    public static String RandomDeletions(String sequence, double numDeletions)
    {
        for (int i = 0 ; i < numDeletions; i++)
        {
            double curProbability = numDeletions - i;

            if (curProbability > 0)
            {
                if (curProbability < 1 && Random.nextDouble() > curProbability)
                    return sequence;

                int randomPositionIndex = Random.nextInt(sequence.length());

                String preSequence = sequence.substring(0, randomPositionIndex);
                String postSequence = sequence.substring(randomPositionIndex + 1, sequence.length());

                sequence = preSequence + postSequence;
            }
        }

        return sequence;
    }
    public static String RandomInsertions(String sequence, double numInsertions)
    {
        for (int i = 0 ; i < numInsertions; i++)
        {
            double curProbability = numInsertions - i;

            if (curProbability > 0)
            {
                if (curProbability < 1 && Random.nextDouble() > curProbability)
                    return sequence;

                int randomLetterIndex = Random.nextInt(GeneHelper.Letters.length());
                int randomPositionIndex = Random.nextInt(sequence.length());

                String randomLetter = GeneHelper.Letters.substring(randomLetterIndex, randomLetterIndex + 1);

                String preSequence = sequence.substring(0, randomPositionIndex);
                String postSequence = sequence.substring(randomPositionIndex, sequence.length());

                sequence = preSequence + randomLetter + postSequence;
            }
        }

        return sequence;
    }
    public static List<String> Crossover(String sequence1, String sequence2)
    {

        List<String> result = new ArrayList<String>();

        List<Integer> crossoverIndexes = new ArrayList<Integer>();

        while (Random.nextDouble() < .5) {
            int index = Random.nextInt(sequence1.length());

            if (!crossoverIndexes.contains(index))
                crossoverIndexes.add(index);
        }

        String out1 = "";
        String out2 = "";
        Boolean swap = Random.nextBoolean();

        int startIndex = 0;
        int stopIndex1 = 0;
        int stopIndex2 = 0;
        boolean brk = false;
        for (int i = 0 ; i < crossoverIndexes.size(); i++)
        {
            if (i == 0)
                startIndex = 0;
            else
                startIndex = crossoverIndexes.get(i) - 1;

            stopIndex1 = stopIndex2 = crossoverIndexes.get(i);

            if (startIndex >= sequence1.length() || startIndex >= sequence2.length())
                break;

            if (stopIndex1 >= sequence1.length() || stopIndex2 >= sequence2.length()) {
                stopIndex1 = sequence1.length() - 1;
                stopIndex2 = sequence2.length() - 1;
                brk = true;
            }

            String subString1 = sequence1.substring(startIndex, stopIndex1);
            String subString2 = sequence2.substring(startIndex, stopIndex2);

            if (swap)
            {
                String temp = subString1;
                subString1 = subString2;
                subString2 = temp;
            }
            swap = !swap;

            out1 += subString1;
            out2 += subString2;

            if (brk)
                break;
        }

        result.add(out1);
        result.add(out2);

        return result;
    }
    public static List<GeneData> FilterDominantGenes(List<GeneData> allGenes)
    {
        HashMap<String, GeneData> resultMap = new HashMap<String, GeneData>();

        for (int i = 0; i < allGenes.size(); i++)
        {
            GeneData newGene = allGenes.get(i);
            if (!resultMap.containsKey(newGene.codon))
                resultMap.put(newGene.codon, allGenes.get(i));
            else
            {
                GeneData existingGene = resultMap.get(newGene.codon);
                if (existingGene.dominance <= newGene.dominance)
                {
                    resultMap.remove(existingGene.codon);
                    resultMap.put(newGene.codon, newGene);
                }
            }
        }

        return new ArrayList(resultMap.values());
    }

    public String Sequence1;
    public String Sequence2;
    public List<GeneData> Sequence1Genes;
    public List<GeneData> Sequence2Genes;
    public List<GeneData> ExpressedGenes;

    public Genome(String sequence)
    {
        this(sequence, sequence);
    }
    public Genome(String sequence1, String sequence2)
    {
        Sequence1 = sequence1;
        Sequence2 = sequence2;

        Translate();
    }

    public void Translate()
    {
        Sequence1Genes = Genome.Translate(Sequence1);
        Sequence2Genes = Genome.Translate(Sequence2);

        List<GeneData> allGenes = new ArrayList<GeneData>(Sequence1Genes);
        allGenes.addAll(Sequence2Genes);

        ExpressedGenes = FilterDominantGenes(allGenes);
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setString("Sequence1", this.Sequence1);
        nbt.setString("Sequence2", this.Sequence2);
    }

    public static Genome readFromNBT(NBTTagCompound nbt)
    {
        return new Genome(nbt.getString("Sequence1"), nbt.getString("Sequence2"));
    }
}
