
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


def main():
    lines = parseInput()
    grid = [[int(c) for c in line] for line in lines]
    sumRiskLevel = 0
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if isLowPoint(grid, i, j):
                print(i,j)
                sumRiskLevel += grid[i][j] + 1
    print(sumRiskLevel)

main()