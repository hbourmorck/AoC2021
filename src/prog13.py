def getcoords():
  f = open('data13.txt')
  
  coords = set()
  folding = []
  empty = False
  
  for line in f:
    if len(line) < 2:
      empty = True
    elif empty:
      elems = line.rstrip().split()[2].split('=')
      folding.append((elems[0], int(elems[1])))
    else:
      elems = line.rstrip().split(',')
      coords.add((int(elems[0]), int(elems[1])))
    
  f.close()
  return coords, folding

def fold(coords, command):
  res = set()
  
  limit = command[1]
  
  for c in coords:
    if command[0] == 'x':
      if c[0] <= limit:
        res.add(c)
      else:
        dist = c[0] - limit
        folded = (limit - dist, c[1])
        res.add(folded)
    else:
      if c[1] <= limit:
        res.add(c)
      else:
        dist = c[1] - limit
        folded = (c[0], limit - dist)
        res.add(folded)
  
  return res     
      
def main1():
  coords, folding = getcoords()
  
  coords = fold(coords, folding[0])

  print('Solution 1: ' + str(len(coords)))

def main2():
  coords, folding = getcoords()

  for f in folding:  
    coords = fold(coords, f)

  maxx = max([a for (a, b) in coords])
  maxy = max([b for (a, b) in coords])
  
  print('Solution 2: ')
  for y in range(maxy+1):
    for x in range(maxx+1):
      if (x, y) in coords:
        print('@', end='')
      else:
        print(' ', end='')
    print()
  
main1()
main2()
