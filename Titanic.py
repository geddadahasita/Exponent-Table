# -*- coding: utf-8 -*-
"""
Created on Wed Nov 20 14:52:48 2019

@author: 790372
"""
import numpy as np
from matplotlib import pyplot as plt
import matplotlib.animation as animation 


def activatePred(W, X):
    z = np.dot(X, W)
    eVal = np.power(np.e, -1 * z)
    pred = 1 / (1 + eVal)
    return pred
 
     
def changeWeights(xRow, weights, lr, actual, pred):
    for i in range(len(xRow)):
        weights[i] = weights[i] + lr*(actual - pred)*xRow[i]
     
def activateCost(W, X, Y):
    pred = activatePred(W, X)
    costArray = -1*((Y * np.log(pred)) + ((1 - Y) * (np.log(pred))))
    cost = np.sum(costArray)
    return cost / len(Y)

def calcGradient(X, Y, W):
# =============================================================================
#     s = 0.0
#     gradientList=[]
#     for w in range(len(W)):
#         s=0.0
#         for i in range(len(X)):
#             s += (predict(X[i], W) - Y[i]) *  X[i][w]
#             
#         gradientList.append(s/len(X))
#     return np.array(gradientList)
# =============================================================================
    p = activatePred(W, X)
    p_y = p - Y
    return np.dot(p_y, X)/len(X)


def createData(fileName): 
    data = np.loadtxt(fileName, dtype = np.str, delimiter = ",", skiprows = 1, usecols = (2, 5, 6, 1))
 
    
    data[:, 1][data[:, 1] == "female"] = 1
    data[:, 1][data[:, 1] == "male"] = 0
    data[:, 2][data[:, 2] == ""] = np.nan

    data = data.astype(np.float)
    mean = np.nanmean(data, axis = 0)
    data[np.isnan(data)] = mean[2]
    
    
    
    X = data[:, 0:3] 
    Y = data[:, 3]
    
    
    maxX = np.max(X, axis = 0)
    minX = np.min(X, axis = 0)
    normX = (X - minX)/(maxX - minX)
  
    
    onesMat = np.ones(len(X))
    outX = np.column_stack((onesMat, normX))
    return outX, Y, normX, mean, minX, maxX

def testData(filename, minX, maxX):
    data = np.loadtxt(filename, dtype = np.str, delimiter = ",", skiprows = 1, usecols = (1, 4, 5))
 
    
    data[:, 1][data[:, 1] == "female"] = 1
    data[:, 1][data[:, 1] == "male"] = 0
    data[:, 2][data[:, 2] == ""] = np.nan

    data = data.astype(np.float)
    mean = np.nanmean(data, axis = 0)
    data[np.isnan(data)] = mean[2]
    
    
    
    X = data[:, 0:3] 

    normX = (X - minX)/(maxX - minX)
  
    
    onesMat = np.ones(len(X))
    outX = np.column_stack((onesMat, normX))
    return outX
    
def solveMat(X, Y):
    mat1 = np.dot(Y, np.transpose(X))
    inv = np.linalg.inv(np.dot(X, np.transpose(X)))
    return np.dot(mat1, inv)   


##################### functions end here ##########################

X, Y, std, mean, minX, maxX = createData("TitanicKaggletrain.csv")
Xtest = testData("TitanicKaggletest.csv", minX, maxX)
W = np.array(np.zeros(len(X[0])))

#print(calcCost(X, W, Y))


numRows = len(X)
numCols = len(Y)
lr = .2

meanSquareError = activateCost(W, X, Y)
print('Initial Cost: ', activateCost(W, X, Y))
finished = False
count = 0    
while not finished:
    gradientList = calcGradient(X, Y, W)
    count += 1
    
    W  = W - lr*gradientList
    if(np.linalg.norm(gradientList) < 0.000001 or count > 100000):
        finished = True
        print(activateCost(W, X, Y))


print('Final Cost: ', activateCost(W, X, Y))

pref = activatePred(W, Xtest)

pref[:][pref[:] >= 0.5] = 1
pref[:][pref[:] < 0.5] = 0


print(pref)

np.savetxt('TitanicPred.csv', pref, fmt = '%u', delimiter = ',')

#0.693... 
#Xtest, Ytest = createData2("Livestock_expenses_Test.csv", std, mean, W)
#print('Weights', W)
#print('dot product')
#print(solveMat(X.T, Y))
#predict2 = np.dot(Xtest, W)
#cost = predict2 - Ytest
#print('Predicted: ', predict2)
#print('Cost: ', cost)    
# =============================================================================
# numRows = len(X)
# numCols = len(X[0])
# 
# lr = 0.1
# W = np.array([1.0,1.5]).astype(float)
#     
# calcGradient(X, Y, W)
# 
# finished = False
# while not finished:
#     gradientList = calcGradient(X, Y, W)
# =============================================================================
#I'll be there for you I'll be there I'll be there 
# =============================================================================
#     cwList = []
#     changeWeights(xRow, weights, lr, actual, pred):
# =============================================================================

# =============================================================================
#     W  = W - lr*gradientList
#     print(W)
#     if(np.linalg.norm(gradientList) < 0.01):
#         finished = True 
# =============================================================================


    
#Plotting 
        
# =============================================================================
# fig = plt.figure()
# ax = fig.add_axes([0.1, 0.1, 0.8, 0.8])
# ax.set_title('Weights')
# ax.set_xlabel('x')
# ax.set_ylabel('y')
# ax.scatter(W[:, 1], costList)
# =============================================================================

# =============================================================================
# ##fix! 
# mean_squared_error = calcCost(X, W, Y)
# gradient = []
# valueList = []
# #[0.875, 2.625]

