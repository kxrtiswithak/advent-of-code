package main

import (
	"fmt"
	"math"
	"slices"
	"strconv"
	"strings"

	"kxrtiswithak.com/puzzleinputparser"
)

func makeLevelsFromLines(lines []string) [][]int {
	var levelsStrings []string
	var levels []int
	var level int

	var levelsInts = [][]int{}

	for i := 0; i < len(lines); i++ {
		levelsStrings = strings.Split(lines[i], " ")
		levels = []int{}
		for j := 0; j < len(levelsStrings); j++ {

			level, _ = strconv.Atoi(levelsStrings[j])
			levels = append(levels, level)
		}

		levelsInts = append(levelsInts, levels)
	}
	return levelsInts
}

func countSafeReports(levels [][]int) int {
	var isPos bool
	var prevIsPos bool
	var safe bool
	var diff int
	var absDiff int

	count := 0

	for i := 0; i < len(levels); i++ {
		safe = true

		for j := 0; j < len(levels[i])-1; j++ {
			diff = levels[i][j+1] - levels[i][j]
			absDiff = int(math.Abs(float64(diff)))

			if absDiff > 3 || absDiff < 1 {
				safe = false
				break
			}
			if j == 0 {
				prevIsPos = !math.Signbit(float64(diff))
			} else if j > 0 {
				isPos = !math.Signbit(float64(diff))

				if isPos != prevIsPos {
					safe = false
					break
				} else {
					prevIsPos = isPos
				}
			}
		}

		if safe {
			count++
		}
	}

	return count
}

func problemDampenerCount(levels [][]int) int {
	var lastChance bool
	var isPos bool
	var prevIsPos bool
	var bothSignbits bool
	var unSafeChange bool
	var safe bool
	var diff int
	var absDiff int

	count := 0

	for i := 0; i < len(levels); i++ {
		lastChance = false
		safe = true

		for j := 0; j < len(levels[i])-1; j++ {
			// fmt.Println("j:", j)
			// fmt.Println(levels[i])

			bothSignbits = false
			unSafeChange = false

			diff = levels[i][j+1] - levels[i][j]
			absDiff = int(math.Abs(float64(diff)))

			if absDiff > 3 || absDiff < 1 {
				unSafeChange = true
			}

			if j == 0 {
				prevIsPos = !math.Signbit(float64(diff))
			} else if j > 0 {
				isPos = !math.Signbit(float64(diff))

				if isPos != prevIsPos {
					// fmt.Println("diff: ", diff, "i: ", levels[i][j])
					bothSignbits = true
				} else {
					prevIsPos = isPos
				}
			}

			if bothSignbits || unSafeChange {
				if !lastChance {
					levels[i] = slices.Delete(levels[i], j+1, j+2)
					// fmt.Println("updated levels:", levels[i])
					lastChance = true
					j--
				} else {
					safe = false
					break
				}
			}
		}

		if safe {
			count++
			// fmt.Println("count:", count)
		}
	}

	return count
}

func partOne(fileName string) int {
	lines := puzzleinputparser.MakeLinesFromFile(fileName)
	levelDiffs := makeLevelsFromLines(lines)

	return countSafeReports(levelDiffs)
}

func partTwo(fileName string) int {
	lines := puzzleinputparser.MakeLinesFromFile(fileName)
	levelDiffs := makeLevelsFromLines(lines)

	return problemDampenerCount(levelDiffs)
}

func main() {
	fmt.Println("Test input 1:", partOne("puzzleinputs/sample_input_2.txt"))
	fmt.Println("Puzzle input 1:", partOne("puzzleinputs/puzzle_input_2.txt"))
	fmt.Println("Test input 2:", partTwo("puzzleinputs/sample_input_2.txt"))
	fmt.Println("Puzzle input 2:", partTwo("puzzleinputs/puzzle_input_2.txt"))
}
