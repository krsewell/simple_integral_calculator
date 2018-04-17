/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/Payload.java
 */

package Paradox.Model;

import java.util.regex.Pattern;

/**
 * This class is essentially a single-variable term class as it holds the details for one term.
 */

public class Payload implements Comparable<Payload> {
  int exponent;

  int coefficient;
  int coefficientDivisor;
  char variable;
  int special_condition; // 0->polynomial term 1->sin 2->cos 3->sec^2 4->x^-1

  public Payload() {
    setDefaults();
  }

  public Payload(int exponent, int coefficient) {
    setDefaults();
    this.coefficient = coefficient;
    this.exponent = exponent;
  }

  private void setDefaults() {
    this.exponent = 1;
    this.variable = 'x';
    this.coefficient = 0;
    this.coefficientDivisor = 1;
    this.special_condition = 0;
  }

  public Payload(String in) {  //of form (coefficient)(variable)^(exponent) where coefficient and exponent are optional.
    in = in.trim();
    setDefaults();
    char var = 'x';
    if (in.contains("sin")) {
      special_condition = 1;
      var = 's';
    } else if (in.contains("cos")) {
      special_condition = 2;
      var = 'c';

    } else if (in.contains("sec^2")) {
      special_condition = 3;
      var = 's';

    } else if (in.contains("x^-1")) {
      special_condition = 4;
    }


    //covers normal polynomial term
    int exp_pos = in.indexOf('^');
    int var_pos = in.indexOf(var);

    if (var_pos == -1) {
      exponent = 0;
      if (exp_pos == -1) {
        setCoefficient(Integer.parseInt(in));
      } else {
        Double d = Math.pow(
                Double.parseDouble(in.substring(0, exp_pos)),
                Double.parseDouble(in.substring(exp_pos + 1))
        );
        setCoefficient(d.intValue());
      }
    } else if (var_pos > 0 && var_pos < in.length()) {
      setCoefficient(Integer.parseInt(in.substring(0, var_pos)));
      if (exp_pos != -1 && exp_pos < in.length() && special_condition != 3)
        setExponent(Integer.parseInt(in.substring(exp_pos + 1)));
      else {
        setExponent(1);
      }
    }
  }

  public int getExponent() {
    return exponent;
  }

  public void setExponent(int exponent) {
    this.exponent = exponent;
  }

  public int getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  public int getCoefficientDivisor() {
    return coefficientDivisor;
  }

  public void setCoefficientDivisor(int coefficientDivisor) {
    this.coefficientDivisor = coefficientDivisor;
  }

  public char getVariable() {
    return variable;
  }

  public void setVariable(char variable) {
    this.variable = variable;
  }

  /**
   * Compares this object with the specified object for order.  Returns a
   * negative integer, zero, or a positive integer as this object is less
   * than, equal to, or greater than the specified object.
   * <p>
   * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
   * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
   * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
   * <tt>y.compareTo(x)</tt> throws an exception.)
   * <p>
   * <p>The implementor must also ensure that the relation is transitive:
   * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
   * <tt>x.compareTo(z)&gt;0</tt>.
   * <p>
   * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
   * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
   * all <tt>z</tt>.
   * <p>
   * <p>It is strongly recommended, but <i>not</i> strictly required that
   * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
   * class that implements the <tt>Comparable</tt> interface and violates
   * this condition should clearly indicate this fact.  The recommended
   * language is "Note: this class has a natural ordering that is
   * inconsistent with equals."
   * <p>
   * <p>In the foregoing description, the notation
   * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
   * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
   * <tt>0</tt>, or <tt>1</tt> according to whether the value of
   * <i>expression</i> is negative, zero or positive.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   */
  @Override
  public int compareTo(Payload o) {
    if (o == null) throw new NullPointerException("Error: object cannot be null.");
    if (this.special_condition < o.special_condition) {
      return 2;
    } else if (this.special_condition > o.special_condition) {
      return -2;
    }
    //then
    if (this.exponent > o.getExponent()) {
      if (this.special_condition == o.special_condition)
        return 1;
    } else if (this.exponent < o.getExponent()) {
      if (this.special_condition == o.special_condition)
        return -1;
    }
    return 0;
  }


  public void integrate() {
    switch (special_condition) {
      case 0:
        exponent++;
        coefficientDivisor *= exponent;
        break;
      case 1:
        coefficient *= -1;
        special_condition = 2;
        break;
      case 2:
        special_condition = 1;
        break;
      case 3: //out tan no change of state
        break;
      case 4: //out is ln x
    }
  }
  //sin -> -cos, cos -> sin, sec^2 -> tan
  // | x^-1 dx = ln|x|

  public String toString() {
    String co = coefficientDivisor != 1 ?
            String.format("(%d/%d)", coefficient, coefficientDivisor) :
            String.format("%d", coefficient);

    if(Double.parseDouble(co) >= 0) {
      co = '+' + co;
    }

    switch (special_condition) {
      case 0:
        return String.format("%sx^%d ", co, exponent);
      case 1:
        return String.format("%ssinx", co);
      case 2:
        return String.format("%scosx", co);
      case 3:
        return String.format("%stanx", co);
      case 4:
        return String.format("%sln|x|", co);
      default:
        return ""; //shouldn't run

    }

  }

  //best effort reduction up to divisible by 99
  public void reduce() {
    boolean done = false;
    while (!done) {
      done = true;
      for (int c = 1; c <= 100; c++) {
        boolean isPrime = true;
        for (int i = 2; i < c; i++) {
          if (c % i == 0) {
            isPrime = false;
            break;
          }

          if (isPrime) {
            if (coefficientDivisor % c == 0 && coefficient % c == 0) {
              coefficient /= c;
              coefficientDivisor /= c;
              done = false;
            }
          }
        }
      }
    }
  }
}
