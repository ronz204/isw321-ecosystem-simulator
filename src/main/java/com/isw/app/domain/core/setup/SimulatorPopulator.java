package com.isw.app.domain.core.setup;

import java.util.Map.Entry;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.factories.AnimalFactory;

public class SimulatorPopulator {

  private SimulatorPopulator() {}

  public static void populate() {
    SimulatorConfig config = SimulatorConfig.getInstance();
    SimulatorContext context = SimulatorContext.getInstance();
    
    Matrix matrix = context.getMatrix();

    for (Entry<Detail, Integer> entry : config.getPopulations()) {
      Detail detail = entry.getKey();
      int quantity = entry.getValue();

      for (int i = 0; i < quantity; i++) {
        Coord coord = matrix.getRandomCoord();
        Animal animal = AnimalFactory.build(detail, coord);

        matrix.getSectorAt(coord).setAnimal(animal);
        context.getAnimals().get(detail).add(animal);
      }
    }
  }
}
