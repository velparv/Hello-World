import csv
import sys
import re

def phoneFormat(phone_number):
    clean_phone_number = re.sub('[^0-9]+', '', phone_number)
    fpn = re.sub("(\d)(?=(\d{3})+(?!\d))", r"\1-", "%d" % int(clean_phone_number[:-1])) + clean_phone_number[-1]
    if fpn[:2] == '1-' :
        fpn = fpn[2:]
    return fpn

csvFileName = sys.argv[1] #weebly export csv taken as argument
ordersMap = {}
on =  ''
with open(csvFileName, 'r') as csvfile: 
    csvreader = csv.DictReader(csvfile) 
    prevOrderNo = ''
    for row in csvreader: 
        orderNo = row['Order #'].strip()
        if not (orderNo in ordersMap) : #if order does not 
            ordersMap[orderNo] = []
        orderList = ordersMap[orderNo]
        orderList.append(row)

    #for orderNo, orderList in ordersMap.items() :
    #    print(len(orderList))

    #print(len(ordersMap))
    with open("out.csv", "w") as csvOut:

        for orderNo, orderList in ordersMap.items() :
            orderDate = phoneNo = fullName = prodQty = email = address = city = notes = ''
            mainPhoneNo = mainFullName= mainEmail = mainAddress = mainCity =''
            gradOrderFound = False
            for orderLineNo, orderLine in enumerate(orderList) :
                prodId = orderLine['Product Id'].strip()
                if prodId == '' :
                    mainPhoneNo = phoneFormat(orderLine['Shipping Phone'])
                    mainFullName = orderLine['Shipping First Name'].strip() + ' ' +  orderLine['Shipping Last Name'].strip() #combine First & Last Name
                    mainEmail = orderLine['Shipping Email'].strip()
                    mainAddress = orderLine['Shipping Address'].strip()
                    mainCity = orderLine['Shipping City'].strip().capitalize()
                elif prodId == '29' :
                    gradOrderFound = True
                    prodQty = orderLine['Product Quantity'].strip()
                    orderDate = orderLine['Date'].strip()
                    #Check for delivery notes
                    prodOptions = orderLine['Product Options']
                    index = prodOptions.find('Delivery Notes')
                    if index > 0 : #if word 'Delivery Notes' found in Product Options 
                        notes = prodOptions[index + len('Delivery Notes'):].replace(':','').replace('\n',';').replace('\r',';')
    
                    index = prodOptions.find('Delivery Street Address - ')
                    if index >= 0 : #if word 'Delivery Notes' found in Product Options 
                        s1 = prodOptions.replace('Delivery Street Address - ','')
                        index = s1.find(', Buyer Name')
                        s2 = s1[:index]
                        addressParts = s2.split(":")
                        address = addressParts[1].strip()
                        cityZip = addressParts[0].split(',')
                        city  = cityZip[0].strip()
    
            if gradOrderFound :
                if address.lower() != mainAddress.lower() or city.lower() != mainCity.lower() :
                    print(f"@@@@@@@@@  Address in Product does not match the main Address [{address},{city}] [{mainAddress},{mainCity}]  @@@@@@@@@@@@@@")
                print('\"',orderNo,'\",\"',orderDate,'\",\"',prodId,'\",\"',prodQty,'\",\"', mainFullName,'\",\"' ,mainEmail, '\",\"',mainAddress,'\",\"', city,'\",\"',mainPhoneNo,'\",\"',notes.strip(),'\"',file=csvOut)
