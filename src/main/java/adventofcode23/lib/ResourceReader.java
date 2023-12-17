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


    public int getLineCount(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        int lineIndex = 0;

        try (InputStreamReader streamReader =
                 new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {

            while (bufferedReader.readLine() != null) {
                lineIndex++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lineIndex;
    }

    public List<ParserResult> parseContents(String fileName, Parser parser) {


        List<ParserResult> parserResults = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);


        try (InputStreamReader streamReader =
                 new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(streamReader)) {

            String line;
            int lineIndex = 0;
            while ((line = bufferedReader.readLine()) != null) {
                parserResults.add(parser.parseLine(line, lineIndex++));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parser.postProcess(parserResults);
    }


}
