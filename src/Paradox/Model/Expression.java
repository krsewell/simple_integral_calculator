/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/Expression.java
 */

package Paradox.Model;

public abstract class Expression {
  //Function<InputType, OutputType> Function.apply(InputType) implementation is expression

  public abstract void delete();
  public abstract boolean isWellFormed();

}
