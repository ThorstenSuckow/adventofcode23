package adventofcode23.day12;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RecordParser extends Parser {



    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if (line.trim().isEmpty()) {
            return res;
        }

        int sum = compute(line);

        res.setValue(sum);

        return res;
    }

    public int compute(String line) {

        int sum = 0;

        String[] parts = line.split(" ");

        List<String> ignoreCode = new ArrayList<>();

        String[] codeParts = parts[0].split("\\.");
        String codeString = parts[0];
        List<String> codes = new ArrayList<>();
        for (String s : codeParts) {
            if (!s.isEmpty()) {
                codes.add(s);
            }
        }
        List<Integer> numbers = new ArrayList<>();
        String[] n = parts[1].split(",");
        for (String s : n) {
            numbers.add(Integer.valueOf(s));
        }

        int cs = codes.size();
        int ns = numbers.size();

        System.out.println("sizes: " + cs + " " + ns);

        int numberCursor = 0;
        char c;
        boolean leadingSeparator = false;

        for (int i = 0; i < codeString.length(); i++) {

            c = codeString.charAt(i);
            String group = "";

            leadingSeparator = c == '.';

            System.out.println(c + " ("+ i + ")");
            if (c == '?' || c == '#') {
                while (c != '.') {
                    group = group + c;
                    if (i + 1 == codeString.length()) {
                        break;
                    }
                    c = codeString.charAt(++i);
                }
            }

            if (group.equals("")) {
                continue;
            }

            if (!leadingSeparator && group.charAt(0) == '?') {
                //group = group.substring(1);
            }

            List<Integer> partitions = new ArrayList<>();
            int groupLen = group.length();
            int fill = 0;
            System.out.println("Number Cursor: " + numberCursor + " " + Arrays.toString(numbers.toArray()));
            for (int j = numberCursor; j < numbers.size(); j++) {
                partitions.add(numbers.get(j));
            }

            String fragments = "";
            for (int o = 0; o < partitions.size(); o++) {
                fragments += "#".repeat(partitions.get(o)) + ".";
                if (fragments.length() > group.length()) {
                    System.out.println("Fragment " + fragments + " doesnt fit into " + group);
                    break;
                } else {
                    System.out.println("Fragment " + fragments + " fits into " + group);
                    String reverse = reverse(fragments);
                    System.out.println("reverse Fragment " + reverse + " fits into " + group);

                }
            }
            fragments = fragments.substring(0, fragments.length() - 1);



            System.out.println("Requires leading: " + fragments + " " + leadingSeparator + "; Partitions for group: " +numberCursor + " . " + group +": " + Arrays.toString(partitions.toArray()));





        }




        return sum;
    }


    public void log(List<Integer> numbers, List<String> codes) {

        for (String code: codes) {
            System.out.print(code + ".");
        }
        for (Integer num: numbers) {
            System.out.print(num + ",");
        }

        System.out.println();

    }

    public int findFixed(String code) {
        Pattern p = Pattern.compile("#");
        Matcher m = p.matcher(code);
        int count = 0;
        while (m.find()){
            count +=1;
        }
        return count;
    }


    public static long fac(int n) {
        long fak = 1L;
        for ( int i = 2; i <= n; i++ )
            fak = fak * i;
        return fak;
    }


    public String reverse(String input) {
        StringBuilder input1 = new StringBuilder();

        // append a string into StringBuilder input1
        input1.append(input);

        // reverse StringBuilder input1
        input1.reverse();

        String o = input1.toString();
        if (input.endsWith(".") && o.startsWith(".")) {
        //    return o.substring(1) + '.';
        }
        if (input.startsWith(".") && o.endsWith(".")) {
          //  return "." + o.substring(0, o.length() - 1);
        }

        return input1.toString();
    }
}
