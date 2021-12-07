def getcrabs():
  f = open('data7.txt')
  crabs = [int(x) for x in f.readline().rstrip().split(',')]
  f.close()
  return crabs

def distance(crabs, pos):
  dist = 0
  for c in crabs:
    dist = dist + abs(pos - c)
  return dist
  
def main1():
  crabs = getcrabs()
  mini = min(crabs)
  maxi = max(crabs)
  
  distances = []
  for pos in range(mini, maxi+1):
    distances.append(distance(crabs, pos))
    
  print('Solution 1: ' + str(min(distances)))

def distance2(crabs, pos, memo):
  dist = 0
  for c in crabs:
    ecart = abs(pos - c)
    if ecart not in memo:
      memo[ecart] = sum(range(1, ecart+1))
    dist = dist + memo[ecart]
  return dist
  
def main2():
  crabs = getcrabs()
  mini = min(crabs)
  maxi = max(crabs)
  
  distances = []
  memo = {}
  for pos in range(mini, maxi+1):
    distances.append(distance2(crabs, pos, memo))
    
  print('Solution 2: ' + str(min(distances)))
  
main1()
main2()
