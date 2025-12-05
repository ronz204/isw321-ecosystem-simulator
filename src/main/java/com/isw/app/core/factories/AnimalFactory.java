package com.isw.app.core.factories;


import java.util.Map;
import java.util.HashMap;
import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.behavior.Behavior;
import com.isw.app.core.behavior.OmnivoreBehavior;
import com.isw.app.core.behavior.CarnivoreBehavior;
import com.isw.app.core.behavior.HerbivoreBehavior;
import com.isw.app.core.behavior.ScavengerBehavior;

public class AnimalFactory {

  private static final Map<Detail, Behavior> behaviors = new HashMap<>();

  static {
    behaviors.put(Detail.OMNIVORE, new OmnivoreBehavior());
    behaviors.put(Detail.CARNIVORE, new CarnivoreBehavior());
    behaviors.put(Detail.HERBIVORE, new HerbivoreBehavior());
    behaviors.put(Detail.SCAVENGER, new ScavengerBehavior());
  }

  public static Animal build(Detail detail, Matrix matrix) {
    Coord coord = matrix.getRandomCoord();
    Behavior behavior = behaviors.get(detail);
    return new Animal(detail, coord, behavior);
  }
}
