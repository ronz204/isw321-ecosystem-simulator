package com.isw.app.models;

import com.isw.app.enums.EcosystemType;
import com.isw.app.helpers.IdentifierHelper;

public class Ecosystem {
  private final String PREFIX = "ECO";

  private final String id;
  private EcosystemType type;
  private Dashboard dashboard;

  public Ecosystem(EcosystemType type) {
    this.id = IdentifierHelper.generate(PREFIX);
    this.dashboard = new Dashboard();
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public EcosystemType getType() {
    return type;
  }

  public Dashboard getDashboard() {
    return dashboard;
  }
}
