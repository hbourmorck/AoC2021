def getfish():
  f = open('data6.txt')
  fish = [int(x) for x in f.readline().rstrip().split(',')]
  f.close()
  return fish
  
def main1():
  fish = getfish()
  
  for day in range(80):
    newfish = []
    added = []
    for f in fish:
      if f == 0:
        newfish.append(6)
        added.append(8)
      else:
        newfish.append(f - 1)
    fish = newfish + added
    
  print('Solution 1: ' + str(len(fish)))
  
def main2():
  fish = getfish()
  
  occurence = [0] * 9
  for f in fish:
    occurence[f] = occurence[f] + 1
    
  newocc = [0] * 9
  for day in range(256):
    newocc[0] = occurence[1]
    newocc[1] = occurence[2]
    newocc[2] = occurence[3]
    newocc[3] = occurence[4]
    newocc[4] = occurence[5]
    newocc[5] = occurence[6]
    newocc[7] = occurence[8]
    newocc[6] = occurence[0] + occurence[7]    
    newocc[8] = occurence[0]
    occurence = newocc[:]  
    
  print('Solution 2: ' + str(sum(occurence)))
  
main1()
main2()
