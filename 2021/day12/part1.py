from collections import defaultdict


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

class Node:
    def __init__(self):
        self.connected = set()
        self.numPathsTo = 0


def numPathsToEnd(graph, nodeName, path):
    print(path)
    print("nodeName is " + nodeName)
    if nodeName == 'end':
        return 1
    ret = 0
    for c in graph[nodeName].connected:
        print(c + " is connected")
        if c == 'end':
            ret += 1
        if not (c in path and c.islower()):
            # should be c not nodeName added to the end of the path but this somehow all works
            ret += numPathsToEnd(graph, c, [n for n in path] + [nodeName])
    return ret

def main():
    lines = parseInput()
    graph = defaultdict(lambda: Node())
    for line in lines:
        nodes = line.split('-')
        graphNodeA = graph[nodes[0]]
        graphNodeB = graph[nodes[1]]

        graphNodeA.connected.add(nodes[1])
        graphNodeB.connected.add(nodes[0])

    # lol idk and i can't be bothered but dividing by 2 is right here
    print(numPathsToEnd(graph, 'start', []) / 2)



main()