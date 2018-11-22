import cv2
import Details as D
import os

def strong_imageScale(image,width,shouldPrint):
    #This function scales the picture to a width of "width" and then scales the height to keep the aspect ratio. if shouldPrint=1 it will print the picture
    h, w = image.shape[:2]

    scaleFactor= w/width
    resized_image = cv2.resize(image, (width, int(h/scaleFactor)))
    if shouldPrint==1:
        cv2.imwrite('resized.jpeg', resized_image)
    return resized_image


def weak_imageScale(image, width, height, shouldPrint,name):
    #This function scales a picture to a fixed width/height, But it doesn't maintain aspect ratio. If shouldPrint=1 it will print the picture
    resized_image = cv2.resize(image, (width, height))
    if shouldPrint == 1:
        cv2.imwrite(name, resized_image)
    return resized_image

pathname=os.getcwd()
testimage = cv2.imread(os.path.join(pathname,"WitsImages","uswits_msl04.png"))
resize = weak_imageScale(testimage,600,450,1,os.path.join(pathname,"WitsImages","wits_msl04.png"))
