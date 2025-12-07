package com.isw.app.domain.core.attempts;

import com.isw.app.domain.core.objects.Animal;
import com.isw.app.domain.core.objects.Matrix;
import com.isw.app.domain.core.objects.Result;

public abstract class Attempt {
  public abstract Result execute(Animal animal, Matrix matrix);
}
