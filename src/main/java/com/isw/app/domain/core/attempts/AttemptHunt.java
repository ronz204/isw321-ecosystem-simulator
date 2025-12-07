package com.isw.app.domain.core.attempts;

import java.util.List;
import java.util.stream.Collectors;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.domain.core.setup.SimulatorContext;
import com.isw.app.application.contexts.SimulationContext;

public class AttemptHunt extends Attempt {

  @Override
  public Result execute(Animal animal, Matrix matrix) {
    List<Animal> adjacentAnimals = matrix.getAdjacentAnimals(animal.getCoord());
    
    List<Animal> huntablePreys = adjacentAnimals.stream()
        .filter(prey -> !prey.isDead() 
            && prey.getTier() <= animal.getTier()
            && !prey.getDetail().equals(animal.getDetail()))
        .collect(Collectors.toList());
    
    if (huntablePreys.isEmpty()) {
      return Result.failed(animal);
    }
    
    int index = RandomHelper.getChooseInt(huntablePreys.size());
    Animal prey = huntablePreys.get(index);
    
    SimulationContext simulation = SimulationContext.getInstance();
    SimulatorContext context = SimulatorContext.getInstance();
    
    Detail preyDetail = prey.getDetail();
    context.getAnimals().get(preyDetail).remove(prey);
    
    if (context.getAnimals().get(preyDetail).isEmpty()) {
      int turn = simulation.getCurrent();
      context.getExtinctions().put(preyDetail, turn);
    }
    
    prey.markAsDead();
    context.getAnimals().get(Detail.CORPSE).add(prey);
  
    animal.getBehavior().resetMealTurns();
    return Result.eat(animal, prey);
  }
}
