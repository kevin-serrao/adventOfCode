
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def combine(values, type):
    if type == 0:
        return sum(values)
    elif type == 1:
        prod = 1
        for val in values:
            prod *= val
        return prod
    elif type == 2:
        return min(values)
    elif type == 3:
        return max(values)
    elif type == 5:
        return values[0] > values[1]
    elif type == 6:
        return values[0] < values[1]
    elif type == 7:
        return values[0] == values[1]

def getValue(b, p):
    if b[p:] == '0' * len(b[p:]):
        return (0, float('inf'))
    type = int(b[p+3:p+6], 2)
    value = 0
    if type == 4:
        newP = p + 6
        while b[newP] == '1':
            value *= (2**4)
            print(int(b[newP + 1:newP + 5], 2))
            value += int(b[newP + 1:newP + 5], 2)
            newP += 5
        value *= (2**4)
        value += int(b[newP + 1:newP + 5], 2)
        newP += 5
        return (value, newP)
    lengthTypeId = int(b[p+6], 2)
    if lengthTypeId == 0:
        subPacketLength = int(b[p+7:p+22], 2)
        newP = p + 22
        values = []
        while newP < p + 22 + subPacketLength:
            (newValue, newP) = getValue(b, newP)
            values.append(newValue)
        finalValue = combine(values, type)
        return (finalValue, newP)
    else:
        numberOfSubPackets = int(b[p+7:p+18], 2)
        values = []
        newP = p + 18
        for _ in range(numberOfSubPackets):
            (newValue, newP) = getValue(b, newP)
            values.append(newValue)
        finalValue = combine(values, type)
        return (finalValue, newP)
    

def main():
    lines = parseInput()
    hex = lines[0]
    b = bin(int(hex, 16))[2:].zfill(len(hex) * 4)
    print(getValue(b, 0)[0])


    
main()