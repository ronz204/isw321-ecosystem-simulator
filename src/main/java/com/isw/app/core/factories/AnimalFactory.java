package com.isw.app.core.factories;

import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Animal;
import com.isw.app.enums.AnimalDetail;

public class AnimalFactory {

  public static Animal buildCarnivore() {
    Coord coord = Matrix.getRandomCoord();
    return new Animal(AnimalDetail.CARNIVORE, coord);
  }

  public static Animal buildHerbivore() {
    Coord coord = Matrix.getRandomCoord();
    return new Animal(AnimalDetail.HERBIVORE, coord);
  }
}
