def getcaves():
  f = open('data12.txt')
  caves = []
  
  for line in f:
    left, right = line.rstrip().split('-')
    if left == 'end':
      caves.append((right, left))
    else:  
      caves.append((left, right))
  
  f.close()
  return caves

def getneighbours(caves, current):
  n = []  
  for c in caves:
    if c[0] == current and c[1] != 'start':
      n.append(c[1])
    elif c[1] == current and c[0] != 'start':
      n.append(c[0])
  return n

def findpath(caves, current_cave, path, solutions):
  if current_cave == 'end':
    solutions.append(path)
  else:
    neighbours = getneighbours(caves, current_cave)
    for n in neighbours:
      if n.isupper() or n not in path:
        findpath(caves, n, path + [n], solutions)
  
def main1():
  caves = getcaves()
  
  solutions = []
  path = ['start']
  
  findpath(caves, 'start', path, solutions)
  
  print('Solution 1: ' + str(len(solutions)))

def getocc(path):
  occone = set()
  occtwo = set()
  
  for p in path:
    c = path.count(p)
    if c == 1 and p.islower():
      occone.add(p)
    elif c >= 2 and p.islower():
      occtwo.add(p)
  
  return occone, occtwo

def getneighbours2(caves, current, path):
  n = []  
  
  for c in caves:
    occone, occtwo = getocc(path)
    
    elem = None  
    if c[0] == current and c[1] != 'start':
      elem = c[1]      
    elif c[1] == current and c[0] != 'start':
      elem = c[0]
      
    if elem:
      if elem.isupper():
        n.append(elem)
      else:
        if elem not in occtwo:
          if elem not in occone:
            n.append(elem)
          elif not occtwo:
            n.append(elem)      
      
  return n
  
def findpath2(caves, current_cave, path, solutions):
  if current_cave == 'end':
    solutions[0] = solutions[0] + 1
  else:
    neighbours = getneighbours2(caves, current_cave, path)
    for n in neighbours:
      findpath2(caves, n, path + [n], solutions)
  
def main2():
  caves = getcaves()
  
  solutions = [0]
  path = ['start']  
      
  findpath2(caves, 'start', path, solutions)
  
  print('Solution 2: ' + str(solutions[0]))
  
main1()
main2()
