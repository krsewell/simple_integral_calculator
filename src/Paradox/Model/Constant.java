/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/Constant.java
 */

package Paradox.Model;

public class Constant extends Expression {
  int constant = 0;

  public Constant(int val){
    constant = val;
  }

  public int getConstant(){
    return constant;
  }

  @Override
  public String toString(){
    return String.valueOf(constant);
  }

  @Override
  public void delete() {
    constant = Integer.MAX_VALUE;
  }

  @Override
  public boolean isWellFormed() {
    return true;
  }
}
