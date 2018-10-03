import ImageCropper as IA
import math
from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
from keras.optimizers import SGD
import os



#image_size = 100
#model = auto_park_det_net((100, 100, 3))
#model_name = 'comvo_1.h5'
#model.load_weights(model_name)

baylist = [0]  # Placeholder
for bayID in baylist:
    polygon = IA.getPolygon(bayID)
    SortedPolygon = IA.polySort(polygon)
    snapshotname = 'snapshot.jpeg'
    croppedImage = IA.Crope(SortedPolygon, snapshotname)
    #cv2.imwrite('ParkingBay2.jpeg', croppedImage)

 #   isfull = IA.classify(croppedImage)

 #   if (isfull == 'occupied'):
 #       val = True
        # Push val to database for parkingspot
 #   else:
 #       val = False
        # Pus val to database for parkingspot
