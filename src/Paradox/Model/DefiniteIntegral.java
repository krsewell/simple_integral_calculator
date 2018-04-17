/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/DefiniteIntegral.java
 */

package Paradox.Model;

public class DefiniteIntegral extends Integral {
  private int lowerBound;
  private int upperBound;

  public DefiniteIntegral(String s) {
    super(s);
    lowerBound = Integer.parseInt(
            s.substring(0,s.indexOf("|"))
    );
    upperBound = Integer.parseInt(
            s.substring(s.indexOf("|")+1,s.indexOf(" "))
    );
  }

  @Override
  public Expression apply(){
    return apply(super.getExpression());
  }


  @Override
  public Expression apply(Expression o){
    Expression ex = super.apply(o);
    int result = ((PolyNomial) ex).getTerms().toEach( i -> {
      double U = Math.pow((double) upperBound, (double) ((Payload)i).exponent) *
              ((Payload)i).coefficient / ((Payload)i).coefficientDivisor;
      double L = Math.pow((double) lowerBound, (double) ((Payload)i).exponent) *
              ((Payload)i).coefficient / ((Payload)i).coefficientDivisor;
      Double r = U - L;
      return r.intValue();
    });

    return new Constant(result);
  }

  @Override
  public String toString(){
    return apply(super.getExpression()).toString();
  }
}
