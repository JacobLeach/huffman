package huffman.test;

import huffman.CharacterFrequency;
import huffman.tree.OptimalHuffmanTree;
import java.util.ArrayList;
import java.util.List;

public class OptimalHuffmanTreeTest
{

    public static void main(String[] args)
    {
        List<CharacterFrequency> test = new ArrayList<>();
        test.add(new CharacterFrequency('f', 5));
        test.add(new CharacterFrequency('e', 9));
        test.add(new CharacterFrequency('c', 12));
        test.add(new CharacterFrequency('b', 13));
        test.add(new CharacterFrequency('d', 16));
        test.add(new CharacterFrequency('a', 45));
        
        OptimalHuffmanTree tree = new OptimalHuffmanTree(test);
        System.out.println(tree.getHead());
        
        
        tree.getCodes();
    }
}
