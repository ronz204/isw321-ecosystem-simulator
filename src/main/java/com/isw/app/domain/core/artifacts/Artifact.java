package com.isw.app.domain.core.artifacts;

import com.isw.app.domain.core.setup.SimulatorConfig;

public abstract class Artifact {  
  public abstract String getName();
  public abstract String getDescription();
  public abstract void apply(SimulatorConfig config);
}
