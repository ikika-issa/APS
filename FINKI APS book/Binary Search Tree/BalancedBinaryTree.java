//Check if the BST is balanced.

import java.util.Scanner;

class BNode<E extends Comparable<E>> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BinarySearchTree<E extends Comparable<E>> {
    
    private BNode<E> root;

    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(E x) {
        root = insert(x, root);
    }
    
    public void remove(E x) {
        root = remove(x, root);
    }
    
    public E findMin() {
        return elementAt(findMin(root));
    }

    public E findMax() {
        return elementAt(findMax(root));
    }

    public BNode<E> find(E x) {
        return find(x, root);
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }


    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }


    private E elementAt(BNode<E> t) {
        if (t == null)
            return null;
        return t.info;
    }


    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } else;  // Duplicate; do nothing
        return t;
    }

    @SuppressWarnings({"raw", "unchecked"})
    private BNode<E> remove(Comparable x, BNode<E> t) {
        if (t == null)
            return t;   // Item not found; do nothing

        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null)
                return t.left;
            else
                return t.right;
        }
        return t;
    }


    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }


    private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }


    private BNode<E> find(E x, BNode<E> t) {
        if (t == null)
            return null;

        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t;
        }
    }

    private void printTree(BNode<E> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.info);
            printTree(t.right);
        }
    }

    public void printTreeWithIndent() {
        printTreeWithIndent(root, 0);
    }

    private void printTreeWithIndent(BNode<E> t, int indent) {
        if (t != null) {
            printTreeWithIndent(t.left, indent+1);
            for (int i=0;i<indent;i++)System.out.print("   ");
            System.out.println(t.info);
            printTreeWithIndent(t.right, indent+1);
        }
    }

    public BNode<E> getRoot() {
        return root;
    }
}

public class BalancedBinaryTree{
    public static int height(BNode<Integer> node){
        if(node == null){
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static boolean isBalanced(BNode<Integer> node){
        int leftH;
        int rightH;

        if(node == null){
            return true;
        }

        leftH = height(node.left);
        rightH = height(node.right);

        if(Math.abs(leftH - rightH) > 1){
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    public static void main(String [] args){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(8);
        tree.insert(2);
        tree.insert(1);
        tree.insert(5);
        tree.insert(3);
        tree.insert(6);
        tree.insert(11);
        tree.insert(19);
        tree.insert(13);

        if(isBalanced(tree.getRoot())){
            System.out.println("Balanced");
        }else{
            System.out.println("Not Balanced");
        }
    }
}
