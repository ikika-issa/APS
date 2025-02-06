//Check if a node exists within a tree.
//Both functions can be implemented within the BTree class for any time of tree.

import java.util.NoSuchElementException;
import java.util.Scanner;

interface Stack<E> {

    public boolean isEmpty ();

    public E peek ();

    public void clear ();

    public void push (E x);

    public E pop ();
}


class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode<E> parent;
    public BNode(E info, BNode<E> parent) {
        this.parent = parent;
        this.info = info;
        left = null;
        right = null;
    }
    public BNode(E info) {
        this.parent = null;
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

class BTree<E> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<E>(elem);
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem, node);

        if (where == BNode.LEFT) {
            if (node.left != null)
                return null;
            node.left = tmp;
        } else {
            if (node.right != null)
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public void inorder() {
        System.out.print("INORDER: ");
        inorderR(root);
        System.out.println();
    }

    public void inorderR(BNode<E> n) {
        if (n != null) {
            inorderR(n.left);
            System.out.print(n.info.toString()+" ");
            inorderR(n.right);
        }
    }

    public void preorder() {
        System.out.print("PREORDER: ");
        preorderR(root);
        System.out.println();
    }

    public void preorderR(BNode<E> n) {
        if (n != null) {
            System.out.print(n.info.toString()+" ");
            preorderR(n.left);
            preorderR(n.right);
        }
    }

    public void postorder() {
        System.out.print("POSTORDER: ");
        postorderR(root);
        System.out.println();
    }

    public void postorderR(BNode<E> n) {
        if (n != null) {
            postorderR(n.left);
            postorderR(n.right);
            System.out.print(n.info.toString()+" ");
        }
    }

    public void inorderNonRecursive() {
        ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
        BNode<E> p = root;
        System.out.print("INORDER (nonrecursive): ");

        while (true) {
            while (p != null) {
                s.push(p);
                p = p.left;
            }

            if (s.isEmpty())
                break;

            p = s.peek();
            System.out.print(p.info.toString()+" ");
            s.pop();
            p = p.right;

        }
        System.out.println();

    }

    int insideNodesR(BNode<E> node) {
        if (node == null)
            return 0;

        if ((node.left == null)&&(node.right == null))
            return 0;

        return insideNodesR(node.left) + insideNodesR(node.right) + 1;
    }

    public int insideNodes() {
        return insideNodesR(root);
    }

    int leavesR(BNode<E> node) {
        if (node != null) {
            if ((node.left == null)&&(node.right == null))
                return 1;
            else
                return (leavesR(node.left) + leavesR(node.right));
        } else {
            return 0;
        }
    }

    public int leaves() {
        return leavesR(root);
    }

    int depthR(BNode<E> node) {
        if (node == null)
            return 0;
        if ((node.left == null)&&(node.right == null))
            return 0;
        return (1 + Math.max(depthR(node.left), depthR(node.right)));
    }

    public int depth() {
        return depthR(root);
    }

    void mirrorR(BNode<E> node) {
        BNode<E> tmp;

        if (node == null)
            return;

        // simetricno preslikuvanje na levoto i desnoto potsteblo
        mirrorR(node.left);
        mirrorR(node.right);

        // smena na ulogite na pokazuvacite na momentalniot jazel
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;

    }

    public void mirror() {
        mirrorR(root);
    }

}

public class LocateNode{
    public static BNode<String> findNode(BNode<String> n, String str){
        if(n == null){
            return null;
        }

        if(n.info.equals(str)){
            return n;
        }

        BNode<String> foundL = findNode(n.left,str);

        if(foundL != null){
            return foundL;
        }

        BNode<String> foundR = findNode(n.right,str);

        return foundR;
    }

    public static boolean isThere(BTree<String> tree, String node){
        if(findNode(tree.root, node) == null){
            return false;
        }

        return true;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BTree<String> tree = new BTree<>();

        tree.makeRoot("apple");
        BNode<String> b = tree.addChild(tree.root, 1, "pear");
        BNode<String> c = tree.addChild(tree.root, 2, "banana");
        BNode<String> d = tree.addChild(c, 1, "lemon");
        BNode<String> e = tree.addChild(c, 2, "orange");

        String node = sc.next();

        if(isThere(tree, node)){
            System.out.println("It exists.");
        }else{
            System.out.println("It does not exist.");
        }
    }
}
