package com.isw.app.core.behavior;

import com.isw.app.core.objects.Animal;
import com.isw.app.core.objects.Matrix;
import com.isw.app.core.objects.Result;

public class OmnivoreBehavior extends Behavior {

  @Override
  public Result act(Animal animal, Matrix matrix) {
    return Result.idle();
  }


  @Override
  public boolean canEat() {
    return true;
  }

  @Override
  public boolean canDie() {
    return true;
  }

  @Override
  public boolean canReproduce() {
    return true;
  }

}
