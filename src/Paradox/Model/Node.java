/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/Node.java
 */

package Paradox.Model;

public class Node<E extends Comparable<E>> implements Cloneable,Comparable<Node<E>> {
  private Comparable<E> object; //ensures object stored is comparable.
  private Node<E> right;
  private Node<E> left;

  public Node(){
    right = null;
    left = null;
    object = null;
  }

  public Node(Comparable<E> obj){
    right = null;
    left = null;
    object = obj;
  }

  public Comparable<E> getObject() {
    return object;
  }

  public void setObject(Comparable<E> object) {
    this.object = object;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node<E> right) {
    this.right = right;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node<E> left) {
    this.left = left;
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
   * @param other the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object
   * is less than, equal to, or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it
   *                              from being compared to this object.
   */

  @Override @SuppressWarnings("unchecked")
  public int compareTo(Node<E> other) throws NullPointerException{
    // this puts the burden of comparing two objects on the implementation of that object and not the node/bst.
    // a.k.a pass the buck
    if (other == null) throw new NullPointerException();
    return getObject().compareTo((E) other.getObject());
  }

}
