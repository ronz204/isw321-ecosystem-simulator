package com.isw.app.domain.core.artifacts.expansions;

import com.isw.app.domain.enums.Balance;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.setup.SimulatorConfig;

public class CorpseExpansion extends BaseExpansion {
  public CorpseExpansion(Balance balance) {
    super(balance);
  }

  @Override
  public String getName() {
    return "Corpse Expansion";
  }

  @Override
  public String getDescription() {
    return "Adds corpses to the ecosystem.";
  }

  @Override
  public void apply(SimulatorConfig config) {
    int population = balance.getPopulation(Detail.CORPSE);
    config.putPopulation(Detail.CORPSE, population);
  }
}
