package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
  
  public static String readJsonAsString(String filename) {
      ObjectMapper mapper = new ObjectMapper();
      try {
          BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
          String line = null;
          StringBuilder builder = new StringBuilder();
          while ((line = reader.readLine()) != null) {
              builder.append(line);
          }
          return builder.toString();
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }
  
}
