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
    frequencies = [[0,0] for char in lines[0]]
    for line in lines:
        for i in range(len(line)):
            char = line[i]
            if char == '0':
                frequencies[i][0] += 1
            else:
                frequencies[i][1] += 1
    gamma = [0 if f[0] > f[1] else 1 for f in frequencies]
    epsilon = [0 if g == 1 else 1 for g in gamma]
    print(toDecimal(epsilon) * toDecimal(gamma))
    
main()