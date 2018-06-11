package com.reuxertz.genesis.api.organisms;

import java.util.ArrayList;
import java.util.List;

public class GeneData {

    public enum GeneType
    {
        NewBornMassFactor,
        ClutchSizeFactor,
        AdultMassFactor,
        AdultAgeTicksFactor,
        GrowthFactor,

        LayerFactor,
    }

    public GeneType geneType;
    public List<Double> values;
    public double dominance;

    public GeneData(GeneType geneType, double dominance, double value)
        {
        this.geneType = geneType;
        this.dominance = dominance;

        List<Double> values = new ArrayList<>();
        values.add(value);

        this.values = values;
    }
    public GeneData(GeneType geneType, double  dominance, double... values)
    {
        this.geneType = geneType;
        this.dominance = dominance;

        this.values = new ArrayList<Double>();
        for (int i = 0 ;i < values.length; i++)
            this.values.add(values[i]);
    }

    public GeneData(GeneType geneType, double  dominance, List<Double> values)
    {
        this.geneType = geneType;
        this.dominance = dominance;
        this.values = new ArrayList<>(values);
    }



}
