package com.isw.app.core.factories;

import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.behavior.Behavior;
import com.isw.app.core.behavior.CarnivoreBehavior;
import com.isw.app.core.behavior.HerbivoreBehavior;

public class AnimalFactory {

  public static Animal buildCarnivore(Matrix matrix) {
    Coord coord = matrix.getRandomCoord();
    Behavior behavior = new CarnivoreBehavior();
    return new Animal(Detail.CARNIVORE, coord, behavior);
  }

  public static Animal buildHerbivore(Matrix matrix) {
    Coord coord = matrix.getRandomCoord();
    Behavior behavior = new HerbivoreBehavior();
    return new Animal(Detail.HERBIVORE, coord, behavior);
  }
}
