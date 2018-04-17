/*
 *  Copyright (C) Kristopher Sewell - All Rights Reserved
 *  Written by Kristopher Sewell, Apr 2018
 *
 *  Name: Kristopher Sewell
 *  NETID: kjs170430
 *  Class: CE2336.002
 *
 *  File: ./Paradox/Model/BinarySearchTree.java
 */

package Paradox.Model;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

@SuppressWarnings({"unchecked","unused"})
public class BinarySearchTree<E extends Comparable<E>> implements Cloneable {

  private Node<E> root;
  private int size;
  
  
  public BinarySearchTree(){
    root = null;
    size = 0;
  }

  public BinarySearchTree(Node<E> root){
    this.root = root;
    size = 1;
  }

  /* 
   *  Getters and Setters 
   */
  
  public Node<E> getRoot() {
    return root;
  }

  public void setRoot(Node<E> root) {
    this.root = root;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public void insert(Comparable<E> obj){
    Node<E> insert = new Node<>(obj);
    root = insert(this.root,insert);
    size++;
  }

  private Node<E> insert(Node<E> root, Node<E> newNode) {

    if(root == null) return newNode;

    //root.left is bigger then object to be inserted
    if (root.compareTo(newNode) > 0){
      root.setLeft(insert(root.getLeft(),newNode));

    } else { //root.left is less then or equal to object to be inserted
      root.setRight(insert(root.getRight(),newNode));
    }

    return root;
  }

  public Comparable<E> search(Comparable<E> obj) {
    Node n = search(this.root,obj);
    return n == null ? null : n.getObject();
  }

  private Node<E> search(Node<E> root, Comparable<E> obj) {
    if (root == null) return null;
    if (root.getObject().compareTo((E) obj) == 0)
      return root;
    if (root.getObject().compareTo((E) obj) > 0)
      return search((Node<E>) root.getLeft(), obj);

    return search((Node<E>) root.getRight(), obj);
  }

  public void delete(){
    deleteOrder(this.root);
    setRoot(null);
    setSize(0);
  }

  private void deleteOrder(Node<E> root){
    if (root == null) return;
    deleteOrder(root.getLeft());
    deleteOrder(root.getRight());
    //remove all node reference pointers
    root.setLeft(null);
    root.setRight(null);
    root.setObject(null);
  }

  /**
   * Clones the binarysearchtree with the same objects held in this tree.
   * @return BinarySearchTree
   */
  @Override
  public BinarySearchTree<E> clone(){
    try {
      BinarySearchTree<E> clone = (BinarySearchTree<E>) super.clone();
      clone.root = cloneTree(root);
      return clone;
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
      return null;
    }
  }

  private Node<E> cloneTree(Node<E> t){
    if (t == null) return null;
    Node<E> newNode = new Node<>();
    newNode.setObject(t.getObject());
    newNode.setLeft(cloneTree(t.getLeft()));
    newNode.setRight(cloneTree(t.getRight()));
    return newNode;
  }

  /**
   * Compares both trees for nodes that contain equivalent objects. This method
   * gives no regard for structure. Therefore an optimized tree maybe equivalent
   * to an un-optimized tree, if and only if they contain the same objects.
   * @param that object to compare
   * @return boolean true if both are BST and contain the same objects.
   */
  @Override
  public boolean equals(Object that){
    if(that == null) return false;

    if(that instanceof BinarySearchTree<?>){
      if(this.size != ((BinarySearchTree<?>) that).size) return false;
      ArrayList tis = this.toArrayList();
      ArrayList tat = ((BinarySearchTree<?>) that).toArrayList();
      for (Object o : tis) {
        try {   //may throw if they are not of same type.
          if (!tat.contains(o)) return false;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      // at this point all object o exist in tat.
      // however tat could contain extra nodes, therefor we must check the reverse.
      for (Object o : tat) {
        try {
          if(!tis.contains(o)) return false;
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return true;
    }
    return false;
  }

  public void toEach(Consumer<E> t){
    toEach(t,this.root);
  }

  public Integer toEach(Function f) {
    return toEach(f,this.root);
  }

  private void toEach(Consumer<E> t, Node<E> r){
    if (r == null) return;
    toEach(t,r.getLeft());
    t.accept((E) r.getObject());
    toEach(t,r.getRight());
  }

  private Integer toEach(Function<E,Integer> f, Node<E> r) {
    int sum = 0;
    if (r == null) return 0;
    sum += toEach(f,r.getLeft());
    sum += toEach(f,r.getRight());
    return f.apply((E) r.getObject()) + sum;

  }

  /**
   * Returns an arrayList containing objects from this tree. Stores elements in-order.
   * Use caution as no type safety is insured other then what is provided by ArrayList.
   * @return ArrayList of E type.
   */
  
  public ArrayList<E> toArrayList(){
    ArrayList<E> al = new ArrayList<>();
    storeInOrder(this.root,al);
    return al;
  }

  public ArrayList<E> toArrayListPre(){
    ArrayList<E> al = new ArrayList<>();
    storePreOrder(this.root,al);
    return al;
  }

  public ArrayList<E> toArrayListPost(){
    ArrayList<E> al = new ArrayList<>();
    storePostOrder(this.root,al);
    return al;
  }

  private void storeInOrder(Node<E> root, ArrayList<E> array){
    if (root == null) return;
    storeInOrder(root.getLeft(),array);
    array.add((E) root.getObject());
    storeInOrder(root.getRight(),array);
  }

  private void storePreOrder(Node<E> root, ArrayList<E> array){
    if (root == null) return;
    array.add((E) root.getObject());
    storePreOrder(root.getLeft(),array);
    storePreOrder(root.getRight(),array);
  }

  private void storePostOrder(Node<E> root, ArrayList<E> array){
    if (root == null) return;
    storePostOrder(root.getLeft(),array);
    storePostOrder(root.getRight(),array);
    array.add((E) root.getObject());
  }

}
