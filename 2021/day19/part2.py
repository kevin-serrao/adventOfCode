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


def getTransform(index,coord):
    (x,y,z) = coord
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
    return transformations[index]


def getOrientations(beacons):
    ret = [([]) for _ in range(24)]
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
    scanner_coords = [(0,0,0) for _ in range(len(scanners))]
    answers = scanners[0]
    answered = set([0])
    while len(answered) < len(scanners):
        for i in range(len(scanners)):
            if i not in answered:
                print(str(i) + ' not in answered')
        normalizationsA = getNormalizationsOfBeacons(answers)
        for j in range(len(normalizations)):
            if j in answered:
                continue
            normalizationsB = normalizations[j]
            for normalizationB in normalizationsB:
                orientationsB = getOrientations(normalizationB)
                for o in range(len(orientationsB)):
                    orientationB = orientationsB[o]
                    for orientationA in normalizationsA:
                        if j in answered:
                            break
                        countInCommon = countOverlap(orientationA, orientationB)
                        if countInCommon >= 12:
                            answerOriginIndex = 0
                            for k in range(len(orientationA)):
                                if orientationA[k] == (0,0,0):
                                    answerOriginIndex = k
                            for i in range(len(orientationB)):
                                if orientationB[i] not in orientationA:
                                        answers.append(
                                            (
                                                answers[answerOriginIndex][0] + orientationB[i][0],
                                                answers[answerOriginIndex][1] + orientationB[i][1],
                                                answers[answerOriginIndex][2] + orientationB[i][2]
                                                )
                                        )
                                if orientationB[i] == (0,0,0):
                                    scanner_coords[j] = (
                                        -getTransform(o,scanners[j][i])[0] + answers[answerOriginIndex][0],
                                        -getTransform(o,scanners[j][i])[1] + answers[answerOriginIndex][1],
                                        -getTransform(o,scanners[j][i])[2] + answers[answerOriginIndex][2])
                            normalizationsA = getNormalizationsOfBeacons(answers)
                            answered.add(j)
                            print(j)
    max_manhattan = 0
    for a in scanner_coords:
        for b in scanner_coords:
            if a != b:
                max_manhattan = max(max_manhattan, abs(a[0] - b[0]) + abs(a[1] - b[1]) + abs(a[2] - b[2]))
    print(max_manhattan)
    



    
main()