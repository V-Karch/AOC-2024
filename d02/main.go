package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	fileContents, err := readFile("input.txt")
	if err != nil {
		fmt.Printf("Error reading file: %v\n", err)
		return
	}

	result1 := part1(fileContents)
	fmt.Printf("Part 1: %d\n", result1)

	fmt.Println("...")
}

func part1(input string) int {
	lines := strings.Split(strings.TrimSpace(input), "\n")
	var data [][]int
	for _, line := range lines {
		stringValues := strings.Fields(line)
		var intValues []int
		for _, str := range stringValues {
			value, err := strconv.Atoi(str)
			if err != nil {
				fmt.Printf("Error converting '%s' to integer: %v\n", str, err)
				return 0
			}
			intValues = append(intValues, value)
		}
		data = append(data, intValues)
	}

	safeCount := 0
	for _, report := range data {
		if isSafe(report) {
			safeCount++
		}
	}
	return safeCount
}

func isSafe(report []int) bool {
	if len(report) < 2 {
		return true
	}

	isIncreasing := report[1] > report[0]
	isDecreasing := report[1] < report[0]

	for i := 1; i < len(report); i++ {
		diff := report[i] - report[i-1]

		if diff < -3 || diff > 3 || diff == 0 {
			return false
		}

		if diff > 0 && !isIncreasing {
			return false
		}
		if diff < 0 && !isDecreasing {
			return false
		}
	}

	return true
}

func readFile(filename string) (string, error) {
	file, err := os.Open(filename)
	if err != nil {
		return "", err
	}
	defer file.Close()

	var sb strings.Builder
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		sb.WriteString(scanner.Text() + "\n")
	}

	if err := scanner.Err(); err != nil {
		return "", err
	}
	return sb.String(), nil
}
