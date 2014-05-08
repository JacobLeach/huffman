package huffman;

import java.util.Comparator;

import huffman.error.DuplicateHuffmanCodeError;

public class HuffmanCodeAssendingOrder implements Comparator<HuffmanCode>
{
    @Override
    public int compare(HuffmanCode o1, HuffmanCode o2)
    {
        int toReturn = 0;

        // This code length is smaller, so return -1
        if (o1.getCodeLength() < o2.getCodeLength())
        {
            toReturn = -1;
        }
        // This code length is longer, so return +1
        else if (o1.getCodeLength() > o2.getCodeLength())
        {
            toReturn = +1;
        }
        // The code lengths are the same
        else
        {
            // This character is smaller, so return -1
            if (o1.getCharacter() < o2.getCharacter())
            {
                toReturn = -1;
            }
            // This character is larger, so return +1
            else if (o1.getCharacter() > o2.getCharacter())
            {
                toReturn = 1;
            }
            // The characters are the same
            else
            {
                throw new DuplicateHuffmanCodeError("There are two identical HuffmanCodes being compared. This should never happen.");
            }
        }

        return toReturn;
    }
}
