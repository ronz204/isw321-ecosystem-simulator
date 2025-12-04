package com.isw.app.core.factories;

import java.util.List;
import com.isw.app.enums.Balance;
import com.isw.app.models.Ecosystem;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Animal;

public class EcosystemFactory {

  public static Ecosystem create(Balance balance, int maxTurns,
      boolean thirdSpeciesEnabled, boolean geneticMutationEnabled) {
    Matrix matrix = new Matrix();

    List<Animal> animals = generateAnimals(balance);

    populateMatrix(matrix, animals);

    return new Ecosystem(matrix, maxTurns, balance.getValue(),
        thirdSpeciesEnabled, geneticMutationEnabled);
  }

  private static List<Animal> generateAnimals(Balance balance) {
    return switch (balance) {
      case BALANCED -> BalanceFactory.buildBalanced();
      case PREY_DOMINANT -> BalanceFactory.buildPreyDominant();
      case PREDATOR_DOMINANT -> BalanceFactory.buildPredatorDominant();
    };
  }

  private static void populateMatrix(Matrix matrix, List<Animal> animals) {
    for (Animal animal : animals) {
      int row = animal.getCoord().getRow();
      int col = animal.getCoord().getCol();

      var sector = matrix.getSectorAt(row, col);
      if (sector.isEmpty()) {
        sector.setAnimal(animal);
      } else {
        placeInEmptyCell(matrix, animal);
      }
    }
  }

  private static void placeInEmptyCell(Matrix matrix, Animal animal) {
    var emptySectors = matrix.getEmptySectors();

    if (!emptySectors.isEmpty()) {
      var emptyCoord = emptySectors.get(0);
      animal.getCoord().setRow(emptyCoord.getRow());
      animal.getCoord().setCol(emptyCoord.getCol());

      matrix.getSectorAt(emptyCoord.getRow(), emptyCoord.getCol()).setAnimal(animal);
    }
  }
}
