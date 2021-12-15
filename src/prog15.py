import networkx as nx

def getgrid():
  f = open('data15.txt')
  
  grid = []
  for line in f:
    grid.append([int(x) for x in line.rstrip()])
  
  f.close()
  return grid

def getneighbours(grid, x, y):
  n = []
  
  if x > 0:
    n.append((x-1, y))
  if y > 0:
    n.append((x, y-1))
  if x < len(grid[y]) - 1:
    n.append((x+1, y))
  if y < len(grid) - 1:
    n.append((x, y+1))
    
  return n

def getlaby(grid):  
  G = nx.DiGraph()
  
  for y in range(len(grid)):
    for x in range(len(grid[y])):
      neighbours = getneighbours(grid, x, y)
      for n in neighbours:
        if ((x, y), n) not in G.edges:
          G.add_edge((x, y), n, weight=grid[n[1]][n[0]])
        if (n, (x, y)) not in G.edges:
          G.add_edge(n, (x, y), weight=grid[y][x])
  
  return G
  
def main1():
  grid = getgrid()
  laby = getlaby(grid)
  
  path = nx.shortest_path(laby, (0, 0), (len(grid[0])-1, len(grid)-1), weight='weight')
  
  risk = 0
  for p in path:
    risk = risk + grid[p[1]][p[0]]
  risk = risk - grid[0][0]
  print('Solution 1: ' + str(risk))

def increaser(grid, inc):
  next = []
  
  for y in range(len(grid)):
    next.append([])
    for x in range(len(grid[y])):
      val = grid[y][x] + inc
      if val > 9:
        val = val - 9
      next[y].append(val)
      
  return next

def copier(bigger, grid, x, y):
  for b in range(len(grid)):
    for a in range(len(grid[b])):
      bigger[y+b][x+a] = grid[b][a]

def biggergrid(grid):
  bigger = []
  
  for y in range(len(grid) * 5):
    bigger.append([0] * len(grid[0]) * 5)
  
  for y in range(5):
    for x in range(5):
      inc = increaser(grid, x+y)
      copier(bigger, inc, x * len(grid[0]), y * len(grid))
  
  return bigger
  
def main2():
  grid = getgrid()
  bigger = biggergrid(grid)
  laby = getlaby(bigger)
  
  path = nx.shortest_path(laby, (0, 0), (len(bigger[0])-1, len(bigger)-1), weight='weight')
  
  risk = 0
  for p in path:
    risk = risk + bigger[p[1]][p[0]]
  risk = risk - bigger[0][0]
  print('Solution 2: ' + str(risk))
  
main1()
main2()
