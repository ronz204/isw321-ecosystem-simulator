package com.isw.app.domain.core.factories;

import java.util.Map;
import java.util.HashMap;
import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.behaviors.Behavior;
import com.isw.app.domain.core.behaviors.OmnivoreBehavior;
import com.isw.app.domain.core.behaviors.HerbivoreBehavior;
import com.isw.app.domain.core.behaviors.CarnivoreBehavior;

public class AnimalFactory {
  private static final Map<Detail, Behavior> behaviors = new HashMap<>();

  static {
    behaviors.put(Detail.OMNIVORE, new OmnivoreBehavior());
    behaviors.put(Detail.HERBIVORE, new HerbivoreBehavior());
    behaviors.put(Detail.CARNIVORE, new CarnivoreBehavior());
  }

  public static Animal build(Detail detail, Coord coord) {
    Behavior behavior = behaviors.get(detail);
    return new Animal(detail, coord, behavior);
  }
}
