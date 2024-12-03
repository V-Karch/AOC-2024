def part1(contents)
    total_sum = 0
    valid_mul_regex = /mul\((\d{1,3}),(\d{1,3})\)/ # REGEX PAIN OWIE
    contents.each do |line|
        matches = line.scan(valid_mul_regex)
        total_sum += matches.sum { |x, y| x.to_i * y.to_i }
    end

    return total_sum
end

def part2(contents)
    enable_regex = /do\(\)/
    disable_regex = /don't\(\)/
    mul_regex = /mul\((\d+),(\d+)\)/
    is_enabled = true
    fragments = contents.scan(/do\(\)|don't\(\)|mul\(\d+,\d+\)/)
    # thank you https://regex101.com/ ;-;
    sum = 0

    fragments.each do |fragment|
        case fragment
        when enable_regex
            is_enabled = true
        when disable_regex
            is_enabled = false
        when mul_regex
            if is_enabled
            x, y = fragment.match(mul_regex).captures.map(&:to_i)
            sum += x * y
        end
    end
end
    return sum
end


def main
    begin
        lines = File.read("input.txt")

        part1_result = part1(lines.split("\n"))
        puts "Part 1: #{part1_result}"

        part2_result = part2(lines)
        puts "Part 2: #{part2_result}"

    rescue Errno::ENOENT
        puts "Couldn't read the input, womp womp"
    rescue => e
        puts "An error occurred: #{e.message}"
    end
end

main
