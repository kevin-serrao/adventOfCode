from collections import defaultdict
from collections import Counter


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    seed = lines[0]
    rules = defaultdict(lambda: '')
    for i in range(2, len(lines)):
        key = lines[i].split('->')[0].strip()
        val = lines[i].split('->')[1].strip()
        rules[key] = val
    
    for i in range(10):
        new_seed = ''
        for j in range(len(seed)):
            if j == len(seed) - 1:
                new_seed += seed[j]
            elif seed[j:j+2] in rules:
                new_seed += seed[j] + rules[seed[j:j+2]]
            else:
                new_seed += seed[j]
        seed = new_seed

    count = Counter(seed)
    print(count)
    
main()