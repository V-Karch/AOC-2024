use std::collections::HashMap;

fn main() {
    let contents = std::fs::read_to_string("input.txt")
        .expect("Failed to read input file");

    let part1_result: i32 = part1(&contents);
    println!("Part 1: {}", part1_result);

    let part2_result = part2(&contents);
    println!("Part 2: {}", part2_result);
}

fn part2(contents: &String) -> i32 {
    let left_list = collect_left_list(contents);
    let right_list = collect_right_list(contents);

    let mut count_map: HashMap<i32, i32> = HashMap::new();

    for i in right_list {
        if !count_map.contains_key(&i) {
            count_map.insert(i, 1);
        } else {
            let current_count = count_map
                .get(&i)
                .expect("Failed to get value from map");

            count_map.insert(i, current_count + 1);
        }
    }

    let mut sum = 0;
    for i in left_list {
        let attempt_get = count_map.get(&i);
        
        let times_appeared = match attempt_get {
            Some(value) => value,
            None => &0,
        };
        
        sum += i * times_appeared;
    }

    return sum;
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