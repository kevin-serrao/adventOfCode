def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def toDecimal(binary):
    ans = 0
    for i in range(len(binary)):
        ans += binary[i] * 2 ** (len(binary) - 1 - i)
    return ans

def main():
    lines = parseInput()
    for i in range(len(lines)):
        lines[i] = [int(c) for c in lines[i]]
    oxygen = lines
    carbon = lines
    i = 0
    while len(oxygen) > 1 and i < len(lines[0]):
        new_oxygen = []
        ones = sum(p[i] for p in oxygen)
        if ones >= (float(len(oxygen)) / 2):
            for p in oxygen:
                if p[i] == 1:
                    new_oxygen.append(p)
        else:
            for p in oxygen:
                if p[i] == 0:
                    new_oxygen.append(p)
        oxygen = new_oxygen
        i += 1
    i = 0
    while len(carbon) > 1 and i < len(lines[0]):
        new_carbon = []
        ones = sum(p[i] for p in carbon)
        print(ones, float(len(carbon)) / 2)
        if ones >= (float(len(carbon)) / 2):
            for p in carbon:
                if p[i] == 0:
                    new_carbon.append(p)
        else:
            for p in carbon:
                if p[i] == 1:
                    new_carbon.append(p)
        carbon = new_carbon
        i += 1
    print(toDecimal(oxygen[0]) * toDecimal(carbon[0]))


    
main()