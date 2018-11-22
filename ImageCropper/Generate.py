import sys
import ImageCropper as IA
import mysql.connector
import cv2
import os
import datetime
import Details as D
import shutil

#generates cropped images from the current image

LotID = sys.argv[1]
mydb=mysql.connector.connect(
   	    host = D.hostn,
	    user = D.usern,
	    passwd=D.passw,
        database = D.dbname
    )
mycur=mydb.cursor()
sql = "SELECT PARK_ID FROM PARKING_SPACE WHERE LOT_ID = %s"
lot=(LotID, )
mycur.execute(sql, lot)
myresult = mycur.fetchall()


dirname = "ParkingBays_" + '{date:%Y-%m-%d %H:%M:%S}'.format(date=datetime.datetime.now())
#dirname = sys.argv[2]
#path_name = os.getcwd()

#os.chmod(/home/dione/ParkingSpaceAppImageCropper,0o777)
os.makedirs(dirname)


for bayID in myresult:
    polygon = IA.getPolygon(bayID[0])
    SortedPolygon = IA.polySort(polygon)
    croppedImage = IA.Crope(SortedPolygon, D.snapshot)
    Bayname = dirname + '/ParkingBay' + bayID[0] + ".jpeg"
    cv2.imwrite(Bayname, croppedImage)

shutil.make_archive(sys.argv[2], 'zip', dirname)
shutil.rmtree(dirname)
