from functools import reduce
from collections import defaultdict

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def isLowPoint(grid, x, y):
    for [i,j] in [[0,1], [1,0], [-1,0], [0,-1]]:
        if x + i > -1 and x + i < len(grid) and y + j > -1 and y + j < len(grid[x+i]):
            if grid[x][y] >= grid[x+i][y+j]:
                return False
    return True

def getLowPoint(grid, x, y):
    if isLowPoint(grid, x, y):
        return (x,y)
    for [i,j] in [[0,1], [1,0], [-1,0], [0,-1]]:
        if x + i > -1 and x + i < len(grid) and y + j > -1 and y + j < len(grid[x+i]) and grid[x + i][y + j] < grid[x][y]:
            # print(str((x, y)) + " flows into " + str((x+i, y+j)))
            return getLowPoint(grid, x + i, y + j)


def main():
    lines = parseInput()
    grid = [[int(c) for c in line] for line in lines]
    basinSizeByLowPointCoord = defaultdict(lambda: 0)
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if (grid[i][j] != 9):
                lowPointForThisPoint = getLowPoint(grid,i,j)
                # print(str((i, j)) + " flows into " + str((lowPointForThisPoint[0], lowPointForThisPoint[1])))
                basinSizeByLowPointCoord[lowPointForThisPoint] += 1
    topThree = [0,0,0]
    for v in basinSizeByLowPointCoord.values():
        topThree[topThree.index(min(topThree))] = max(v, min(topThree))
    print(basinSizeByLowPointCoord)
    print(reduce(lambda x,y: x*y, topThree))

main()