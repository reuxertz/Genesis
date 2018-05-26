package com.reuxertz.genesis.api.organisms;

public class SpeciesFeature {

    public enum FeatureTypes
    {
        NewbornMass,
        ClutchSize,
        AdultMass,
    }


    public FeatureTypes featureType;
    public double value;

    public SpeciesFeature(FeatureTypes type, double value)
    {
        this.featureType = type;
        this.value = value;
    }
}
