from collections import defaultdict


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    i = 0
    grid = defaultdict(lambda: 0)
    max_x = 0
    max_y = 0
    while len(lines[i]) > 0:
        [x,y] = [int(n) for n in lines[i].split(',')]
        grid[(x,y)] = 1
        max_x = max(max_x, x)
        max_y = max(max_y,y)
        i += 1
    i += 1
    while i < len(lines):
        instruction = lines[i].split()[2].split('=')
        print(instruction)
        for (x,y) in grid.keys():
            if instruction[0] == 'y':
                if y > int(instruction[1]):
                    grid[(x,y)] = 0
                    grid[(x, 2 * int(instruction[1]) - y)] = 1
            else:
                if x > int(instruction[1]):
                    grid[(x,y)] = 0
                    grid[(2 * int(instruction[1]) - x,y)] = 1
        i += 1
    max_y = 6
    max_x = 40
    display = [['.' for _ in range(max_x + 1)] for _ in range(max_y + 1)]
    for i in range(max_x + 1):
        for j in range(max_y + 1):
            if grid[(i,j)] == 1:
                display[j][i] = "#"
    print(display)
main()