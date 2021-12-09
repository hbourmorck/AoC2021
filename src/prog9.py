from PIL import Image

def getheights():
  f = open('data9.txt')
  heights = []
  
  for line in f:
    heights.append([int(c) for c in line.rstrip()])
  
  f.close()
  return heights

def islower(heights, x, y):
  h = heights[y][x] 
  
  if y > 0 and heights[y-1][x] <= h:
    return False
  if y < len(heights)-1 and heights[y+1][x] <= h:
    return False
  if x > 0 and heights[y][x-1] <= h:
    return False
  if x < len(heights[y])-1 and heights[y][x+1] <= h:
    return False
  
  return True
  
def generate(heights):
   maxval = 9
   img = []
   
   for y in range(0, len(heights)):
     for x in range(0, len(heights[0])):
       prop = heights[y][x] / maxval
       img.append(int(255 * prop))

   res = Image.frombytes('L', (len(heights[0]), len(heights)), bytes(img), 'raw')
   res.save('image9.png')
  
def main1():
  heights = getheights()
  generate(heights)
  
  low = []
  for y in range(len(heights)):
    for x in range(len(heights[y])):
      if islower(heights, x, y):
        low.append(heights[y][x] + 1)
        
  print('Solution 1: ' + str(sum(low)))

def getlowneighbours(heights, x, y, processed):
  n = set()
  
  if y > 0 and (x, y-1) not in processed and heights[y-1][x] < 9:
    n.add((x, y-1))
    
  if y < len(heights)-1 and (x, y+1) not in processed and heights[y+1][x] < 9:
    n.add((x, y+1))
    
  if x > 0 and (x-1, y) not in processed and heights[y][x-1] < 9:
    n.add((x-1, y))
    
  if x < len(heights[y])-1 and (x+1, y) not in processed and heights[y][x+1] < 9:
    n.add((x+1, y))
  
  return n

def getbasin(heights, x, y, processed):
  neighbours = getlowneighbours(heights, x, y, processed)
  
  if neighbours:
    for n in neighbours:
      processed.add(n)
      processed = getbasin(heights, n[0], n[1], processed)
    return processed
  else:
    return processed

def main2():
  heights = getheights()
  
  low = []
  for y in range(len(heights)):
    for x in range(len(heights[y])):
      if islower(heights, x, y):
        low.append((x, y))

  basins = []
  for l in low:
    x = l[0]
    y = l[1]
    processed = set()
    processed.add((x, y))
    basin = getbasin(heights, x, y, processed)
    basins.append(basin)
  
  lg = []  
  for b in basins:
    lg.append(len(b))
    
  a = max(lg)
  lg.remove(a)
  b = max(lg)
  lg.remove(b)
  c = max(lg)
  
  print('Solution 2: ' + str(a*b*c))
  
main1()
main2()
