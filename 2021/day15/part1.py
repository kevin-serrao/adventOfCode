

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    grid = [[int(x) for x in line] for line in lines]
    min_risks = [[float('inf') for x in line] for line in lines]
    visited = set([(0,0)])
    min_risks[0][0] = 0
    min_risks[0][1] = grid[0][1]
    min_risks[1][0] = grid[1][0]
    to_visit = [(0,1), (1,0)]
    to_visit = sorted(to_visit, key=lambda coord: -min_risks[coord[0]][coord[1]])
    print(to_visit)

    while len(to_visit) > 0:
        next = to_visit.pop()
        print(next)
        visited.add(tuple(next))
        (x,y) = next
        for [i,j] in [[0,1], [1,0], [-1,0], [0,-1]]:
            if x+i > -1 and x+i < len(grid) and y+j > -1 and y+j < len(grid[x+i]) and (x+i,y+j) not in visited:
                min_risks[x+i][y+j] = min(min_risks[x+i][y+j], min_risks[x][y] + grid[x+i][y+j])
                if (x+i,y+j) not in to_visit:
                    to_visit.append((x+i,y+j))
        to_visit = sorted(to_visit, key=lambda coord: -min_risks[coord[0]][coord[1]])
    print(min_risks[len(grid) - 1][len(grid[0]) - 1])
    
main()