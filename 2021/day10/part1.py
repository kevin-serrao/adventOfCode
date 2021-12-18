
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

scoreForCorruptedCharacter = {')': 3, ']': 57, '}': 1197, '>': 25137, '*': 0}

def getCorruptedCharacter(line):
    stack = []
    for c in line:
        if c in ['(','{','[', '<']:
            stack.append(c)
        else:
            l = stack.pop()
            if c == ')':
                if l != '(':
                    return c
            elif c == ']':
                if l != '[':
                    return c
            elif c == '}':
                if l != '{':
                    return c
            elif c == '>':
                if l != '<':
                    return c
    return '*'

def main():
    lines = parseInput()
    totalScore = 0
    for line in lines:
        totalScore += scoreForCorruptedCharacter[getCorruptedCharacter(line)]
    print(totalScore)

main()