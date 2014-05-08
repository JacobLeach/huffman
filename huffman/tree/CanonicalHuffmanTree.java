package huffman.tree;

import huffman.CanonicalHuffmanCode;
import huffman.CharacterFrequency;
import huffman.HuffmanCode;
import huffman.HuffmanCodeDescendingOrder;
import huffman.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CanonicalHuffmanTree
{
    public CanonicalHuffmanTree(List<HuffmanCode> codes)
    {

    }

    public List<CanonicalHuffmanCode> generateCanonicalCodes(List<HuffmanCode> codes)
    {

        List<HuffmanCode> copy = new ArrayList<>(codes);
        Collections.sort(copy, new HuffmanCodeDescendingOrder());

        List<CanonicalHuffmanCode> canonicalCodes = new ArrayList<>();

        // Try 3 --- Works fine but isn't what Carle did
        // String code = "";
        // long maxLength = copy.get(copy.size() - 1).getCodeLength();
        //
        // for(long i = 0; i <= maxLength; i++)
        // {
        // code = StringUtils.shiftLeft(code, 1);
        //
        // String startCode = code;
        // int numberOfCodes = 0;
        //
        // while ((copy.size() > 0) && (copy.get(0).getCodeLength() == i))
        // {
        // canonicalCodes.add(new
        // CanonicalHuffmanCode(copy.get(0).getCharacter(),
        // startCode.substring(1)));
        // copy.remove(0);
        // startCode = StringUtils.addOne(startCode);
        // numberOfCodes++;
        // }
        //
        // for(int j = 0; j < numberOfCodes; j++)
        // {
        // code = StringUtils.addOne(code);
        // }
        // }

        // Try 2 (Really like 4th distinct try 2)

        // List<HuffmanCode> copy = new ArrayList<>(codes);
        // Collections.sort(copy);
        // Collections.reverse(copy);
        //
        // List<CanonicalHuffmanCode> canonicalCodes = new ArrayList<>();
        //
        // long maxLength = copy.get(0).getCodeLength();
        //
        // String code = "";
        // while (code.length() < maxLength)
        // {
        // code += "0";
        // }
        //
        // long lastCode = maxLength;
        //
        // System.out.println("Code: " + code);
        //
        // for (long i = maxLength; i >= 0; i--)
        // {
        // // Perform code + lastCode
        // for (long j = 0; j < lastCode; j++)
        // {
        // code = StringUtils.addOne(code);
        // }
        //
        // // Perform shift
        // code = StringUtils.shiftRight(code, 1);
        //
        // // Get all codes in this code length
        // String startCode = code;
        // while ((copy.size() > 0) && (copy.get(0).getCodeLength() == i))
        // {
        // System.out.println(copy.get(0) + ", Start Code: " + startCode);
        //
        // canonicalCodes.add(new
        // CanonicalHuffmanCode(copy.get(0).getCharacter(), startCode));
        // copy.remove(0);
        // startCode = StringUtils.addOne(startCode);
        // }
        //
        // lastCode = code.length();
        // }

        // Try 1 (Really like 4th distinct try 1)

        String code = "0";
        while (code.length() < copy.get(0).getCodeLength())
        {
            code += "0";
        }

        for (int i = 0; i < copy.size() - 1; i++)
        {
            HuffmanCode current = copy.get(i);
            HuffmanCode next = copy.get(i + 1);

            canonicalCodes.add(new CanonicalHuffmanCode(current.getCharacter(), code));
            code = StringUtils.shiftLeft(code, next.getCodeLength() - current.getCodeLength());
            code = StringUtils.addOne(code);
        }

        canonicalCodes.add(new CanonicalHuffmanCode(copy.get(copy.size() - 1).getCharacter(), code));

        return canonicalCodes;
    }
}
