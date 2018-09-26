#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Sat Oct  7 21:33:46 2017

@author: julien
"""

from keras.applications.vgg16 import VGG16
from keras.layers.core import Dense,Dropout ,Flatten
from keras.models import Model

def auto_park_det_net(input_shape):
    model = VGG16(weights='imagenet',include_top=False,classes=2,input_shape=input_shape)
    x = model.get_layer('block5_pool').output
    x = Flatten(name='Flatten')(x)
    x = Dense(4608, activation='relu', name='fc1')(x)
    x = Dropout(0.5)(x)
    x = Dense(2304, activation='relu', name='fc2')(x)
    x = Dropout(0.5)(x)
    x = Dense(1152, activation='relu', name='fc3')(x)
    x = Dropout(0.5)(x)
    predictions = Dense(2, activation='softmax')(x)
    model = Model(inputs=model.input, outputs=predictions)
    return model


