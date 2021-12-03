def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

def main():
    lines = parseInput()
    commands = [[line.split(" ")[0], int(line.split(" ")[1])] for line in lines]
    x = 0
    y = 0
    for command in commands:
        if command[0] == 'up':
            y -= command[1]
        elif command[0] == 'down':
            y += command[1]
        elif command[0] == 'forward':
            x += command[1]
    print(x*y)
    
main()