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
