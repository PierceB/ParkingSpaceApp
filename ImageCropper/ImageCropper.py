import math
from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
from keras.optimizers import SGD
import os


def polySort(
        polygon):  # Pretty dirty method to make sure we don't get crossing lines, wont always work but will work for non extreme cases
    minpoint = polygon[0]

    for point in polygon:
        if point[0] < minpoint[0]:
            minpoint = point

    x1 = minpoint
    polygon.remove(x1)

    minpoint = polygon[0]
    for point in polygon:
        if point[0] < minpoint[0]:
            minpoint = point

    x2 = minpoint
    polygon.remove(x2)

    x3 = polygon[0]
    x4 = polygon[1]

    if ((x3[0] + x3[1]) < (x4[0] + x4[1])):
        newpoly = [x1, x2, x4, x3]
    else:
        newpoly = [x1, x2, x3, x4]

    return (newpoly)


############################################Cropper helper methods

def onSegment(p, q, r):  # Given three colinear points, check if Q lies on the line pr
    if ((q[0] <= max(p[0], r[1])) and (q[0] >= min(p[0], r[0])) and (q[1] <= max(p[1], r[1])) and (
            q[1] >= min(q[1], r[1]))):
        return (True)
    return (False)


def orientation(p, q, r):  # Check orientation of the triplet: 0= colinear, 1=clockwise, 2=counterclockwise
    orientationVal = (q[1] - p[1]) * (r[0] - q[0]) - (q[0] - p[0]) * (r[1] - q[1])

    if (orientationVal == 0):
        return (0)
    if (orientationVal > 0):
        return (1)
    else:
        return (2)


def doesItIntersect(p1, q1, p2, q2):
    # Used o check the orientations for special/general cases

    o1 = orientation(p1, q1, p2)
    o2 = orientation(p1, q1, q2)
    o3 = orientation(p2, q2, p1)
    o4 = orientation(p2, q2, q1)

    # General case

    if (o1 != o2 and o3 != o4):
        return (True)

    if (o1 == 0 and onSegment(p1, p2, q1)):
        return (True)

    if (o2 == 0 and onSegment(p1, q2, q1)):
        return (True)

    if (o3 == 0 and onSegment(p2, p1, q2)):
        return (True)

    if (o4 == 0 and onSegment(p2, q1, q2)):
        return (True)

    return (False)


def isInside(polygon, p):
    extreme = [0, p[1]]  # create a point for line segment

    i = 0
    count = 0

    while (1):

        next = (i + 1) % 4

        if (doesItIntersect(polygon[i], polygon[next], p, extreme)):
            if (orientation(polygon[i], p, polygon[next]) == 0):
                return (onSegment(polygon[i], p, polygon[next]))

            count = count + 1;
        i = next
        if (i == 0):
            break
    if (count % 2 == 1):
        return (True)

    return (False)


#########################################Cropper
def Crope(polygon, snapshotname):
    minX = float('inf')  # Find the Biggest x and biggest y, and smallest x and smallest y
    maxX = -1
    minY = float('inf')
    maxY = -1

    for point in polygon:  # Cycle through the points give to find the smallest/largest to use as boundries

        x = point[0]
        y = point[1]

        if x < minX:
            minX = x
        if x > maxX:
            maxX = x
        if y < minY:
            minY = y
        if y > maxY:
            maxY = y

    ImageMatrix = cv2.imread(snapshotname)  # Input the pictures name to whatever is decided to be called
    cropedImage = np.zeros_like(ImageMatrix)

    for x in range(minX, maxX):
        for y in range(minY, maxY):
            if x < minX or x > maxX or y < minY or y > maxY:
                continue  # Check if point is inside boundry, if not go to next iteration of loop

            temp = [x, y]

            if (isInside(polygon, temp)):
                cropedImage[x, y, 0] = ImageMatrix[x, y, 0]
                cropedImage[x, y, 1] = ImageMatrix[x, y, 1]
                cropedImage[x, y, 2] = ImageMatrix[x, y, 2]

    return (cropedImage)
    # cv2.imwrite('ParkingBay2.jpeg',cropedImage) #Use if you want to draw image


##################################################Classifier

# !/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Mar 10 00:25:14 2018

@author: julien
"""



def classify(image):
    # input image
    #image = cv2.imread(image)
    image_size = 100
    model = auto_park_det_net((100, 100, 3))
    model_name = 'comvo_1.h5'
    model.load_weights(model_name)
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


def getPolygon(ParkingBayID):
    # Method to get coordinates from the database server
    # STILL TODO:
    # polygon= [4 coordinates]
    polygon = [[25, 50], [100, 50], [100, 100], [50, 100]]
    return (polygon)


