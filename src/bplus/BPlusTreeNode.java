package bplus;

public class BPlusTreeNode {
    private String[] keys;
    private String[] values;
    private BPlusTreeNode[] children;
    private int numKeys;
    private boolean isLeaf;

    // Constructor
    public BPlusTreeNode(int order, boolean isLeaf) {
        this.keys = new String[order - 1];
        this.values = new String[order - 1];
        this.children = new BPlusTreeNode[order];
        this.numKeys = 0;
        this.isLeaf = isLeaf;
    }

    // Getter and setter methods
    public String getKey(int index) {
        return keys[index];
    }

    public void setKey(int index, String key) {
        keys[index] = key;
    }

    public String getValue(int index) {
        return values[index];
    }

    public void setValue(int index, String value) {
        values[index] = value;
    }

    public BPlusTreeNode getChild(int index) {
        return children[index];
    }

    public void setChild(int index, BPlusTreeNode child) {
        children[index] = child;
    }

    public int getNumKeys() {
        return numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
