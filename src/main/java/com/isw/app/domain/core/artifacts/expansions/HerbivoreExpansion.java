package com.isw.app.domain.core.artifacts.expansions;

import com.isw.app.domain.enums.Balance;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.setup.SimulatorConfig;

public class HerbivoreExpansion extends BaseExpansion {
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
  public void apply(SimulatorConfig config) {
    int population = balance.getPopulation(Detail.HERBIVORE);
    config.putPopulation(Detail.HERBIVORE, population);
  }
}
