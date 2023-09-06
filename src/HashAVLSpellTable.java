import java.util.LinkedList;
import java.util.List;

/**
 * HashAVLSpellTable is a data structure that stores Spells in an AVL Tree-based hash table.
 * It provides methods to add spells, search for spells, retrieve the number of spells,
 * retrieve the number of spells in a specific category, and retrieve the top K spells in a category.
 */
public class HashAVLSpellTable {
    private LinkedList<AVLTree> buckets[];
    private int tableSize;
    private int numSpells;

    /**
     * Constructs a HashAVLSpellTable with the specified table size.
     *
     * @param size the size of the hash table
     */
    public HashAVLSpellTable(int size) {
        tableSize = size;
        buckets = new LinkedList[tableSize];
        for (int i = 0; i < size; i++)
            buckets[i] = new LinkedList<>();
    }

    /**
     * Calculates the hash value for a given category.
     *
     * @param category the category of the spell
     * @return the hash value for the category
     */
    private int hash(String category) {
        int hash = 0;
        for (int i = 0; i < category.length(); i++) {
            char c = category.charAt(i);
            hash += (int)c;
        }
        return hash % tableSize;
    }

    /**
     * Adds a spell to the hash AVL spell table.
     *
     * @param s the spell to add
     */
    public void addSpell(Spell s) {
        int index = hash(s.getCategory());
        if (buckets[index].isEmpty()){
            AVLTree T1 = new AVLTree(s);
            buckets[index].add(T1);
            numSpells++;
            return;
        }
        for (int i = 0; i < buckets[index].size(); i++){
            if (buckets[index].get(i).getCategory().equals(s.getCategory())){
                buckets[index].get(i).insert(s);
                numSpells++;
                return;
            }
        }
        AVLTree T2=new AVLTree(s);
        buckets[index].add(T2);
        numSpells++;
    }

    /**
     * Searches for a spell in the hash AVL spell table based on category, spell name, and power level.
     *
     * @param category    the category of the spell
     * @param spellName   the name of the spell
     * @param powerLevel  the power level of the spell
     * @return the spell if found, null otherwise
     */
    public Spell searchSpell(String category, String spellName, int powerLevel) {
        int index = hash(category);
        for (int i = 0; i < buckets[index].size(); i++){
            if (buckets[index].get(i).getCategory().equals(category)){
                return buckets[index].get(i).search(spellName,powerLevel);
            }
        }
        return null;

    }

    /**
     * Retrieves the total number of spells in the hash AVL spell table.
     *
     * @return the number of spells
     */
    public int getNumberSpells(){
        return numSpells;
    }

    /**
     * Retrieves the number of spells in a specific category.
     *
     * @param category the category of the spells
     * @return the number of spells in the category
     */
    public int getNumberSpells(String category){
        int index = hash(category);
        for (int i = 0; i < buckets[index].size(); i++){
            if (buckets[index].get(i).getCategory().equals(category)){
                return buckets[index].get(i).getSize();
            }
        }
        return 0;
    }

    /**
     * Retrieves the top K spells in a specific category.
     *
     * @param category the category of the spells
     * @param k        the number of top spells to retrieve
     * @return a list of the top K spells in the category
     */
    public List<Spell> getTopK(String category, int k) {
        int index = hash(category);
        for (int i = 0; i < buckets[index].size(); i++){
            if (buckets[index].get(i).getCategory().equals(category)){
                return buckets[index].get(i).getTopK(k);
            }
        }
        return null;
    }
}