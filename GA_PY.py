# -*- coding: utf-8 -*-
"""
Created on Tue Dec  6 09:54:16 2022

@author: Sergio
"""

import subprocess
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

archivo=open("C:\\Users\\sig_o\\Documents\\NetBeansProjects\\PKS\\mknap1.txt","r")
datos=archivo.readlines()
tipoEstudio=1

evaluaciones = range(1,31)
pc = [0,0.2,0.4,0.6,0.8,1]

if tipoEstudio==1:
    pm = [0,0.2,0.4,0.6,0.8,1]
else:
    pm = [0.2,0.3,0.4,0.5,0.6]


lineaInicio = 82
optimoReal = float(datos[lineaInicio-1].split()[2])

print(optimoReal)

fig = plt.figure(figsize=(5,5))
#Gráfica con los resultados del óptimo en función de las evaluaciones para cada valor de pc y de pm
gs = fig.add_gridspec(len(pc), len(pm), hspace=0, wspace=0)
ax = gs.subplots(sharex='col', sharey='row')

#fig1.suptitle("KnapSack Problem - Optimum: 4015")
fig.supxlabel('Ejecución', y=0.15)
if tipoEstudio==1:
    fig.supylabel('Óptimo', x=0.15)   
else:
    fig.supylabel('Evaluaciones', x=0.15)
# plt.title('Búsqueda de óptimo para diversos valores de probabilidades',
#              horizontalalignment = 'left',
#              x=0.05,
#              y=0.9,
#              transform=fig.transFigure,
#              fontsize=30,
#              fontweight="bold")

#Datos para calcular el máx, min, media, mediana, desviación estándar de todas las evaluaciones para cada valor de pc y de pm
datos=np.zeros((len(pc),len(pm),5))

a = len(pc)-1
for i in pc:
    b = 0
    for j in pm:
        optimo = []
        for k in evaluaciones:
            print(i,j,k)
            p=subprocess.run(["java", "-jar", "C:/Users/sig_o/Documents/NetBeansProjects/PKS/dist/PKS.jar", str(i), str(j), str(lineaInicio), str(tipoEstudio)],stdout=subprocess.PIPE,shell=True)
            lineas = p.stdout.decode("utf-8").splitlines()
            #print(lineas)
            if tipoEstudio==1:
                optimo.append(float(lineas[-1]))  
            else:
                optimo.append(int(lineas[-3].split()[-2]))         
        if len(pm)==1 and len(pc)==1:
            ax.plot(evaluaciones,optimo)
        else:             
            ax[a,b].plot(evaluaciones,optimo)
            if tipoEstudio==1:
                ax[a,b].plot(evaluaciones,optimoReal*np.ones(len(evaluaciones)), linestyle = 'dotted')
            else:
                ax[a,b].set_ylim(0,100000)
            ax[a,b].set_title("pc: "+str(i)+" , "+" pm "+str(j))
            
            #Almacena las estadísticas
            datos[a,b,0]=np.max(optimo)
            datos[a,b,1]=np.min(optimo)
            datos[a,b,2]=np.mean(optimo)
            datos[a,b,3]=np.median(optimo)
            datos[a,b,4]=np.std(optimo)
        
        b += 1
    a -= 1  
  
for ax in fig.get_axes():
    ax.label_outer()

fig.savefig("gráficas" + str(tipoEstudio) +str(lineaInicio)+ "mutacionUnBit"+ ".png")

def tablaDatos(datos, titulo):
    
    
    # Crear un rango de valores de 0 a 1 con un paso de 0.2
    #valores = [round(i,1) for i in list(np.arange(0,1.2,0.2))]
    
    # Crear un DataFrame vacío con los valores como índices y columnas
    tabla = pd.DataFrame(index=pc, columns=pm)
    # Llenar la tabla con los valores de la multiplicación de las filas por las columnas
    i=len(pc)-1
    for fila in tabla.index:
        j=0
        for columna in tabla.columns:
            tabla.loc[fila, columna] = round(datos[i,j],2)
            
            j+=1
        i-=1    
    fig = plt.figure()

    # Crear un heatmap con los valores de la tabla
    plt.pcolor(tabla.astype(float, errors = 'raise'),cmap='RdBu')
    #heatmap = plt.pcolor(tabla, cmap='RdBu')
    
    # Añadir los valores de la tabla en cada celda
    for fila in range(len(pc)):
        for columna in range(len(pm)):
            plt.text(columna+0.5, fila+0.5, str(tabla.iloc[fila, columna]), ha='center', va='center')
    
    # Añadir etiquetas a los ejes
    plt.xticks([i+0.5 for i in range(len(pm))], pm)
    plt.yticks([i+0.5 for i in range(len(pc))], pc)
    plt.xlabel('pm', y=0.075)
    plt.ylabel('pc', x=0.075)
    
    
    
    # Ajustar los límites de los ejes
    plt.xlim(0, len(pm))
    plt.ylim(0, len(pc))
    
    # Añadir un título
    # plt.title(titulo,
    #          loc="left",
    #          x=0.05,
    #          y=0.9,
    #          transform=fig.transFigure,
    #          fontsize=20,
    #          fontweight="regular")
    plt.savefig('tabla'+titulo+str(tipoEstudio)+str(lineaInicio)+ "mutacionUnBit"+'.png')
    
    # Guardar el heatmap en formato png
    #plt.savefig('tabla'+titulo+'.png' )



tablaDatos(datos[:,:,0],"Máximo")
tablaDatos(datos[:,:,1],"Mínimo")
tablaDatos(datos[:,:,2],"Media")
tablaDatos(datos[:,:,3],"Mediana")
tablaDatos(datos[:,:,4],"Desviación estándar")



    


