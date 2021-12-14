from collections import defaultdict
import math

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    positions = [int(x) for x in lines[0].split(',')]

    costs = defaultdict(lambda: 0)
    mn = min(positions)
    mx = max(positions)
    for pos in positions:
        for m in range(mn, mx + 1):
            diff = abs(pos - m)
            if diff <= 1:
                costs[m] += diff
            else:
                cost = (diff + 1) * diff / 2
                if (diff < 5):
                    print(diff, cost)
                costs[m] += cost
    ret = float('inf')
    for key in costs.keys():
        ret = min(ret, costs[key])
    print(ret)

    

main()