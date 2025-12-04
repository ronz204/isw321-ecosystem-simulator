package com.isw.app.core.factories;

import java.util.List;
import java.util.ArrayList;
import com.isw.app.core.objects.Animal;

public class BalanceFactory {

  public static List<Animal> buildBalanced() {
    List<Animal> animals = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      animals.add(AnimalFactory.buildHerbivore());
    }

    for (int i = 0; i < 18; i++) {
      animals.add(AnimalFactory.buildCarnivore());
    }

    return animals;
  }

  public static List<Animal> buildPredatorDominant() {
    List<Animal> animals = new ArrayList<>();

    for (int i = 0; i < 12; i++) {
      animals.add(AnimalFactory.buildHerbivore());
    }

    for (int i = 0; i < 25; i++) {
      animals.add(AnimalFactory.buildCarnivore());
    }

    return animals;
  }

  public static List<Animal> buildPreyDominant() {
    List<Animal> animals = new ArrayList<>();

    for (int i = 0; i < 30; i++) {
      animals.add(AnimalFactory.buildHerbivore());
    }

    for (int i = 0; i < 10; i++) {
      animals.add(AnimalFactory.buildCarnivore());
    }

    return animals;
  }
}
