import cv2
import numpy as np
import math

#It currently needs the polygon to be ordered from top left-> top right -> bottom right -> bottom left
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


def Crope(polygon, snapshotname):
    minX = math.inf  # Find the Biggest x and biggest y, and smallest x and smallest y
    maxX = -1
    minY = math.inf
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

    cv2.imwrite('ParkingBay1.jpeg', cropedImage)



polygon = [[25, 50], [100, 50], [100, 100], [50, 100]]
snapshotname = 'snapshot.jpeg'
Crope(polygon, snapshotname)

