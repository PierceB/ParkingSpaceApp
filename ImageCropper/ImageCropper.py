import cv2
import numpy as np



def Crop(imageSnapshot, x1,x2,x3,x4):
    # Read a image
    I = cv2.imread('snapshot.jpeg')   #Input the pictures name to whatever is decided to be called 
    
    # Define the polygon coordinates to use or the crop
    polygon = [[x1,x2,x3,x4]]            #These would be the 4 points to crop. This method works for more points if you want to do a convex hull
    
    
    # Finding the outer points of the polygon
    minX = I.shape[1]            #Get rows and columns of the picture and set the min to the boundry (largest possible value)
    maxX = -1
    minY = I.shape[0]
    maxY = -1
    
    for point in polygon[0]:           #Cycle through the points give to find the smallest/largest to use as boundries
    
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
    
    
    cropedImage = np.zeros_like(I)      #create an array of same size/type as the picture but with all values as 0
    for y in range(0,I.shape[0]):
        for x in range(0, I.shape[1]): #Iterate through all rows and columns
    
            if x < minX or x > maxX or y < minY or y > maxY:      
                continue                                            #Check if point is inside boundry, if not go to next iteration of loop
    
            if cv2.pointPolygonTest(np.asarray(polygon),(x,y),False) >= 0: #Check if point is in polygon, if it is then assign the pixel data from the original image to the cropped image array
                cropedImage[y, x, 0] = I[y, x, 0]
                cropedImage[y, x, 1] = I[y, x, 1]
                cropedImage[y, x, 2] = I[y, x, 2]

    # Now we can crop again just the envloping rectangle
    finalImage = cropedImage[minY:maxY,minX:maxX]
    
    cv2.imwrite('ParkingBay2.jpeg',finalImage)

x1=[0,130]                     #Set 4 coordinates you want to crop here as x1,x2,x3,x4 
x2=[130,0]
x3=[0,0]
x4=[130,130]
Crop('snapshot.jpeg', x1, x2, x3, x4)
