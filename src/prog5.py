ORIGIN = 0
DEST = 1

X = 0
Y = 1

def getdata():
  f = open('data5.txt')
  data = []
  
  for line in f:
    debstr, finstr = line.rstrip().split(' -> ')
    deb = [int(c) for c in debstr.split(',')]
    fin = [int(c) for c in finstr.split(',')]
    data.append((deb, fin))
  
  f.close()
  return data

def is_straight(d):
  return d[ORIGIN][X] == d[DEST][X] or d[ORIGIN][Y] == d[DEST][Y]

def draw(grid, d):
  pos = (d[ORIGIN][X], d[ORIGIN][Y])
  
  while pos != (d[DEST][X], d[DEST][Y]):
    if pos not in grid:
      grid[pos] = 0
    grid[pos] = grid[pos] + 1
      
    if d[ORIGIN][X] < d[DEST][X]:
      pos = (pos[0]+1, pos[1])
    elif d[ORIGIN][X] > d[DEST][X]:
      pos = (pos[0]-1, pos[1])

    if d[ORIGIN][Y] < d[DEST][Y]:
      pos = (pos[0], pos[1]+1)
    elif d[ORIGIN][Y] > d[DEST][Y]:
      pos = (pos[0], pos[1]-1)

  if pos not in grid:
    grid[pos] = 0
  grid[pos] = grid[pos] + 1
  
def count(grid):
  cnt = 0   
  
  for elem in grid:
    if grid[elem] > 1:
      cnt = cnt + 1
      
  return cnt
       
def main1():
  data = getdata()
  grid = {}
  
  for d in data:
    if is_straight(d):
      draw(grid, d) 
  
  print('Solution 1: ' + str(count(grid)))

def main2():
  data = getdata()
  grid = {}
  
  for d in data:
    draw(grid, d)
    
  print('Solution 2: ' + str(count(grid)))
    
main1()
main2()
