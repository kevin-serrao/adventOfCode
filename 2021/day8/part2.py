def parseInput():
    with open('input.txt') as f:
        lines = [line.strip() for line in f.readlines()]
        f.close()
        return lines


def main():
    lines = parseInput()
    lines = [line.split("|") for line in lines]
    signals = [line[0].split() for line in lines] 
    outputs = [line[1].split() for line in lines]   
    s = 0
    for i in range(len(signals)):
        signal = signals[i]
        one = filter(lambda s:  len(s) == 2, signal)[0]
        seven = filter(lambda s: len(s) == 3, signal)[0]
        four = filter(lambda s: len(s) == 4, signal)[0]
        eight = filter(lambda s: len(s) == 7, signal)[0]
        three = filter(lambda s: len(s) == 5 and all(c in s for c in one), signal)[0]
        five = filter(lambda s: len(s) == 5 and sum(c in s for c in one) == 1 and sum(c in s for c in four) == 3, signal)[0]
        two = filter(lambda s: len(s) == 5 and s != five and s != three, signal)[0]
        six = filter(lambda s: len(s) == 6 and sum(c in s for c in one) == 1, signal)[0]
        zero = filter(lambda s: len(s) == 6 and sum(c in s for c in four) == 3 and s != six, signal)[0]
        nine = filter(lambda s: len(s) == 6 and s != zero and s != six, signal)[0]
        nums = [tuple(sorted(zero)),tuple(sorted(one)), tuple(sorted(two)), tuple(sorted(three)), tuple(sorted(four)), tuple(sorted(five)), tuple(sorted(six)), tuple(sorted(seven)), tuple(sorted(eight)), tuple(sorted(nine))]
        
        for j in range(len(outputs[i])):
            digit = outputs[i][j]
            for k in range(len(nums)):
                if i == 1 and j == 0:
                    print(tuple(sorted(digit)), k, nums[k])
                if nums[k] == tuple(sorted(digit)):
                    # print(k)
                    s += k * 10 ** (3 - j)
        print(s)
    print(s)
    

main()