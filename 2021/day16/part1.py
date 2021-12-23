
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def getVersionSum(b):
    version = int(b[:3], 2)
    type = int(b[3:6], 2)
    if type == 4:
        p = 6
        while b[p] == 1:
            p += 5
        p += 5
        if len(b[p:]) < 4:
            return version
        return getVersionSum(b[p:])
    lengthTypeId = int(b[6], 2)
    if lengthTypeId == 0:
        subPacketLength = int(b[7:22], 2)
        return version + getVersionSum(b[22:22+subPacketLength])
    else:
        numberOfSubPackets = int(b[7,18], 2)
        
    

def main():
    lines = parseInput()
    hex = lines[0]
    print(hex)
    b = bin(int(hex, 16))[2:]
    print(getVersionSum(b, 1))


    
main()