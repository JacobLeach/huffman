package huffman.test;

import static org.junit.Assert.*;

import huffman.CharacterFrequency;
import huffman.error.NegativeFrequencyError;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class CharacterFrequencyTest
{
    
    @Test
    public void testConstructor()
    {
        CharacterFrequency test = new CharacterFrequency('a', 5);
        assertTrue("", test.getCharacter() == 'a');
        assertTrue("", test.getFrequency() == 5);
    }
    
    @Test(expected=NegativeFrequencyError.class)
    public void testErrorOnNegativeFrequency()
    {
        new CharacterFrequency('a', -1);
    }
    
    @Test
    public void testEquals()
    {
        CharacterFrequency test = new CharacterFrequency('a', 5);
        assertTrue("", test.equals(new CharacterFrequency('a', 5)));
        assertFalse("", test.equals(new CharacterFrequency('a', 6)));
        assertFalse("", test.equals(new CharacterFrequency('b', 5)));
        assertFalse("", test.equals(new CharacterFrequency('b', 6)));
        assertFalse("", test.equals(null));
        assertFalse("", test.equals(new ArrayList<Character>()));
    }
    
    @Test
    public void testComparable()
    {
        ArrayList<CharacterFrequency> list = new ArrayList<>();
        
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(5);
        test.add(6);
        test.add(3);
        
        Collections.sort(test);
        System.out.println(test.get(0));
        
        list.add(new CharacterFrequency('a', 5));
        list.add(new CharacterFrequency('b', 5));
        list.add(new CharacterFrequency('c', 4));
        list.add(new CharacterFrequency((char) 0, 6));
        Collections.sort(list);
        
        assertTrue("", list.get(0).equals(new CharacterFrequency('c', 4)));
        assertTrue("", list.get(1).equals(new CharacterFrequency('a', 5)));
        assertTrue("", list.get(2).equals(new CharacterFrequency('b', 5)));
        assertTrue("", list.get(3).equals(new CharacterFrequency((char) 0, 6)));
    }
}
