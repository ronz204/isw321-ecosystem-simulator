package com.isw.app.domain.core.attempts;

import java.util.List;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Action;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.domain.core.objects.Sector;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.domain.core.setup.SimulatorContext;
import com.isw.app.domain.core.factories.AnimalFactory;
import com.isw.app.application.services.MutationService;

public class AttemptSex extends Attempt {

  @Override
  public Result execute(Animal animal, Matrix matrix) {
    List<Coord> adjacentEmpty = matrix.getAdjacentEmptySectors(animal.getCoord());
    
    if (adjacentEmpty.isEmpty()) {
      String description = String.format("%s quiso reproducirse pero no hay espacio disponible", 
          animal.getDetail().getLabel());
      return Result.build(animal, Action.IDLE, description, false);
    }
    
    int index = RandomHelper.getChooseInt(adjacentEmpty.size());
    Coord childCoord = adjacentEmpty.get(index);
    
    Detail parentDetail = animal.getDetail();
    Animal child = AnimalFactory.build(parentDetail, childCoord);
    
    child = MutationService.applyRandomMutation(child);
    
    Sector childSector = matrix.getSectorAt(childCoord);
    childSector.setAnimal(child);
    
    SimulatorContext context = SimulatorContext.getInstance();
    context.getAnimals().get(child.getDetail()).add(child);
    
    animal.getBehavior().resetSexTurns();
    String description = String.format("%s se reprodujo y nació un nuevo %s en %s", 
        parentDetail.getLabel(), child.getDetail().getLabel(), childCoord);
    return Result.build(animal, Action.SEX, description, true);
  }
}
