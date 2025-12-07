package com.isw.app.domain.core.setup;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.artifacts.mutations.BaseMutation;
import com.isw.app.domain.core.artifacts.expansions.BaseExpansion;

public class SimulatorConfig {
  private static SimulatorConfig instance;

  private SimulatorConfig() {
    this.mutations = new ArrayList<>();
    this.populations = new HashMap<>();
    this.expansions = new ArrayList<>();
  }
  
  public static void reset() {
    instance = null;
  }

  public static SimulatorConfig getInstance() {
    if (instance == null) {
      instance = new SimulatorConfig();
    }
    return instance;
  }

  private List<BaseMutation> mutations;
  private List<BaseExpansion> expansions;
  private Map<Detail, Integer> populations;

  public void addMutation(BaseMutation mutation) {
    this.mutations.add(mutation);
  }

  public void addExpansion(BaseExpansion expansion) {
    this.expansions.add(expansion);
  }

  public void putPopulation(Detail detail, int count) {
    populations.put(detail, count);
  }

  public Set<Entry<Detail, Integer>> getPopulations() {
    return populations.entrySet();
  }

  public void apply() {
    for (BaseExpansion expansion : expansions) {
      expansion.apply(this);
    }

    for (BaseMutation mutation : mutations) {
      mutation.apply(this);
    }
  }
}
