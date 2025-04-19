package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * a class monster to be used in loot generator
 * @author yutong
 */
public class Monster {

    private String name;
    private String type;
    private int level;
    private String tc;

    /**
     * construct a new monster
     * @param n the name of the monster
     * @param t the type of the monster
     * @param l the level of the monster
     * @param tc the treasure class of the monster
     */
    public Monster(String n, String t, int l, String tc) {
        name = n;
        type = t;
        level = l;
        this.tc = tc;
    }

    /**
     * get the name of the monster
     * @return a string which is the name of the affix
     */
    public String getName() {
        return name;
    }

    /**
     * get the type of the monster
     * @return a string which is the type of the monster
     */
    public String getType() {
        return type;
    }

    /**
     * get the level of the monster
     * @return an integer which is the level of the monster
     */
    public int getLevel() {
        return level;
    }

    /**
     * get the treasure class of the monster
     * @return a string which is the treasure class
     */
    public String getTC() {
        return tc;
    }

    /**
     * parse a file and store the monsters in a list
     * @param path the path of the file to be parsed
     * @return the list that stores the monsters
     */
    public static List<Monster> parse(String path) {
        List<Monster> monsters = new ArrayList<>();
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < content.size(); i++) {
                String line = content.get(i);
                String[] things = line.split("\t");
                if (things.length == 4) {
                    monsters.add(new Monster(things[0], things[1],
                            Integer.parseInt(things[2]), things[3]));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return monsters;
    }
}
