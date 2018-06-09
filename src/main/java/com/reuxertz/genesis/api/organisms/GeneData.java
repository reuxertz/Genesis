package com.reuxertz.genesis.api.organisms;

public class GeneData {

    public enum GeneType
    {
        NewBornMassFactor,
        ClutchSizeFactor,
        AdultMassFactor,
        GrowthFactor,
        LayerFactor,
    }

    public GeneType geneType;
    public String geneTypeClass;
    public double value;
    public double dominance;

    public GeneData(GeneType geneType, double value, double dominance) {
        this(geneType, null, value, dominance);
    }
    public GeneData(GeneType geneType, String geneTypeClass, double value, double dominance)
    {
        this.geneType = geneType;
        this.geneTypeClass = geneTypeClass;
        this.value = value;
        this.dominance = dominance;
    }



}
