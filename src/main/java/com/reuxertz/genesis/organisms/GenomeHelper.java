package com.reuxertz.genesis.organisms;

import com.reuxertz.genesis.api.organisms.GeneData;
import com.reuxertz.genesis.api.organisms.SpeciesFeature;
import com.reuxertz.genesis.registry.SpeciesRegistry;
import com.reuxertz.genesis.util.MathHelper;

public class GenomeHelper {

    public enum ExpressionType { PseudoLinear, Sigmoid }

    public static double expressValue(String speciesName, SpeciesFeature.FeatureTypes featureType, Genome genome, GeneData.GeneType geneType, ExpressionType expressionType)
    {
        SpeciesFeature speciesFeature = SpeciesRegistry.getSpeciesFeature(speciesName, featureType);
        return expressValue(genome, geneType, speciesFeature.value, expressionType);
    }

    public static double expressValue(Genome genome, GeneData.GeneType geneType, ExpressionType expressionType) {
        return expressValue(genome, geneType, 1, expressionType);
    }
    private static double expressValue(Genome genome, GeneData.GeneType geneType, double featureValue, ExpressionType expressionType)
    {
        GeneData geneData = genome.getGene(geneType);
        double geneValue = 0.0;

        if (geneData != null)
            geneValue = geneData.value;
        else
            geneValue = 0.0;

        double expValue = 0;

        if (expressionType == ExpressionType.PseudoLinear) {
            double base = 10;
            double expFactor = MathHelper.Log(1 + Math.pow(base, geneValue), base);
            double normalizedExpFactor = expFactor * (1.0 / MathHelper.Log(2, base));
            expValue = normalizedExpFactor * featureValue;
        }
        else if (expressionType == ExpressionType.Sigmoid)
        {
            double slope = 1;
            double expFactor = 1.0 / (1.0 + Math.pow(Math.E, -1.0 * geneValue * slope));
            expValue = expFactor * featureValue;
        }

        return expValue;
    }
}
