package com.reuxertz.genesis.organisms;

import com.reuxertz.genesis.api.organisms.GeneData;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class Genome
{
    public static Random random = new Random();

    public static List<GeneData> Translate(String genomeSequence)
    {
        List<GeneData> result = new ArrayList<>();
        for (int i = 0; i < genomeSequence.length() - 3; i++)
        {
            String nextThree = genomeSequence.substring(i, i + 3);
            if (!nextThree.equals(GeneHelper.startCodon))
                continue;

            int remainingLength = genomeSequence.length() - i;
            if (remainingLength < GeneHelper.geneLength)
                break;

            String geneSubString = genomeSequence.substring(i, GeneHelper.geneLength);
            GeneData newGeneData = GeneHelper.Translate(geneSubString);

            if (newGeneData == null)
                continue;

            result.add(newGeneData);
            i += GeneHelper.geneLength - 1;
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
                if (curProbability < 1 && random.nextDouble() > curProbability)
                    return sequence;

                int randomPositionIndex = random.nextInt(sequence.length());

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
                if (curProbability < 1 && random.nextDouble() > curProbability)
                    return sequence;

                int randomLetterIndex = random.nextInt(GeneHelper.letters.length());
                int randomPositionIndex = random.nextInt(sequence.length());

                String randomLetter = GeneHelper.letters.substring(randomLetterIndex, randomLetterIndex + 1);

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

        while (random.nextDouble() < .5) {
            int index = random.nextInt(sequence1.length());

            if (!crossoverIndexes.contains(index))
                crossoverIndexes.add(index);
        }

        String out1 = "";
        String out2 = "";
        Boolean swap = random.nextBoolean();

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
            if (!resultMap.containsKey("" + newGene.geneType))
                resultMap.put("" + newGene.geneType, allGenes.get(i));
            else
            {
                GeneData existingGene = resultMap.get("" + newGene.geneType);
                if (existingGene.dominance <= newGene.dominance)
                {
                    resultMap.remove(existingGene.geneType);
                    resultMap.put("" + newGene.geneType, newGene);
                }
            }
        }

        return new ArrayList(resultMap.values());
    }

    public String sequence1;
    public String sequence2;
    public List<GeneData> sequence1Genes;
    public List<GeneData> sequence2Genes;
    public List<GeneData> expressedGenes;
    public Map<GeneData.GeneType, GeneData> expressedGenesMap = new HashMap<>();

    public Genome(String sequence)
    {
        this(sequence, sequence);
    }
    public Genome(String sequence1, String sequence2)
    {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;

        Translate();
    }

    public Genome(List<GeneData> genes)
    {
        String sequence = "";
        for (int i = 0; i < genes.size(); i++)
            sequence += GeneHelper.getGeneString(genes.get(i));

        sequence1 = sequence;
        sequence2 = sequence;

        Translate();
    }

    public void Translate()
    {
        sequence1Genes = Genome.Translate(sequence1);
        sequence2Genes = Genome.Translate(sequence2);

        List<GeneData> allGenes = new ArrayList<>(sequence1Genes);
        allGenes.addAll(sequence2Genes);

        expressedGenes = FilterDominantGenes(allGenes);

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
