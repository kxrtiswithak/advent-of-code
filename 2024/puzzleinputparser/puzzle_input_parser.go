package puzzleinputparser

import (
	"bufio"
	"log"
	"os"
)

func MakeLinesFromFile(fileName string) []string {
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

func MakeOneLineFromFile(fileName string) string {
	var line []byte

	line, err := os.ReadFile(fileName)
	if err != nil {
		log.Fatal(err)
	}

	return string(line)
}
