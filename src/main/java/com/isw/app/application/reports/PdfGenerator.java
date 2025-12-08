package com.isw.app.application.reports;

import com.isw.app.domain.core.objects.Detail;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PdfGenerator {

  public void generate(ReportData data, String outputPath) throws FileNotFoundException {
    PdfBuilder builder = new PdfBuilder(outputPath);

    builder
        .addTitle("Reporte de Simulación del Ecosistema")
        .addEmptyLine()
        .addParagraph("Fecha de generación: " + getCurrentDateTime())
        .addEmptyLine()
        .addSubtitle("Resumen de la Simulación")
        .addParagraph("Total de turnos ejecutados: " + data.getTotalTurns())
        .addEmptyLine();

    if (data.hasExtinctions()) {
      builder.addSubtitle("Especies Extintas");
      addExtinctionTable(builder, data.getExtinctionData());
    } else {
      builder.addParagraph("✓ No hubo especies extintas durante la simulación");
    }

    builder.close();
  }

  private void addExtinctionTable(PdfBuilder builder, Map<Detail, Integer> extinctions) {
    PdfBuilder.TableBuilder table = builder
        .createTable(2)
        .addHeaderCell("Especie")
        .addHeaderCell("Turno de Extinción");

    extinctions.forEach((detail, turn) -> {
      if (turn > 0) {
        table
            .addCell(detail.name())
            .addCell(String.valueOf(turn));
      }
    });

    table.build();
  }

  private String getCurrentDateTime() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    return LocalDateTime.now().format(formatter);
  }
}
