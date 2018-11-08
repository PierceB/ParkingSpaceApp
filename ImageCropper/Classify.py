import ImageCropper as IA
import math
from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
from keras.optimizers import SGD
import os
import mysql.connector
import Details as D



##################################################Classifier

# !/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Mar 10 00:25:14 2018

@author: julien
"""



def classifier(image,model):                 #Actual classifier
    # input image
    #image = cv2.imread(image)
    image_size = 100

    #model = auto_park_det_net((100, 100, 3))           #Just moved this into the classify method so it doesn't have to reload the model everytime
    #model_name = 'comvo_1.h5'
    #model.load_weights(model_name)

    # susbtract the dataset mean from each image
    image2 = image - [128.0141111]

    # hope you got this
    image3 = cv2.resize(image2, (image_size, image_size))

    # adding a new dimension to the image for the predict function that take an input of format (image_size,image_size,channel,number_of_images)
    # in this instance, we only have one image per pass, since we are predicting
    image3 = image3[np.newaxis, :, :, ]

    sgd = SGD(lr=0.0001, momentum=0.9, nesterov=True)

    model.compile(loss='categorical_crossentropy', optimizer=sgd, metrics=["accuracy"])

    cnn_train = model.predict(image3)
    # we chose the second index because it corresponds to how vacant the spot is.
    if cnn_train[0][0] > 0.65:
        return 'vacant'
    else:
        return 'occupied'


def classify(lot_ID):           #Fetches and does all preoprocessing

    model = auto_park_det_net((100, 100, 3))
    model_name = 'comvo_1.h5'
    model.load_weights(model_name)

    mydb=mysql.connector.connect(
   	    host = D.hostn,
	    user = D.usern,
	    passwd=D.passw,
        database = D.dbname
    )
    mycur=mydb.cursor()
    sql = "SELECT PARK_ID FROM PARKING_SPACE WHERE LOT_ID = %s"
    lot=(lot_ID, )
    mycur.execute(sql, lot)
    myresult = mycur.fetchall()
    for bayID in myresult:
        polygon = IA.getPolygon(bayID[0])
        SortedPolygon = IA.polySort(polygon)
        croppedImage = IA.Crope(SortedPolygon, D.snapshot)
        #cv2.imwrite('ParkingBay2.jpeg', croppedImage)

        isfull = classifier(croppedImage, model)

        if (isfull == 'occupied'):
            val = "1"
            sql = "UPDATE PARKING_SPACE SET PARK_IS_OPEN = '1' WHERE PARK_ID = %s"
            # Push val to database for parkingspot
        else:
            val = "0"
            sql = "UPDATE PARKING_SPACE SET PARK_IS_OPEN ='0' WHERE PARK_ID = %s"
            # Pus val to database for parkingspot

        #sql = "UPDATE PARKING_SPACE SET PARK_IS_OPEN = " + val + " WHERE PARK_ID = " + bayID[0]

        mycur.execute(sql,bayID)
        mydb.commit()

    mydb.close()
