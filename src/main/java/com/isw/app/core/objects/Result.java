package com.isw.app.core.objects;

public class Result {

  private Action action;

  private Animal actor;
  private Animal child;
  private Animal target;

  private Coord fromCoord;
  private Coord toCoord;

  private Result(Action action) {
    this.action = action;
  }

  public static Result move(Animal actor, Coord next) {
    Result result = new Result(Action.MOVE);
    result.fromCoord = actor.getCoord();
    result.toCoord = next;
    result.actor = actor;
    return result;
  }

  public static Result eat(Animal actor, Animal target) {
    Result result = new Result(Action.EAT);
    result.target = target;
    result.actor = actor;
    return result;
  }

  public static Result reproduce(Animal actor, Animal child) {
    Result result = new Result(Action.REPRODUCE);
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
    return new Result(Action.IDLE);
  }

  public Action getAction() {
    return action;
  }

  public Coord getFromCoord() {
    return fromCoord;
  }

  public Coord getToCoord() {
    return toCoord;
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
