package com.reuxertz.genesis.organics;


import com.reuxertz.genesis.api.organisms.GeneData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeneHelper
{
    //Genetic Constants
    public static String letters = "ACGT";
    public static final int codonLength = 3;
    public static final int geneLength = 12;
    public static final double codonCombinations = getCodonCombinations();
    public static final double getCodonCombinations() { return Math.pow(letters.length(), codonLength); }

    //Translation Constants
    public static final String startCodon = "ATG";
    public static final String stopCodon = "TAGTAG";
    public static final HashMap<GeneData.GeneType, String> typeToCodonMap = InitTypeToCodonMap();
    public static final HashMap<String, GeneData.GeneType> codonToTypeMap = InitCodonToTypeMap();

    //Test Constants
    public static String TestGene = "ATGAAAAAAAAA";

    //Static Initializers
//    public static HashMap<GeneData.GeneType, Gene> GeneExpressionMap = InitExpressionMap();
//    public static HashMap<GeneData.GeneType, Gene> InitExpressionMap() {
//
//        HashMap<GeneData.GeneType, Gene> result = new HashMap<>();
//
//        //result.put(GeneData.GeneType.AdultMassFactor, new Gene(GeneData.GeneType.AdultMassFactor, typeToCodonMap.get(GeneData.GeneType.AdultMassFactor)).Clone(.5, .5));
//
//        return result;
//    }
    public static HashMap<GeneData.GeneType, String> InitTypeToCodonMap()
    {
        HashMap<GeneData.GeneType, String> result = new HashMap<>();

        result.put(GeneData.GeneType.AdultMassFactor, "AAA");
        result.put(GeneData.GeneType.GrowthFactor, "AAC");
        result.put(GeneData.GeneType.NewBornMassFactor, "AAG");
        result.put(GeneData.GeneType.ClutchSizeFactor, "AAT");

        result.put(GeneData.GeneType.SkinLayer, "ACA");
        result.put(GeneData.GeneType.EyesLayer, "ACC");
        result.put(GeneData.GeneType.HairLayer, "ACG");
        result.put(GeneData.GeneType.MouthLayer, "ACT");

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
            int letterIndex = letters.indexOf(letter);
            double power = Math.pow(letters.length(), (codon.length() - (i + 1)));
            double value = (letterIndex) * power;

            result += value;
        }

        return result / (codonCombinations - 1);
    }
    public static String ConvertDoubleToCodon(double value)
    {
        String result = "";
        value *= (codonCombinations - 1);

        int[] a = new int[codonLength];
        for (int i = 0; i < a.length; i++)
        {
            int level = (int)Math.pow(letters.length(), a.length - (i + 1));
            int modValue = (int)(value / level);
            value -= level * modValue;
            result += letters.substring(modValue, modValue + 1);
        }

        return result;
    }
    public static double Transform(double proportion, double lowerBound, double upperBound)
    {
        return lowerBound + ((upperBound - lowerBound) * proportion);
    }
    public static GeneData Translate(String geneString) {

        if (geneString.length() < 9)
            return null;

        String typeCodon = geneString.substring(0, 3);
        if (!codonToTypeMap.containsKey(typeCodon))
            return null;

        double geneDominance = ConvertCodonToDouble(geneString.substring(3, 6));
        //double geneValue = ConvertCodonToDouble(geneString.substring(9, 12));

        String valueSubstring = geneString.substring(3, geneString.length());
        int valueCount = valueSubstring.length() / GeneHelper.codonLength;

        List<Double> values = new ArrayList<>();
        for (int i = 0; i < valueCount; i++) {
            String valueCodon = valueSubstring.substring(i * 3, (i + 1) * 3);
            double value = ConvertCodonToDouble(valueCodon);
            values.add(value);
        }

        GeneData.GeneType geneType = codonToTypeMap.get(typeCodon);
        GeneData newGene = new GeneData(geneType, geneDominance, values);

        return newGene;
        //return null;
    }

    public static String getGeneString(GeneData geneData)
    {
        String result = startCodon + typeToCodonMap.get(geneData.geneType) + ConvertDoubleToCodon(geneData.dominance);

        for (int i = 0; i < geneData.values.size(); i++)
            result += ConvertDoubleToCodon(geneData.values.get(i));

        result += stopCodon + stopCodon;
        return result;
    }

}
