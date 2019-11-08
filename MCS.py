# -*- coding: utf-8 -*-
"""
Created on Fri Oct 25 21:12:06 2019

@author: sunge
"""
import numpy as np
import pandas as pd
#--------------------------------Part 1: Wind generator------------------------
# Altitude should be provided in a sorted ***np array***
#inputw[0]: wind dirction average & inputw[1]: wind dirction stdv
#inputw[2]: wind velocity average & inputw[3]: wind velocity stdv
'''
@input:
    wNormal: 
                generates normal distributed data when true
                otherwise chooses data according to probability
    Inputw:     
        When wNormal is TRUE:
                inputw[0]: wind dirction average & inputw[1]: wind dirction stdv
                inputw[2]: wind velocity average & inputw[3]: wind velocity stdv
        Otherwise: 2D np array
                inputw[0]: a np array of possibale data points
                inputw[1]: a np array of probabilities of the above data points
                inputw[0]&[1] mush have the same length
    wind_altitude:
                
'''
def wind_data_generator(wind_altitude,inputw,wNormal = True):
    wind_direction = np.zeros(wind_altitude.shape[1])
    wind_velocity = np.zeros(wind_altitude.shape[1])
    if wNormal:     
        wind_direction = np.random.normal(inputw[0],inputw[1],wind_altitude.shape[1] ).reshape(1,5)#.round(2)
        wind_velocity = np.random.normal(inputw[2],inputw[3],wind_altitude.shape[1]).reshape(1,5)#.round(2)
    else:
        wind_direction = np.random.choice(inputw[0],wind_altitude.shape[1] ,p=inputw[1]).reshape(1,5)
        wind_velocity = np.random.choice(inputw[2],wind_altitude.shape[1] ,p=inputw[3]).reshape(1,5)

    wind_data = np.concatenate((wind_altitude,wind_direction,wind_velocity), axis = 0)
    wind_df = pd.DataFrame(wind_data.T,
                           columns =['altidude','dirction','velocity'])

    return wind_df
#-------------------------------Part 2: Monte Carlo----------------------------
'''
aNormal:
    same as wNormal explained
num_cycles: 
    number of cycles of running the simulator
inputa: 
    same as inputw
'''
def MCS(inputa,wind_altitude,inputw,aNormal = True ,wNormal= True,num_cycles = 3 ):
    angles = []
    if aNormal:
        angles = np.random.normal(inputa[0],inputa[1],num_cycles)
    else:
        angles = np.random.choice(inputa[0],num_cycles, p=inputa[1])
    
    for angle in angles:
          mock_wind = wind_data_generator(wind_altitude,inputw,wNormal)
          print(angle,"\n")
          print(mock_wind)


#Fake
def simulator(a,b):
    a=a+b


#----------------------------Part 3: Sample Code-------------------------------
'''
VERY IMPORTANT: Notice the dimension!
'''
w_al = np.array([80,60,40,20,0])
inputw_c = np.array([[12,7,8],
                     [0.2,0.3,0.5],
                     [122,72,82],
                     [0.1,0.2,0.7]])
inputa_c = np.array([[90,89,91],[0.2,0.3,0.5]])  
inputw_n = np.array([4,3,7,10])
inputa_n = np.array([1,3,4,7])

# T&T: both normal
MCS(inputa_n,w_al,inputw_n)
# T&F: normal angle
MCS(inputa_n,w_al,inputw_c,wNormal = False)
# F&T: normal wind
MCS(inputa_c,w_al,inputw_n,aNormal = False)
# F&F: both false
MCS(inputa_c,w_al,inputw_c,aNormal = False,wNormal = False)






#inner logic--------------------------------don't care----------debugging
d = np.array([[1,2,3,4,5],
             [5,4,3,2,1]])
w_al.shape[1]
wind_data = np.concatenate((w_al,d),axis = 0)
wind_df = pd.DataFrame(wind_data.T,
                           columns =['altidude','dirction','velocity'])
angles = np.random.normal(inputw_n[0],inputw_n[1],5).reshape(1,5)
a = inputw_c[0]
b = inputw_c[1]
x = np.random.choice(a,5,p=b)




#---------------------------------First Version--------------------------------
'''
def MCS(option,num_cycles,angle_input1,angle_input2,wind_input1,wind_input2):
    
    angle_target = []
    wind_target = []
    
    if(option == "BOTH"):
        #Add checking to other options
        if(type(angle_input1) != float and type(angle_input2) != float):
            print("Inputs must be floats!")
            return 
        angle_target = generate_angle(angle_input1,angle_input2, num_cycles)
        wind_target = generate_wind(wind_input1,wind_input2, num_cycles)
    elif(option == "ANGLE"):
        
# Option 2 : Specify certain angles as input
# Generate normally valuesributed wind data
#------------------------------------------------------------------------------                         
        #angle_target contains angle_values, angle_prob is the probability
        angle_target = generate_angle(angle_input1,angle_input2, num_cycles)
        wind_target = normal_wind(wind_input1,wind_input2, num_cycles)
    elif(option == "WIND"):
# Option 3 : Specify certain wind data as input
# Generate normally distributed wind data
#------------------------------------------------------------------------------                                          
        #!!!Currently wind is assumed to be 1D array                 
        angle_target = normal_angle(angle_input1,angle_input2, num_cycles)
        wind_target = generate_wind(wind_input1,wind_input2, num_cycles)      
    else:
# Option default : Generate normally distributed random variables as input
#------------------------------------------------------------------------------     
        
        angle_target = normal_angle(angle_input1,angle_input2, num_cycles)  
        wind_target = normal_wind(wind_input1,wind_input2, num_cycles)
    
    for x in range(num_cycles):
        simulator(angle_target,wind_target)     
    
#------------------------------------------------------------------------------
'''


        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        