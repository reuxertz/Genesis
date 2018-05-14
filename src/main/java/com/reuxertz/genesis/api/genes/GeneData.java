package com.reuxertz.genesis.api.genes;

public class GeneData {

    //Gene Types
    public enum GeneType
    {
        MassFactor,
        OffspringYieldFactor,
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
