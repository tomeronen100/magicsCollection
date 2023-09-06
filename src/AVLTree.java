import java.util.ArrayList;
import java.util.List;

/**
 * AVLTree is a self-balancing binary search tree implementation that stores Spell objects.
 * It maintains the height balance of the tree to ensure efficient search, insertion, and deletion operations.
 */
public class AVLTree {

    private Node root;
    private int size;
    private final String category;



    /**
     * Node represents a node in the AVL Tree, which stores a Spell object.
     */
    private class Node {
        private  Spell spell;
        private Node left;
        private Node right;
        private int height;

        /**
         * Constructs a new Node with the specified Spell object.
         *
         * @param spell the spell to store in the node
         */
        private Node(Spell spell) {
            this.spell = spell;
            left = null;
            right = null;
            height = 0;
        }
    }



    /**
     * Constructor for AVLTree.
     *
     * @param spell The initial spell to be inserted as the root of the tree.
     */
    public AVLTree(Spell spell) {
        root = new Node(spell);
        size = 1;
        category = spell.getCategory();
    }

    /**
     * Returns the height of the AVLTree.
     *
     * @return The height of the AVLTree.
     */
    public int getTreeHeight(){
        return root.height;
    }

    /**
     * Returns the number of nodes in the AVLTree.
     *
     * @return The size of the AVLTree.
     */
    public int getSize(){
        return size;
    }

    /**
     * Returns the category of the AVLTree.
     *
     * @return The category of the AVLTree.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Searches for a spell with the given name and power level in the AVLTree.
     *
     * @param spellName   The name of the spell to search for.
     * @param powerLevel  The power level of the spell to search for.
     * @return The spell found, or null if not found.
     */
    public Spell search(String spellName, int powerLevel) {
        Node node = root;
        while (node != null) {
            if (node.spell.getPowerLevel() == powerLevel && node.spell.getName().equals(spellName)) {
                return node.spell;
            }
            else if (node.spell.getPowerLevel() < powerLevel) {
                if (node.right != null) {
                    node = node.right;
                }
                else {
                    break;
                }
            }
            else {
                if (node.left != null) {
                    node = node.left;
                }
                else {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * Inserts a new spell into the AVLTree.
     *
     * @param spell The spell to be inserted.
     */
    public void insert(Spell spell) {
        root = insertNode(root, spell);
        size ++;
    }

    /**
     * Inserts a new node with the specified spell into the AVLTree.
     *
     * @param node The new node to insert.
     * @param spell The current node being visited during the insertion process.
     * @return The updated node after insertion, maintaining the AVLTree property.
     */
    private Node insertNode(Node node, Spell spell) {
        if (node == null) {
            return new Node(spell);
        }
        else if (node.spell.getPowerLevel() > spell.getPowerLevel()) {
            node.left = insertNode(node.left, spell);
        }
        else if (node.spell.getPowerLevel() < spell.getPowerLevel()) {
            node.right = insertNode(node.right, spell);
        }
        return rebalance(node);
    }

    /**
     * Rebalances the AVL tree by performing rotations if necessary to maintain the AVL property.
     * This method updates the height of the given node and checks its balance factor to determine
     * @param node The node to rebalance
     * @return The updated node after performing rotations, maintaining the AVL property.
     */
    public Node rebalance(Node node) {
        updateNodeHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (getHeight(node.right.right) > getHeight(node.right.left)) {
                node = leftRotate(node);
            }
            else {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }
        else if (balance < -1) {
            if (getHeight(node.left.left) > getHeight(node.left.right)) {
                node = rightRotate(node);
            }
            else {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        }
        return node;
    }

    /**
     * Updates the height of a given node.
     * @param node - the node is updated
     */
    void updateNodeHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Retrieves the top K spells from the AVLTree based on power level.
     *
     * @param k The number of top spells to retrieve.
     * @return A list of the top K spells.
     */
    public List<Spell> getTopK(int k) {
        ArrayList<Spell> topSpells = new ArrayList<>();
        getTopKSpells(root,topSpells);
        ArrayList<Spell> topK = new ArrayList<>();
        int min = Math.min(k, getSize());
        for (int i = 0; min > i; i++){
            topK.add(topSpells.get(i));
        }
        return topK;
    }

    /**
     * Retrieves the top K spells in the AVLTree based on power level and adds them to the provided list.
     *
     * @param node      The current node being visited during the traversal.
     * @param topSpells The list to store the top K spells.
     */
    private void getTopKSpells(Node node, List<Spell> topSpells) {
        if (node != null) {
            getTopKSpells(node.right, topSpells);
            topSpells.add(node.spell);
            getTopKSpells(node.left, topSpells);
        }
    }

    /**
     * Returns the height of the specified node.
     *
     * @param node The node to get the height of.
     * @return The height of the node, or 0 if the node is null.
     */
    private int getHeight(Node node) {
        if (node == null)
            return -1;
        return node.height;
    }

    /**
     * Calculates and returns the balance factor of the specified node.
     *
     * @param node The node to calculate the balance factor for.
     * @return The balance factor of the node (height of left subtree - height of right subtree).
     */
    private int getBalance(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.right) - getHeight(node.left);
    }

    /**
     * Performs a right rotation on the specified node.
     *
     * @param y The node to perform the right rotation on.
     * @return The new root node after the rotation.
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateNodeHeight(x);
        updateNodeHeight(y);

        return x;
    }

    /**
     * Performs a left rotation on the specified node.
     *
     * @param y The node to perform the left rotation on.
     * @return The new root node after the rotation.
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        updateNodeHeight(x);
        updateNodeHeight(y);

        return x;
    }
}


