package com.isw.app.domain.core.objects;

public class Result {

  private Animal actor;
  private Action action;
  private boolean success;
  private String description;

  private Result(Action action, boolean success) {
    this.action = action;
    this.success = success;
  }

  public static Result build(Animal actor, Action action, String description) {
    Result result = new Result(action, true);
    result.description = description;
    result.actor = actor;
    return result;
  }

  /*
   * public static Result eat(Animal actor, String description) {
   * Result result = new Result(Action.EAT, true);
   * result.actor = actor;
   * return result;
   * }
   * 
   * public static Result sex(Animal actor) {
   * Result result = new Result(Action.SEX, true);
   * result.actor = actor;
   * return result;
   * }
   * 
   * public static Result death(Animal actor) {
   * Result result = new Result(Action.DEATH, true);
   * result.actor = actor;
   * return result;
   * }
   * 
   * public static Result idle(Animal actor) {
   * Result result = new Result(Action.IDLE, true);
   * result.actor = actor;
   * return result;
   * }
   * 
   * public static Result failed(Animal actor) {
   * Result result = new Result(Action.IDLE, false);
   * result.actor = actor;
   * return result;
   * }
   */

  public Animal getActor() {
    return actor;
  }

  public Action getAction() {
    return action;
  }

  public boolean isSuccess() {
    return success;
  }

  public String getDescription() {
    return description;
  }
}
