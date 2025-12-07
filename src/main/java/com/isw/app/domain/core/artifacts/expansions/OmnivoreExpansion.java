package com.isw.app.domain.core.artifacts.expansions;

import com.isw.app.domain.enums.Balance;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.setup.SimulatorConfig;

public class OmnivoreExpansion extends BaseExpansion {
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
  public void apply(SimulatorConfig config) {
    int population = balance.getPopulation(Detail.OMNIVORE);
    config.putPopulation(Detail.OMNIVORE, population);
  }
}
