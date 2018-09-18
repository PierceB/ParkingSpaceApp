#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Mar 10 00:25:14 2018

@author: julien
"""

from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
from keras.optimizers import SGD
import os
image_size = 100

model = auto_park_det_net((100,100,3))

model_name = 'comvo_1.h5'
model.load_weights(model_name)

def classify(image):

    #input image 
    image = cv2.imread(image)
    
    #susbtract the dataset mean from each image
    image2 = image - [128.0141111]
    
    # hope you got this
    image3 = cv2.resize(image2,(image_size,image_size))    
    
    #adding a new dimension to the image for the predict function that take an input of format (image_size,image_size,channel,number_of_images)
    # in this instance, we only have one image per pass, since we are predicting
    image3 = image3[np.newaxis,:,:,]    

    sgd = SGD(lr=0.0001,momentum=0.9,nesterov=True)
    
    model.compile(loss='categorical_crossentropy', optimizer=sgd,metrics=["accuracy"])
    
    cnn_train = model.predict(image3)
    # we chose the second index because it corresponds to how vacant the spot is.
    if cnn_train[0][0] > 0.65:
        return 'vacant'
    else:
        return 'occupied'
print "classification ..."

path = 'sample'

#list the directories
files = os.listdir(path)

total_number_of_images = 0
good_classification = 0

# i will be either occupied or vacant
for i in files:
    # j will be the image itself
    for j in os.listdir((os.path.join(path,i))):
        image = os.path.join(path,i,j)
        decision = classify(image)
        #print "decision for image ",i,"is :",decision
        if decision == i:
            # in python 2, please do not forget to put the decimal dot
            good_classification+=1.0
        total_number_of_images +=1.0

performance = ((good_classification)/total_number_of_images) * 100

print "Goodness of the proposed classifier is: ", performance,"%"

