package edu.grinnell.csc207.lootgenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testTreasureClassParse() {
        BST<String, TreasureClass> tcs = TreasureClass.parse("data/small/TreasureClassEx.txt");
        assertEquals(true, tcs.contains("Act 5 (H) Equip B"));
        assertEquals(true, tcs.contains("armo3"));
    }

    @Test
    public void testArmorParse() {
        BST<String, Armor> armors = Armor.parse("data/small/armor.txt");
        assertEquals(true, armors.contains("Leather Armor"));
        assertEquals(true, armors.contains("Quilted Armor"));
        assertEquals(true, armors.contains("Buckler"));
        assertEquals(true, armors.contains("Embossed Plate"));
        assertEquals(true, armors.contains("Sun Spirit"));
        assertEquals(true, armors.contains("Fury Visor"));
        assertEquals(true, armors.contains("Sacred Rondache"));
        assertEquals(true, armors.contains("Mage Plate"));
        assertEquals(true, armors.contains("Diadem"));
    }

    @Test
    public void testSuffixParse() {
        List<Affix> suffixes = Affix.parse("data/small/MagicSuffix.txt");
        assertEquals(suffixes.get(0).getName(), ("of Precision"));
        assertEquals(suffixes.get(1).getName(), ("of Regrowth"));
        assertEquals(suffixes.get(2).getName(), ("of the Tiger"));
        assertEquals(suffixes.get(3).getName(), ("of the Titan"));
        assertEquals(suffixes.get(4).getName(), ("of the Leech"));
    }

    @Test
    public void testPrefixParse() {
        List<Affix> prefixes = Affix.parse("data/small/MagicPrefix.txt");
        assertEquals(prefixes.get(0).getName(), ("Glorious"));
        assertEquals(prefixes.get(1).getName(), ("Brutal"));
        assertEquals(prefixes.get(2).getName(), ("Dragon's"));
        assertEquals(prefixes.get(3).getName(), ("Emerald"));
        assertEquals(prefixes.get(4).getName(), ("Glowing"));
    }

}
