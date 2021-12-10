def getlines():
  f = open('data10.txt')
  lines = []
  
  for line in f:
    lines.append(line.rstrip())
  
  f.close()
  return lines

def corrupted(line):
  stack = []
  
  for c in line:
    if c == '(' or c == '[' or c == '{' or c == '<':
      stack.append(c)
    else:
      opening = stack.pop()
      if c == ')' and opening != '(':
        return True, ')'
      elif c == '}' and opening != '{':
        return True, '}'
      elif c == ']' and opening != '[':
        return True, ']'
      elif c == '>' and opening != '<':
        return True, '>'
  
  return False, '*' 
  
def main1():
  lines = getlines()
  
  errors = {}
  
  for line in lines:
    corrupt, char = corrupted(line)
    if corrupt:
      if char not in errors:
        errors[char] = 0
      errors[char] = errors[char] + 1
  
  scores = {')': 3, ']': 57, '}': 1197, '>': 25137}
  total = 0
  for k in errors:
    total = total + (errors[k] * scores[k])
  print('Solution 1: ' + str(total))

def remaining(line):
  stack = []
  
  for c in line:
    if c == '(' or c == '[' or c == '{' or c == '<':
      stack.append(c)
    else:
      stack.pop()
  
  stack.reverse()  
  return stack

def getscore(inc):
  scores = {'(': 1, '[': 2, '{': 3, '<': 4}    
  total = 0
  
  for i in inc:
    total = (total * 5) + scores[i]
    
  return total

def main2():
  lines = getlines()
  
  incomplete = []
  
  for line in lines:
    corrupt, char = corrupted(line)
    if not corrupt:
      incomplete.append(remaining(line))
  
  scores = []
  for inc in incomplete:
    scores.append(getscore(inc))
  
  scores.sort()
  pos = len(scores) // 2
  print('Solution 2: ' + str(scores[pos]))
  
main1()
main2()
