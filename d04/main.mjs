import fs from "fs";

function main() {
    console.log("Part 1: " + part1());
    console.log("Part 2: " + part2());
}

function part1() {
    const grid = getFileContentsAsGrid();
    const horizontals = extractHorizontalLines(grid);
    const verticals = extractVerticalLines(grid);
    const diagonals = extractDiagonalLines(grid);
    const allLines = [...horizontals, ...verticals, ...diagonals];
    const xmasCount = countOccurrences(allLines, "XMAS");
    const samxCount = countOccurrences(allLines, "SAMX");
    return xmasCount + samxCount;
}

function part2() {
    const grid = getFileContentsAsGrid();
    return findXMASPatterns(grid);
}

function getFileContentsAsGrid() {
    try {
        const fileContents = fs.readFileSync("input.txt", "utf8");
        return fileContents
            .split(/\r?\n/)
            .filter(line => line.trim() !== "")
            .map(line => [...line]);
    } catch (error) {
        console.error(`Error reading file: ${error.message}`);
        return [];
    }
}

function findXMASPatterns(grid) {
    const rows = grid.length;
    const cols = grid[0]?.length || 0;
    let xMASCount = 0;

    // All possible X-MAS patterns
    const patterns = [
        // Original pattern
        [
            ['M', '.', 'S'],
            ['.', 'A', '.'],
            ['M', '.', 'S']
        ],
        // Rotations
        [
            ['S', '.', 'M'],
            ['.', 'A', '.'],
            ['S', '.', 'M']
        ],
        [
            ['M', 'S', '.'],
            ['.', 'A', '.'],
            ['.', 'S', 'M']
        ],
        [
            ['.', 'S', 'M'],
            ['.', 'A', '.'],
            ['M', 'S', '.']
        ]
    ];

    // Check all possible starting positions for each pattern
    for (let startRow = 0; startRow < rows - 2; startRow++) {
        for (let startCol = 0; startCol < cols - 2; startCol++) {
            for (const pattern of patterns) {
                if (matchPattern(grid, startRow, startCol, pattern)) {
                    xMASCount++;
                }
            }
        }
    }

    return xMASCount;
}

function matchPattern(grid, startRow, startCol, pattern) {
    // Check if the pattern fits within the grid
    if (startRow + 2 >= grid.length || startCol + 2 >= grid[0].length) {
        return false;
    }

    // Check each cell in the pattern
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            const patternChar = pattern[i][j];
            const gridChar = grid[startRow + i][startCol + j];

            // Skip '.' in pattern, which can match any character
            if (patternChar === '.') continue;

            // If pattern character doesn't match grid character
            if (patternChar !== gridChar) {
                return false;
            }
        }
    }

    return true;
}

function extractHorizontalLines(grid) {
    return grid.map(row => row.join(""));
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
    const searchRegex = new RegExp(searchStr, "g");
    for (const line of lines) {
        const matches = line.match(searchRegex);
        if (matches) {
            count += matches.length;
        }
    }
    return count;
}

main();