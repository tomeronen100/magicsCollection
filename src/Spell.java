/**
 * Represents a spell in a fantasy game with a name, category, power level, and words.
 */
public class Spell {
    private String name;
    private String category;
    private int powerLevel;
    private String words;

    /**
     * Creates a new spell with the specified attributes.
     *
     * @param name        the name of the spell
     * @param category    the category of the spell
     * @param powerLevel  the power level of the spell
     * @param words the words of the spell
     */
    public Spell(String name, String category, int powerLevel, String words) {
        this.name = name;
        this.category = category;
        this.powerLevel = powerLevel;
        this.words = words;
    }

    /**
     * Returns the name of the spell.
     *
     * @return the name of the spell
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the category of the spell.
     *
     * @return the category of the spell
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the power level of the spell.
     *
     * @return the power level of the spell
     */
    public int getPowerLevel() {
        return powerLevel;
    }

    /**
     * Returns a string representation of the spell.
     *
     * @return a string representation of the spell
     */
    @Override
    public String toString() {
        return name + " (" + category + ") - Power Level: " + powerLevel + ", to cast say: " + words;
    }
}
