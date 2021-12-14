def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def isFinishedBoard(board):
    if any(sum(r) == -5 for r in board):
        return True
    for i in range(5):
        s = 0
        for row in board:
            s += row[i]
        if s == -5:
            return True
    return False

def removeNumberFromBoard(board, number):
    for i in range(5):
        for j in range(5):
            if board[i][j] == number:
                board[i][j] = -1
    
def scoreBoard(board):
    score = 0
    for i in range(5):
        for j in range(5):
            if board[i][j] != -1:
                score += board[i][j]
    return score




def main():
    lines = parseInput()
    called = lines[0]
    called = [int(c) for c in called.split(',')]
    boards = []
    curBoard = []
    for i in range(2, len(lines)):

        if i % 6 == 1:
            boards.append(curBoard)
            curBoard = []
        else: 
            curBoard.append([int(c) for c in lines[i].split()])
    boards.append(curBoard)
    for i in range(len(called)):
        number = called[i]
        newBoards = []
        for j in range(len(boards)):
            board = boards[j]
            # print(board)
            removeNumberFromBoard(board, number)
            if isFinishedBoard(board):
                print(scoreBoard(board) * number)
            else:
                newBoards.append(board)
        boards = newBoards
main()