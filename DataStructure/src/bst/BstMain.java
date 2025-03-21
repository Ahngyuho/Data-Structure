package bst;

import com.sun.source.tree.Tree;

public class BstMain {
    public static void main(String[] args) {

        Bst bst = new Bst();
        bst.insert(1);
        bst.insert(2);
        bst.insert(3);

        TreePrinter.print(bst.getRoot());

    }
}
