import ImageCropper as IA
import pytest

def test_1():
	polygon = [[0,0],[10,0],[10,10],[0,10]]
	point = [5,5]
	assert IA.isInside(polygon,point) == True
