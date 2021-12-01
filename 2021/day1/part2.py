def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def main():
    lines = parseInput()
    numbers = [int(line) for line in lines]
    ascenscions = [numbers[i] + numbers[i-1] + numbers[i+1] > numbers[i-2] + numbers[i-1] + numbers[i] for i in range(2, len(numbers) - 1)]
    print(sum(ascenscions))


main()