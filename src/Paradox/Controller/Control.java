/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Controller/Control.java
 */

package Paradox.Controller;

import Paradox.Model.*;
import Paradox.View.*;

import java.util.function.Consumer;

public class Control {
  private IOFile view;
  private Integral model;

  public Control(){
    Consumer<String> process = i -> processString(i);
    view = new IOFile();
    view.getInputStream().forEach(process);

  }

  private void processString(String i) {
    String s = i.trim();
    if (s.charAt(0) == '|') {
      model = new Integral(s);
    } else {
      model = new DefiniteIntegral(s);
    }
    Expression applyEx = model.apply();
    view.printOutput(applyEx.toString());
    model.getExpression().delete(); //as per instructions

  }


}
