from collections import defaultdict
from collections import Counter

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
    if nodeName == 'end':
        # print(path)
        return 1
    ret = 0
    for c in graph[nodeName].connected:
        if c == 'start':
            continue
        else:
            if not c.islower():
                ret += numPathsToEnd(graph, c, [n for n in path] + [c])
            else:
                counter = Counter(path)
                if any([k.islower() and counter[k] > 1 for k in counter.keys()]) and counter[c] > 0:
                    continue
                else:
                    ret += numPathsToEnd(graph, c, [n for n in path] + [c])

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

    print(numPathsToEnd(graph, 'start', []))



main()