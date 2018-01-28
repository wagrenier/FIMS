import firebase
import json
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import datetime
import os

os.system('cls')

while True:
	###START DATE LIST TO BE WRITTEN TO JSON###
	jData = ["ITEM_PLACEHOLDER", "UPC_PLACEHOLDER", "DATE_PLACEHOLDER"] #used to print stuff to console as a debugging tool
	jName = "ITEM"
	jUPC = "ZERO"
	jDay = "YESTERDAY"
	jMonth = "13TH MONTH"
	jYear = "EPOCH"

	###END JSON###


	###START UPC DETECTION STUFF ###

	#scan barcode (Program will not continue until barcode is scanned)
	UPC = input()
	UPC = UPC.strip()
	jData[1] = UPC
	jUPC = UPC
	#set standard url
	myUrl = "https://upcdatabase.com/item/"

	#append upc to url
	myUrl = myUrl + UPC
	
	#open connection and grab raw html
	uClient = uReq(myUrl)
	pageHTML = uClient.read()
	uClient.close()

	#parse html
	page_soup = soup(pageHTML, "html.parser")

	#get data class TODO: ERASE
	dataContainer = page_soup.find("table", {"class" : "data"})

	#look, I know the index method is shit but please give me a break. I learned python today
	index1 = 0
	limit1 = 3

	#get data from the correct row
	for rows in page_soup.find_all('tr'):
		index1 += 1
		column = rows.find_all('td')
		if index1 == limit1:
			item = column
			break

	item = column[2]

	itemList = list(item)

	jData[0] = itemList
	jName = itemList
		
	####END UPC STUFF###

	###START DATE/TIME STUFF###

	currentDateTime = datetime.datetime.now()
	jData[2] = currentDateTime.strftime("%d-%m-%y @ %H:%M:%S")
	jDay = currentDateTime.strftime("%d")
	jMonth = currentDateTime.strftime("%m")
	jYear = currentDateTime.strftime("%y")

	os.system('cls')
	print(jData)

	###END DATE/TIME STUFF###


	###START JSON WRITE###
	
	#open/create json
	jsonName = "test.json"
	file = open(jsonName, "w")
	
	#write data to json
	
	file.write("{ \n")#JSON opening bracket
	
	stringFormat = "\t\"day\": \"%s\"," %(jDay) #write day to file
	stringFormat += "\n\t\"month\": \"%s\"," %(jMonth)#write month to file
	stringFormat += "\n\t\"name\": \"%s\"," %(jName)#write name to file
	stringFormat += "\n\t\"upc\": \"%s\"," %(jUPC)#write upc to file
	stringFormat += "\n\t\"year\": \"%s\"\n" %(jYear)#write year to file
	
	file.write(stringFormat) #write formatted JSON data to file
	
	file.write("}") #write closing bracket
	
	file.close()#close file
	
	###END JSON WRITE###

	###SEND JSON###
	data = json.load(open('test.json')) #reading the json file
	URL = 'fims-therubberducks/users/fYZcFgBZx0U26C1MCn6ZtP9J8UB2/buffer' #parsing the address of the firebase database
	None
	firebase.push(URL, data) #envoi de l ecriture au database

	### Thats all folks###