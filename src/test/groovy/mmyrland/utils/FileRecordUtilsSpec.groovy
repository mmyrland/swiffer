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

    @Unroll
    def "listContains returns #result when #scenario"() {
        expect:
        result == FileRecordUtils.listContains(stringList, substring)

        where:
        scenario             | stringList                       | substring                              || result
        "substring is null"  | ["well now...", "LOOK OUT!!"]    | null                                   || false
        "list contains null" | ["I'm not null, he is ->", null] | "I'm not null."                        || false
        "list is null"       | null                             | "Well, what am I supposed to do then?" || false
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
        "list is empty"          | []               || 0d
        "random list of numbers" | [-1d, 51d, 250d] || 300d
    }

    @Unroll
    def "buildSortStringSet correctly sorts string values."() {
        expect:
        Map<String, Long> result = FileRecordUtils.buildSortStringSet(stringList)
        result.size() == distinctListSize
        result.get("two") == 2l

        where:
        stringList                                            | distinctListSize | line1count
        ["one", "two", "two", "three", "four", "five", "six"] | 6                | 2l


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
