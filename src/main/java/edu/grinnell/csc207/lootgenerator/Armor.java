package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * a class armor to be used in loot generator
 * @author yutong
 */
public class Armor {
    String name;
    int minac;
    int maxac;

    /**
     * construct a new armor
     * @param n the name of the armor
     * @param min the min integer value that might be generated
     * @param max the max integer value that might be generated
     */
    public Armor(String n, int min, int max) {
        name = n;
        minac = min;
        maxac = max;
    }

    /**
     * get the name of the armor 
     * @return a string which is the name of the armor
     */
    public String getName() {
        return name;
    }

    /**
     * get the min value of the armor
     * @return an integer that is the min value
     */
    public int getMin() {
        return minac;
    }

    /**
     * get the max value of the armor
     * @return an integer that is the max value
     */
    public int getMax() {
        return maxac;
    }
    
    /**
     * parse a file and store the armors in a list
     * @param path the path of the file to be parsed
     * @return the list that stores the armors
     */
    public static BST parse(String path) {
        BST armors = new BST<>();
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < content.size(); i++) {
                String line = content.get(i);
                String[] things = line.split("\t");
                if (things.length == 3) {
                    armors.insert(things[0], new Armor(things[0], 
                        Integer.parseInt(things[1]), Integer.parseInt(things[2])));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return armors;
    }
}