import qualified Data.Set as Set
import Data.List (elemIndex)

-- Type aliases for better readability
type Position = (Int, Int)
type Direction = Char

-- Direction mapping (next guard state)
nextGuardState :: Direction -> Direction
nextGuardState '^' = '>'
nextGuardState '>' = 'v'
nextGuardState 'v' = '<'
nextGuardState '<' = '^'
nextGuardState _   = error "Invalid direction"

-- Function to find the initial guard position
findGuard :: [String] -> Maybe Position
findGuard grid = findGuardHelper grid 0
  where
    findGuardHelper [] _ = Nothing
    findGuardHelper (line:rest) i =
      case elemIndex '^' line of
        Just pos -> Just (i, pos)
        Nothing  -> findGuardHelper rest (i + 1)

-- Function to get the next position based on the current position and direction
getNextPosition :: Position -> Direction -> Position
getNextPosition (x, y) '^' = (x - 1, y)
getNextPosition (x, y) '>' = (x, y + 1)
getNextPosition (x, y) 'v' = (x + 1, y)
getNextPosition (x, y) '<' = (x, y - 1)
getNextPosition _ _         = error "Invalid direction"

-- Function to check if a given position has an obstacle or is out of bounds
checkPositionForObstacle :: Position -> [String] -> Either String Bool
checkPositionForObstacle (x, y) grid
    | x < 0 || y < 0 || x >= length grid || y >= length (head grid) = Left "Edge"
    | otherwise = Right (grid !! x !! y == '#')

-- Main function
main :: IO ()
main = do
    -- Read input from file
    content <- readFile "input.txt"
    let grid = lines content

    -- Find initial guard position
    let initialPosition = findGuard grid
    case initialPosition of
        Nothing -> putStrLn "Guard not found"
        Just pos -> do
            let positions = simulateMovement grid pos '^' Set.empty
            putStrLn $ "Part 1: " ++ show (Set.size positions + 1) -- +1 for original position

-- Function to simulate the guard's movement and track visited positions
simulateMovement :: [String] -> Position -> Direction -> Set.Set Position -> Set.Set Position
simulateMovement grid currentPos guardState visited
    | obstacleCheck == Left "Edge" = visited
    | obstacleCheck == Right True  = simulateMovement grid currentPos (nextGuardState guardState) visited
    | otherwise = simulateMovement grid nextPos guardState (Set.insert nextPos visited)
  where
    -- Add the initial position before moving
    nextPos = getNextPosition currentPos guardState
    obstacleCheck = checkPositionForObstacle nextPos grid
