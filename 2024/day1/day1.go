package main

import (
	"encoding/json"
	"fmt"
	"math"
	"regexp"
	"slices"
	"strconv"

	"kxrtiswithak.com/puzzleinputparser"
)

func makeIntSlicesFromLines(lines []string) ([]int, []int) {
	var numbers []string

	leftNumbers := make([]int, len(lines))
	rightNumbers := make([]int, len(lines))
	r := regexp.MustCompile(`\d+`)

	for i := 0; i < len(lines); i++ {
		numbers = r.FindAllString(lines[i], 2)
		leftNumbers[i], _ = strconv.Atoi(numbers[0])
		rightNumbers[i], _ = strconv.Atoi(numbers[1])

	}

	return leftNumbers, rightNumbers
}

func makeStringFromNumbersSlice(numbers []int) string {
	numbersBytes, _ := json.Marshal(numbers)

	return string(numbersBytes)
}

func sortIntSlice(numbers []int) []int {
	slices.Sort(numbers)

	return numbers
}

func calcSumDifference(leftNumbers []int, rightNumbers []int) int {
	sum := 0.0

	for i := 0; i < len(leftNumbers); i++ {
		sum += math.Abs(float64(leftNumbers[i] - rightNumbers[i]))
	}

	return int(sum)
}

func calcSimilarityScore(leftNumbers []int, rightNumbers []int) int {
	rightNumbersString := makeStringFromNumbersSlice(rightNumbers)
	similarityScore := 0

	for i := 0; i < len(leftNumbers); i++ {
		r := regexp.MustCompile(strconv.Itoa(leftNumbers[i]))
		count := len(r.FindAllString(rightNumbersString, -1))
		similarityScore += leftNumbers[i] * count
	}

	return similarityScore
}

func partOne(fileName string) int {
	lines := puzzleinputparser.MakeLinesFromFile(fileName)
	leftNumbers, rightNumbers := makeIntSlicesFromLines(lines)
	leftNumbers = sortIntSlice(leftNumbers)
	rightNumbers = sortIntSlice(rightNumbers)
	diffSum := calcSumDifference(leftNumbers, rightNumbers)

	return diffSum
}

func partTwo(fileName string) int {
	lines := puzzleinputparser.MakeLinesFromFile(fileName)
	leftNumbers, rightNumbers := makeIntSlicesFromLines(lines)
	similarityScore := calcSimilarityScore(leftNumbers, rightNumbers)

	return similarityScore
}

func main() {
	fmt.Println("Sample input 1:", partOne("puzzleinputs/sample_input_1.txt"))
	fmt.Println("Puzzle input 1:", partOne("puzzleinputs/puzzle_input_1.txt"))
	fmt.Println("Puzzle input 2:", partTwo("puzzleinputs/puzzle_input_1.txt"))
}
