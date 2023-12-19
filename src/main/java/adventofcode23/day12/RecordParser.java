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

        sum = probe(codes, numbers.stream().mapToInt(i -> i ).toArray());


        return sum;
    }

    public int probe(List<String> codes, int[] numbers) {

        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < numbers.length; i++) {
            list.add("#".repeat(numbers[i]));
        }

        int sum = 0;
        int start = 0;
        for (String code: codes) {
            int cmp = 0;
            List<Integer> partitions = new ArrayList<>();
            System.out.println("starting at " + start);
            for (int i = start; i < numbers.length; i++) {
                start = i;
                if (cmp + numbers[i] + (i > 0 && i <= numbers.length - 1 ? 1 : 0)  <= code.length()) {
                    cmp += numbers[i] + i;
                    partitions.add(numbers[i]);
                } else {
                    break;
                }
            }
            System.out.println(partitions.size() + " possible partitions for " + code + " (" + Arrays.toString(partitions.toArray()) + ")");

            int[] parts = partitions.stream().mapToInt(i->i).toArray();

            List<int[]> groups = new ArrayList<>();


            for (int i = 0; i < parts.length; i++) {

                int tail = 0;
                for (int j = 0; j < i; j++) {
                    tail += numbers[j];
                }

                int head = 1;
                for (int j = i + 1; j < parts.length; j++) {
                    head += numbers[j];
                }

                groups.add(
                    new int[]{tail, (code.length()) - head
                    }
                );
                System.out.println("added " + tail + " " + (code.length() - head) + " codeLength: " + code.length());
            }

            int i = 0;
            int partialResult = 0;
            System.out.println(Arrays.toString(parts));
            for (int[] group: groups) {
                int spots = (group[1]) - group[0] + 1;
                int length = parts[i];
                System.out.println("partial result for " + (spots) +" " + parts[i]);
                partialResult += spots - (groups.size() - 1 )- parts[i] + 1;
                i++;
            }
            sum += partialResult ;
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
