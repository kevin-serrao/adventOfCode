from collections import defaultdict


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def flash(grid, hasFlashedThisRound, x, y):
    flashes = 1
    hasFlashedThisRound[(x,y)] = True
    for i in range(-1, 2, 1):
        for j in range(-1, 2, 1):
            if i == 0 and j == 0:
                continue
            if x + i > -1 and x + i < len(grid) and y + j > -1 and y + j < len(grid[x+i]):
                if not hasFlashedThisRound[(x+i, y+j)]:
                    grid[x+i][y+j] += 1
                    if grid[x+i][y+j] > 9:
                        flashes += flash(grid, hasFlashedThisRound, x+i, y+j)
    grid[x][y] = 0
    return flashes


def main():
    lines = parseInput()
    grid = [[int(c) for c in line] for line in lines]
    flashes = 0
    for x in range(100):
        hasFlashedThisRound = defaultdict(lambda: False)
        grid = [[x + 1 for x in line] for line in grid]
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if grid[i][j] > 9: 
                    if not hasFlashedThisRound[(i, j)]:
                        flashes += flash(grid, hasFlashedThisRound, i, j)
    print(flashes)

main()