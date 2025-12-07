package com.isw.app.infrastructure.repositories.actions;

import com.isw.app.domain.core.objects.Result;

public interface ActionRepository {
  void save(Result result);
}
