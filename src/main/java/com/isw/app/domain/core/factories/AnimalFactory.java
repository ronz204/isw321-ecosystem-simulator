package com.isw.app.domain.core.factories;

import com.isw.app.domain.core.objects.Coord;
import com.isw.app.domain.core.objects.Detail;
import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.behaviors.Behavior;
import com.isw.app.domain.core.behaviors.OmnivoreBehavior;
import com.isw.app.domain.core.behaviors.HerbivoreBehavior;
import com.isw.app.domain.core.behaviors.CarnivoreBehavior;

public class AnimalFactory {

  public static Animal build(Detail detail, Coord coord) {
    Behavior behavior = getBehavior(detail);
    return new Animal(detail, coord, behavior);
  }

  private static Behavior getBehavior(Detail detail) {
    switch (detail) {
      case OMNIVORE:
        return new OmnivoreBehavior();
      case CARNIVORE:
        return new CarnivoreBehavior();
      case HERBIVORE:
        return new HerbivoreBehavior();
      default:
        return null;
    }
  }
}
