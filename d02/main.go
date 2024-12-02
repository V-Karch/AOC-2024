package main

import (
	"fmt"
	"os"
)

func main() {
	contents, err := os.ReadFile("input.txt")
	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println(contents)
}
