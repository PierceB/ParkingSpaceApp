import sys
import ImageCropper as IA
import mysql.connector
import cv2
import os
import datetime

snapshotname = 'snapshot.jpeg'

LotID = sys.argv[1]
mydb=mysql.connector.connect(
   	    host = "localhost",
	    user = "connect",
	    passwd="connectpw",
        database = "PARKINGAPPDB"
    )
mycur=mydb.cursor()
sql = "SELECT PARK_ID FROM PARKING_SPACE WHERE LOT_ID = %s"
lot=(LotID, )
mycur.execute(sql, lot)
myresult = mycur.fetchall()

dirname = "ParkingBays_" + '{date:%Y-%m-%d %H:%M:%S}'.format(date=datetime.datetime.now())
os.makedirs(dirname)


for bayID in myresult:
    polygon = IA.getPolygon(bayID[0])
    SortedPolygon = IA.polySort(polygon)
    croppedImage = IA.Crope(SortedPolygon, snapshotname)
    Bayname = dirname + '/ParkingBay' + bayID[0] + ".jpeg"
    cv2.imwrite(Bayname, croppedImage)