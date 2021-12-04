def getdata():
  f = open('data4.txt')
  grids = []
  numbers = None
  grid = []
  
  for line in f:
    if numbers:
      if len(line) > 1:
        row = [[int(x), False] for x in line.lstrip().rstrip().split()]
        grid.append(row)
      else:
        if grid:
          grids.append(grid)
        grid = []
    else:
      numbers = [int(x) for x in line.rstrip().split(',')]
  
  f.close()
  return numbers, grids

def horizontal(grid):
  for row in grid:
    res = [y for (x, y) in row]
    if all(res):
      return True
  return False

def vertical(grid):
  for pos in range(len(grid[0])):
    res = []
    for row in range(len(grid)):
      res.append(grid[row][pos][1])
    if all(res):
      return True
  return False

def winning_grid(grids):
  for pos, grid in enumerate(grids):
    if horizontal(grid) or vertical(grid):
      return pos
  return -1

def winning(grids):
  return winning_grid(grids) != -1

def apply(number, grids):
  for grid in grids:
    for row in grid:
      for elem in row:
        if elem[0] == number:
          elem[1] = True
          
def score(grid):
  s = 0
  for row in grid:
    for elem in row:
      if not elem[1]:
        s = s + elem[0]
  return s

def main1():
  numbers, grids = getdata()
  
  i = 0
  while not winning(grids):
    apply(numbers[i], grids)
    i = i + 1
    
  g = winning_grid(grids)
  print('Solution 1: ' + str(score(grids[g]) * numbers[i-1]))
  
def main2():
  numbers, grids = getdata()
  
  i = 0
  while not winning(grids) or len(grids) > 1:
    apply(numbers[i], grids)
    g = winning_grid(grids)
    while g != -1 and len(grids) > 1:
      grids.pop(g)
      g = winning_grid(grids)      
    i = i + 1
    
  print('Solution 2: ' + str(score(grids[0]) * numbers[i-1]))
  
main1()
main2()
