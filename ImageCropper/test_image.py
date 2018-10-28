import ImageCropper as IA
import Classify as cl
from Networks.Auto_Park_Space_network_vgg_16 import auto_park_det_net
import cv2
import numpy as np
from keras.optimizers import SGD
import pytest

#Tests for isInside()
def test_1():               #Check isInside for true case
	polygon = [[0,0],[10,0],[10,10],[0,10]]
	point = [5,5]
	assert IA.isInside(polygon,point) == True

def test_2():               #Check is inside for false case
	polygon = [[0,0],[10,0],[10,10],[0,10]]
	point = [13,13]
	assert IA.isInside(polygon,point) == False

#Tests for polySort()
def test_3():                 #Check polySort method
	poly=[[13,2],[15,100],[100,100],[0,0]] 
	assert IA.polySort(poly)== [[0,0],[13,2],[100,100],[15,100]]

#Tests for onSegment()
def test_4():		      #Check with 3 non colinear points
	p=[2,2]
	q=[100,100]                   
	r=[1,1]
	assert IA.onSegment(p,q,r) == False    

def test_5():          #Colinear points with q lying inbetween pr 
	p=[5,5]
	q=[10,10]
	r=[12,12]
	assert IA.onSegment(p,q,r) == True 
	
def test_6():         #Colinear points with r not inbetween p and q
	p=[5,5]
	q=[10,10]
	r=[12,12]
	assert IA.onSegment(p,r,q) == False


#Tests for orientation()
def test_7():          #Colinear points
	p=[1,1] 
	q=[3,3]   
	r=[5,5]
	assert IA.orientation(p,q,r) == 0

def test_8():          #Clockwise rotation between points
	p=[1,1] 
	q=[3,5]   
	r=[5,5]
	assert IA.orientation(p,q,r) == 1
	
def test_9():          #Counter Clockwise rotation between points
	p=[1,1] 
	q=[5,3]   
	r=[5,5]
	assert IA.orientation(p,q,r) == 2	

#Tests for doesItIntersect()
def test_10():                         #Doesn't intersect
	p1=[3,3]
	p2=[13,2]
	q1=[19,100]
	q2=[25,3] 
	assert IA.doesItIntersect(p1,q1,p2,q2) == False 

def test_11():                        #Does intersect
	p1=[3,3]
	p2=[3,15]
	q1=[3,2]
	q2=[3,100]
	assert IA.doesItIntersect(p1,q1,p2,q2) == True

#Test for classify()

def test_12():
	polygon = [[25, 50], [100, 50], [100, 100], [50, 100]] 
	SortedPolygon = IA.polySort(polygon)
	snapshotname = 'snapshot.jpeg'
	croppedImage = IA.Crope(SortedPolygon, snapshotname)
	model = auto_park_det_net((100, 100, 3))
	model_name = 'comvo_1.h5'
	model.load_weights(model_name)
	isfull = cl.classifier(croppedImage, model)
	assert isfull=='vacant' 
