segment = {0: 6, 1: 2, 2: 5, 3: 5, 4: 4, 5: 5, 6: 6, 7: 3, 8: 7, 9: 6}

def getdata():
  f = open('data8.txt')
  data = []
  
  for line in f:
    left, right = line.rstrip().split(' | ')
    l = [''.join(sorted(x)) for x in left.split()]
    r = [''.join(sorted(x)) for x in right.split()]
    data.append((l, r))
  
  f.close()
  return data
  
def main1():
  data = getdata()
  
  cnt = 0
  
  for d in data:
    for digit in d[1]:
      if len(digit) == segment[1] or len(digit) == segment[4] \
        or len(digit) == segment[7] or len(digit) == segment[8]:
          cnt = cnt + 1
  
  print('Solution 1: ' + str(cnt))

def getAllByLength(data, lg):
  res = []
  for d in data[0]:
    if len(d) == lg:
      res.append(d)
  return res

def getOne(data):
  return getAllByLength(data, segment[1]).pop()
  
def getFour(data):
  return getAllByLength(data, segment[4]).pop()
  
def getSeven(data):
  return getAllByLength(data, segment[7]).pop()

def getEight(data):
  return getAllByLength(data, segment[8]).pop()
  
def getTop(seven, one):
  return set(seven).difference(set(one)).pop()
  
def getMiddleAndUpLeft(four, one):
  return ''.join(sorted(set(four).difference(set(one))))
  
def get069(data):
  nbr = getAllByLength(data, segment[0])
  one = getOne(data)
  four = getFour(data)
  mul = getMiddleAndUpLeft(four, one)
  
  six = None
  for di in nbr:
    if one[0] not in di or one[1] not in di:
      six = di
      
  zero = None
  for di in nbr:
    if mul[0] not in di or mul[1] not in di:
      zero = di
      
  nbr.remove(six)
  nbr.remove(zero)
  nine = nbr.pop()
  
  return zero, six, nine
  
def get235(data):
  nbr = getAllByLength(data, segment[2])
  one = getOne(data)
  four = getFour(data)
  mul = getMiddleAndUpLeft(four, one)
  
  three = None
  for di in nbr:
    if one[0] in di and one[1] in di:
      three = di
      
  five = None
  for di in nbr:
    if mul[0] in di and mul[1] in di:
      five = di
      
  nbr.remove(three)
  nbr.remove(five)
  two = nbr.pop()
      
  return two, three, five

def decode(data):
  one = getOne(data)
  four = getFour(data)
  seven = getSeven(data)
  eight = getEight(data)
  zero, six, nine =  get069(data)
  two, three, five = get235(data)
  
  answer = []
  for digit in data[1]:
    if digit == zero:
      answer.append('0')
    elif digit == one:
      answer.append('1')
    elif digit == two:
      answer.append('2')
    elif digit == three:
      answer.append('3')
    elif digit == four:
      answer.append('4')
    elif digit == five:
      answer.append('5')
    elif digit == six:
      answer.append('6')
    elif digit == seven:
      answer.append('7')
    elif digit == eight:
      answer.append('8')
    elif digit == nine:
      answer.append('9')
    else:
      answer.append('*')
  return int(''.join(answer))

def main2():
  data = getdata()
  
  total = 0
  
  for d in data:
    total = total + decode(d)
    
  print('Solution 2: ' + str(total))
  
main1()
main2()
