/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/Integral.java
 */

package Paradox.Model;

import java.util.function.Consumer;
import java.util.function.Function;

public class Integral implements Function<Expression,Expression>{
  private Expression poly;

  public Integral(){
    poly = new PolyNomial();
  }

  public Integral(String in) {
    StringBuilder work = new StringBuilder();
    work.append(in.trim());
    work.deleteCharAt(0);
    work.deleteCharAt(work.length()-1);
    work.deleteCharAt(work.length()-1);

    poly = new PolyNomial(work.toString());
  }


  public Expression getExpression(){
    return poly;
  }

  @Override
  public String toString(){

    return apply().toString();
  }

  /**
   * Just a default to use with an already saved expression.
   *
   * @return the function result
   */
  public Expression apply() {
    return apply(poly);
  }

  /**
   * Applies this function to the given argument.
   *
   * @param expression the function argument
   * @return the function result
   */
  @Override
  public Expression apply(Expression expression) {
    Consumer<Payload> consume = Integral::integrate;
    ((PolyNomial) expression).getTerms().toEach(consume);
    return null;
  }

  private static void integrate(Payload i) {
    i.integrate();
  }
}
