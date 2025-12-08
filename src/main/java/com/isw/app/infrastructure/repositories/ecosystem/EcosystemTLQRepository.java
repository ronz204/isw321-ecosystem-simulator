package com.isw.app.infrastructure.repositories.ecosystem;

import java.io.IOException;
import java.io.BufferedWriter;
import com.isw.app.domain.enums.TLQPath;
import com.isw.app.domain.models.Ecosystem;
import com.isw.app.application.helpers.BufferedHelper;
import com.isw.app.infrastructure.repositories.BaseTLQRepository;

public class EcosystemTLQRepository extends BaseTLQRepository implements EcosystemRepository {

  @Override
  public void save(Ecosystem ecosystem) {
    try (BufferedWriter writer = BufferedHelper.getWriter(TLQPath.ECOSYSTEM)) {
      writeDelimiter(writer);
      writeField(writer, "turns", String.valueOf(ecosystem.getTurns()));
      writeField(writer, "balance", ecosystem.getBalance().getDisplay());
      writeField(writer, "flagZombie", String.valueOf(ecosystem.getFlagZombieMutation()));
      writeField(writer, "flagOmnivore", String.valueOf(ecosystem.getFlagOmnivoreExpansion()));
      writeDelimiter(writer);
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
