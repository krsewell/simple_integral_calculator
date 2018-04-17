/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/PolyNomial.java
 */

package Paradox.Model;

import java.util.ArrayList;

/**
 * Just a wrapper for the BST to implement additional functions pertaining to polynomials.
 */
public class PolyNomial extends Expression {
  BinarySearchTree<Payload> terms;

  public BinarySearchTree<Payload> getTerms() {
    return terms;
  }

  PolyNomial() {
    terms = new BinarySearchTree<>();
  }

  PolyNomial(String in) {
    terms = new BinarySearchTree<>();
    addExpression(in);
  }

  public void addExpression(String in) {
    // a+ b - c +d where a,b,c,d are well-formed terms & spaces may or may not be provided.
    String work = in.replaceAll("( \\+ )|(\\+ )|( \\+)", "#"); //remove spaces around '+'
    work = work.replaceAll("- ", "-"); //move negative close to its term.
    work = work.replaceAll("(?<=\\p{Alnum})-", "#-"); //'-' proceeded by something that isn't a ' '
    //at this point all terms are separated by '#'
    String[] termArray = work.split("#");
    for (String s : termArray) {
      Payload pl = new Payload(s);
      Payload n = null;
      if (terms.search(pl) != null)
        n = (Payload) terms.search(pl);
      if (n == null) {
        terms.insert(pl);
      } else {
        //only returns if special condition & exponent are equal
        //we shouldn't need to handle fractions on input per specification.
        n.setCoefficient(n.getCoefficient() + pl.getCoefficient());
      }
    }
  }

  public void reduce() {
    ArrayList<Payload> termList = terms.toArrayList();
    for (Payload p : termList) {
      p.reduce();
    }
  }

  @Override
  public String toString() {
    ArrayList al = terms.toArrayList();
    StringBuilder rtn = new StringBuilder();
    for(Object p : al){
      rtn.append(p.toString());
    }
    if (rtn.charAt(0) == '+')
      rtn.deleteCharAt(0); //don't want the first character to have a +
    return rtn.toString();
  }

  @Override
  public void delete() {
    terms.delete();
  }

  @Override
  public boolean isWellFormed() {
    return true; //shouldn't need this as file should contain no errors att.
  }
}
