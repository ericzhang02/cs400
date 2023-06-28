import java.util.ArrayList;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

//package com.mkyong.xml.dom;

import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class ItemFinder implements IItemFinder {
  ArrayList<IItem> itemList = new ArrayList<IItem>();

  /**
   * Loads the shows by reading through the xml file.
   */
  @Override
  public void loadItems() {

    final String xmlFile = "./shop_items.xml";

    DocumentBuilderFactory databaseFile = DocumentBuilderFactory.newInstance();

    try {
      databaseFile.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

      DocumentBuilder database = databaseFile.newDocumentBuilder();
      Document documentDatabase = database.parse(new File(xmlFile));

      documentDatabase.getDocumentElement().normalize();
      NodeList listTags = documentDatabase.getElementsByTagName("item");
      for (int tempListTag = 0; tempListTag < listTags.getLength(); tempListTag++) {
        Node node = listTags.item(tempListTag);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
          Element nodeElement = (Element) node;

          //String id = nodeElement.getAttribute("UniqId");

          String id = nodeElement.getElementsByTagName("UniqId").item(0).getTextContent();

          String productName =
              nodeElement.getElementsByTagName("ProductName").item(0).getTextContent();
          double SellingPrice =
              Double.parseDouble(nodeElement.getElementsByTagName("SellingPrice").item(0).getTextContent().replaceAll("[\\n\\t\\$ ]",""));
          int quantity =
              Integer.parseInt(nodeElement.getElementsByTagName("Quantity").item(0).getTextContent().replaceAll("[\\n\\t ]",""));

          int reviewCount =
              Integer.parseInt(nodeElement.getElementsByTagName("ReviewCount").item(0).getTextContent().replaceAll("[\\n\\t ]",""));

          double AverageReviewRating =
              Double.parseDouble(nodeElement.getElementsByTagName("AverageReviewRating").item(0).getTextContent().replaceAll("[\\n\\t ]",""));

          //                    System.out.println("item : " + listTags);
          //                    System.out.println("UniqId: " + id);
          //                    System.out.println("Product Name: " + productName);
          //                    System.out.println("Selling Price: " + SellingPrice);
          //                    System.out.println("Quantity: " + quantity);
          //                    System.out.println("Review Count: " + reviewCount);
          //                    System.out.println("Average Review Rating: " + AverageReviewRating);

          //                    System.out.println("UniqId: " + id);
          //                  System.out.println("Product Name: " + productName);
          //                System.out.println("Selling Price: " + SellingPrice);
          //              System.out.println("Quantity: " + quantity);
          //            System.out.println("Review Count: " + reviewCount);
          //          System.out.println("Average Review Rating: " + AverageReviewRating);




          itemList.add(new Item(quantity, SellingPrice, AverageReviewRating,
              reviewCount, productName));
        }

      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }
  }
}


