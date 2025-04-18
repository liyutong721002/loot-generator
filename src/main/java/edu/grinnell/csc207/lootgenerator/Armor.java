package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Armor {
    String name;
    int minac;
    int maxac;

    public Armor(String n, int min, int max) {
        name = n;
        minac = min;
        maxac = max;
    }

    public String getName() {
        return name;
    }

    public int getMin() {
        return minac;
    }

    public int getMax() {
        return maxac;
    }

    public static BST parse(String path) {
        BST Armors = new BST<>();
        List<String> content = new ArrayList<>();
        try {
            content = Files.readAllLines(Paths.get(path));
            for (int i = 0; i < content.size(); i++) {
                String line = content.get(i);
                String[] things = line.split("\t");
                if (things.length == 3) {
                    Armors.insert(things[0], new Armor(things[0], 
                    Integer.parseInt(things[1]), Integer.parseInt(things[2])));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return Armors;
    }
}