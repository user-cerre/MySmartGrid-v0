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

**Versión 4 — Streams**

**En el programa principal MySmartGrid, después de las auditorías:**

1) Imprimir todos los consumos que demanden menos de 5 kwh. Tu salida debería ser la siguiente (si consideramos consumos5.bin). Debes hacerlo con un stream paralelo.

   Consumos con kwh \< 5 para consumos5.bin

   VEH\_Z1\_3

   DOM\_Z3\_13

   IND\_Z0\_16

   DOM\_Z4\_19 

   IND\_Z0\_28

   IND\_Z0\_35 

   VEH\_Z0\_45

2) Imprimir en pantalla el importe del consumo más alto. Debería aparecer: 26.0 (considerando consumos5.bin). Hazlo con streams paralelos.  
3) Imprimir después de las Auditorías el mensaje “Encontrado” si hay un pedido cuya dirección es “Sagitario, 24” (no hay ninguno). Prueba ahora con “Berna, 11” (sí hay)  
4) Ahora vamos a hacer las Auditorías de las zonas energéticas usando un stream paralelo. Eliminamos la forma en la que lo hacíamos antes. La auditoría de la red energética no hace falta hacerla con streams. Debes modificar el método imprimeAuditoría() de la clase RedEnergetica. Ten en cuenta que ahora las auditorías de las zonas no tienen porqué salir en orden, como hasta ahora, pero sí deben arrojar el mismo resultado.

# **Versión 5 — Monitores**

## **Objetivos de la versión**

En esta versión se introduce el uso de **monitores explícitos** para gestionar recursos compartidos con capacidad limitada y resolver problemas de sincronización de tipo **productor–consumidor**.

El sistema se amplía incorporando **energías renovables** (solar y eólica), producidas de forma concurrente por hilos independientes, y consumidas en cantidades variables durante la tramitación de los consumos energéticos.

Los principales objetivos de esta versión son:

* Diseñar monitores explícitos usando mecanismos avanzados de sincronización.  
* Gestionar recursos compartidos con capacidad limitada.  
* Coordinar productores y consumidores sin esperas activas.  
* Consumir simultáneamente múltiples recursos de forma consistente.  
  Integrar nuevos comportamientos concurrentes sin modificar el funcionamiento previo del sistema.

## **Contexto**

Hasta la versión anterior, cada zona energética disponía de una batería principal que se utilizaba para satisfacer los consumos.

En esta versión, cada zona pasa a disponer de **tres baterías**:

1. Una batería principal, que se mantiene sin cambios respecto a versiones anteriores.  
2. Una batería de **energía solar**.  
3. Una batería de **energía eólica**.

Las baterías solar y eólica se recargan de forma automática mediante **robots productores**, que generan energía de manera continua y concurrente.  
Los consumos pueden requerir energía de uno o varios tipos, y en cantidades variables.

## **Tareas a realizar**

### **1\. Baterías de energía renovable**

Debes implementar dos nuevas baterías por cada zona energética:

* una batería solar,  
* una batería eólica.

Cada una de estas baterías debe cumplir las siguientes condiciones:

* Tiene una **capacidad máxima limitada**, igual a la de la batería principal. Viene dado en Config.  
* Permite **depositar** unidades de energía (10 KWh cada vez)  
* Permite **retirar** unidades de energía.  
* Si no hay suficiente energía para retirar la cantidad solicitada, el hilo debe **bloquearse**.  
  Si no hay espacio suficiente para depositar energía, el hilo debe **bloquearse**.

La implementación de estas baterías debe realizarse mediante **monitores explícitos**, utilizando:

* Lock  
* ReentrantLock  
* variables de condición (Condition)

No está permitido el uso de synchronized, wait() ni notify() en estas baterías.

### **2\. Robots productores de energía**

Para cada zona energética deben existir dos hilos independientes:

* un robot productor de energía solar,  
* un robot productor de energía eólica.

El comportamiento de cada robot es el siguiente:

* Produce energía de forma indefinida.  
* En cada producción genera **1 unidad de energía** (1KWh)  
* Deposita la energía producida en la batería correspondiente.  
* Si la batería está llena, el robot debe quedar bloqueado hasta que haya espacio disponible.

Estos robots funcionan de manera concurrente e independiente del resto del sistema.

### **3\. Tramitación de consumos con múltiples tipos de energía**

Al tramitar un consumo, el operario de red debe:

* Analizar la lista de demandas asociadas al consumo.  
* Para cada demanda:  
  * identificar el tipo de energía requerida,  
  * retirar **exactamente la cantidad indicada** de la batería correspondiente.

Un consumo puede requerir:

* solo batería principal,  
* solo energía solar,  
* solo energía eólica,  
* o una combinación de varias de ellas.

Si alguna de las baterías necesarias no dispone de suficiente energía:

* la tramitación del consumo debe **bloquearse**,  
* y solo continuar cuando todas las demandas puedan satisfacerse.

No está permitido tramitar parcialmente un consumo.

## **Resultado esperado**

Al finalizar la implementación:

* Los robots productores generan energía de forma concurrente.  
* Las baterías renovables gestionan correctamente el acceso concurrente.  
* Los consumos se tramitan únicamente cuando todas sus demandas energéticas pueden satisfacerse.  
* El sistema mantiene un estado coherente y consistente.  
* La auditoría final refleja correctamente el uso de las distintas fuentes de energía.

# **Versión 6 — Semáforos: coordinación de arranque y control de acceso**

## **Objetivos de la versión**

En esta versión se introduce el uso de **semáforos** como mecanismo de sincronización, complementando las técnicas vistas en versiones anteriores.

El objetivo principal es aprender a utilizar semáforos para:

* coordinar el **arranque simultáneo de un conjunto de hilos**,  
* limitar el **grado de concurrencia** en determinadas operaciones,  
* y reforzar la comprensión de los semáforos como herramienta distinta de monitores y bloqueos explícitos.

La versión se construye directamente sobre la Versión 4, sin modificar el diseño general del sistema.

## **Contexto**

En la versión anterior, los distintos tipos de hilos (operarios, productores de consumos, robots de energía) comienzan su ejecución de forma independiente, tan pronto como son lanzados.

En sistemas concurrentes reales, sin embargo, es habitual:

* coordinar el inicio de múltiples hilos,  
* garantizar que ciertos componentes estén completamente inicializados antes de comenzar a trabajar,  
* y controlar cuántos hilos pueden acceder simultáneamente a un recurso o a una fase concreta del sistema.

En esta versión se introducirán **semáforos** para cubrir estas necesidades.

## **Tareas a realizar**

### **1\. Barrera de arranque de los operarios**

Los operarios de red **no deben comenzar a tramitar consumos inmediatamente** al ser creados.

El sistema debe garantizar que:

* todos los operarios de una zona han sido creados,  
* y **solo entonces** se les permite comenzar su trabajo.

Para ello:

* los operarios deben bloquearse al inicio de su ejecución,  
* y comenzar todos a la vez cuando el sistema les dé permiso.

Esta coordinación debe implementarse utilizando **semáforos**.

### **2\. Limitación del número de consumos en tramitación simultánea**

En esta versión se introduce una nueva restricción operativa:

**Cada zona energética solo puede tramitar un número máximo de consumos simultáneamente (considera una nueva constante en el fichero Config)**

Este límite representa una restricción realista del sistema (capacidad de procesamiento, seguridad, etc.).

El sistema debe garantizar que:

* si se alcanza el número máximo de consumos en tramitación,  
* los consumos adicionales **se bloquean** hasta que haya capacidad disponible.

Este control debe implementarse **mediante semáforos**, no mediante synchronized, Lock o monitores.

### **3\. Integración con el sistema existente**

La incorporación de los semáforos debe realizarse de forma que:

* no se modifique el comportamiento lógico de las versiones anteriores,  
* no se eliminen los mecanismos de sincronización ya existentes,  
* y el sistema siga funcionando correctamente bajo concurrencia.

Los semáforos se añaden como **una capa adicional de control**, no como sustitución de los mecanismos previos.

## **Restricciones**

* No se permite usar semáforos como sustituto de monitores o locks existentes.  
* No se permiten esperas activas.  
* Los semáforos deben utilizarse exclusivamente para:  
  * coordinación de arranque,  
  * control de concurrencia.

## **Resultado esperado**

Al finalizar la implementación:

* todos los operarios comienzan su trabajo de forma coordinada,  
* el número de consumos en tramitación simultánea está correctamente limitado,  
* el sistema se comporta de forma estable incluso con alta concurrencia,  
* la auditoría final refleja un funcionamiento coherente.

# **Versión 7 — RxJava**

Sólo tendrás que hacer dos cosas.

* Queremos tener dos observadores que de forma concurrente reciban los consumos de un observable creado a partir de la lista de todos los consumos. Uno de los observadores calculará la suma de todos los consumos y el otro imprimirá aquellos consumos mayores que 5\. Debes usar subscribeOn con el scheduler computation. Cuando imprimas algo, imprime también el thread que lo está ejecutando, para ver que efectivamente son threads diferentes al main.  
* Ahora crearemos el método *pedidosDesdeFicheroObservable*, que será igual que el que tenemos desde el principio (*pedidosDesdeFichero*), pero cambia la cabecera, para devolver un Observable, tal y como tienes más abajo. Además, dentro del método hay que crear ese Observable con *create* y emitir un evento cada vez que se lee un pedido del fichero. Ese evento lo trataremos en el main para lanzar un pedido de forma concurrente. Para lanzar los pedidos usaremos *flatMap*. En tu fichero Config usarás una constante para ver si lanzas los pedidos de la forma tradicional o con observables. En este último caso no debes leer los pedidos como lo hacías antes sino usando el nuevo método *pedidosDesdeFicheroObservable*.

	public static Observable\<Pedido\> pedidosDesdeFicheroObservable () 