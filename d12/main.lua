local file = io.open("input.txt", "r")

if not file then
    print("Could not open file " .. file_name)
    return
end

local lines = {}

for line in file:lines() do
    table.insert(lines, line)
end

file:close()

for _, line in ipairs(lines) do
    print(line)
end
