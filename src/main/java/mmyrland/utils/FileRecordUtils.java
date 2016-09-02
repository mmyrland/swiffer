package mmyrland.utils;

import org.apache.commons.math3.util.Precision;

import java.util.List;

public class FileRecordUtils {

    public static boolean isNumberRecord(String line) {
        if (line == null) {
            return false;
        }
        if (line.trim().length() == 0) {
            return false;
        }
        try {
            Long.parseLong(line.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }

    }

    public static boolean listContains(List<String> textLines, String subString) {
        if (textLines == null || subString == null) {
            return false;
        }
        return textLines
                .stream()
                .filter(l -> l != null)
                .anyMatch(l -> l.contains(subString));
    }

    public static Double buildAverage(List<Double> numberList) {
        return numberList.stream().mapToDouble(n -> n).average().orElse(0);
    }

    public static Double buildMedian(List<Double> numberList) {

        Integer middle = numberList.size() / 2;
        if (numberList.size() % 2 == 0) {
            return Precision.round((numberList.get(middle - 1) + numberList.get(middle)) / 2, 2);
        }

        return numberList.get(middle);

    }

    public static Double buildSum(List<Double> numberList) {
        return numberList.stream().mapToDouble(d -> d).sum();
    }

}
