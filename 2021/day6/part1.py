from collections import defaultdict


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    ages = lines[0].split(',')
    timer = [0 for i in range(9)]
    for age in ages:
        timer[int(age)] += 1
    for _ in range(80):
        newTimer = [timer[i + 1] for i in range(len(timer) - 1)] + [0]
        newTimer[8] = timer[0]
        newTimer[6] = timer[0] + timer[7]
        timer = newTimer
    print(sum(timer))

    

main()