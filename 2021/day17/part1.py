
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

    

def main():
    lines = parseInput()
    target_x = [240, 292]
    target_y = [-57, -90]
    for i in range(160):
        v = 0
        y = i*(i+1) / 2
        while y >= target_y[1]:
            if y <= target_y[0] and y >= target_y[1]:
                print(str(i*(i+1) / 2) + " passes. i is " + str(i) )
            y += v
            v -= 1


    
main()