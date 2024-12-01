fn main() {
    let contents = std::fs::read_to_string("input.txt")
        .expect("Failed to read input file");

    let part1_result = part1(&contents);
}

fn part1(contents: &String) -> i32 {
    let left_list = collect_left_list(contents);
    let right_list = collect_right_list(contents);
    return 0; // Placeholder
}

fn collect_left_list(contents: &String) -> Vec<i32> {
    return contents
        .lines()
        .filter_map(|line| line.get(..5))
        .filter_map(|s| s.parse::<i32>().ok())
        .collect();
}

fn collect_right_list(contents: &String) -> Vec<i32> {
    return contents
        .lines()
        .filter_map(|line| line.get(8..))
        .filter_map(|s| s.parse::<i32>().ok())
        .collect();
}