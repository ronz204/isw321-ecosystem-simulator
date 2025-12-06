package com.isw.app.core.artifacts;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ArrayList;
import com.isw.app.core.objects.Coord;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Result;
import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Detail;
import com.isw.app.core.factories.AnimalFactory;
import com.isw.app.core.expansions.species.SpeciesExpansion;
import com.isw.app.core.expansions.mutations.MutationExpansion;

public class ContextArtifact {
  private static ContextArtifact instance;

  private int turn;
  private Matrix matrix;
  private List<Result> history;
  private Map<Detail, Integer> populations;
  private Map<Detail, Integer> extinctions;
  private Map<Detail, List<Animal>> animals;

  public List<SpeciesExpansion> species;
  public List<MutationExpansion> mutations;

  public static ContextArtifact getInstance() {
    if (instance == null) {
      instance = new ContextArtifact();
    }
    return instance;
  }

  private ContextArtifact() {
    initialize();
  }

  public void initialize() {
    this.turn = 0;
    this.matrix = new Matrix();
    this.animals = new HashMap<>();
    this.history = new ArrayList<>();
    this.extinctions = new HashMap<>();
    this.populations = new HashMap<>();

    this.species = new ArrayList<>();
    this.mutations = new ArrayList<>();

    for (Detail detail : Detail.values()) {
      animals.put(detail, new ArrayList<>());
      extinctions.put(detail, 0);
      populations.put(detail, 0);
    }
  }

  public int getTurn() {
    return turn;
  }

  public Matrix getMatrix() {
    return matrix;
  }

  public List<Result> getHistory() {
    return history;
  }

  public Map<Detail, Integer> getExtinctions() {
    return extinctions;
  }

  public void addSpecies(SpeciesExpansion expansion) {
    species.add(expansion);
  }

  public void addMutation(MutationExpansion expansion) {
    mutations.add(expansion);
  }

  public void applyExpansions() {
    for (SpeciesExpansion expansion : species) {
      expansion.apply(this);
    }

    for (MutationExpansion expansion : mutations) {
      expansion.apply(this);
    }
  }

  public void putPopulation(Detail detail, int count) {
    populations.put(detail, count);
  }

  public void populateEcosystem() {
    for (Entry<Detail, Integer> entry : populations.entrySet()) {
      Detail specie = entry.getKey();
      int counter = entry.getValue();

      for (int i = 0; i < counter; i++) {
        Coord coord = matrix.getRandomCoord();
        Animal animal = AnimalFactory.build(specie, coord);

        matrix.getSectorAt(coord).setAnimal(animal);
        animals.get(specie).add(animal);
      }
    }
  }
}
