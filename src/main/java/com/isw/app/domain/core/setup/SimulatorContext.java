package com.isw.app.domain.core.setup;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Matrix;

public class SimulatorContext {
  private static SimulatorContext instance;

  private SimulatorContext() {
    this.matrix = new Matrix();
    this.animals = new HashMap<>();
    this.extinctions = new HashMap<>();

    for (Detail detail : Detail.values()) {
      extinctions.put(detail, 0);
      animals.put(detail, new ArrayList<>());
    }
  }

  public static void reset() {
    instance = null;
  }

  public static SimulatorContext getInstance() {
    if (instance == null) {
      instance = new SimulatorContext();
    }
    return instance;
  }

  private Matrix matrix;
  private Map<Detail, Integer> extinctions;
  private Map<Detail, List<Animal>> animals;

  public Matrix getMatrix() {
    return matrix;
  }

  public Map<Detail, Integer> getExtinctions() {
    return extinctions;
  }

  public Map<Detail, List<Animal>> getAnimals() {
    return animals;
  }
}
