package com.reuxertz.genesisAPI.organics;

import java.util.ArrayList;
import java.util.List;

public class SpeciesFeature {

    public enum FeatureTypes
    {
        NewbornMass,
        ClutchSize,
        AdultAgeTicks,
        AdultMass,

        EyeLayer,
        HairLayer,
        MouthLayer,
        SkinLayer
    }

    public FeatureTypes featureType;
    public List<Double> values = new ArrayList<>();

    public SpeciesFeature(FeatureTypes type, double double1)
    {
        List<Double> values = new ArrayList<Double>();
        values.add(double1);

        this.featureType = type;
        this.values = values;
    }
    public SpeciesFeature(FeatureTypes type, int int1)
    {
        List<Double> values = new ArrayList<Double>();
        values.add((double)int1);

        this.featureType = type;
        this.values = values;
    }
    public SpeciesFeature(FeatureTypes type, List<Double> value)
    {
        this.featureType = type;
        this.values = new ArrayList<>(value);
    }
}
