package com.reuxertz.genesis.genetics;

import java.util.HashMap;

public class Gene
{
    //Genetic Constants
    public static String letters = "ACTG";
    public static int codonLength = 3;
    public static int geneCodonLength = 12;

    //Translation Constants
    public static String startCodon = "ATG";
    public static HashMap<String, Gene> geneMap = InitGeneTypeMap();

    //Test Constants
    public static String TestGene = "ATGAAAAAAAAA";

    //Static Initializers
    public static HashMap<String, Gene> InitGeneTypeMap()
    {
        HashMap<String, Gene> result = new HashMap<String, Gene>();

        result.put("AAA", new Gene("AAA"));

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
            double power = Math.pow(letters.length(), i);
            double value = (letterIndex) * power;

            result += value;
        }

        return result;
    }
    public static Gene Translate(String geneString) {

        String geneType = geneString.substring(3, 6);
        if (!Gene.geneMap.containsKey(geneType))
            return null;

        double geneValue = ConvertCodonToDouble(geneString.substring(6, 9));
        double geneDominance = ConvertCodonToDouble(geneString.substring(9, 12));

        Gene geneTemplate = geneMap.get(geneType);
        Gene newGene = geneTemplate.Clone(geneValue, geneDominance);

        return newGene;
    }

    //Gene Types
    public enum GeneType
    {
        MassFactor
    }

    //Fields
    protected String codon;
    protected double value;
    protected double dominance;

    public Gene(String codon, double value, double dominance)
    {
        this.codon = codon;
        this.value = value;
        this.dominance = dominance;
    }
    public Gene(String codon)
    {
        this(codon, 0.0, 0.0);
    }

    public Gene Clone(double value, double dominance)
    {
        return new Gene(codon, value, dominance);
    }

}
