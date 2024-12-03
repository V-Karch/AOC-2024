def part1(contents)
    total_sum = 0
    valid_mul_regex = /mul\((\d{1,3}),(\d{1,3})\)/ # REGEX PAIN OWIE
    contents.each do |line|
        matches = line.scan(valid_mul_regex)
        total_sum += matches.sum { |x, y| x.to_i * y.to_i }
    end

    return total_sum
end

def main
    begin
        lines = File.read("input.txt").split("\n")

        part1_result = part1(lines)
        puts "Part 1: #{part1_result}"
        
    rescue Errno::ENOENT
        puts "Couldn't read the input, womp womp"
    rescue => e
        puts "An error occurred: #{e.message}"
    end
end

main
