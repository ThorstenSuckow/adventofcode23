package adventofcode23.day12;

import adventofcode23.lib.Parser;
import adventofcode23.lib.ParserResult;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RecordParser extends Parser {



    public ParserResult parseLine(String line, int lineIndex) {

        ParserResult res = new ParserResult();

        if (line.trim().isEmpty()) {
            return res;
        }

        long sum = compute(line);

        res.setValue(sum);

        // # ### ##

        return res;
    }

    public long compute(String line) {

        long sum = 0;

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

        //sum = probe(codes, numbers.stream().mapToInt(i -> i ).toArray());

        sum = probe(parts[0], numbers.stream().mapToInt(i -> i ).toArray());

        return sum;
    }

    public long probe(List<String> codes, int[] numbers) {
        return 0;
    }

    public long probe(String codes, int[] numbers) {

        long sum = 0;
        long product = 1;
        int numberCursor = 0;
        System.out.println(Arrays.toString(numbers));

        String code = codes;

       // for (int w = 0; w < codes.size(); w++) {

            int hashPos = 0;
            int codeLen = code.length();
            int objs = 0;
            List<Integer> numParts = new ArrayList<>();

            int complete = 0;

            for (int i = 0; i < numbers.length; i++) {
                numParts.add(numbers[i]);
            }

            System.out.println("Probing " + numberCursor + "/" + " with " + Arrays.toString(numbers) + " for " + code + " and " + codeLen);
            for (int i = numberCursor; i < numbers.length; i++) {

                // check if number can be skipped if a pattern of the same length
                // appears in code
            //    int hashes = sumSymbols(code, "#");
              //  code = code.replace("#", "");

                // normalize the code string based on the required length
               if (complete + numbers[i] + numParts.size() <= codeLen) {
                    complete += numbers[i];
                    if (numbers[i] > 1) {
                        objs += (numbers[i] -1);
                    }
                    numParts.add(numbers[i]);
                 //   numberCursor++;
                } else {
                    break;
                }
            }

            System.out.println("Finding pattern for "+ code + " " + Arrays.toString(numParts.stream().mapToInt(i->i).toArray()));
            code = findPattern(code, numParts);
            System.out.println("New Code: "+ code + " " + Arrays.toString(numParts.stream().mapToInt(i->i).toArray()));


          //  codeLen -= (objs + Math.max(0, numParts.size() - 1));

            int[] parts = numParts.stream().mapToInt(i->i).toArray();
            codeLen = code.length();
            for (int i = 0; i < parts.length; i++) {
                if (parts[i] > 1) {
                    codeLen -= (parts[i] - 1);
                }
            }
            codeLen -= Math.max(0, parts.length - 1);

            System.out.println("Codelen " + codeLen + " (" + code + ") " + Arrays.toString(parts) + " (numberCursor: " + numberCursor + ")");


            int set = codeLen;
            int objects = parts.length;
            if (set == 0 || objects == 0) {
                System.err.println("set or objects == 0");
            } else {
                long partialResult = fac(set) / (fac(set - objects) * fac(objects));
                System.out.println("Computing combinations for a set of " + set + " and " + objects + " objects: " + partialResult);
                System.out.println(" --");
                product *= partialResult;
                sum += product;
            }


        //}

        return sum;

    }


    public String findPattern(String code, List<Integer> numParts) {

        int[] numbers = numParts.stream().mapToInt(i->i).toArray();

        String newCode = "";

        if (code.contains("#")) {

            int textCursor = 0;

            for (int j = 0; j < numbers.length; j++) {
                String cmp = "1".repeat(numbers[j]);
                String middle = "0" + cmp + "0";
                String start = cmp + "0";
                String end = "0" +  cmp;

                String base = code.replace("?" , "0");
                base = base.replace("#" , "1");
                cmp = padRightZeros("1".repeat(numbers[j]), base.length());

                int max = 0;
                int pos = 0;
                for (int c = 0; c < base.length(); c++) {
                    cmp = padRightZeros("0".repeat(c) + "1".repeat(numbers[j]), base.length());
                    int startC = c;
                    int endC = Math.min(base.length(), c + cmp.length());

                    int user = Integer.parseUnsignedInt(
                            base.substring(startC, endC),
                            2
                    );
                    int mask = Integer.parseUnsignedInt(cmp, 2);

                    if ((user & mask) > max) {
                        pos = c;
                    }
                    max = Math.max(max, user & mask);
                }

                System.out.println("Remaining: " + remaining(j + 1, numbers));
                if (max > 0 && remaining(j + 1, numbers) <= code.length() - (pos + 1)) {
                    if (pos + numbers[j] < base.length()) {
                        if (base.charAt(pos + numbers[j]) == '1') {
                            continue;
                        }
                    }

                    System.out.print("Current code: "+ code+ "; Position: " + pos +"; textCursor: " + textCursor);

                    for (int i = 0; i < numParts.size(); i++) {
                        if (numParts.get(i).equals(numbers[j])) {
                            numParts.remove(i);
                            break;
                        }
                    }
                    char leadingChar = pos > 0 ? code.charAt(pos - 1) : '_';

                    boolean trimLeft = pos > 0 && code.charAt(pos - 1) == '?' ;
                    boolean trimRight =  pos + numbers[j] < code.length() && code.charAt(pos + numbers[j] ) == '?' ;

                    boolean trimLeftAndRight = pos > 0 && pos + numbers[j] - 1 < code.length() && code.charAt(pos) == '#' && code.charAt(pos + numbers[j] - 1) == '#';

                    if (trimLeftAndRight) {
                        //throw new RuntimeException();
                    }

                    //pos = leadingChar == '?' ? pos - 1 : pos;
                    code = code.substring(
                            textCursor,
                            textCursor + pos
                    )
                            + code.substring(Math.min(code.length() , textCursor + pos + numbers[j] + (trimLeftAndRight ? 1 : 0) ));
                    //textCursor += cmp.length();
                    if (trimLeftAndRight && !code.isEmpty()) {
                        code = code.substring(1, code.length());
                    } else if ((trimLeft || trimRight) && !code.isEmpty()) {
                        code = code.substring(1);
                    }

                    System.out.println(" ; code: " + code + "; numParts: " + Arrays.toString(numParts.stream().mapToInt(i->i).toArray()));


                }
            }

        }

        return code;
    }

    private Long fac(int n) {
        return LongStream.rangeClosed(1, n)
             .reduce(1, (long x, long y) -> x * y);
    }

    public int remaining(int index, int[] numbers) {
        int sum = 0;
        for (int i = index; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public void oldProbe(List<String> codes, int[] numbers) {

        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add("#".repeat(numbers[i]));
        }

        HashMap<String, List<String>> groups = new HashMap<>();
        for (String code: codes) {

            groups.computeIfAbsent(code, k -> new ArrayList<String>());

            int codeLen = code.length();
            int end = 1;
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < numbers.length; i++) {

                for (int j = 0; j <= i; j++) {
                    t.append("#".repeat(numbers[j]));
                }

                String dots = ".".repeat(Math.max(code.length() - t.length(), 0));
                t.append(dots);

                groups.get(code).add(t.toString());
                System.out.println(code + "->" + t);
                t = new StringBuilder();
            }

        }

        int sum = 0;
        List<Integer> skipGroups = new ArrayList<>();
        for (String code: codes) {

            List<String> tokenGroups = groups.get(code);
            int lastSuccessFullToken = 0;


            for (int i = 0; i < tokenGroups.size(); i++) {

                if (skipGroups.contains(i)) {
                    System.out.println(" xx Skipping " + tokenGroups.get(i) + " (" + i + ") for code " + code);
                    continue;
                }
                String tokenGroup = tokenGroups.get(i);
                if (tokenGroup.length() > code.length()) {
                    System.out.println("tokenGroup bigger than code, skipping (code: " + code + "; tokenGroup: " +tokenGroup + ")");
                    continue;
                }
                List<String> permutations = getPermutations(tokenGroup);

                if (!permutations.isEmpty()) {
                    permutations = sanitizePermutations(permutations, code, i, numbers);
                }

                if (permutations.isEmpty()) {
                    System.out.println("No permutation found for " + code + " and " +tokenGroup);
                    System.out.println("-- last successfull tokengroup was " + i);
                    break;
                } else {
                    System.out.println(" -> Found " + permutations.size() + " permutation for " + code + " and " +tokenGroup);
                    skipGroups.add(i);
                }

            }

        }

    }

    public int sumSymbols(String token, String symbol) {
        int index = token.indexOf(symbol);
        int sum = 0;
        while (index >= 0) {
            sum++;
            index = token.indexOf(symbol, index + 1);
        }
        return sum;
    }

    public List<String> sanitizePermutations(List<String> permutations, String code, int i, int[] numbers) {

        System.out.println("Sanitizing " + code + " for " + i + " (" + Arrays.toString(numbers));
        int maxHashes = Arrays.stream(Arrays.copyOfRange(numbers, 0, i)).sum();
        int minDots = i;

        List<String> filtered = new ArrayList<>();

        for (String permutation: permutations) {

            if (sumSymbols(permutation, "#") != maxHashes && sumSymbols(permutation, ".") < minDots) {
                System.out.println("Permutation " + permutation + " is invalid (minDots: " + minDots + "; maxHashes: " +  maxHashes+")");
                continue;
            }

            System.out.println(" - - " + permutation + " looks valid.");
            filtered.add(permutation);
            /*

            if (code.contains("#")) {
                int first = code.indexOf("#");
                first = Math.max(first - 1, first);
                int j = first +1;
                // ?#.
                // ?#?
                // #.
                //
                while (code.charAt(first + 1)) {

                }
                int j = 0:
                while (first < code.length() && j < numbers[i]) {

                }*/



        }

        return permutations;
    }



    public List<String> getPermutations(String s)
    {
        char[] c = s.toCharArray();
        Arrays.sort(c);

        List<String> permutations = new ArrayList<>();

        permutate(0, c, permutations);

        for (String opt: permutations) {
            //System.out.println(opt);
        }

        return permutations;
    }


    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public String padRightZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        inputString += "0";
        return padRightZeros(inputString, length);
    }
    private void swap(int pos1, int pos2, char[] c)
    {
        char temp = c[pos1];
        c[pos1] = c[pos2];
        c[pos2] = temp;
    }

    public void permutate(int start, char[] c, List<String> permutations)
    {
        if (start == c.length) {
            permutations.add(new String(c));
        }

        for (int i = start; i < c.length; i++) {
            if (i == start || (c[i] != c[i-1] && c[i] != c[start])) {
                swap(start, i, c);
                permutate(start + 1, c, permutations);
                swap(start, i, c);
            }
        }
    }


}
