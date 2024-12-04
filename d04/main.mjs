import fs from "fs";

function main() {
    const grid = getFileContentsAsGrid();

    const horizontals = extractHorizontalLines(grid);
    const verticals = extractVerticalLines(grid);
    const diagonals = extractDiagonalLines(grid);

    const allLines = [...horizontals, ...verticals, ...diagonals];

    const xmasCount = countOccurrences(allLines, "XMAS");
    const samxCount = countOccurrences(allLines, "SAMX");

    console.log(xmasCount + samxCount);
}

function getFileContentsAsGrid() {
    try {
        const fileContents = fs.readFileSync("input.txt", "utf8");
        // Split into lines and then split each line into characters
        return fileContents
            .split(/\r?\n/)
            .filter(line => line.trim() !== "")
            .map(line => [...line]); // Convert each line into an array of characters
    } catch (error) {
        console.error(`Error reading file: ${error.message}`);
        return [];
    }
}

function extractHorizontalLines(grid) {
    return grid.map(row => row.join("")); // Join characters in each row to form strings
}

function extractVerticalLines(grid) {
    if (grid.length === 0) return [];
    const cols = grid[0].length;
    const verticals = [];
    for (let col = 0; col < cols; col++) {
        let verticalLine = "";
        for (let row = 0; row < grid.length; row++) {
            verticalLine += grid[row][col];
        }
        verticals.push(verticalLine);
    }
    return verticals;
}

function extractDiagonalLines(grid) {
    const diagonals = [];
    const rows = grid.length;
    const cols = grid[0]?.length || 0;

    // Extract diagonals from top-left to bottom-right
    for (let d = 1 - rows; d < cols; d++) {
        let diagonal = "";
        for (let row = 0; row < rows; row++) {
            const col = row + d;
            if (col >= 0 && col < cols) {
                diagonal += grid[row][col];
            }
        }
        if (diagonal) diagonals.push(diagonal);
    }

    // Extract diagonals from top-right to bottom-left
    for (let d = 0; d < rows + cols - 1; d++) {
        let diagonal = "";
        for (let row = 0; row < rows; row++) {
            const col = d - row;
            if (col >= 0 && col < cols) {
                diagonal += grid[row][col];
            }
        }
        if (diagonal) diagonals.push(diagonal);
    }

    return diagonals;
}

function countOccurrences(lines, searchStr) {
    let count = 0;
    const searchRegex = new RegExp(searchStr, "g"); // Create a regex for global search
    for (const line of lines) {
        const matches = line.match(searchRegex); // Match all occurrences in the line
        if (matches) {
            count += matches.length; // Add the number of matches to the count
        }
    }
    return count;
}

main();
