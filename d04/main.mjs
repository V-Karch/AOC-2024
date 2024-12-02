import fs from "fs";

function main() {
    let contents = getFileContentsAsLines();
    console.log(contents);
}

function getFileContentsAsLines() {
    try {
        const fileContents = fs.readFileSync("input.txt", 'utf8');
        const lines = fileContents.split(/\r?\n/); 
        return lines;
    } catch (error) {
        console.error(`Error reading file: ${error.message}`);
        return [];
    }
}

main();