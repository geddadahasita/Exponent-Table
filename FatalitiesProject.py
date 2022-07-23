# -*- coding: utf-8 -*-
"""
Created on Thu Feb 27 23:29:08 2020

@author: 790372
"""
import numpy as np
import sklearn.model_selection as model_selection
from matplotlib import pyplot as plt
import matplotlib.animation as animation 
from sklearn.preprocessing import OneHotEncoder
from keras.datasets import mnist
from keras import models
from keras import layers
import numpy as np
from keras.utils import to_categorical
import matplotlib.pyplot as plt


def standardize(X): 
    X = X.astype(float)
    mean = np.mean(X, axis = 0)
    std = np.std(X, axis = 0)
    xStd = (X - mean)/std    
    return xStd       
   
def createData():

    fileName = 'FatalitiesProject.csv'
    print("fileName: ", fileName)
    raw_data = open(fileName, 'rt')
    data = np.loadtxt(raw_data, dtype = np.str, usecols = (1, 2, 3, 4, 5, 6, 7, 8), skiprows = 1, delimiter=",")

    X = data[:, 0:7]
    Y = data[:, 7]
  
    data[:, 1][data[:, 1] == "1982"] = 1
    data[:, 1][data[:, 1] == "1983"] = 2
    data[:, 1][data[:, 1] == "1984"] = 3
    data[:, 1][data[:, 1] == "1985"] = 4
    data[:, 1][data[:, 1] == "1986"] = 5
    data[:, 1][data[:, 1] == "1987"] = 6
    
    
    data[:, 1][data[:, 2] == "no"] = 0
    data[:, 1][data[:, 2] == "yes"] = 1
    
    data[:, 1][data[:, 3] == "no"] = 0
    data[:, 1][data[:, 3] == "yes"] = 1
    
    categorical_data = data[:, 0:3]
    ohe = OneHotEncoder(sparse = False)
    categorical_data = ohe.fit_transform(categorical_data)
    
    
    
# =============================================================================
# standardize 
# =============================================================================
    
    non_categorical = data[:, 3:8]
    xStd = standardize(non_categorical)               

    X = np.column_stack((categorical_data, xStd))

   

    x_train, x_test, y_train, y_test = model_selection.train_test_split(X, Y, 
                    train_size=0.75,test_size=0.25, random_state=101)
    return x_train, x_test, y_train, y_test 



train_data, test_data, train_targets, test_targets = createData()


model = models.Sequential()
model.add(layers.Dense(64, activation='relu', input_shape = (len(train_data[0]),)))
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(1))
model.compile(optimizer='rmsprop', loss='mse', metrics=['mae'])
model.get_weights()

W = model.get_weights()



# =============================================================================
# PROCESS DATA 
# =============================================================================
    
history = model.fit(train_data, train_targets, epochs=100, batch_size=50)


# =============================================================================
# EVALUATE DATA 
# =============================================================================
mae_history = history.history['mean_absolute_error']        

test_mse_score, test_mae_score = model.evaluate(test_data, test_targets)

predicted_deaths = model.predict(test_data)
# =============================================================================
# Plotting 
# =============================================================================
fig, ax = plt.subplots()
ax.plot(range(100), mae_history, 'bo', label='MAE')
# b is for "solid blue line"
#ax.plot(test_data, test_targets, 'b', label='Validation loss')
ax.set(xlabel='MAE', ylabel='Loss',
       title='Training and validation loss');
ax.legend()

plt.show()












