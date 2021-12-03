def getdata():
  f = open('data3.txt')
  data = []
  
  for line in f:
    data.append(line.rstrip())
  
  f.close()      
  return data
  
def getcount(data, pos, bit):
  cnt = 0
  for d in data:
    if d[pos] == bit:
      cnt = cnt + 1
  return cnt
      
def main1():
  data = getdata()
  seuil = len(data) // 2
  
  gamma = []
  epsilon = []
  
  for pos in range(len(data[0])):
    cnt = getcount(data, pos, '1')
    if cnt > seuil:
      gamma.append('1')
      epsilon.append('0')
    else:
      gamma.append('0')
      epsilon.append('1')
      
  g = int(''.join(gamma), 2)
  e = int(''.join(epsilon), 2)
  
  print('Solution 1: ' + str(g * e))
  
def filter(data, pos, bit):
  res = []
  
  for d in data:
    if d[pos] == bit:
      res.append(d)
      
  return res
  
def main2():
  data = getdata()
  
  oxygen = data[:]
  pos = 0
  
  while len(oxygen) > 1:
    cnt = getcount(oxygen, pos, '1')
    if cnt >= len(oxygen) / 2:
      oxygen = filter(oxygen, pos, '1')
    else:
      oxygen = filter(oxygen, pos, '0')
    pos = pos + 1
    
  co2 = data[:]
  pos = 0
  
  while len(co2) > 1:
    cnt = getcount(co2, pos, '0')
    if cnt <= len(co2) / 2:
      co2 = filter(co2, pos, '0')
    else:
      co2 = filter(co2, pos, '1')
    pos = pos + 1
      
  o = int(''.join(oxygen[0]), 2)
  c = int(''.join(co2[0]), 2)
  
  print('Solution 2: ' + str(o * c))
  
main1()
main2()
