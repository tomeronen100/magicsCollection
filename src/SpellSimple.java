/**
 * SpellSimple represents a spell in the hash table.
 * It contains the name of the spell and the words required to cast the spell.
 */
public class SpellSimple {
    private final String name;
    private final String words;

    /**
     * Constructs a SpellSimple object with the specified name and words.
     *
     * @param name  the name of the spell
     * @param words the words required to cast the spell
     */
    SpellSimple(String name, String words) {
        this.name = name;
        this.words = words;
    }

    /**
     * Returns the name of the spell.
     *
     * @return the name of the spell
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the words required to cast the spell.
     *
     * @return the words required to cast the spell
     */
    public String getWords(){
        return words;
    }
}