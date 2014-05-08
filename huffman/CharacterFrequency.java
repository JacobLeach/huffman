package huffman;

import huffman.error.DuplicateCharacterFrequencyError;
import huffman.error.NegativeFrequencyError;

public class CharacterFrequency implements Comparable<CharacterFrequency>
{
    private final Character _character;
    private final long      _frequency;

    public CharacterFrequency(Character character, long frequency)
    {
        if (frequency < 0)
        {
            throw new NegativeFrequencyError("A character can not appear a negative amount of times.");
        }
        _character = character;
        _frequency = frequency;
    }

    public Character getCharacter()
    {
        return _character;
    }

    public long getFrequency()
    {
        return _frequency;
    }

    @Override
    public String toString()
    {
        return "Character: " + _character + ", Frequency: " + _frequency;
    }

    @Override
    public boolean equals(Object other)
    {
        boolean toReturn;
        
        if (other == null)
        {
            toReturn = false;
        }
        else if (!(other instanceof CharacterFrequency))
        {
            toReturn = false;
        }
        else
        {
            CharacterFrequency cast = (CharacterFrequency) other;
            toReturn = (_character == cast._character) && (_frequency == cast._frequency);
        }
        
        return toReturn;
    }

    @Override
    public int compareTo(CharacterFrequency other)
    {
        int toReturn = 0;

        // This frequency is less so it is less, return -1
        if (_frequency < other._frequency)
        {
            toReturn = -1;
        }
        // This frequency is higher so it is more, return 1
        else if (_frequency > other._frequency)
        {
            toReturn = 1;
        }
        // This frequency is the same as the other, check character
        else
        {
            //If this is within a node and we don't have a character, return 0
            if(_character == null)
            {
                toReturn = 0;
            }
            // This character is less so it is less, return -1
            else if (_character < other._character)
            {
                toReturn = -1;
            }
            // This character is higher so it is more, return 1
            else if (_character > other._character)
            {
                toReturn = 1;
            }
            else
            {
                throw new DuplicateCharacterFrequencyError("There are two CharacterFrequencies that are the same. This can not happen in a correct optimal huffman tree.");
            }
        }

        return toReturn;
    }
}
