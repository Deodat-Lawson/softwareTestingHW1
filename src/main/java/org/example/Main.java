package org.example;

import java.util.ArrayList;
public class Main {
  public static void main(String[] args) {


    ArrayList<String> list = new ArrayList<String>(3);
    list.add("Hello");
    list.add("World");
    list.add("!");
    list.add("!");

    for (String str : list) {
      System.out.println(str);
    }
  }
}