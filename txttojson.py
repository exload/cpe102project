import json

try:
   open("../cpe102hw1/gaia.sav")
except:
   print "file not found"

with open("../cpe102hw1/gaia.sav", 'r')as infile:
   wholefile = infile.readlines()

with open("res/newworld", 'w+')as outfile:
   dic = {}
   wod = []
   bacd = [] 
   thing = wholefile[0]

   for x in range(len(wholefile)):
      theline = wholefile[x]
      words = theline.split()
      entity = {}

      if words[0] == "miner":
         entity['type'] = words[0]
         
         loc = {}
         loc['x'] = words[2]
         loc['y'] = words[3]
         entity['location'] = loc

         entity['resourceLimit'] = words[4]
         entity['rate'] = words[5]
         entity['animationRate'] = words[6]

         wod.append(entity)

      elif words[0] == "vein":
         entity['type'] = words[0]
         
         loc = {}
         loc['x'] = words[2]
         loc['y'] = words[3]
         entity['location'] = loc

         entity['rate'] = words[4]
         entity['resourceDistance'] = words[5]

         wod.append(entity)

      elif words[0] == "blacksmith":
         entity['type'] = words[0]
         
         loc = {}
         loc['x'] = words[2]
         loc['y'] = words[3]
         entity['location'] = loc

         entity['resourceLimit'] = words[4]
         entity['rate'] = words[5]
         entity['animationRate'] = words[6]

         wod.append(entity)

      elif words[0] == "obstacle":
         entity['type'] = words[0]
         
         loc = {}
         loc['x'] = words[2]
         loc['y'] = words[3]
         entity['location'] = loc

         wod.append(entity)

      elif words[0] == "background":
         entity['type'] = words[1]
         
         loc = {}
         loc['x'] = words[2]
         loc['y'] = words[3]
         entity['location'] = loc

         bacd.append(entity)

      else:
         print "Error, type: " + words[0] + " undefined"


   dic['worldObjects'] = wod
   dic['background'] = bacd

   outfile.write(json.dumps(dic))