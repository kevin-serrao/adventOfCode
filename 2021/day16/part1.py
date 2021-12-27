
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def getVersionSum(b, p):
    print(p)
    if b[p:] == '0' * len(b[p:]):
        return (0, float('inf'))
    version = int(b[p:p+3], 2)
    type = int(b[p+3:p+6], 2)
    if type == 4:
        newP = p + 6
        while b[newP] == '1':
            newP += 5
        newP += 5
        return (version, newP)
    lengthTypeId = int(b[p+6], 2)
    if lengthTypeId == 0:
        subPacketLength = int(b[p+7:p+22], 2)
        totalVersion = version
        newP = p + 22
        while newP < p + 22 + subPacketLength:

            (newVersion, newP) = getVersionSum(b, newP)
            totalVersion += newVersion
        return (totalVersion, newP)
    else:
        numberOfSubPackets = int(b[p+7:p+18], 2)
        totalVersion = version
        newP = p + 18
        for _ in range(numberOfSubPackets):
            (newVersion, newP) = getVersionSum(b, newP)
            totalVersion += newVersion
        return (totalVersion, newP)
    

def main():
    lines = parseInput()
    hex = lines[0]
    b = bin(int(hex, 16))[2:].zfill(len(hex) * 4)
    print(getVersionSum(b, 0)[0])


    
main()