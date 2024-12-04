package main

import (
	"fmt"
	"regexp"
	"strconv"
	"strings"

	"kxrtiswithak.com/puzzleinputparser"
)

func main() {
	fmt.Println("Test Input 1:", partOne("puzzleinputs/part_one_sample_input_3.txt"))
	fmt.Println("Puzzle Input 1:", partOne("puzzleinputs/puzzle_input_3.txt"))
	fmt.Println("Test Input 2:", partTwo("puzzleinputs/part_two_sample_input_3.txt"))
	fmt.Println("Puzzle Input 2:", partTwo("puzzleinputs/puzzle_input_3.txt"))
}

func partOne(fileName string) int {
	return calcMuls(puzzleinputparser.MakeOneLineFromFile(fileName))

}

func partTwo(fileName string) int {
	reDoDont := regexp.MustCompile(`(?s)don't\(\).*?(?:do\(\)|$)`)
	line := puzzleinputparser.MakeOneLineFromFile(fileName)
	line = reDoDont.ReplaceAllString(line, "")

	return calcMuls(line)
}

func calcMuls(corruptedMemory string) int {
	var numbers []string
	var x, y int

	sum := 0
	reMul := regexp.MustCompile(`mul\(\d+,\d+\)`)
	muls := reMul.FindAllString(corruptedMemory, -1)

	for _, mul := range muls {
		numbers = strings.Split(mul[4:len(mul)-1], ",")
		x, _ = strconv.Atoi(numbers[0])
		y, _ = strconv.Atoi(numbers[1])
		sum += x * y
	}

	return sum
}
