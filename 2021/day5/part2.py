from collections import defaultdict


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    lines = [ line.split(' -> ') for line in lines]
    newLines = []

    for line in lines:
        newLines.append([coord.split(',') for coord in line])

    chart = defaultdict(lambda: 0)
    count = 0
    for coordPair in newLines:
        # print(coordPair)
        x1 = int(coordPair[0][0])
        y1 = int(coordPair[0][1])
        x2 = int(coordPair[1][0])
        y2 = int(coordPair[1][1])

        if abs(y2 - y1) == abs(x2 - x1) and x1 != x2:
            # print(x1,y1,x2,y2)
            if y2 > y1:
                if x2 > x1:
                    for d in range(y2 - y1 + 1):
                        x = x1 + d
                        y = y1 + d
                        print(x,y)
                        chart[(x, y)] += 1
                        if chart[(x, y)] == 2:
                            print(x, y, 'hit')
                            count += 1
                else:
                    for d in range(y2 - y1 + 1):
                        x = x1 - d
                        y = y1 + d
                        chart[(x, y)] += 1
                        if chart[(x, y)] == 2:
                            print(x, y, 'hit')
                            count += 1
            else:
                if x2 > x1:
                    for d in range(y1 - y2 + 1):
                        x = x1 + d
                        y = y1 - d
                        chart[(x, y)] += 1
                        if chart[(x, y)] == 2:
                            print(x, y, 'hit')
                            count += 1
                else:
                    for d in range(y1 - y2 + 1):
                        x = x1 - d
                        y = y1 - d
                        print(x,y)
                        chart[(x, y)] += 1
                        if chart[(x, y)] == 2:
                            print(x, y, 'hit')
                            count += 1
        if x2 == x1:
            x = x1
            if y1 < y2:
                for y in range(y1, y2 + 1):
                    chart[(x, y)] += 1
                    if chart[(x, y)] == 2:
                        print(x,y,'hit')
                        count += 1
            else:
                for y in range(y2, y1 + 1):
                    chart[(x, y)] += 1
                    if chart[(x, y)] == 2:
                        print(x,y,'hit')
                        count += 1
        if y1 == y2:
            y = y1
            if x1 < x2:
                for x in range(x1, x2 + 1):
                    chart[(x, y)] += 1
                    if chart[(x, y)] == 2:
                        print(x,y,'hit')
                        count += 1
            else:
                for x in range(x2, x1 + 1):
                    chart[(x, y)] += 1
                    if chart[(x, y)] == 2:
                        print(x,y,'hit')
                        count += 1
    # print(chart)
    print(count)
    

main()