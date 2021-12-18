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
    while len(lines[i]) > 0:
        [x,y] = [int(n) for n in lines[i].split(',')]
        grid[(x,y)] = 1
        i += 1
    i += 1
    first_instruction = lines[i].split()[2].split('=')
    print(first_instruction)
    for (x,y) in grid.keys():
        if first_instruction[0] == 'y':
            if y > int(first_instruction[1]):
                grid[(x,y)] = 0
                grid[(x, 2 * int(first_instruction[1]) - y)] = 1
        else:
            if x > int(first_instruction[1]):
                grid[(x,y)] = 0
                grid[(2 * int(first_instruction[1]) - x,y)] = 1
    s = 0
    for (x,y) in grid.keys():
        s += grid[(x,y)]

    print(s)
main()