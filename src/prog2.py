def getcommands():

  f = open('data2.txt')
  commands = []
  
  for line in f:
    commands.append(line.rstrip().split(' '))
  
  f.close()
  return commands
  
def main1():
  commands = getcommands()
  pos = 0
  depth = 0
  
  for c in commands:
    if c[0] == 'up':
      depth = depth - int(c[1])
    elif c[0] == 'down':
      depth = depth + int(c[1])
    else:
      pos = pos + int(c[1])
      
  print('Solution 1: ' + str(pos * depth))
  
def main2():
  commands = getcommands()
  pos = 0
  depth = 0
  aim = 0
  
  for c in commands:
    if c[0] == 'up':
      aim = aim - int(c[1])
    elif c[0] == 'down':
      aim = aim + int(c[1])
    else:
      pos = pos + int(c[1])
      depth = depth + (aim * int(c[1]))
      
  print('Solution 2: ' + str(pos * depth))
  
main1()
main2()
