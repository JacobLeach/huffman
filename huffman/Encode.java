package huffman;

import huffman.tree.CanonicalHuffmanTree;
import huffman.tree.OptimalHuffmanTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Encode
{
    public static void main(String[] args)
    {
        encode(new File("sample5.txt"), new File("test.huf"));
    }
    
    private static void encode(File input, File output)
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            HashMap<Character, Integer> counts = new HashMap<Character, Integer>();
            int nextChar = reader.read();

            while (nextChar != -1)
            {
                if (counts.containsKey((char) nextChar))
                {
                    counts.put((char) nextChar, counts.get((char) nextChar) + 1);
                }
                else
                {
                    counts.put((char) nextChar, 1);
                }

                nextChar = reader.read();
            }
            
            reader.close();
            
            // Add EOF
            counts.put((char) 0, 1);
            
            //Generate OptimalHuffmanTree
            List<CharacterFrequency> characterFrequencies = new ArrayList<>();
            List<Character> chars = new ArrayList<>(counts.keySet());
            for(Character current : chars)
            {
                characterFrequencies.add(new CharacterFrequency(current, counts.get(current)));
            }
            
            OptimalHuffmanTree tree = new OptimalHuffmanTree(characterFrequencies);
            
            //Generate Canonical Huffman Codes and put in map
            CanonicalHuffmanTree canonicalTree = new CanonicalHuffmanTree(tree.getCodes());
            HashMap<Character, String> codes = new HashMap<>();
            for(CanonicalHuffmanCode current : canonicalTree.getCodes())
            {
                codes.put(current.getCharacter(), current.getCode());
            }
            
            FileOutputStream writer = new FileOutputStream(output);
            
            //First output code book
            writer.write(canonicalTree.getEncodingBook());
            
            //Write out the rest
            reader = new BufferedReader(new FileReader(input));
            nextChar = (char) reader.read();
            StringBuilder buffer = new StringBuilder();

            while (nextChar != -1)
            {
                buffer.append(codes.get((char) nextChar));
                
                if (buffer.length() >= 8)
                {
                    byte toWrite = 0;
                    int power = 7;
                    
                    for (int i = 0; i < 8; i++)
                    {
                        toWrite += (Math.pow(2, power) * (buffer.charAt(i) == '0' ? 0 : 1));
                        power--;
                    }
                    buffer.delete(0, 8);

                    writer.write(toWrite);
                }

                nextChar = reader.read();
            }
                        
            //Add EOF
            buffer.append(codes.get((char) 0));
            
            //Make it byte sized
            while(buffer.length() % 8 != 0)
            {
                buffer.append('0');
            }

            while(buffer.length() > 0)
            {
                
                byte toWrite = 0;
                int power = 7;
                
                for (int i = 0; i < 8; i++)
                {
                    toWrite += (Math.pow(2, power) * (buffer.charAt(i) == '0' ? 0 : 1));
                    power--;
                }
                
                buffer.delete(0, 8);

                writer.write(toWrite);
            }
            
            writer.flush();
            writer.close();
            
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
