package com.isw.app.core.expansions.species;

import com.isw.app.enums.Balance;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.artifacts.ContextArtifact;

public class ScavengerExpansion extends SpeciesExpansion {

  public ScavengerExpansion(Balance balance) {
    super(balance);
  }

  @Override
  public String getName() {
    return "Scavenger Expansion";
  }

  @Override
  public String getDescription() {
    return "Adds scavenger animals to the ecosystem.";
  }

  @Override
  public void apply(ContextArtifact context) {
    int population = balance.getPopulation(Detail.SCAVENGER);
    context.putPopulation(Detail.SCAVENGER, population);
  }
}
