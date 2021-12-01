def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def main():
    lines = parseInput()
    numbers = [int(line) for line in lines]
    ascenscions = [numbers[i] > numbers[i-1] for i in range(1, len(numbers))]
    print(ascenscions)
    print(sum(ascenscions))


main()