import math
#from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
#from keras.optimizers import SGD
import os
import mysql.connector

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
    if (x3[1]!=x4[1]):
        if (x3[1]) < (x4[1]):
            newpoly = [x1, x2, x4, x3]
        else:
            newpoly = [x1, x2, x3, x4]

    else:
        if (x3[0]) < (x4[0]):
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


def getPolygon(ParkingBayID):
    # Method to get coordinates from the database server
    # STILL TODO:
    # polygon= [4 coordinates]
    if(ParkingBayID =="-1"):         #Just for testing purposes
        polygon = [[25, 50], [100, 50], [100, 100], [50, 100]]
        return (polygon)

    mydb=mysql.connector.connect(
    	host = "localhost",
	user = "connect",
	passwd="connectpw",
    database = "PARKINGAPPDB"
    )
    mycursor=mydb.cursor()
    sql= "SELECT * FROM PARKING_SPACE WHERE PARK_ID = %s"
    adr=(ParkingBayID, )

    mycursor.execute(sql,adr)
    mydb.close()

    for x in mycursor:
        x1=x[2].split(',')
        x2=x[3].split(',')
        x3=x[4].split(',')
        x4=x[5].split(',')

    polygon = [[int(x1[0]) ,int(x1[1])], [int(x2[0]) ,int(x2[1])],[int(x3[0]) ,int(x3[1])],[int(x4[0]) ,int(x4[1])]]
    return (polygon)




