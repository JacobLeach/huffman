package huffman.tree;

import huffman.CharacterFrequency;

public class Node implements Comparable<Node>
{
    private final Node                   _left;
    private final Node                   _right;
    private final CharacterFrequency _characterFrequency;

    public Node(CharacterFrequency characterFrequency)
    {
        _left = null;
        _right = null;
        _characterFrequency = characterFrequency;
    }

    public Node(Node left, Node right)
    {
        _left = left;
        _right = right;
        _characterFrequency = new CharacterFrequency(null, left.getFrequency() + right.getFrequency());
    }

    public Character getChar()
    {
        return _characterFrequency.getCharacter();
    }

    public long getFrequency()
    {
        return _characterFrequency.getFrequency();
    }

    public String toString()
    {
        return toString(6);
    }

    public Node getLeft()
    {
        return _left;
    }

    public Node getRight()
    {
        return _right;
    }

    public String toString(int indent)
    {
        String temp = "Char: " + _characterFrequency.getCharacter() + ", Frequency: " + _characterFrequency.getFrequency() + "\n";
        for (int i = 0; i < indent; i++)
        {
            temp += " ";
        }

        if (_left != null)
        {
            temp += _left.toString(indent + 6) + "\n";
            for (int i = 0; i < indent; i++)
            {
                temp += " ";
            }
        }

        if (_right != null)
        {
            temp += _right.toString(indent + 6);
        }
        return temp;
    }

    @Override
    public int compareTo(Node other)
    {
        return _characterFrequency.compareTo(other._characterFrequency);
    }
}
