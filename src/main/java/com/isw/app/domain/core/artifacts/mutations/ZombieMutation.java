package com.isw.app.domain.core.artifacts.mutations;

import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.factories.AnimalFactory;

public class ZombieMutation extends BaseMutation {

  @Override
  public String getName() {
    return "Zombie Mutation";
  }

  @Override
  public String getDescription() {
    return "Allows the animal to eat other animals, regardless of their diet.";
  }

  @Override
  public Animal mutate(Animal child) {
    return AnimalFactory.build(Detail.ZOMBIE, child.getCoord());
  }
}
