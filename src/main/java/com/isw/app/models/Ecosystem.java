package com.isw.app.models;

import com.isw.app.enums.EcosystemType;

public class Ecosystem {

  private EcosystemType type;
  private Dashboard dashboard;

  public Ecosystem(EcosystemType type) {
    this.dashboard = new Dashboard();
    this.type = type;
  }

  public EcosystemType getType() {
    return type;
  }

  public Dashboard getDashboard() {
    return dashboard;
  }
}
