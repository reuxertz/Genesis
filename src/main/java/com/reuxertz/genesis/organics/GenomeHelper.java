package com.reuxertz.genesis.organics;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import com.reuxertz.genesis.util.MathHelper;
import com.reuxertz.genesis.util.RandomHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GenomeHelper {

    public enum ExpressionType { PseudoLinear, Sigmoid }
    public static String NBTGenomeTag = "genome";

    public static double expressValue(String speciesName, SpeciesFeature.FeatureTypes featureType, Genome genome, GeneData.GeneType geneType, ExpressionType expressionType)
    {
        SpeciesFeature speciesFeature = SpeciesRegistry.getSpeciesFeature(speciesName, featureType);
        return expressValue(genome, geneType, speciesFeature.values.get(0), expressionType);
    }

    public static double expressValue(Genome genome, GeneData.GeneType geneType, ExpressionType expressionType) {
        return expressValue(genome, geneType, 1, expressionType);
    }
    private static double expressValue(Genome genome, GeneData.GeneType geneType, double featureValue, ExpressionType expressionType)
    {
        GeneData geneData = genome.getGene(geneType);
        double geneValue = 0.0;

        if (geneData != null)
            geneValue = geneData.values.get(0);
        else
            geneValue = 0.0;

        double expValue = 0;

        if (expressionType == ExpressionType.PseudoLinear) {
            double base = 10;
            double normalizedExpFactor = MathHelper.PseudoLinear(geneValue, base);
            expValue = normalizedExpFactor * featureValue;
        }
        else if (expressionType == ExpressionType.Sigmoid)
        {
            double slope = 1;
            double expFactor = MathHelper.Sigmoid(geneValue, slope);
            expValue = expFactor * featureValue;
        }

        return expValue;
    }

    public static Genome reproduce(Random random, Genome g1, Genome g2)
    {
        int randChromosomeG1 = random.nextInt(2);
        int randChromosomeG2 = random.nextInt(2);

        String g1s = null;
        String g2s = null;

        if (randChromosomeG1 == 0)
            g1s = g1.sequence1;
        else
            g1s = g1.sequence2;

        if (randChromosomeG2 == 0)
            g2s = g2.sequence1;
        else
            g2s = g2.sequence2;

        Genome newGenome = new Genome(g1s, g2s);

        return newGenome;
    }
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

            String remainderString = genomeSequence.substring(i, genomeSequence.length());
            int stopIndex = remainderString.indexOf(GeneHelper.stopCodon + GeneHelper.stopCodon);

            String subString = remainderString.substring(0, stopIndex);
            int geneLength = subString.length();

            String geneSubString = genomeSequence.substring(i + GeneHelper.startCodon.length(), i + geneLength);
            GeneData newGeneData = GeneHelper.Translate(geneSubString);

            if (newGeneData == null)
                continue;

            result.add(newGeneData);
            i += subString.length() + GeneHelper.stopCodon.length() - 1;
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
                if (curProbability < 1 && RandomHelper.random.nextDouble() > curProbability)
                    return sequence;

                int randomPositionIndex = RandomHelper.random.nextInt(sequence.length());

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
                if (curProbability < 1 && RandomHelper.random.nextDouble() > curProbability)
                    return sequence;

                int randomLetterIndex = RandomHelper.random.nextInt(GeneHelper.letters.length());
                int randomPositionIndex = RandomHelper.random.nextInt(sequence.length());

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

        while (RandomHelper.random.nextDouble() < .5) {
            int index = RandomHelper.random.nextInt(sequence1.length());

            if (!crossoverIndexes.contains(index))
                crossoverIndexes.add(index);
        }

        String out1 = "";
        String out2 = "";
        Boolean swap = RandomHelper.random.nextBoolean();

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


    public static boolean validateNBT(ItemStack stack)
    {
        if (!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
            return false;
        }

        return true;
    }
    public static boolean validateGenome(String name, String breed, Genome genome)
    {
        if (!genome.isValid())
        {
            NBTTagCompound nbtGenome = new NBTTagCompound();
            Genome speciesGenome = SpeciesRegistry.getSpeciesGenome(name, breed).clone();
            genome.setSequence(speciesGenome.sequence1, speciesGenome.sequence2);
            return false;
        }

        return true;
    }

//    public static void addGenomeToItemStack(ItemStack stack, Genome genome)
//    {
//        if (!stack.hasTagCompound())
//            stack.setTagCompound(new NBTTagCompound());
//
//        if (!stack.getTagCompound().hasKey("genomeStack"))
//            stack.getTagCompound().setTag("genomeStack", new NBTTagList());
//
//        NBTTagList tagList = stack.getTagCompound().getTagList("genomeStack", 10);
//
//        NBTTagCompound genomeNBT = genome.writeToNBT(new NBTTagCompound());
//        tagList.appendTag(genomeNBT);
//        stack.getTagCompound().setTag("genomeStack", tagList);
//    }
//    public static Genome removeGenomeToItemStack(ItemStack stack, String name)
//    {
//        if (stack.hasTagCompound()) {
//            if (stack.getTagCompound().hasKey("genomeStack"))
//            {
//                NBTTagList list = stack.getTagCompound().getTagList("genomeStack", 10);
//                NBTTagCompound nbtTagCompound = (NBTTagCompound)list.get(list.tagCount() - 1);
//                list.removeTag(list.tagCount() - 1);
//                stack.getTagCompound().setTag("genomeStack", list);
//
//                Genome genome = new Genome(nbtTagCompound.getString(Genome.sequence1Tag), nbtTagCompound.getString(Genome.sequence2Tag));
//                return genome;
//            }
//        }
//
//        Genome speciesGenome = SpeciesRegistry.getSpeciesGenome(name).clone();
//        return speciesGenome;
//
//    }
}
