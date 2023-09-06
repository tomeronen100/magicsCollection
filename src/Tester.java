import java.util.List;

/**
 * Tester class is responsible for testing the functionality of the HashAVLSpellTable.
 */
public class Tester {

    /**
     * The main method that runs the test cases.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        ///////////////////////////////////// PART 1 //////////////////////////////////////
        System.out.println("Part 1 Tests: ");

        // Create a hash table with capacity 7
        DoubleHashTable table = new DoubleHashTable(7);

        // Add spells to the table
        table.put(new SpellSimple("Abracadabra", "Avada Kedavra"));
        table.put(new SpellSimple("Expecto Patronum", "I’m gonna stand here like a unicorn"));
        table.put(new SpellSimple("Wingardium Leviosa", "Get up, stand up"));
        table.put(new SpellSimple("Shazam", "24K Magic in the air"));

        // Get the spells by name
        System.out.println(table.getCastWords("Shazam")); // prints "24K Magic in the air"
        System.out.println(table.getCastWords("Abracadabra")); // prints "Avada Kedavra"

        // Get the size of the table
        int size = table.getSize();
        System.out.println("Table size: " + size); // prints "Table size: 4"

        // Test using the return value of put method
        boolean added = table.put(new SpellSimple("Abracadabra", "Expelliarmus"));
        System.out.println("Spell added: " + added); // prints "Spell added: false" (spell with the same name already exists)

        // Add more spells to reach the capacity
        table.put(new SpellSimple("Lumos", "Let there be light"));
        table.put(new SpellSimple("Nox", "Extinguish the light"));
        table.put(new SpellSimple("Alohomora", "Open Sesame"));

        // Try to add another spell when the table is full
        boolean addedFullTable = table.put(new SpellSimple("Accio", "Summon the object"));
        System.out.println("Spell added to full table: " + addedFullTable); // prints "Spell added to full table: false"

        // Test getLastSteps method
        int lastSteps = table.getLastSteps();
        System.out.println("Last steps taken: " + lastSteps); // prints "Last steps taken: 7"

        // Get the size of the table again
        size = table.getSize();
        System.out.println("Table size: " + size); // prints "Table size: 7"
        System.out.println(" ");


        ///////////////////////////////////// PART 2 //////////////////////////////////////
        System.out.println("Part 2 Tests: ");

        // create a new hash AVL spell table
        HashAVLSpellTable table2 = new HashAVLSpellTable(10);

        // create some spells
        Spell spell1 = new Spell("fireball", "fire", 10, "fireball!");
        Spell spell2 = new Spell("frostbolt", "ice", 7, "freeze please");
        Spell spell3 = new Spell("thunderstorm", "lightning", 9, "I`m going to shock you");
        Spell spell4 = new Spell("poison spray", "poison", 5, "sssss");
        Spell spell5 = new Spell("shockwave", "lightning", 8, "go pikachu!");

        // add the spells to the hash AVL spell table
        table2.addSpell(new Spell("lightning bolt", "lightning", 11, "go lightning bolt"));
        table2.addSpell(spell1);
        table2.addSpell(spell2);
        table2.addSpell(spell3);
        table2.addSpell(spell4);
        table2.addSpell(spell5);

        // add more spells to an existing category
        table2.addSpell(new Spell("flamethrower min", "fire", 6, "foo"));
        table2.addSpell(new Spell("flamethrower", "fire", 8, "foo better"));
        table2.addSpell(new Spell("fireball II", "fire", 12, "fireball!!"));
        table2.addSpell(new Spell("flamethrower II", "fire", 15, "foooooooo!"));
        table2.addSpell(new Spell("shockwave II", "lightning", 10, "be useful pikachu."));
        table2.addSpell(new Spell("frost nova", "ice", 4, "chill dude"));

        System.out.println("The current number of spells is " + table2.getNumberSpells()); // prints the total number of spells
        System.out.println("The current number of fire spells spells is " + table2.getNumberSpells("fire")); // prints the total number of fire spells

        // get the top 3 spells in the "fire" category
        System.out.println("Top 3 spells in the 'fire' category:");
        List<Spell> fireSpells = table2.getTopK("fire", 3);
        for (Spell s : fireSpells) {
            System.out.println(s.toString()); // prints the top 3 spells in fire category in descending order
        }

        // get the top 3 spells in the "lightning" category
        System.out.println("Top 3 spells in the 'lightning' category:");
        List<Spell> lightningSpells = table2.getTopK("lightning", 3);
        for (Spell s : lightningSpells) {
            System.out.println(s.toString()); // prints the top 3 spells in lightning category in descending order
        }

        // spell that exists in the table
        Spell searchedSpell = table2.searchSpell("fire", "fireball", 10);
        if (searchedSpell != null) {
            System.out.println("Spell Found: " + searchedSpell.toString());
        } else {
            System.out.println("Spell Not Found");
        }

        // search for a spell that does not exist in the table
        searchedSpell = table2.searchSpell("fire", "fireball", 11);
        if (searchedSpell != null) {
            System.out.println("Spell Found: " + searchedSpell.toString());
        } else {
            System.out.println("Spell Not Found");
        }

        // search for a spell that does not exist in the table
        searchedSpell = table2.searchSpell("ice", "fireball", 10);
        if (searchedSpell != null) {
            System.out.println("Spell Found: " + searchedSpell.toString());
        } else {
            System.out.println("Spell Not Found");
        }


        // add a spell with the same power level as an existing spell in the same category
        table2.addSpell(new Spell("fireball II", "fire", 11, "more fire!"));

        // print the updated top 3 spells in the "fire" category
        System.out.println("Updated top 3 spells in the 'fire' category:");
        fireSpells = table2.getTopK("fire", 3);
        for (Spell s : fireSpells) {
            System.out.println(s.toString());
        }

        // print the updated number of fire spells and total number of spells
        System.out.println("The current number of fire spells is " + table2.getNumberSpells("fire"));
        System.out.println("The current number of spells is " + table2.getNumberSpells());

    }
}
