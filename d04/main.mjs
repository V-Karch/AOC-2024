import fs from "fs";

function main() {
    const grid = getFileContentsAsGrid();
    const horizontals = extractHorizontalLines(grid);
    const verticals = extractVerticalLines(grid);
    const diagonals = extractDiagonalLines(grid);

    console.log("Horizontal lines:", horizontals);
    console.log("Vertical lines:", verticals);
    console.log("Diagonal lines:", diagonals);
}

function getFileContentsAsGrid() {
    try {
        const fileContents = fs.readFileSync("input.txt", "utf8");
        const lines = fileContents.split(/\r?\n/).filter(line => line.trim() !== ""); // Filter out empty lines
        return lines.map(line => line.split("")); // Split each line into characters
    } catch (error) {
        console.error(`Error reading file: ${error.message}`);
        return [];
    }
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

main();
