
def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines

target_x = [240, 292]
target_y = [-57, -90]
    
# target_x = [20, 30]
# target_y = [-5, -10]

def test(init_vx, init_vy):
    x = 0
    y = 0
    vx = init_vx
    vy = init_vy
    while y >= target_y[1]:
        if x >= target_x[0] and x <= target_x[1] and y <= target_y[0] and y >= target_y[1]:
            return True
        y += vy
        x += vx
        vx = max(0, vx-1)
        vy -= 1
    return False

def main():
    lines = parseInput()
    valid_y = set()
    valid_x = set()
    for i in range(target_y[1], 90):
        v = 0
        y = i*(i+1) / 2
        while y >= target_y[1]:
            if y <= target_y[0] and y >= target_y[1]:
                valid_y.add(i)
            y += v
            v -= 1
    for i in range(1000):
        v = i
        x = 0
        while v > 0:
            if x >= target_x[0] and x <= target_x[1]:
                print(str(x) + ' is valid')
                valid_x.add(i)
            x += v
            v -= 1
    count = 0
    for x in valid_x:
        for y in valid_y:
            if test(x,y):
                count += 1
    print(count)
    


    
main()