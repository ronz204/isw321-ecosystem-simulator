package com.isw.app.models;

import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Sector;
import com.isw.app.helpers.IdentifierHelper;

public class Occurrence {
  private final String PREFIX = "OCC";

  private String uuid;
  private Animal animal;
  private Sector sector;
  private String description;

  public Occurrence(Animal animal, Sector sector) {
    uuid = IdentifierHelper.generate(PREFIX);
    this.animal = animal;
    this.sector = sector;
  }

  public String getUuid() {
    return uuid;
  }

  public Animal getAnimal() {
    return animal;
  }

  public Sector getSector() {
    return sector;
  }

  public String getDescription() {
    return description;
  }
}
