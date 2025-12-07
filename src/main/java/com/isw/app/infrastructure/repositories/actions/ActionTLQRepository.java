package com.isw.app.infrastructure.repositories.actions;

import java.io.IOException;
import java.io.BufferedWriter;
import com.isw.app.domain.enums.TLQPath;
import com.isw.app.domain.core.objects.Result;
import com.isw.app.application.helpers.BufferedHelper;
import com.isw.app.infrastructure.repositories.BaseTLQRepository;

public class ActionTLQRepository extends BaseTLQRepository implements ActionRepository {

  @Override
  public void save(Result result) {
    try (BufferedWriter writer = BufferedHelper.getWriter(TLQPath.ACTIONS)) {
      writeDelimiter(writer);
      writeField(writer, "actor", result.getActor().getLabel());
      writeField(writer, "action", result.getAction().getDisplay());
      writeDelimiter(writer);
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
