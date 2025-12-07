package com.isw.app.domain.core.behaviors;

public class ZombieBehavior extends Behavior {
  @Override
  public boolean canEat() {
    return true;
  }

  @Override
  public boolean canDie() {
    return false;
  }

  @Override
  public boolean canSex() {
    return false;
  }
}
