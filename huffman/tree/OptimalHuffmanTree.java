package huffman.tree;

import huffman.CharacterFrequency;
import huffman.HuffmanCode;
import huffman.error.IllegalOptimalHuffmanTreeTraversalError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptimalHuffmanTree
{
    private static final String     leftTraversalAddition  = "0";
    private static final String     rightTraversalAddition = "1";

    private final Node              _head;
    private final List<HuffmanCode> _codes;

    public OptimalHuffmanTree(List<CharacterFrequency> characterFrequencies)
    {
        _head = generateTree(generateLeafNodes(characterFrequencies));
        _codes = generateCodes(_head);
    }

    public List<HuffmanCode> getCodes()
    {
        return _codes;
    }

    public Node getHead()
    {
        return _head;
    }

    private List<Node> generateLeafNodes(List<CharacterFrequency> characterFrequencies)
    {
        ArrayList<Node> leafNodes = new ArrayList<>();

        for (CharacterFrequency current : characterFrequencies)
        {
            leafNodes.add(new Node(current));
        }

        return leafNodes;
    }

    private Node generateTree(List<Node> leafNodes)
    {
        while (leafNodes.size() > 1)
        {
            Collections.sort(leafNodes);
            leafNodes.add(new Node(leafNodes.remove(0), leafNodes.remove(0)));
        }

        return leafNodes.get(0);
    }

    private List<HuffmanCode> generateCodes(Node head)
    {
        List<HuffmanCode> codes = new ArrayList<>();
        generateCodes(head, "", codes);

        return codes;
    }

    private void generateCodes(Node current, String code, List<HuffmanCode> codes)
    {
        if (current == null)
        {
            throw new IllegalOptimalHuffmanTreeTraversalError("Accessed null node.");
        }

        // If this is a leaf node
        if ((current.getLeft() == null) && (current.getRight() == null))
        {
            System.out.println("Found: " + current.getChar() + ", Code: " + code + ", Length: " + code.length());
            codes.add(new HuffmanCode(current.getChar(), code.length()));
        }
        
        // If not a leaf and a left node exists
        if (current.getLeft() != null)
        {
            generateCodes(current.getLeft(), code + leftTraversalAddition, codes);
        }
        
        // If not a lead and a right node exists
        if (current.getRight() != null)
        {
            generateCodes(current.getRight(), code + rightTraversalAddition, codes);
        }
    }
}
