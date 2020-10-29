
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

    public void setElement(C elt) { element = elt; }
    public void setLeft(BST<C> l) { left = l; }
    public void setRight(BST<C> r) { right = r; }

    public void visit() { System.out.print(element + " "); }

    //Binary Tree methods
    //accessors
    public void inOrder() { /*left, parent, right*/ }
    public void preOrder() { /* parent, left, right */   }
    public void postOrder() { /* left, right, parent */   }
    
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
	if (root == null) root = new BST(elt);
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
    
    public boolean remove(C elt){ return false;   }
       
    public static void main(String[] args){
	BST<Integer> tree = new BST<Integer>(6);
	tree.add(3);
	tree.add(10);

	tree.visit();
	tree.getLeft().visit();
	tree.getRight().visit();
    }
}
