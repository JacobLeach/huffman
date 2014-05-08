package huffman.test;

import static org.junit.Assert.*;

import huffman.HuffmanCode;
import huffman.HuffmanCodeAssendingOrder;
import huffman.HuffmanCodeDescendingOrder;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class HuffmanCodeTest
{

    @Test
    public void testConstructor()
    {
        char testChar = 'a';
        int testLength = 4;

        HuffmanCode test = new HuffmanCode(testChar, testLength);
        assertTrue("", test.getCharacter() == testChar);
        assertTrue("", test.getCodeLength() == testLength);
    }

    @Test
    public void testEquals()
    {
        HuffmanCode test = new HuffmanCode('a', 3);
        assertTrue("", test.equals(new HuffmanCode('a', 3)));
        assertFalse("", test.equals(new HuffmanCode('a', 2)));
        assertFalse("", test.equals(new HuffmanCode('b', 3)));
        assertFalse("", test.equals(new HuffmanCode('b', 2)));
        assertFalse("", test.equals(null));
        assertFalse("", test.equals(new ArrayList<Character>()));
    }

    @Test
    public void testComparable()
    {
        ArrayList<HuffmanCode> list = new ArrayList<>();

        list.add(new HuffmanCode('a', 4));
        list.add(new HuffmanCode('b', 4));
        list.add(new HuffmanCode('c', 2));
        list.add(new HuffmanCode('d', 2));
        list.add(new HuffmanCode('e', 5));

        Collections.sort(list, new HuffmanCodeAssendingOrder());

        assertTrue("", list.get(0).equals(new HuffmanCode('c', 2)));
        assertTrue("", list.get(1).equals(new HuffmanCode('d', 2)));
        assertTrue("", list.get(2).equals(new HuffmanCode('a', 4)));
        assertTrue("", list.get(3).equals(new HuffmanCode('b', 4)));
        assertTrue("", list.get(4).equals(new HuffmanCode('e', 5)));
        
        Collections.sort(list, new HuffmanCodeDescendingOrder());

        
        assertTrue("", list.get(0).equals(new HuffmanCode('e', 5)));
        assertTrue("", list.get(1).equals(new HuffmanCode('a', 4)));
        assertTrue("", list.get(2).equals(new HuffmanCode('b', 4)));
        assertTrue("", list.get(3).equals(new HuffmanCode('c', 2)));
        assertTrue("", list.get(4).equals(new HuffmanCode('d', 2)));
        
        

    }

}
