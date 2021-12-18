
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

# scoreForCorruptedCharacter = {')': 3, ']': 57, '}': 1197, '>': 25137, '*': 0}

# def getCorruptedCharacter(line):
#     stack = []
#     for c in line:
#         if c in ['(','{','[', '<']:
#             stack.append(c)
#         else:
#             l = stack.pop()
#             if c == ')':
#                 if l != '(':
#                     return c
#             elif c == ']':
#                 if l != '[':
#                     return c
#             elif c == '}':
#                 if l != '{':
#                     return c
#             elif c == '>':
#                 if l != '<':
#                     return c
#     return '*'

scoreForCompletedCharacter = {'(': 1, '[': 2, '{': 3, '<': 4}

def getScoreFromStack(stack):
    score = 0
    x = stack.pop()
    while len(stack) > 0 and x in scoreForCompletedCharacter.keys():
        score *= 5
        score += scoreForCompletedCharacter[x]
        x = stack.pop()
    if len(stack) == 0:
        return score * 5 + scoreForCompletedCharacter[x]
    return score 

def getScoreForLine(line):
    stack = []
    for c in line:
        if c in ['(','{','[', '<']:
            stack.append(c)
        else:
            l = stack.pop()
            if c == ')':
                if l != '(':
                    return 0
            elif c == ']':
                if l != '[':
                    return 0
            elif c == '}':
                if l != '{':
                    return 0
            elif c == '>':
                if l != '<':
                    return 0
    return getScoreFromStack(stack)

def main():
    lines = parseInput()
    scores = []
    for line in lines:
        scoreForLine =getScoreForLine(line)
        if scoreForLine > 0:
            scores.append(scoreForLine) 
    print(sorted(scores)[len(scores) // 2])

main()