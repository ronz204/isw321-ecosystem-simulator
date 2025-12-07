package com.isw.app.domain.core.objects;

public class Result {

  private Action action;
  private Animal actor;
  private Animal child;
  private Animal target;
  private boolean success;

  private Result(Action action, boolean success) {
    this.action = action;
    this.success = success;
  }

  public static Result move(Animal actor) {
    Result result = new Result(Action.MOVE, true);
    result.actor = actor;
    return result;
  }

  public static Result eat(Animal actor, Animal target) {
    Result result = new Result(Action.EAT, true);
    result.target = target;
    result.actor = actor;
    return result;
  }

  public static Result sex(Animal actor, Animal child) {
    Result result = new Result(Action.SEX, true);
    result.actor = actor;
    result.child = child;
    return result;
  }

  public static Result death(Animal actor) {
    Result result = new Result(Action.DEATH, true);
    result.actor = actor;
    return result;
  }

  public static Result idle(Animal actor) {
    Result result = new Result(Action.IDLE, true);
    result.actor = actor;
    return result;
  }

  public static Result failed(Animal actor) {
    Result result = new Result(Action.IDLE, false);
    result.actor = actor;
    return result;
  }

  public Action getAction() {
    return action;
  }

  public boolean isSuccess() {
    return success;
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
