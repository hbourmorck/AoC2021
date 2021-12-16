import math

def getpacket():
  f = open('data16.txt')
  line = f.readline().rstrip()
  f.close()
  
  packet = []
  
  for c in line:
    packet.append(bin(int(c, 16))[2:].zfill(4))
  
  return ''.join(packet)

def parsenumber(packet, startpos):

  ind = startpos + 6
  endreached = False
  
  while not endreached:
    elem = packet[ind:ind+5]
    if elem[0] == '0':
      endreached = True
    ind = ind + 5
  
  nextpos = ind
  
  return nextpos

def parseoperator(packet, startpos, versions):
  
  lengthTypeID = packet[startpos+6]
  
  if lengthTypeID == '0':
    lg = int(packet[startpos+7:startpos+22], 2)
    nextpos = startpos+22
    while nextpos < startpos + 22 + lg:
      nextpos = parse(packet, nextpos, versions)
      
  else:
    nbr = int(packet[startpos+7:startpos+18], 2)    
    nextpos = startpos+18
    for i in range(nbr):
      nextpos = parse(packet, nextpos, versions)
    
  return nextpos

def parse(packet, startpos, versions):
  version = int(packet[startpos:startpos+3], 2)
  typeID = int(packet[startpos+3:startpos+6], 2)
  
  versions.append(version)
  
  if typeID == 4:
    nextpos = parsenumber(packet, startpos)
  else:
    nextpos = parseoperator(packet, startpos, versions)
    
  return nextpos
  
def main1():
  packet = getpacket()
  
  versions = []
  parse(packet, 0, versions)
  
  print('Solution 1: ' + str(sum(versions)))

def evaluatenumber(packet, startpos):

  number = []
  ind = startpos + 6
  endreached = False
  
  while not endreached:
    elem = packet[ind:ind+5]
    if elem[0] == '0':
      endreached = True
    number.append(elem[1:])
    ind = ind + 5
  
  value = int(''.join(number), 2)
  nextpos = ind
  
  return value, nextpos

def evaluateoperator(packet, startpos):
  typeID = int(packet[startpos+3:startpos+6], 2)
  lengthTypeID = packet[startpos+6]
  
  values = []
  
  if lengthTypeID == '0':
    lg = int(packet[startpos+7:startpos+22], 2)
    nextpos = startpos+22
    while nextpos < startpos + 22 + lg:
      value, nextpos = evaluate(packet, nextpos)
      values.append(value)
      
  else:
    nbr = int(packet[startpos+7:startpos+18], 2)    
    nextpos = startpos+18
    for i in range(nbr):
      value, nextpos = evaluate(packet, nextpos)
      values.append(value)
  
  if typeID == 0:
    result = sum(values)
  elif typeID == 1:
    result = math.prod(values)
  elif typeID == 2:
    result = min(values)
  elif typeID == 3:
    result = max(values)
  elif typeID == 5:
    if values[0] > values[1]:
      result = 1
    else:
      result = 0
  elif typeID == 6:
    if values[0] < values[1]:
      result = 1
    else:
      result = 0
  elif typeID == 7:
    if values[0] == values[1]:
      result = 1
    else:
      result = 0
    
  return result, nextpos

def evaluate(packet, startpos):
  typeID = int(packet[startpos+3:startpos+6], 2)
  
  if typeID == 4:
    value, nextpos = evaluatenumber(packet, startpos)
  else:
    value, nextpos = evaluateoperator(packet, startpos)
    
  return value, nextpos

def main2():
  packet = getpacket()
  
  value, nextpos = evaluate(packet, 0)
  
  print('Solution 2: ' + str(value))
  
main1()
main2()
