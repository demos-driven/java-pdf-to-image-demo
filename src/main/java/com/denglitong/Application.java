/**
 * Copyright (C) 2022 Urban Compass, Inc.
 */
package com.denglitong;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class Application {

  public static void main(String[] args) throws IOException {
    String pdfName = "ComputerNetworking.pdf";
    String pdfPagePathPrefix = System.getenv().get("PWD") + "/src/main/resources/" +
        pdfName.replace(".pdf", "");

    URL pdfURL = Application.class.getClassLoader().getResource(pdfName);
    PDDocument document = PDDocument.load(new File(pdfURL.getPath()));

    List<PDPage> list = document.getDocumentCatalog().getAllPages();

    System.out.println("Total pages to be converted -> " + list.size());

    int pageNumber = 1;
    for (PDPage page : list) {
      BufferedImage image = page.convertToImage();
      File outputFile = new File(pdfPagePathPrefix + "_" + pageNumber + ".jpg");
      System.out.println("Image Created -> " + outputFile.getAbsolutePath());
      ImageIO.write(image, "jpg", outputFile);
      pageNumber++;
    }
    document.close();
  }
}
