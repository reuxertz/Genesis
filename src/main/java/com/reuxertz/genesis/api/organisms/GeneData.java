package com.reuxertz.genesis.api.organisms;

public class GeneData {

    public enum GeneType
    {
        MassFactor
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
