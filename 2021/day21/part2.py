from collections import defaultdict

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines



dp = defaultdict(lambda: -1)

def recordWins(pos, scores, playerIndex):
    if dp[(pos,scores,playerIndex)] != -1:
        return dp[(pos,scores,playerIndex)]
    if scores[0] >= 21:
        dp[(pos,scores,playerIndex)] = [1,0]
        return dp[(pos,scores,playerIndex)]
    elif scores[1] >= 21:
        dp[(pos,scores,playerIndex)] = [0,1]
        return dp[(pos,scores,playerIndex)]
    
    wins = [0,0]
    for i in [1,2,3]:
        for j in [1,2,3]:
            for k in [1,2,3]:
                newPos = (pos[playerIndex] + i + j + k) % 10
                if newPos == 0:
                    newPos = 10
                newWins = []
                if playerIndex == 0:
                    newWins = recordWins((newPos, pos[1]), (scores[0] + newPos, scores[1]), 1)
                else:
                    newWins = recordWins((pos[0], newPos), (scores[0], scores[1] + newPos), 0)
                wins[0] += newWins[0]
                wins[1] += newWins[1]
    dp[(pos,scores,playerIndex)] = wins
    return wins

def main():
    pos = (2,7)
    scores = (0,0)
    turn = 0
    wins = recordWins(pos, scores, turn)
    print(max(wins))

        


    
    



    
main()