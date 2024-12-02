package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	file, err := os.Open("input.txt")
	if err != nil {
		fmt.Printf("Error opening file: %v\n", err)
		return
	}
	defer file.Close()

	var data [][]int

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		stringValues := strings.Fields(line)
		var intValues []int
		for _, str := range stringValues {
			value, err := strconv.Atoi(str)
			if err != nil {
				fmt.Printf("Error converting '%s' to integer: %v\n", str, err)
				return
			}
			intValues = append(intValues, value)
		}
		data = append(data, intValues)
	}

	if err := scanner.Err(); err != nil {
		fmt.Printf("Error reading file: %v\n", err)
		return
	}

	fmt.Println("Parsed Data:")
	for _, line := range data {
		fmt.Println(line)
	}
}
