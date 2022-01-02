

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def main():
    lines = parseInput()
    alg = ['1' if x == "#" else "0" for x in lines[0]]
    
    grid = lines[2:]
    newGrid = []
    for row in grid:
        newGrid.append(['1' if x == "#" else "0" for x in row])
    grid = newGrid
    print(grid)
    
    
    for k in range(50):
        print(k)
        n = len(grid[0])
        grid = [['0' if k % 2 == 0 else '1' for _ in range(n)]] + grid + [['0' if k % 2 == 0 else '1' for _ in range(n)]]
        m = len(grid)
        for i in range(m):
            char = '0'
            if k % 2 == 1:
                char = '1'
            grid[i] = [char] + grid[i] + [char]   
        newGrid = [['0' for _ in range(len(grid[i]))] for i in range(len(grid))]         
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                code = ''
                for di in [-1, 0, 1]:
                    for dj in [-1, 0, 1]:
                        if i + di < 0 or i + di >= len(grid) or j + dj < 0 or j + dj >= len(grid[i + di]):
                            if k % 2 == 0:
                                code += '0'
                            else:
                                code += '1'
                        else:
                            code += grid[i + di][j + dj]
                index = int(code, 2)
                newGrid[i][j] = alg[index]
        grid = newGrid
    count = 0
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            if grid[i][j] == '1':
                count += 1
    print(count)
        


    
    



    
main()