from collections import defaultdict
from collections import Counter


def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

memo = defaultdict(lambda: Counter())

def countLetters(s, iterations, rules):
    if ((s, iterations) in memo):
        return memo[(s, iterations)]
    if iterations == 0:
        return Counter(s[:-1])
    c = Counter()
    for i in range(len(s) - 1):
        if s[i:i+2] in rules:
            c += countLetters(s[i] + rules[s[i:i+2]] + s[i+1], iterations - 1, rules)

    memo[(s, iterations)] = c
    return c


def main():
    lines = parseInput()
    seed = lines[0]
    rules = defaultdict(lambda: '')
    for i in range(2, len(lines)):
        key = lines[i].split('->')[0].strip()
        val = lines[i].split('->')[1].strip()
        rules[key] = val

    count = countLetters(seed, 40, rules) + Counter(seed[-1])
    print(count)
    
main()