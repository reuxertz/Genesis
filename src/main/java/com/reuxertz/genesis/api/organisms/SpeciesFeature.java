package com.reuxertz.genesis.api.organisms;

public class SpeciesFeature {

    public enum FeatureTypes
    {
        NewbornMass,
        ClutchSize,
        AdultMass,
        Layer,
    }

    public FeatureTypes featureType;
    public String typeClass;
    public double value;

    public SpeciesFeature(FeatureTypes type, double value)
    {
        this(type, null, value);
    }
    public SpeciesFeature(FeatureTypes type, String typeClass, double value)
    {
        this.featureType = type;
        this.typeClass = typeClass;
        this.value = value;
    }
}
