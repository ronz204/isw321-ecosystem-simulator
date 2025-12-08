package com.isw.app.application.reports;

import java.io.IOException;
import com.itextpdf.layout.Document;
import java.io.FileNotFoundException;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.properties.HorizontalAlignment;
import java.net.MalformedURLException;

public class PdfBuilder {
  private PdfWriter writer;
  private PdfDocument pdfDocument;
  private Document document;

  public PdfBuilder(String filePath) throws FileNotFoundException {
    this.writer = new PdfWriter(filePath);
    this.pdfDocument = new PdfDocument(writer);
    this.document = new Document(pdfDocument);
  }

  public PdfBuilder addTitle(String title) {
    Paragraph titleParagraph = new Paragraph(title)
        .setFontSize(20)
        .setBold()
        .setTextAlignment(TextAlignment.CENTER)
        .setMarginBottom(20);
    document.add(titleParagraph);
    return this;
  }

  public PdfBuilder addSubtitle(String subtitle) {
    Paragraph subtitleParagraph = new Paragraph(subtitle)
        .setFontSize(16)
        .setBold()
        .setMarginTop(15)
        .setMarginBottom(10);
    document.add(subtitleParagraph);
    return this;
  }

  public PdfBuilder addParagraph(String text) {
    Paragraph paragraph = new Paragraph(text)
        .setFontSize(12)
        .setMarginBottom(10);
    document.add(paragraph);
    return this;
  }

  public PdfBuilder addEmptyLine() {
    document.add(new Paragraph("\n"));
    return this;
  }

  public PdfBuilder addImage(String imagePath) throws MalformedURLException {
    Image image = new Image(ImageDataFactory.create(imagePath));
    image.setHorizontalAlignment(HorizontalAlignment.CENTER);
    image.setWidth(450);
    document.add(image);
    return this;
  }

  public TableBuilder createTable(int columns) {
    return new TableBuilder(this, columns);
  }

  void addTable(Table table) {
    document.add(table);
  }

  public void close() {
    try {
      document.close();
      pdfDocument.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static class TableBuilder {
    private final PdfBuilder parent;
    private final Table table;

    TableBuilder(PdfBuilder parent, int columns) {
      this.parent = parent;
      this.table = new Table(columns);
      this.table.setWidth(500);
      this.table.setMarginBottom(20);
    }

    public TableBuilder addHeaderCell(String text) {
      table.addHeaderCell(
          new Paragraph(text)
              .setBold()
              .setFontSize(12)
              .setBackgroundColor(ColorConstants.LIGHT_GRAY)
              .setTextAlignment(TextAlignment.CENTER));
      return this;
    }

    public TableBuilder addCell(String text) {
      table.addCell(
          new Paragraph(text)
              .setFontSize(11)
              .setTextAlignment(TextAlignment.CENTER));
      return this;
    }

    public PdfBuilder build() {
      parent.addTable(table);
      return parent;
    }
  }
}
