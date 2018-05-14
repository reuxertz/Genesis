package com.reuxertz.genesis.genetics.genes;


import com.reuxertz.genesis.api.genes.GeneData;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneHelper
{
    //Genetic Constants
    public static String Letters = "ACGT";
    public static final int CodonLength = 3;
    public static final int GeneLength = 12;
    public static final double CodonCombinations = getCodonCombinations();
    public static final double getCodonCombinations() { return Math.pow(Letters.length(), CodonLength); }

    //Translation Constants
    public static final String StartCodon = "ATG";
    public static final HashMap<GeneData.GeneType, String> TypeToCodonMap = InitTypeToCodonMap();
    public static final HashMap<String, GeneData.GeneType> CodonToTypeMap = InitCodonToTypeMap();

    //Test Constants
    public static String TestGene = "ATGAAAAAAAAA";

    //Static Initializers
//    public static HashMap<GeneData.GeneType, Gene> GeneExpressionMap = InitExpressionMap();
//    public static HashMap<GeneData.GeneType, Gene> InitExpressionMap() {
//
//        HashMap<GeneData.GeneType, Gene> result = new HashMap<>();
//
//        //result.put(GeneData.GeneType.MassFactor, new Gene(GeneData.GeneType.MassFactor, TypeToCodonMap.get(GeneData.GeneType.MassFactor)).Clone(.5, .5));
//
//        return result;
//    }
    public static HashMap<GeneData.GeneType, String> InitTypeToCodonMap()
    {
        HashMap<GeneData.GeneType, String> result = new HashMap<>();

        result.put(GeneData.GeneType.MassFactor, "AAA");

        return result;
    }
    public static HashMap<String, GeneData.GeneType> InitCodonToTypeMap()
    {
        HashMap<GeneData.GeneType, String> typeToCodonMap = InitTypeToCodonMap();
        ArrayList<GeneData.GeneType> types = new ArrayList<>(typeToCodonMap.keySet());
        ArrayList<String> values = new ArrayList<String>(typeToCodonMap.values());

        HashMap<String, GeneData.GeneType> result = new HashMap<>();
        for (int i = 0; i < types.size(); i++)
            result.put(values.get(i), types.get(i));

        return result;
    }

    //Static Functions
    public static double ConvertCodonToDouble(String codon)
    {
        double result = 0.0;
        for (int i = 0 ; i < codon.length(); i++)
        {
            String letter = codon.substring(i, i+1);
            int letterIndex = Letters.indexOf(letter);
            double power = Math.pow(Letters.length(), (codon.length() - (i + 1)));
            double value = (letterIndex) * power;

            result += value;
        }

        return result / (CodonCombinations - 1);
    }
    public static String ConvertDoubleToCodon(double value)
    {
        String result = "";
        value *= (CodonCombinations);

        int[] a = new int[CodonLength];
        for (int i = 0; i < a.length; i++)
        {
            int level = (int)Math.pow(Letters.length(), a.length - (i + 1));
            int modValue = (int)(value / level);
            value -= level * modValue;
            result += Letters.substring(modValue, modValue + 1);
        }

        return result;
    }
    public static double Transform(double proportion, double lowerBound, double upperBound)
    {
        return lowerBound + ((upperBound - lowerBound) * proportion);
    }
    public static GeneData Translate(String geneString) {

        String typeCodon = geneString.substring(3, 6);
        if (!CodonToTypeMap.containsKey(typeCodon))
            return null;

        double geneValue = ConvertCodonToDouble(geneString.substring(6, 9));
        double geneDominance = ConvertCodonToDouble(geneString.substring(9, 12));

        GeneData.GeneType geneType = CodonToTypeMap.get(typeCodon);
        //Gene newGene = GeneExpressionMap.get(geneType).Clone(geneValue, geneDominance);

        //return newGene;
        return null;
    }

    public String getGeneString()
    {
        return null;//StartCodon + codon + ConvertDoubleToCodon(value) + ConvertDoubleToCodon(dominance);
    }

}
