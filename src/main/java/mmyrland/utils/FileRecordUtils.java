package mmyrland.utils;

import org.apache.commons.math3.util.Precision;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileRecordUtils {

    public static boolean isNumberRecord(String line) {
        if (line == null || line.trim().length() == 0) {
//Doesn't throw exception - if value is null or blank, it should be evaluated as text.
            return false;
        }
        try {
            Double.parseDouble(line.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }

    }

    public static boolean listContains(List<String> textLines, String subString) {
        if (textLines == null || subString == null) {
            throw new IllegalArgumentException("text lines and substring input for search must not be null.");
        }
        return textLines
                .stream()
                .filter(l -> l != null)
                .anyMatch(l -> l.contains(subString));
    }

//Using Double instead of primitive to allow nulls (valid if no number records exist in file)
    public static Double buildAverage(List<Double> numberList) {
//Lets calling service determine how to handle null lists.
        if (numberList == null || numberList.size() == 0) {
            return null;
        }
        Double avg = numberList
                .stream()
                .mapToDouble(n -> n)
                .average()
                .orElse(0);

        return Precision.round(avg, 2);
    }


//Using Double instead of primitive to allow nulls (valid if no number records exist in file)
    public static Double buildMedian(List<Double> numberList) {
        //Lets calling service determine how to handle null lists.
        if (numberList == null || numberList.size() == 0) {
            return null;
        }

        Integer middle = numberList.size() / 2;
        if (numberList.size() % 2 == 0) {
            return Precision.round(
                    (numberList.get(middle - 1) + numberList.get(middle)) / 2
                    , 2
            );
        }

        return numberList.get(middle);

    }

//Using Double instead of primitive to allow nulls (valid if no number records exist in file)
    public static Double buildSum(List<Double> numberList) {
        //Lets calling service determine how to handle null lists.
        if (numberList == null || numberList.size() == 0) {
            return null;
        }

        return Precision.round(
                numberList
                        .stream()
                        .mapToDouble(d -> d)
                        .sum()
                , 2);
    }

    public static Map<String, Long> buildSortStringSet(List<String> textLines) {
        Map<String, Long> stringMap = textLines.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return stringMap;

    }

    public static Double getPercentage(Integer total, Integer subTotal) {
        //Lets calling service determine how to handle null options.
        if (total == 0 || subTotal == 0) {
            return null;
        }
        return Precision.round(
                (
                        ((double) subTotal) / ((double) total)) * 100
                , 2
        );
    }

}
