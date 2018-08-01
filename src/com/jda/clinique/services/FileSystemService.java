package com.jda.clinique.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.jda.clinique.util.Constants;

public class FileSystemService {
  public <T> T readFile(File file) throws JsonIOException, JsonSyntaxException, FileNotFoundException {
    Gson gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT).create();
    Type type = new TypeToken<ArrayList<T>>() {}.getType();
    return gson.fromJson(new BufferedReader(new FileReader(file)), type);
  }
  
  public <T> void saveFile(T item, File file) throws IOException {
    file.createNewFile();
    try (Writer writer = new FileWriter(file)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat(Constants.DATE_FORMAT).create();
      gson.toJson(item, writer);
    }
  }
}
