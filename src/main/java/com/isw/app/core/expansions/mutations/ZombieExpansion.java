package com.isw.app.core.expansions.mutations;

import com.isw.app.core.artifacts.ContextArtifact;

public class ZombieExpansion extends MutationExpansion {

  @Override
  public String getName() {
    return "Zombie Expansion";
  }

  @Override
  public String getDescription() {
    return "Introduces zombies that infect other animals upon contact.";
  }

  @Override
  public void apply(ContextArtifact context) {
    
  }
}
