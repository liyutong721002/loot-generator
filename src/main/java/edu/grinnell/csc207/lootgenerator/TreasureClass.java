package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * a treasure class used in the loot generator
 * @author yutong
 */
public class TreasureClass {

    private String tc;
    private List<String> items;

    /**
     * construct a new treasure class 
     * @param tc the name of the treasure class
     * @param i a list of items the might be dropped 
     */
    public TreasureClass(String tc, List<String> i) {
        this.tc = tc;
        items = i;
    }

    /**
     * get the name of the treasure class
     * @return the name of the treasure class
     */
    public String getName() {
        return tc;
    }

    /**
     * get a list of the items 
     * @return a list of items 
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * parse a file and store the treasure classes in a list
     *
     * @param path the path of the file to be parsed
     * @return the list that stores the treasure classes
     */
    public static BST<String, TreasureClass> parse(String path) {
        BST<String, TreasureClass> tcs = new BST<>();
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < content.size(); i++) {
                String line = content.get(i);
                String[] things = line.split("\t");
                List<String> items = new ArrayList<>();
                for (int j = 1; j < things.length; j++) {
                    items.add(things[j]);
                }
                if (things.length == 4) {
                    tcs.insert(things[0], new TreasureClass(things[0], items));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tcs;
    }
}
