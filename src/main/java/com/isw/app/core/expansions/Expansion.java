package com.isw.app.core.expansions;

import com.isw.app.core.artifacts.ContextArtifact;

public abstract class Expansion {
  public abstract String getName();
  public abstract String getDescription();
  public abstract void apply(ContextArtifact context);
}
