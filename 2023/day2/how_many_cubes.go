package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"regexp"
	"strconv"
	"strings"
)

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

func makeMapFromLines(lines []string) map[int]map[string][]int {
	var gameId int
	var gameIdIndex []int
	var sets []string
	var subsets [][]string
	var gameMap map[int]map[string][]int

	gameIdRegex := regexp.MustCompile(`\d+`)

	for i := 0; i < len(lines); i++ {
		gameId, _ = strconv.Atoi(gameIdRegex.FindString(lines[i]))
		gameIdIndex = gameIdRegex.FindStringIndex(lines[i])
		sets = strings.Split(lines[i][gameIdIndex[1]+1:], ";")

		for j := 0; j < len(sets); j++ {
			if j == 0 {
				subsets = [][]string{}
			}

			subsets = append(subsets, strings.Split(sets[j], ","))
		}

		gameMap[gameId], ok := makeColourMap(subsets)
	}

	return gameMap
}

func makeColourMap(subsets [][]string) map[string][]int {
	var colour string
	var noOfCubes int
	colourMap := map[string][]int{
		"red":   {},
		"green": {},
		"blue":  {},
	}

	colourRegex := regexp.MustCompile("[a-z]+")
	numberRegex := regexp.MustCompile("[0-9]+")

	for i := 0; i < len(subsets); i++ {
		for j := 0; j < len(subsets[i]); j++ {
			colour = colourRegex.FindString(subsets[i][j])
			noOfCubes, _ = strconv.Atoi(numberRegex.FindString(subsets[i][j]))

			// fmt.Println("i:", i, "\nj:", j, "\ncolour:", colour, "\ncubes:", noOfCubes, "\n\n")

			colourMap[colour] = append(colourMap[colour], noOfCubes)
		}
	}
	fmt.Println(colourMap)
	return colourMap
}

func makeMapFromFile(fileName string) map[int]map[string][]int {
	lines := makeLinesFromFile(fileName)
	gameMap := makeMapFromLines(lines)

	return gameMap
}

func main() {
	fmt.Println(makeMapFromFile("sample_input.txt"))
}

// line to map - key is game id, value is nested map (key is colour, value is slice of amounts)

// iterate against it checking value is not exceeded
// if true then add game id (key) to sum

// input to slice of strings

// refactor already made method into separate module and other reusable methods
// go mod init from aoc root and build where necessary
