def getdata():
  f = open('data1.txt')
  data = []
  
  for line in f:
    data.append(int(line.rstrip()))
  
  f.close()
  return data
  
def main1():
  data = getdata()
  
  prev = data[0]
  inc = 0
  
  for i in range(1, len(data)):
    if data[i] > prev:
      inc = inc + 1
    prev = data[i]
    
  print('Solution 1: ' + str(inc))
  
def main2():
  data = getdata()
  
  prev = [data[0], data[1], data[2]]
  inc = 0
  
  for i in range(3, len(data)):
    cur = [prev[1], prev[2], data[i]]
    if sum(cur) > sum(prev):
      inc = inc + 1
    prev = cur
    
  print('Solution 2: ' + str(inc))
  
main1()
main2()
