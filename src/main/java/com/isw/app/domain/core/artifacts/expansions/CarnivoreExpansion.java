package com.isw.app.domain.core.artifacts.expansions;

import com.isw.app.domain.enums.Balance;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.setup.SimulatorConfig;

public class CarnivoreExpansion extends BaseExpansion {
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
  public void apply(SimulatorConfig config) {
    int population = balance.getPopulation(Detail.CARNIVORE);
    config.putPopulation(Detail.CARNIVORE, population);
  }
}
