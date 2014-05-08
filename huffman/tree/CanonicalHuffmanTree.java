package huffman.tree;

import huffman.CanonicalHuffmanCode;
import huffman.CharacterFrequency;
import huffman.HuffmanCode;
import huffman.HuffmanCodeAlphabeticalOrder;
import huffman.HuffmanCodeDescendingOrder;
import huffman.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CanonicalHuffmanTree
{
    private List<CanonicalHuffmanCode> _codes;
    
    public CanonicalHuffmanTree(List<HuffmanCode> codes)
    {
        _codes = generateCanonicalCodes(codes);
    }
    
    public byte[] getEncodingBook()
    {
        byte[] book = new byte[1 + (_codes.size() * 2)];
        book[0] = (byte) _codes.size();

        // Get a sorted list of codes
        Collections.sort(_codes, new HuffmanCodeAlphabeticalOrder());

        // Put the char values into the byte array
        for (int i = 0; i < _codes.size(); i++)
        {
            book[(i * 2) + 1] = (byte) _codes.get(i).getCharacter().charValue();
            book[(i * 2) + 2] = (byte) _codes.get(i).getCode().length();
        }

        return book;
    }
    
    public List<CanonicalHuffmanCode> getCodes()
    {
        return _codes;
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

        /*
         * This only did not work before because of bugs in my shiftLeft and addOne methods. :(
         * Really should have done more unit testing.
         */
        
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
