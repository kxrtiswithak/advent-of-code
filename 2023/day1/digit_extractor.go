package main

import (
    "fmt"
    "strconv"
    "os"
    "log"
    "bufio"
    "strings"
)

func extractDigits(line string) int {
    var digits []int

    for i := 0; i < len(line); i++ {
        if  s, err := strconv.Atoi(string(line[i])); err == nil {
            digits = append(digits, s)
        }
    }

    return digits[0]*10 + digits[len(digits)-1]
}

// the first number that occurs in the line must be replaced first
// if the line contains any of the numbers then replace the first instance
// iterate through the line one character at a time comparing against slice
// check against min 3 characters, max 5
// run specific string replace for match, set i after replaced number and proceed
// The right calibration values for string "eighthree" is 83 and for "sevenine" is 79.
// The examples do not cover such cases.
// credit: https://www.reddit.com/r/adventofcode/comments/1884fpl/2023_day_1for_those_who_stuck_on_part_2/

func makeWordsAsNumbersOnly(line string) string {
    numbersMap := map[string]string {
        "one": "o1e",
        "two": "t2o",
        "three": "t3e",
        "four": "f4r",
        "five": "f5e",
        "six": "s6x",
        "seven": "s7n",
        "eight": "e8t",
        "nine": "n9e",
    }
    var numberKey string

    if len(line) > 2 {
        for i := 0; i < len(line); i++ {
            for j := i + 2; j <= len(line); j++ {
                numberKey = line[i:j]
                if len(numberKey) > 5 {
                    break
                }

                if numberVal, exists := numbersMap[numberKey]; exists {
                    line = strings.Replace(line, numberKey, numberVal, 1)
                }
            }
        }
    }
    // fmt.Println(line)
    return line
}

func makeLinesFromFile(fileName string) []string {
    var lines []string

    file, err := os.Open(fileName)
    if err != nil {
        log.Fatal(err)
    }
    defer file.Close()

    scanner := bufio.NewScanner(file)

    for scanner.Scan() {
        lines = append(lines, scanner.Text())
    }

    if err := scanner.Err(); err != nil {
        log.Fatal(err)
    }

    return lines
}

func calcSumOfExtractedDigits(fileName string, isPartTwo bool) int {
    sum := 0
    lines := makeLinesFromFile(fileName)

    for i := 0; i < len(lines); i++ {
        if isPartTwo {
            sum += extractDigits(makeWordsAsNumbersOnly(lines[i]))
        } else {
            sum += extractDigits(lines[i])
        }
    }

    return sum
}


func main() {
    fmt.Println("Part One:", calcSumOfExtractedDigits("test_input.txt", false))
    fmt.Println("Part Two:", calcSumOfExtractedDigits("test_input.txt", true))
}
