package huffman;

public class CanonicalHuffmanCode
{
    private final Character _character;
    private final String _code;
    
    public CanonicalHuffmanCode(Character character, String code)
    {
        _character = character;
        _code = code;
    }
    
    @Override
    public String toString()
    {
        return "Character: " + (_character == (char) 0 ? "EOF" : _character) + ", Code: " + _code;
    }
}
