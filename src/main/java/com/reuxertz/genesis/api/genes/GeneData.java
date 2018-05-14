package com.reuxertz.genesis.api.genes;

public class GeneData {

    //Gene Types
    public enum GeneType
    {
        MassFactor
    }

    public GeneType geneType;
    public String codon;
    public double value;
    public double dominance;
    public double upperBound;
    public double lowerBound;

    public GeneData(GeneType geneType, double value, double dominance, double upperBound, double lowerBound)
    {
        this.geneType = geneType;
        this.value = value;
        this.dominance = dominance;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
    }



}
