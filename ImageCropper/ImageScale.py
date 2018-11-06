import cv2
import Details as D

def strong_imageScale(image,width,shouldPrint): #This function scales the picture to a width of "width" and then scales the height to keep the aspect ratio
    h, w = image.shape[:2]

    scaleFactor= w/width
    resized_image = cv2.resize(image, (width, int(h/scaleFactor)))
    if shouldPrint==1:
        cv2.imwrite('resized.jpeg', resized_image)
    return resized_image


def weak_imageScale(image, width, height, shouldPrint):

    resized_image = cv2.resize(image, (width, height))
    if shouldPrint == 1:
        cv2.imwrite('resized.jpeg', resized_image)
    return resized_image


#testimage = cv2.imread("snapshot.jpeg")
#resize = weak_imageScale(testimage,600,450,1)
