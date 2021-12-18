
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    lines = [line.split("|") for line in lines]
    digits = [line[1].split() for line in lines]
    count = 0
    for digitsList in digits:
        for digit in digitsList:
            if len(digit) == 2 or len(digit) == 4 or len(digit) == 3 or len(digit) == 7:
                count += 1
    print(count)

    

main()