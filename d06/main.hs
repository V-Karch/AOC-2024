import System.IO

main :: IO ()
main = do
    contents <- readFile "input.txt"  -- Read the contents of the file
    let linesArray = lines contents   -- Split the contents into lines
    print linesArray                  -- Print the array (list) of lines
