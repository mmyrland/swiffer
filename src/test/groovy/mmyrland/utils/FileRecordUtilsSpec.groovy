package mmyrland.utils

import spock.lang.Specification
import spock.lang.Unroll

class FileRecordUtilsSpec extends Specification {

    @Unroll
    def "isNumberRecord returns #result when #scenario"() {
        expect:
        result == FileRecordUtils.isNumberRecord(text)

        where:
        scenario                                     | text                         || result
        "string with number and text"                | "1, 2, 5 - Three sir!"       || false
        "string with just number"                    | "42"                         || true
        "string with just number padded with spaces" | " 42 "                       || true
        "string with only text"                      | "We are but boys grown tall" || false
        "empty string"                               | ""                           || false
        "padded empty string"                        | " "                          || false
    }

    def "listContains returns false when exact substring is not in any string in list."() {
        given:
        def stringList = ["the grey fox", "Another cuppa tea please", " "]
        def subString = "foxes with boots"

        when:
        def result = FileRecordUtils.listContains(stringList, subString)

        then:
        result == false
    }

    def "listContains returns true when substring is in string in list and is a number."() {
        given:
        def stringList = ["pi = 3.14 and more", "[insert math joke...]", " "]
        def subString = "3.14"

        when:
        def result = FileRecordUtils.listContains(stringList, subString)

        then:
        result == true
    }


    def "listContains throws IllegalArgumentException when list is null"() {
        given:
        def subString = "Nobody to compare to..."

        when:
        FileRecordUtils.listContains(null, subString)

        then:
        IllegalArgumentException exception = thrown()
        exception.getMessage() == "text lines and substring input for search must not be null."
    }


    def "listContains throws IllegalArgumentException when subString is null"() {
        given:
        def stringList = ["pi = 3.14 and more", "[insert math joke...]", " "]

        when:
        FileRecordUtils.listContains(stringList, null)

        then:
        IllegalArgumentException exception = thrown()
        exception.getMessage() == "text lines and substring input for search must not be null."
    }

    @Unroll
    def "buildAverage returns #result when #scenario"() {
        expect:
        result == FileRecordUtils.buildAverage(doubleList)

        where:
        scenario                          | doubleList                  || result
        "list contains positive integers" | [41d, 42d, 43d, 44d, 45d]   || 43d
        "list contains zeroes"            | [0d, 0d, 0d, 0d, 0d, 6.66d] || 1.11d
        "list contains negative number"   | [-6d, 6d]                   || 0d
    }

    @Unroll
    def "buildMedian returns #result when #scenario."() {
        expect:
        result == FileRecordUtils.buildMedian(doubleList)

        where:
        scenario                         | doubleList             || result
        "size of list is even"           | [1d, 2d, 4d, 9d]       || 3d
        "size of list is odd"            | [6d, 7d, 8d, 9d, 102d] || 8d
        "list contains negative numbers" | [-1d, 1d, 250d]        || 1d
        "list contains zeroes in middle" | [-1d, 0d, 0d, 250d]    || 0d
        "list contains decimals"         | [50.5d, 50.6d, 50.7d]  || 50.6d
    }

    @Unroll
    def "buildSum returns #result when #scenario."() {
        expect:
        result == FileRecordUtils.buildSum(doubleList)

        where:
        scenario                 | doubleList       || result
        "list size is 1"         | [1d]             || 1d
        "list is empty"          | []               || null
        "random list of numbers" | [-1d, 51d, 250d] || 300d
    }

    @Unroll
    def "buildSortStringSet correctly sorts string values."() {
        expect:
        LinkedHashMap<String, Long> result = FileRecordUtils.buildSortStringSet(stringList)
        result.size() == distinctListSize
        List<Map.Entry<String, Long>> resultList = new ArrayList<>(result.entrySet())
        resultList.get(0).getKey() == firstKey
        resultList.get(distinctListSize - 1).getKey() == lastKey

        where:
        stringList                                            | firstKey | lastKey | distinctListSize
        ["one", "two", "two", "three", "four", "five", "six"] | "two"    | "five"  | 6
        ["abc", "abc", "bac", "bac", "cab"]                   | "cab"    | "abc"   | 3


    }

    def "getPercentage returns valid percentage."() {
        expect:
        percentage == FileRecordUtils.getPercentage(total, subTotal)

        where:
        total | subTotal | percentage
        2     | 1        | 50
        3     | 1        | 33.33
        3     | 2        | 66.67
        50    | 1        | 2
    }

}
