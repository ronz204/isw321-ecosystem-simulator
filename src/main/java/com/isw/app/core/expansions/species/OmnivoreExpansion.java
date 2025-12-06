package com.isw.app.core.expansions.species;

import com.isw.app.enums.Balance;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.artifacts.ContextArtifact;

public class OmnivoreExpansion extends SpeciesExpansion {

  public OmnivoreExpansion(Balance balance) {
    super(balance);
  }

  @Override
  public String getName() {
    return "Omnivore Expansion";
  }

  @Override
  public String getDescription() {
    return "Adds omnivorous animals to the ecosystem.";
  }

  @Override
  public void apply(ContextArtifact context) {
    int population = balance.getPopulation(Detail.OMNIVORE);
    context.putPopulation(Detail.OMNIVORE, population);
  }
}
