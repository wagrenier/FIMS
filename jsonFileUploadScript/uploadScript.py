import firebase
import json

data = json.load(open('test.json')) #reading the json file
URL = 'fims-therubberducks/users/fYZcFgBZx0U26C1MCn6ZtP9J8UB2/products' #parsing the address of the firebase database
None
firebase.push(URL, data) #envoi de l ecriture au database












