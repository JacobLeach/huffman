package huffman;

import huffman.error.DuplicateHuffmanCodeError;

import java.util.Comparator;

public class HuffmanCodeAlphabeticalOrder implements Comparator<CanonicalHuffmanCode>
{
    @Override
    public int compare(CanonicalHuffmanCode o1, CanonicalHuffmanCode o2)
    {
        if (o1.getCharacter() < o2.getCharacter())
        {
            return -1;
        }
        else if (o1.getCharacter() > o2.getCharacter())
        {
            return +1;
        }
        else
        {
            throw new DuplicateHuffmanCodeError("Two Huffman Codes have the same letter. This should never happen.");
        }
    }
}
