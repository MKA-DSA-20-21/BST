
/* BST
 * DSA 2020-2021
 */
import java.util.*;
import java.lang.*;

public class BST<C extends Comparable<C>>{
    private C element;
    private BST<C> left;
    private BST<C> right;

    public BST(C elt){ element = elt; left = null; right = null; }
    public BST(C elt, BST<C> l, BST<C> r){
	element = elt; left = l; right = r;
    }

    // Binary node methods
    public BST<C> getLeft() { return left; }
    public BST<C> getRight() { return right; }
    public C getElement() { return element; }

    public boolean hasLeft() { return left != null; }
    public boolean hasRight() { return right != null; }
    public boolean hasChildren() { return hasLeft() || hasRight(); }
    public boolean hasTwoChildren() { return hasLeft() && hasRight(); }

    public void setElement(C elt) { element = elt; }
    public void setLeft(BST<C> l) { left = l; }
    public void setRight(BST<C> r) { right = r; }

    public void visit() { System.out.print(element + " "); }

    //Binary Tree methods
    //accessors
    public void inOrder() { /*left, parent, right*/
	if (this.hasLeft()) this.getLeft().inOrder();
	visit();
	if (this.hasRight()) this.getRight().inOrder();
    }
    public void preOrder() { /* parent, left, right */
	visit();
	if (this.hasLeft()) this.getLeft().preOrder();
	if (this.hasRight()) this.getRight().preOrder();
    }
    public void postOrder() { /* left, right, parent */
	if (this.hasLeft()) this.getLeft().postOrder();
	if (this.hasRight()) this.getRight().postOrder();
	visit();		
    }
    
    public void breadthFirst() { /* across the depths*/   }
    
    public boolean contains(C elt){
	BST<C> root = this;
	if (root.getElement().compareTo(elt) == 0) return true;
	if (root.getElement().compareTo(elt) < 0 && root.hasRight())
	    return root.getRight().contains(elt);    
	else if( root.getElement().compareTo(elt) > 0 && root.hasLeft())
	    return root.getLeft().contains(elt);
	else return false;
    }
    
    //mutators
    public boolean add(C elt) {
	BST<C> root = this;
	if (root == null) root = new BST(elt); //not necessary 
	else if (root.getElement().compareTo(elt) == 0 ) return false;
	else if (root.getElement().compareTo(elt) < 0){
	    if (root.hasRight()) return root.getRight().add(elt);
	    else root.setRight(new BST(elt));
	}
	else{
	    if (root.hasLeft()) return root.getLeft().add(elt);
	    else root.setLeft(new BST(elt));
	}
	return true;
    }

    private BST<C> smallestOnRight(){
	BST<C> curr = this.getRight();
	while (curr.hasLeft()){
	    curr = curr.getLeft();
	}
	return curr;
    }

    
    public boolean remove(C elt){
	//consider the root being the thing to remove
	if (this.getElement().compareTo(elt) > 0 && this.hasLeft()){ //go left
	    return this.getLeft().removeHelper(elt, this);
	}
	else if (this.getElement().compareTo(elt) < 0 && this.hasRight()){
	    return this.getRight().removeHelper(elt, this);
	}
	else if (this.getElement().compareTo(elt) == 0){
	    if (this.hasTwoChildren()){
		//this ok b/c this condition doesn't use parent
		return this.removeHelper(elt, null); 
	    }

	    else if (this.hasRight()){
		BST<C> temp = this.getRight();
		this.setElement(temp.getElement());
		this.setLeft(temp.getLeft());
		this.setRight(temp.getRight());
		return true;
	    }

	    else if (this.hasLeft()){
		BST<C> temp = this.getLeft();
		this.setElement(temp.getElement());
		this.setLeft(temp.getLeft());
		this.setRight(temp.getRight());
		return true;
	    }
	    else{ //no children
		this = null;
		return true;
	    }

	}
	else{
	    return false;  
	} 
    }

    
    private boolean removeHelper(C elt, BST<C> parent){
	// found the thing and two child situation
	if (this.getElement().compareTo(elt) == 0 && this.hasTwoChildren()){
	    //swap with smallest on right
	    C temp = this.getElement();
	    BST<C> toSwap = smallestOnRight();
	    this.setElement(toSwap.getElement());
	    toSwap.setElement(temp);
	    return this.getRight().removeHelper(elt, this);
	}
	else if (this.getElement().compareTo(elt) == 0 && this.hasRight() && !this.hasLeft()){
	    //need to know which child this is of parent's
	    if (this.getElement().compareTo(parent.getElement()) < 0){ // this is left of parent
		parent.setLeft(this.getRight());
		return true;
	    }
	    else { //this is right of parent
		parent.setRight(this.getRight());
		return true;
	    }
	    
	}
	else if (this.getElement().compareTo(elt) == 0 && this.hasLeft()){
	    //need to know which child this is of parent's
	    if (this.getElement().compareTo(parent.getElement()) < 0){ // this is left of parent
		parent.setLeft(this.getLeft());
		return true;
	    }
	    else { //this is right of parent
		parent.setRight(this.getLeft());
		return true;
	    }
	}
	//found it and its a leaf
	//haven't found it yet

	return false;   }
       
    public static void main(String[] args){
	BST<Integer> tree = new BST<Integer>(6);
	tree.add(3);
	tree.add(10);
	tree.add(8);
	tree.add(2);
	tree.add(4);
	tree.add(1);

	tree.postOrder();

	
    }
}
