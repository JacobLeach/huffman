package huffman;

import java.util.Comparator;

import huffman.error.DuplicateHuffmanCodeError;

public class HuffmanCode
{
    private final Character _character;
    private final long      _codeLength;

    public HuffmanCode(Character character, long codeLength)
    {
        _character = character;
        _codeLength = codeLength;
    }

    public long getCodeLength()
    {
        return _codeLength;
    }

    public Character getCharacter()
    {
        return _character;
    }

    @Override
    public String toString()
    {
        return "Character: " + (_character == (char) 0 ? "EOF" : _character) + ", Code: " + _codeLength;
    }

    @Override
    public boolean equals(Object other)
    {
        boolean toReturn;

        if (other == null)
        {
            toReturn = false;
        }
        else if (!(other instanceof HuffmanCode))
        {
            toReturn = false;
        }
        else
        {
            HuffmanCode cast = (HuffmanCode) other;
            toReturn = (_character == cast._character) && (_codeLength == cast._codeLength);
        }

        return toReturn;
    }

}
