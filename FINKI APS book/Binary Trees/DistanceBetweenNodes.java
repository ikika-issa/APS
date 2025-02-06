//Find the distance between two nodes.

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
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        return (depth == 0);
    }


    public E peek () {
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        elems[depth++] = x;
    }


    public E pop () {
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

        mirrorR(node.left);
        mirrorR(node.right);
        
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;

    }

    public void mirror() {
        mirrorR(root);
    }

}

public class DistanceBetweenNodes {
    public static BNode<Integer> findNode(BNode<Integer> node, int n){
        if(node == null){
            return null;
        }

        if(node.info.equals(n)){
            return node;
        }

        BNode<Integer> foundL = findNode(node.left, n);

        if(foundL != null){
            return foundL;
        }

        BNode<Integer> foundR = findNode(node.right, n);

        return foundR;
    }
    public static boolean isThere(BTree<Integer> tree, int n){
        if(findNode(tree.root, n) == null){
            return false;
        }

        return true;
    }

    public static BNode<Integer> findLCA(BNode<Integer> node, int a, int b){
        if(node == null){
            return null;
        }

        if(node.info.equals(a) || node.info.equals(b)){
            return node; //this node is LCA
        }

        BNode<Integer> leftLCA = findLCA(node.left, a, b);
        BNode<Integer> rightLCA = findLCA(node.right, a, b);

        if(leftLCA != null && rightLCA != null){
            return node;
        }

        if(leftLCA == null && rightLCA == null){
            return null;
        }

        if(leftLCA == null){
            return rightLCA;
        }else{
            return leftLCA;
        }
    }

    public static int getDistance(BTree<Integer> tree, int a, int b){
        BNode<Integer> node = findLCA(tree.root, a, b);

        int d1 = getLevel(node, a, 0);
        int d2 = getLevel(node, b, 0);

        return d1 + d2;
    }

    public static int getLevel(BNode<Integer> node, int a, int level){
        if(node == null){
            return 0;
        }

        if(node.info.equals(a)){
            return level;
        }

        int tmp = getLevel(node.left, a, level + 1);

        if(tmp > 0){
            return tmp;
        }

        tmp = getLevel(node.right, a, level + 1);

        return tmp;
    }

    public static int getLevel(BTree<Integer> tree, int a){
        int level = getLevel(tree.root, a, 0);

        return level;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        BTree<Integer> tree = new BTree<>();

        tree.makeRoot(6);
        BNode<Integer> b = tree.addChild(tree.root, 1, 5);
        BNode<Integer> c = tree.addChild(tree.root, 2, 10);
        BNode<Integer> d = tree.addChild(c, 1, 10);
        BNode<Integer> e = tree.addChild(c, 2, 15);

        int n = sc.nextInt();
        int n1 = sc.nextInt();

        BNode<Integer> found = findNode(tree.root, n);
        BNode<Integer> found1 = findNode(tree.root, n1);

        if(found != null && found1 != null){
            System.out.print(getDistance(tree, n, n1));
        }

    }
}
