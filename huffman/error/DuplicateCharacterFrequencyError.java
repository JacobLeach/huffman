package huffman.error;

@SuppressWarnings("serial")
public class DuplicateCharacterFrequencyError extends Error
{  
    public DuplicateCharacterFrequencyError(String message)
    {
        super(message);
    }
}
