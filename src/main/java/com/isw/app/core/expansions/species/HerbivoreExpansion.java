package com.isw.app.core.expansions.species;

import com.isw.app.enums.Balance;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.artifacts.ContextArtifact;

public class HerbivoreExpansion extends SpeciesExpansion {

  public HerbivoreExpansion(Balance balance) {
    super(balance);
  }

  @Override
  public String getName() {
    return "Herbivore Expansion";
  }

  @Override
  public String getDescription() {
    return "Adds herbivorous animals to the ecosystem.";
  }

  @Override
  public void apply(ContextArtifact context) {
    int population = balance.getPopulation(Detail.HERBIVORE);
    context.putPopulation(Detail.HERBIVORE, population);
  }
}
