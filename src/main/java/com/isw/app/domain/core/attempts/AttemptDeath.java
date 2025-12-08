package com.isw.app.domain.core.attempts;

import com.isw.app.domain.core.objects.Action;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.domain.core.setup.SimulatorContext;
import com.isw.app.application.contexts.SimulationContext;

public class AttemptDeath extends Attempt {

  @Override
  public Result execute(Animal animal, Matrix matrix) {
    SimulationContext simulation = SimulationContext.getInstance();
    SimulatorContext context = SimulatorContext.getInstance();
    Detail original = animal.getDetail();

    context.getAnimals().get(original).remove(animal);

    if (context.getAnimals().get(original).isEmpty()) {
      int turn = simulation.getCurrent();
      context.getExtinctions().put(original, turn);
    }

    animal.markAsDead();
    context.getAnimals().get(Detail.CORPSE).add(animal);
    
    String description = "";
    return Result.build(animal, Action.DEATH, description);
  }
}
