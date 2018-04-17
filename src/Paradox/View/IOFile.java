/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/View/IOFile.java
 */

package Paradox.View;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IOFile {
  private Path input;
  private Path output;

  public IOFile() {
    input = Paths.get("integrals.txt");
    output = Paths.get("answers.txt");
  }

  public Stream<String> getInputStream() {
    try {
      return Files.lines(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void printOutput(String out) {
    try {
      BufferedWriter bw = Files.newBufferedWriter(output);
      bw.append(out);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
