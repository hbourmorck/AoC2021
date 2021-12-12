def getocto():
  f = open('data11.txt')
  octo = []
  
  for line in f:
    elem = [[int(x), False] for x in line.rstrip()]
    octo.append(elem)
  
  f.close()
  return octo

def increase(octo):
  for line in octo:
    for o in line:
      o[0] = o[0] + 1

def getneighbours(octo, x, y):
  ne = []
  
  for a in range(x-1, x+2):
    for b in range(y-1, y+2):
      if a >= 0 and a < len(octo[0]) and b >= 0 and b < len(octo) and (a != x or b != y):
        ne.append((a, b)) 
  
  return ne

def flash(octo, x, y, done):
  if octo[y][x][0] > 9 and (x, y) not in done:
    done.add((x, y))
    octo[y][x][1] = True
    neighbours = getneighbours(octo, x, y)
    for n in neighbours:
      octo[n[1]][n[0]][0] = octo[n[1]][n[0]][0] + 1
      flash(octo, n[0], n[1], done)

def propagate(octo):
  done = set()
  for y in range(len(octo)):
    for x in range(len(octo[y])):
      flash(octo, x, y, done)     

def powerdown(octo):
  nbr = 0
  
  for line in octo:
    for o in line:
      if o[1]:
        o[0] = 0
        o[1] = False
        nbr = nbr + 1
    
  return nbr
  
def display(octo):
  for line in octo:
    for o in line:
      if o[0] > 9:
        print('*', end='')
      else:
        print(o[0], end='')
    print()
  print()
  
def main1():
  octo = getocto()
  total = 0
  
  for rnd in range(100):
    #display(octo)
    increase(octo)
    propagate(octo)
    nbr = powerdown(octo)
    total = total + nbr
    
  print('Solution 1: ' + str(total))

def hasallflashed(octo):
  for line in octo:
    for o in line:
      if not o[1]:
        return False
  return True
  
def main2():
  octo = getocto()
  allflash = False
  rnd = 0
  
  while not allflash:
    increase(octo)
    propagate(octo)
    allflash = hasallflashed(octo)
    powerdown(octo)
    rnd = rnd + 1
    
  print('Solution 2: ' + str(rnd))
  
main1()
main2()
