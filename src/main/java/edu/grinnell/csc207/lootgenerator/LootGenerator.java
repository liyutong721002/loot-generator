package edu.grinnell.csc207.lootgenerator;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {

    /**
     * The path to the dataset (either the small or large set).
     */
    private static final String DATA_SET = "data/large";
    private Monster monster;
    private Armor armor;
    private TreasureClass TC;
    private Affix suffix;
    private Affix prefix;

    private String findBase(TreasureClass tc, BST<String, TreasureClass> TCs, Random rand) {
        List<String> items = tc.getItems();
        String baseItem = items.get(rand.nextInt(items.size()));
        if (TCs.contains(baseItem)) {
            return findBase(TCs.get(baseItem), TCs, rand);
        }
        return baseItem;
    }

    public void generateItem() {
        Random rand = new Random();

        List<Monster> Monsters = Monster.parse(DATA_SET + "/monstats.txt");
        BST<String, TreasureClass> TCs = (BST<String, TreasureClass>) TreasureClass.parse(DATA_SET + "/TreasureClassEx.txt");
        BST<String, Armor> armors = (BST<String, Armor>) Armor.parse(DATA_SET + "/armor.txt");
        List<Affix> suffixes = Affix.parse(DATA_SET + "/MagicSuffix.txt");
        List<Affix> prefixes = Affix.parse(DATA_SET + "/MagicPrefix.txt");
    
        monster = Monsters.get(rand.nextInt(Monsters.size()));

        TC = TCs.get(monster.getTC());
        String baseItem = findBase(TC, TCs, rand);

        armor = armors.get(baseItem);

        int ac = 0;
        if (armor.getMax() > armor.getMin()){
            ac = rand.nextInt(armor.getMax() - armor.getMin() + 1) + armor.getMin();
        }
        String baseStats = "Damage: " + ac;

        if (rand.nextBoolean()) {
            prefix = prefixes.get(rand.nextInt(prefixes.size()));
        }
        if (rand.nextBoolean()) {
            suffix = suffixes.get(rand.nextInt(suffixes.size()));
        }
        String itemName = "";
        String statsPre = "";
        String statsSuf = "";
        if (prefix != null) {
            itemName += prefix.getName() + " ";
            int value = rand.nextInt(prefix.getMax() - prefix.getMin() + 1) + prefix.getMin();
            statsPre = value + " " + prefix.getCode();
        }
        itemName += baseItem;
        if (suffix != null) {
            itemName += " " + suffix.getName();
            int value = rand.nextInt(suffix.getMax() - suffix.getMin() + 1) + suffix.getMin();
            statsSuf = value + " " + suffix.getCode();
        }

        System.out.println("Fighting " + monster.getName());
        System.out.println("You have slain " + monster.getName() + "!");
        System.err.println(monster.getName() + " dropped:");
        System.out.println("");
        System.out.println(itemName);
        System.out.println(baseStats);
        if (!statsPre.equals("")) {
            System.out.println(statsPre);
        }
        if (!statsSuf.equals("")) {
            System.out.println(statsSuf);
        }
    }

    public static void main(String[] args) {
        boolean play = true;
        LootGenerator gen = new LootGenerator();
        while (play) {
            gen.generateItem();
            System.out.print("\nFight again [y/n]? ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine().trim().toLowerCase();
            while (!input.equals("y") && !input.equals("n")) {
                System.out.print("Fight again [y/n]? ");
                input = sc.nextLine().trim().toLowerCase();
            }
            if (input.equals("n")) {
                play = false;
            }
        }
    }
}
