package huffman;

public class StringUtils
{
    public static String addOne(String code)
    {
        String newCode = "";

        while (code.length() > 1 && code.charAt(code.length() - 1) == '1')
        {
            newCode = "0" + newCode;
            code = code.substring(0, code.length() - 1);
        }

        System.out.println("ADD: " + code + ", Length: " + code.length());
        
        if (code.length() == 1 && code.charAt(0) == '0')
        {
            newCode = "1" + newCode;
        }
        else if(code.length() == 1 && code.charAt(0) == '1')
        {   
            newCode = "10" + newCode;
        }
        else
        {
            newCode = code.substring(0, code.length() - 1) + "1" + newCode;
        }

        return newCode;
    }

    public static void main(String[] args)
    {
        int shift = 4;

        String test = "11101";
        System.out.println(test + " to " + StringUtils.shiftRight(test, shift));

        int intTest = 29;
        System.out.println(Integer.toBinaryString(intTest) + " to " + Integer.toBinaryString(intTest >> shift));
    }

    public static String shiftLeft(String code, long shift)
    {
        if (shift < 0)
        {
            return shiftRight(code, Math.abs(shift));
        }
        else
        {
            for (long i = 0; i < shift; i++)
            {
                code += "0";
            }

            return code;
        }
    }

    public static String shiftRight(String code, long number)
    {
        if(number == 0)
        {
            return code;
        }
        
        return code.substring(0, (int) (code.length() - number));
    }
}
