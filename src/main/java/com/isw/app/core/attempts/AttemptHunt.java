package com.isw.app.core.attempts;

import java.util.List;
import java.util.stream.Collectors;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Result;
import com.isw.app.helpers.RandomHelper;

public class AttemptHunt {

  public static Result execute(Animal hunter, Matrix matrix) {
    List<Animal> adjacent = matrix.getAdjacentAnimals(hunter.getCoord());

    List<Animal> preies = adjacent.stream()
        .filter(animal -> animal.getTier() < hunter.getTier())
        .collect(Collectors.toList());

    if (preies.isEmpty()) {
      return Result.idle();
    }

    int choose = RandomHelper.getRandomInt(0, preies.size() - 1);
    Animal prey = preies.get(choose);
    
    return Result.eat(hunter, prey);
  }
}
