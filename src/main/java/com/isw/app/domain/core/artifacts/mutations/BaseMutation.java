package com.isw.app.domain.core.artifacts.mutations;

import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.artifacts.Artifact;

public abstract class BaseMutation extends Artifact {
  public abstract Animal mutate(Animal child);
}
