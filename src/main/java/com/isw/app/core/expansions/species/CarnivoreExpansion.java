package com.isw.app.core.expansions.species;

import com.isw.app.enums.Balance;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.artifacts.ContextArtifact;

public class CarnivoreExpansion extends SpeciesExpansion {

  public CarnivoreExpansion(Balance balance) {
    super(balance);
  }

  @Override
  public String getName() {
    return "Carnivore Expansion";
  }

  @Override
  public String getDescription() {
    return "Adds carnivorous animals to the ecosystem.";
  }

  @Override
  public void apply(ContextArtifact context) {
    int population = balance.getPopulation(Detail.CARNIVORE);
    context.putPopulation(Detail.CARNIVORE, population);
  }
}
