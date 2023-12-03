package adventofcode23.lib;

import adventofcode23.day1.CalibrationValueParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    public List<ParserResult> parseContents(String fileName, Parser parser) {


        List<ParserResult> parserResults = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);


        try (InputStreamReader streamReader =
                 new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                parserResults.add(parser.parseLine(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parserResults;
    }
}
