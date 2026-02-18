**Proyecto MySmartGrid**

**Versión 1 \- Inclusión de threads**

**Objetivo:** 

Introducir concurrencia creando threads.

**Qué hay que hacer**: 

Crear un thread por cada consumo a tramitar. Si tengo 100 consumos lanzaré 100 threads. 

Lo hacemos creando una nueva clase que implemente Runnable y haga el trabajo de invocar *tramitarConsumo* sobre la zona energética correspondiente. Fíjate en el ejemplo del Banco. Tendrás que hacer algo similar a lo que hicimos con la clase *Auditor* o la clase *Transfer*.

**A tener en cuenta**: 

Observa el resultado de las auditorías con varios ejemplos diferentes de ficheros de Consumo. En algún caso observarás que no salen bien (diferentes ejecuciones del programa con el mismo fichero de entrada pueden dar resultados diferentes). Debes entender bien por qué es así. El ejemplo del Banco debe servirte de guía.

**Versión 2 \- Solución de Exclusión  Mutua**

**Objetivo:**

Solucionar los problemas de exclusión mutua que se plantean después de hacer la Versión 1\.

**Qué hay que hacer:**

* Identifica los diferentes sitios donde puede haber un problema de exclusión mutua y usa **synchronized** apropiadamente.  
* Comprueba que ahora todo va bien y siempre sale el mismo resultado.

# **Versión 3 — Condiciones de sincronización y coordinación entre hilos**

### **Objetivos de la versión**

En esta versión se profundiza en el estudio de la **sincronización entre hilos**, abordando situaciones en las que la exclusión mutua no es suficiente y es necesario **coordinar explícitamente cuándo un hilo puede continuar su ejecución**.

El sistema evoluciona hacia un modelo **productor–consumidor**, en el que distintos tipos de hilos colaboran para tramitar los consumos energéticos de forma correcta, ordenada y eficiente.

Los principales objetivos son:

* Introducir condiciones de sincronización.  
* Coordinar hilos con responsabilidades distintas.  
* Bloquear y desbloquear hilos en función del estado del sistema.  
* Evitar esperas activas.  
* Diferenciar entre exclusión mutua y condición de sincronización.

### **Contexto**

En versiones anteriores, los consumos se tramitaban de forma concurrente, pero **no existía una relación explícita entre el hilo que generaba un consumo y el hilo que lo tramitaba** (de hecho eran el mismo  hilo).

En esta versión, los consumos pasan a gestionarse mediante un **sistema centralizado**, que actúa como punto de encuentro entre:

* hilos que generan consumos,  
* hilos que los tramitan.

Además, el sistema debe ser capaz de **reaccionar ante la falta de recursos energéticos (batería)**, activando procesos concurrentes destinados a recargar la energía necesaria para continuar la tramitación.

### **Tareas a realizar**

#### **1\. Operarios de red concurrentes por zona**

Para cada zona energética deben existir **varios hilos operarios de red** encargados de tramitar los consumos.

* El número de operarios por zona debe ser configurable (fichero Config)  
* Los operarios trabajan de forma concurrente e independiente.  
* Los consumos se depositarán en el Centro de Control.  
* Cada operario espera hasta que haya un consumo disponible.  
* Cuando esté disponible, lo coge y lo procesa.

#### **2\. Hilos productores de consumos**

Cada consumo es gestionado por un **hilo productor** (los que teníamos hasta ahora) que:

* deposita el consumo en el sistema,  
* queda bloqueado hasta que dicho consumo ha sido completamente tramitado,  
* solo continúa su ejecución cuando recibe la notificación correspondiente.

#### **3\. Gestión centralizada de consumos**

Debe existir un mecanismo central por cada zona energética (CentroControl) que:

* almacene consumos pendientes,  
* permita que los operarios los recojan cuando estén disponibles,  
* bloquee a los operarios cuando no haya consumos.

La coordinación debe realizarse mediante **condiciones de sincronización** (wait, notify, notifyAll), no mediante esperas activas.

#### **4\. Bloqueo selectivo por consumo**

El hilo productor debe bloquearse **únicamente esperando a su propio consumo**, y no sobre un objeto global. 

* Cada consumo mantiene su propio estado.  
* El productor espera a que ese estado indique que el consumo ha finalizado.  
* Al finalizar la tramitación, se desbloquea exclusivamente al productor correspondiente.  
* Considera la creación de una nueva clase que encapsule un Consumo y ofrezca un método para que el hilo que deposita el consumo se bloquee hasta que el consumo esté procesado. Obviamente debe ofrecer otro método para desbloquear al hilo que está esperando por el procesado del consumo.

#### **5\. Recarga concurrente de energía. Operarios de carga.**

Durante la tramitación de un consumo puede ocurrir que **no exista energía suficiente** para satisfacerlo (hasta ahora simplemente no se atendía). 

En ese caso:

* el sistema debe recargar la energía de la zona avisando a los operarios de carga que serán quienes lo hagan,  
* estos operarios de carga son hilos que trabajan de forma independiente a los operarios de red,  
* una vez recargada la energía, el consumo puede continuar su tramitación.

Este comportamiento introduce un **segundo nivel de sincronización**, ahora sobre los recursos energéticos (batería).

#### **6\. Finalización correcta del sistema**

El sistema debe garantizar que:

* todos los consumos han sido tramitados,  
* todos los hilos productores han finalizado,  
* solo entonces debe mostrarse la auditoría final, que debe ser siempre la misma ante el mismo fichero de entrada.