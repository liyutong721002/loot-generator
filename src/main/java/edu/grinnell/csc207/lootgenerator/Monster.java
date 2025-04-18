package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Monster {

    private String name;
    private String type;
    private int level;
    private String TC;

    public Monster(String n, String t, int l, String tc) {
        name = n;
        type = t;
        level = l;
        TC = tc;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public String getTC() {
        return TC;
    }

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
