package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Affix {
    private String name;
    private String mod1code;
    private int mod1min;
    private int mod1max;

    public Affix(String n, String code, int min, int max) {
        name = n;
        mod1code = code;
        mod1min = min;
        mod1max = max;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return mod1code;
    }

    public int getMin() {
        return mod1min;
    }

    public int getMax() {
        return mod1max;
    }

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
