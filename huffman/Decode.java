package huffman;

import huffman.tree.CanonicalHuffmanTree;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decode
{
    public static void main(String[] args)
    {
        decode(new File("test.huf"), new File("veryTest.txt"));
    }

    private static void decode(File input, File output)
    {
        FileInputStream reader;
        try
        {
            reader = new FileInputStream(input);
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));
            List<HuffmanCode> characterCodeLengths = new ArrayList<>();

            // Get the dictionary size
            int numberOfChars = reader.read();

            // Read in the rest of the tree
            byte[] bytes = new byte[numberOfChars * 2];
            reader.read(bytes);

            for (int i = 0; i < bytes.length; i += 2)
            {
                System.out.println(Integer.toBinaryString(bytes[i]));
                System.out.println(Integer.toBinaryString(bytes[i + 1]));
                characterCodeLengths.add(new HuffmanCode((char) bytes[i], (int) bytes[i + 1]));
            }

            // Get encoding
            List<CanonicalHuffmanCode> codeList = new CanonicalHuffmanTree(characterCodeLengths).getCodes();
            HashMap<String, Character> codes = new HashMap<>();
            int longestCode = 0;
            for (CanonicalHuffmanCode current : codeList)
            {
                if (current.getCode().length() > longestCode)
                {
                    longestCode = current.getCode().length();
                }
                codes.put(current.getCode(), current.getCharacter());
                System.out.println(current);
            }

            // Get first encoded byte
            int nextByte = reader.read();
            System.out.println(StringUtils.intToString(nextByte));
            boolean done = false;
            StringBuilder buffer = new StringBuilder();

            while (!done)
            {
                buffer.append(StringUtils.intToString(nextByte));

                while (buffer.length() > 8 && buffer.length() > longestCode)
                {
                    for (int i = 1; i < buffer.length(); i++)
                    {
                        String prefix = buffer.substring(0, i);

                        if (codes.containsKey(prefix))
                        {
                            if (codes.get(prefix) == (char) 0)
                            {
                                done = true;
                                buffer.delete(0, buffer.length());
                                break;
                            }
                            else
                            {
                                //System.out.println("Found... " + codes.get(prefix) + " in " + buffer);
                                writer.append(codes.get(prefix));
                                buffer.delete(0, i);
                                break;
                            }
                        }
                    }
                }

                nextByte = reader.read();
            }

            writer.flush();
            writer.close();
            reader.close();

        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
