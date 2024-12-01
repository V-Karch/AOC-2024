fn main() {
    let contents = std::fs::read_to_string("input.txt")
        .expect("Failed to read input file");

    let part1_result = part1(&contents);
    println!("Part 1: {}", part1_result);
}

fn part1(contents: &String) -> i32 {
    let mut left_list = collect_left_list(contents);
    let mut right_list = collect_right_list(contents);

    left_list.sort();
    right_list.sort();

    let mut sum = 0;

    for index in 0..left_list.len() {
        sum += (left_list[index] - right_list[index]).abs();
    }

    return sum;
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