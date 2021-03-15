import csv
import sys
import re

def phoneFormat(phone_number):
    clean_phone_number = re.sub('[^0-9]+', '', phone_number)
    formatted_phone_number = re.sub("(\d)(?=(\d{3})+(?!\d))", r"\1-", "%d" % int(clean_phone_number[:-1])) + clean_phone_number[-1]
    return formatted_phone_number

csvFileName = sys.argv[1] #weebly export csv taken as argument

with open(csvFileName, 'r') as csvfile: 
    csvreader = csv.DictReader(csvfile) 
      
    prevRow = {}
    rowCount = 0
    for row in csvreader: 

        rowCount += 1
        #An order has 2 rows, read both rows (1st row has most details, 2nd row has delivery notes in 'Prod Options')
        if rowCount > 1 and prevRow['Order #'] == row['Order #'] : #if prevRow has been parsed

            #validate if order # for this row & previous row matches
            orderNo = row['Order #'].strip()
            orderDate = row['Date'].strip()

            #Check for delivery notes
            prodOptions = row['Product Options']
            notes = ''
            index = prodOptions.find('Delivery Notes')
            if index > 0 : #if word 'Delivery Notes' found in Product Options 
                notes = prodOptions[index + len('Delivery Notes'):].replace(':','').replace('\n',';').replace('\r',';')

            prodId = row['Product Id'].strip()
            phoneNo = phoneFormat(prevRow['Shipping Phone'])
            fullName = prevRow['Shipping First Name'].strip() + ' ' +  prevRow['Shipping Last Name'].strip() #combine First & Last Name
            prodQty = row['Product Quantity'].strip()
            email = prevRow['Shipping Email'].strip()
            address = prevRow['Shipping Address'].strip()
            city = prevRow['Shipping City'].strip()

            if(prodId == '29') : #Product id 29 is for Grad Signs
                print('\"',orderNo,'\",\"',orderDate,'\",\"',prodId,'\",\"',prodQty,'\",\"', fullName,'\",\"' ,email, '\",\"',address,'\",\"', city,'\",\"',phoneNo,'\",\"',notes.strip(),'\"')
        else :
            if rowCount % 2 == 0 : #if order numbers for 2nd row does not match the prev row, stop and print error
                print("CSV Error, Order # does not match", row)
                break
            else :
                prevRow = row