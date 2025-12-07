package com.isw.app.application.services;

import java.util.List;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.setup.SimulatorConfig;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.domain.core.artifacts.mutations.BaseMutation;

public class MutationService {

  public static Animal applyRandomMutation(Animal animal) {
    if (!RandomHelper.coinFlip()) {
      return animal;
    }

    BaseMutation mutation = getRandomMutation();    
    if (mutation == null) {
      return animal; 
    }

    return mutation.mutate(animal);
  }

  private static BaseMutation getRandomMutation() {
    SimulatorConfig config = SimulatorConfig.getInstance();
    List<BaseMutation> mutations = config.getMutations();
    
    if (mutations.isEmpty()) {
      return null;
    }

    int index = RandomHelper.getChooseInt(mutations.size());
    return mutations.get(index);
  }
}
