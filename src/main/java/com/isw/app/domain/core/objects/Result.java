package com.isw.app.domain.core.objects;

public class Result {

  private Action action;
  private Animal actor;
  private Animal child;
  private Animal target;

  private Result(Action action) {
    this.action = action;
  }

  public static Result move(Animal actor) {
    Result result = new Result(Action.MOVE);
    result.actor = actor;
    return result;
  }

  public static Result eat(Animal actor, Animal target) {
    Result result = new Result(Action.EAT);
    result.target = target;
    result.actor = actor;
    return result;
  }

  public static Result sec(Animal actor, Animal child) {
    Result result = new Result(Action.SEX);
    result.actor = actor;
    result.child = child;
    return result;
  }

  public static Result death(Animal actor) {
    Result result = new Result(Action.DEATH);
    result.actor = actor;
    return result;
  }

  public static Result idle() {
    Result result = new Result(Action.IDLE);
    return result;
  }

  public Action getAction() {
    return action;
  }

  public Animal getActor() {
    return actor;
  }

  public Animal getChild() {
    return child;
  }

  public Animal getTarget() {
    return target;
  }
}
