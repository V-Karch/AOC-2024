begin
    lines = File.read("input.txt").split("\n")
    lines.each do |line|
        puts line
    end
rescue Errno::ENOENT
    puts "Error: File '#{file_name}' not found."
rescue => e
    puts "An error occurred: #{e.message}"
end
