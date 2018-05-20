package com.reuxertz.genesis.api.organisms;

public class SpeciesData {

    public enum SpeciesDataType
    {
        BabyMass,
        AdultMass,
    }


    public SpeciesDataType featureType;
    public double value;
    public double deviation;

    public SpeciesData(SpeciesDataType type, double value, double deviation)
    {
        this.featureType = type;
        this.value = value;
        this.deviation = deviation;
    }
}
