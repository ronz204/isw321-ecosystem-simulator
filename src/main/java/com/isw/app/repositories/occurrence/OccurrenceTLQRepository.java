package com.isw.app.repositories.occurrence;

import java.io.IOException;
import java.io.BufferedWriter;
import com.isw.app.enums.DataPath;
import com.isw.app.models.Occurrence;
import com.isw.app.helpers.BufferedHelper;
import com.isw.app.repositories.BaseTLQRepository;

public class OccurrenceTLQRepository extends BaseTLQRepository implements OccurrenceRepository {

  @Override
  public void save(Occurrence occurrence) {
    try (BufferedWriter writer = BufferedHelper.getWriter(DataPath.OCCURRENCES)) {
      writeDelimiter(writer);
      writeField(writer, "uuid", occurrence.getUuid());
      writeField(writer, "animal_uuid", occurrence.getAnimal().getUuid());
      writeField(writer, "animal_type", occurrence.getAnimal().getType());
      writeField(writer, "sector_row", String.valueOf(occurrence.getSector().getCoord().getRow()));
      writeField(writer, "sector_col", String.valueOf(occurrence.getSector().getCoord().getCol()));
      writeField(writer, "description", occurrence.getDescription());
      writeDelimiter(writer);
      writer.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
