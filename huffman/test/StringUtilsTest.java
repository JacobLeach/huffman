package huffman.test;

import huffman.StringUtils;

public class StringUtilsTest
{
    // TODO: Use unit tests
    public static void main(String[] args)
    {
        String test = "0111";
        System.out.println(test + " to " + StringUtils.addOne(test));

        int intTest = 7;
        System.out.println(Integer.toBinaryString(intTest) + " to " + Integer.toBinaryString(intTest + 1));

        System.out.println();
        
        int shift = 3;
        System.out.println(test + " to " + StringUtils.shiftLeft(test, shift));
        System.out.println(Integer.toBinaryString(intTest) + " to " + Integer.toBinaryString(intTest << shift));
    }

}
