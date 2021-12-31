from copy import deepcopy
from operator import itemgetter

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def sortBeacons(beacons):
    beaconsCopy = deepcopy(beacons)
    return sorted(beaconsCopy, key=itemgetter(0,1,2))

def getNormalizationsOfBeacons(beacons):
    ret = []
    for i in range(len(beacons)):
        ret.append([])
        origin = beacons[i]
        for beacon in beacons:
            ret[i].append((beacon[0] - origin[0], beacon[1] - origin[1], beacon[2] - origin[2]))
    return ret


def getOrientations(beacons):
    ret = [[] for _ in range(24)]
    for beacon in beacons:
        [x,y,z] = beacon
        transformations = [
            (x,y,z),
            (x,-z,y),
            (x,-y,-z),
            (x,z,-y),
            (-x,y,-z),
            (-x,z,y),
            (-x,-y,z),
            (-x,-z,-y),
            (y,x,-z),
            (y,z,x),
            (y,-x,z),
            (y,-z,-x),
            (-y,x,z),
            (-y,-z,x),
            (-y,-x,-z),
            (-y,z,-x),
            (z,y,-x),
            (z,x,y),
            (z,-y,x),
            (z,-x,-y),
            (-z,y,x),
            (-z,-x,y),
            (-z,-y,-x),
            (-z,x,-y),
        ]
        for i in range(len(transformations)):
            ret[i].append(transformations[i])
    return ret


def countOverlap(oA, oB):
    c = 0
    sa = set(oA)
    sb = set(oB)
    for o in sa:
        if o in sb:
            c += 1
    return c

def main():
    lines = parseInput()
    scanners = []
    i = -1
    for line in lines:
        if 'scanner' in line:
            scanners.append([])
            i += 1
        elif len(line) > 0:
            scanners[i].append([int(coord) for coord in line.split(',')])
    normalizations = [0 for _ in range(len(scanners))]
    for i in range(len(scanners)):
        normalizations[i] = getNormalizationsOfBeacons(scanners[i])
    print(len(scanners[0]))
    answers = set(normalizations[0][0])
    answered = set([0])
    while len(answered) < len(scanners):
        print(answers)
        for i in range(len(scanners)):
            if i not in answered:
                print(str(i) + ' not in answered')
        for j in range(len(normalizations)):
            normalizationsA = getNormalizationsOfBeacons(list(answers))
            if j in answered:
                continue
            normalizationsB = normalizations[j]
            for normalizationB in normalizationsB:
                for orientationB in getOrientations(normalizationB):
                    for orientationA in normalizationsA:
                        if j in answered:
                            break
                        countInCommon = countOverlap(orientationA, orientationB)
                        if countInCommon >= 12:
                            answers = set(orientationA + orientationB)
                            answered.add(j)
                            print(j)
    print(answers)
    print(len(answers))
    



    
main()