package com.isw.app.models;

import com.isw.app.helpers.IdentifierHelper;

public class Ecosystem {
  private final String PREFIX = "ECO";

  private final String id;

  public Ecosystem() {
    this.id = IdentifierHelper.generate(PREFIX);
  }

  public String getId() {
    return id;
  }
}