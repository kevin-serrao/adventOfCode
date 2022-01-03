

def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def main():
    pos = [2,7]
    scores = [0,0]
    turn = 0
    while max(scores) < 1000:
        playerIndex = turn % 2
        dieIndex = ((turn * 3) + 1)
        increase = 0
        for roll in [(dieIndex % 100), ((dieIndex + 1) % 100), ((dieIndex + 2) % 100)]:
            if roll == 0:
                increase += 100
            else: 
                increase += roll
        pos[playerIndex] = (pos[playerIndex] + increase) % 10
        if pos[playerIndex] == 0:
            scores[playerIndex] += 10
        else:
            scores[playerIndex] += pos[playerIndex]
        turn += 1
    print(min(scores) * (turn * 3))

        


    
    



    
main()