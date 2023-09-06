/**
 * DoubleHashTable represents a hash table implementation using double hashing.
 * The key is the name of the magic spell, and the value is the spell words required to cast the spell.
 * It provides methods to insert spells and retrieve spell words based on the spell name.
 */
public class DoubleHashTable {
    private final SpellSimple[] table;
    private final int capacity;
    private int size;
    private int steps = 0;

    /**
     * Constructs a DoubleHashTable object with the specified capacity.
     *
     * @param capacity the capacity of the hash table
     */
    public DoubleHashTable(int capacity) {
        this.capacity = capacity;
        table = new SpellSimple[capacity];
        size = 0;
    }

    /**
     * Inserts a new spell into the hash table.
     *
     * @param spell the SpellSimple object to be inserted
     * @return true if the spell is successfully inserted, false if the hash table is full
     */
    public boolean put(SpellSimple spell) {
        //int stepsBefore = steps;
        int h1 = hash1(spell.getName());
        int h2 = hash2(spell.getName());

        if (table[h1] == null){
            table[h1] = spell;
            size++;
            return true;
        }

        for (int i = 1; i < capacity; i++) {
            int index = (h1 + i * h2) % capacity;
            steps = i;

            if (table[index] == null) {
                table[index] = spell;
                size++;
                return true;
            }

            if (table[h1].getName().equals(spell.getName()))
                return false;  // Spell with the same name already exists
        }

        steps = capacity;
        //steps = stepsBefore;
        return false;
    }

    /**
     * Retrieves the cast words for a spell with the specified name from the hash table.
     *
     * @param name the name of the spell
     * @return the cast words for the spell if found, or null if the spell is not in the hash table
     */
    public String getCastWords(String name) {
        //int stepsBefore = steps;
        int h1 = hash1(name);
        int h2 = hash2(name);

        if (table[h1].getName().equals(name)){
            return table[h1].getWords();
        }

        for (int i = 1; i < capacity; i++) {
            int index = (h1 + i * h2) % capacity;
            steps = i;

            if (table[index].getName().equals(name)) {
                return table[index].getWords();
            }
        }
        steps = capacity;
        //steps = stepsBefore;
        return null;
    }

    /**
     * Returns the number of spells currently stored in the hash table.
     *
     * @return the size of the hash table
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the number of steps taken in the last put or getCastWords operation.
     *
     * @return the number of steps
     */
    public int getLastSteps() { return steps; }

    /**
     * Calculates the hash value using the first hash function for a given name.
     *
     * @param name the name of the spell
     * @return integer - the hash value
     */
    private int hash1(String name) {
        int hash = 0;
        for (char c: name.toCharArray())
            hash = hash + c * 31;
        return hash % capacity;
    }

    /**
     * Calculates the secondary hash value using the second hash function for a given name.
     *
     * @param name the name of the spell
     * @return the secondary hash value
     */
    private int hash2(String name) {
        int hash = 0;
        for (char c : name.toCharArray())
            hash = hash + c * 13;
        return 1 + (hash % (capacity - 2));
    }
}