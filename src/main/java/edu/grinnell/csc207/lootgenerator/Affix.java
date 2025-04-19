package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * a class affix to be used in loot generator
 * @author yutong
 */
public class Affix {

    private String name;
    private String mod1code;
    private int mod1min;
    private int mod1max;

    /**
     * construct a new affix
     * @param n the name of the affix
     * @param code the additional statistic text that the affix will introduce
     * to the base item
     * @param min the min integer value that might be generated
     * @param max the max integer value that might be generated
     */
    public Affix(String n, String code, int min, int max) {
        name = n;
        mod1code = code;
        mod1min = min;
        mod1max = max;
    }

    /**
     * get the name of the affix
     * @return a string which is the name of the affix
     */
    public String getName() {
        return name;
    }

    /**
     * get the additional statistical text from the affix
     * @return a string which is the code from the affix
     */
    public String getCode() {
        return mod1code;
    }

    /**
     * get the min value from the affix
     * @return an integer that is the min value
     */
    public int getMin() {
        return mod1min;
    }

    /**
     * get the max value from the affix
     * @return an integer that is the max value
     */
    public int getMax() {
        return mod1max;
    }

    /**
     * parse a file and store the affixes in a list
     * @param path the path of the file to be parsed
     * @return the list that stores the affixes
     */
    public static List<Affix> parse(String path) {
        List<Affix> affixes = new ArrayList<>();
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < content.size(); i++) {
                String line = content.get(i);
                String[] things = line.split("\t");
                if (things.length == 4) {
                    affixes.add(new Affix(things[0], things[1],
                            Integer.parseInt(things[2]), Integer.parseInt(things[3])));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return affixes;
    }
}
