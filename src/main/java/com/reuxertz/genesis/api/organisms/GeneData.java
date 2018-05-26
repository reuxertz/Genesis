package com.reuxertz.genesis.api.organisms;

public class GeneData {

    public enum GeneType
    {
        NewBornMassFactor,
        ClutchSizeFactor,
        AdultMassFactor,
        GrowthFactor,
    }

    public GeneType geneType;
    public double value;
    public double dominance;

    public GeneData(GeneType geneType, double value, double dominance)
    {
        this.geneType = geneType;
        this.value = value;
        this.dominance = dominance;
    }



}
