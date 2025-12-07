package com.isw.app.domain.core.attempts;

import java.util.List;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.domain.core.objects.Sector;
import com.isw.app.application.helpers.RandomHelper;
import com.isw.app.domain.core.setup.SimulatorContext;
import com.isw.app.domain.core.factories.AnimalFactory;

public class AttemptSex extends Attempt {

  @Override
  public Result execute(Animal animal, Matrix matrix) {
    List<Coord> adjacentEmpty = matrix.getAdjacentEmptySectors(animal.getCoord());
    
    if (adjacentEmpty.isEmpty()) {
      return Result.failed(animal);
    }
    
    int index = RandomHelper.getChooseInt(adjacentEmpty.size());
    Coord childCoord = adjacentEmpty.get(index);
    
    Detail parentDetail = animal.getDetail();
    Animal child = AnimalFactory.build(parentDetail, childCoord);
    
    Sector childSector = matrix.getSectorAt(childCoord);
    childSector.setAnimal(child);
    
    SimulatorContext context = SimulatorContext.getInstance();
    context.getAnimals().get(parentDetail).add(child);
    
    animal.getBehavior().resetSexTurns();
    return Result.sex(animal, child);
  }
}
