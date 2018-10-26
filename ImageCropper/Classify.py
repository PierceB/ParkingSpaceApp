import ImageCropper as IA
import math
from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
from keras.optimizers import SGD
import os
import mysql.connector


def classify(lot_ID):

    mydb=mysql.connector.connect(
   	    host = "localhost",
	    user = "connect",
	    passwd="connectpw",
        database = "PARKINGAPPDB"
    )
    mycur=mydb.cursor()
    sql = "SELECT PARK_ID FROM PARKING_SPACE WHERE LOT_ID = %s"
    lot=(lot_ID, )
    mycur.execute(sql, lot)
    myresult = mycur.fetchall()
    for bayID in myresult:
        polygon = IA.getPolygon(bayID[0])
        SortedPolygon = IA.polySort(polygon)
        snapshotname = 'snapshot.jpeg'
        croppedImage = IA.Crope(SortedPolygon, snapshotname)
        #cv2.imwrite('ParkingBay2.jpeg', croppedImage)

        isfull = IA.classify(croppedImage)

        if (isfull == 'occupied'):
            val = "1"
            # Push val to database for parkingspot
        else:
            val = "0"
            # Pus val to database for parkingspot

        sql = "UPDATE PARKING_SPACE SET PARK_IS_OPEN = " + val + " WHERE PARK_ID = " + bayID[0]

        mycur.execute(sql)
        mydb.commit()

    mydb.close()

classify("1")