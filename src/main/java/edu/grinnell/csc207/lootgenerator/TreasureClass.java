package edu.grinnell.csc207.lootgenerator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TreasureClass {

    private String TC;
    private List<String> items;

    public TreasureClass(String tc, List<String> i) {
        TC = tc;
        items = i;
    }

    public String getName() {
        return TC;
    }

    public List<String> getItems() {
        return items;
    }

    public static BST<String, TreasureClass> parse(String path) {
        BST<String, TreasureClass> TCs = new BST<>();
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
                    TCs.insert(things[0], new TreasureClass(things[0], items));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return TCs;
    }
}
