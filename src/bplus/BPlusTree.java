package bplus;

public class BPlusTree {
    private BPlusTreeNode root;
    private int order;

    // Constructor
    public BPlusTree(int order) {
        this.order = order;
        root = new BPlusTreeNode(order, true);
    }

    // Insert a new word-meaning pair
    public void insert(String key, String value) {
        BPlusTreeNode node = root;

        // If root is full, split it
        if (node.getNumKeys() == order - 1) {
            BPlusTreeNode newRoot = new BPlusTreeNode(order, false);
            newRoot.setChild(0, root);
            root = newRoot;
            splitChild(0, root, node);
            insertNonFull(root, key, value);
        } else {
            insertNonFull(node, key, value);
        }
    }

    // Helper method to insert into a non-full node
    private void insertNonFull(BPlusTreeNode node, String key, String value) {
        int i = node.getNumKeys() - 1;

        // If node is a leaf, insert the new key-value pair into the correct position
        if (node.isLeaf()) {
            while (i >= 0 && node.getKey(i).compareTo(key) > 0) {
                node.setKey(i + 1, node.getKey(i));
                node.setValue(i + 1, node.getValue(i));
                i--;
            }
            node.setKey(i + 1, key);
            node.setValue(i + 1, value);
            node.setNumKeys(node.getNumKeys() + 1);
        } else {
            // If node is not a leaf, find the child node to insert the new key-value pair
            while (i >= 0 && node.getKey(i).compareTo(key) > 0) {
                i--;
            }
            i++;
            BPlusTreeNode child = node.getChild(i);
            if (child.getNumKeys() == order - 1) {
                splitChild(i, node, child);
                if (node.getKey(i).compareTo(key) < 0) {
                    i++;
                }
            }
            insertNonFull(node.getChild(i), key, value);
        }
    }

    // Helper method to split a full child node
    private void splitChild(int i, BPlusTreeNode parent, BPlusTreeNode child) {
        BPlusTreeNode newNode = new BPlusTreeNode(order, child.isLeaf());
        newNode.setNumKeys(order / 2);

        for (int j = 0; j < order / 2; j++) {
            newNode.setKey(j, child.getKey(j + order / 2));
            newNode.setValue(j, child.getValue(j + order / 2));
        }

        if (!child.isLeaf()) {
            for (int j = 0; j < order / 2 + 1; j++) {
                newNode.setChild(j, child.getChild(j + order / 2));
            }
        }

        child.setNumKeys(order / 2);

        for (int j = parent.getNumKeys(); j >= i + 1; j--) {
            parent.setChild(j + 1, parent.getChild(j));
        }

        parent.setChild(i + 1, newNode);

        for (int j = parent.getNumKeys() - 1; j >= i; j--) {
            parent.setKey(j + 1, parent.getKey(j));
            parent.setValue(j + 1, parent.getValue(j));
        }

        parent.setKey(i, newNode.getKey(0));
        parent.setValue(i, newNode.getValue(0));
        parent.setNumKeys(parent.getNumKeys() + 1);
    }

    // Search for the meaning of a word
    public String search(String key) {
        BPlusTreeNode node = root;
        while (node != null) {
            int i = 0;
            while (i < node.getNumKeys() && key.compareTo(node.getKey(i)) > 0) {
                i++;
            }
            if (i < node.getNumKeys() && key.compareTo(node.getKey(i)) == 0) {
                return node.getValue(i);
            } else if (node.isLeaf()) {
                return null;
            } else {
                node = node.getChild(i);
            }
        }
        return null;
    }

    // Traverse the tree and print its contents
    public boolean traverse(String word) {
        return traverse(root, word);
    }

    private boolean traverse(BPlusTreeNode node, String word) {
        int i;
        for (i = 0; i < node.getNumKeys(); i++) {
            if (!node.isLeaf()) {
                traverse(node.getChild(i), word);
            }
            if(node.getKey(i).equals(word)) 
            {
                System.out.print(node.getKey(i) + ": " + node.getValue(i));
                return true;
                
            }
        }
        if (!node.isLeaf()) {
            traverse(node.getChild(i), word);
        }
        return false;
    }
}
