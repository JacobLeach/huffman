package huffman.test;

import static org.junit.Assert.*;
import huffman.CanonicalHuffmanCode;
import huffman.HuffmanCode;
import huffman.tree.CanonicalHuffmanTree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CanonicalHuffmanTreeTest
{

    public static void main(String[] args)
    {
        List<HuffmanCode> test = new ArrayList<>();
        // test.add(new CharacterFrequency('f', 5));
        // test.add(new CharacterFrequency('e', 9));
        // test.add(new CharacterFrequency('c', 12));
        // test.add(new CharacterFrequency('b', 13));
        // test.add(new CharacterFrequency('d', 16));
        // test.add(new CharacterFrequency('a', 45));

        test.add(new HuffmanCode((char) 0, 4));
        test.add(new HuffmanCode('a', 4));
        test.add(new HuffmanCode('b', 4));
        test.add(new HuffmanCode('c', 3));
        test.add(new HuffmanCode('d', 3));
        test.add(new HuffmanCode('e', 2));
        test.add(new HuffmanCode('f', 3));
        test.add(new HuffmanCode('g', 3));
        test.add(new HuffmanCode('h', 4));


        // OptimalHuffmanTree tree = new OptimalHuffmanTree(test);
        CanonicalHuffmanTree canonicalTree = new CanonicalHuffmanTree(test);

        List<CanonicalHuffmanCode> canonicalCodes = canonicalTree.generateCanonicalCodes(test);
        for (CanonicalHuffmanCode current : canonicalCodes)
        {
            System.out.println(current);
            //0001 A 0010 B 0010 B 010 C 010 C 010 C 011 D 011 D 011 D 011 D 11 E 11 E 11 E 11 E 100 F 100 F 100 F 101 G 101 G
            //H 0011 EOF 0000 00000000
        }
    }

}
