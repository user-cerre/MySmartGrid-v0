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
* Permite **depositar** unidades de energía (1 KWh cada vez)  
* Permite **retirar** unidades de energía (las que sean  necesarias cada vez).  
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

4. Los robots productores generan energía de forma concurrente.  
5. Las baterías renovables gestionan correctamente el acceso concurrente.  
6. Los consumos se tramitan únicamente cuando todas sus demandas energéticas pueden satisfacerse.  
7. El sistema mantiene un estado coherente y consistente.  
8. La auditoría final refleja correctamente el uso de las distintas fuentes de energía.

# **Versión 6 — Semáforos: coordinación de arranque y control de acceso**

## **Objetivos de la versión**

En esta versión se introduce el uso de **semáforos** como mecanismo de sincronización, complementando las técnicas vistas en versiones anteriores.

El objetivo principal es aprender a utilizar semáforos para:

* coordinar el **arranque simultáneo de un conjunto de hilos**,  
* limitar el **grado de concurrencia** en determinadas operaciones,  
* y reforzar la comprensión de los semáforos como herramienta distinta de monitores y bloqueos explícitos.

La versión se construye directamente sobre la Versión 5, sin modificar el diseño general del sistema.

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

1. Queremos tener dos observadores que de forma concurrente reciban los consumos de un observable creado a partir de la lista de todos los consumos. Uno de los observadores calculará la suma de todos los consumos y el otro imprimirá aquellos consumos mayores que 20Kwh. Debes usar subscribeOn con el scheduler computation. Cuando imprimas algo, imprime también el thread que lo está ejecutando, para ver que efectivamente son threads diferentes al main. Lo puedes hacer justo al leer los consumos del fichero.  
2. Ahora crearemos el método *consumosDesdeFicheroObservable*, que será igual que el que tenemos desde el principio (*consumosDesdeFichero*), pero cambia la cabecera, para devolver un Observable, tal y como tienes más abajo. Además, dentro del método hay que crear ese Observable con *create* y emitir un evento cada vez que se lee un pedido del fichero. Ese evento lo trataremos en el main para lanzar un pedido de forma concurrente. Para lanzar los pedidos usaremos *flatMap*. En tu fichero Config usarás una constante para ver si lanzas los pedidos de la forma tradicional o con observables. En este último caso no debes leer los pedidos como lo hacías antes sino usando el nuevo método *consumosDesdeFicheroObservable*.

	public static Observable\<Consumo\> consumosDesdeFicheroObservable (String path) 

# **Versión 8 — Introduciendo estructuras avanzadas**

1) Usa **Executor** para lanzar los consumos, fijando el número de threads a los núcleos disponibles en tu ordenador (recuerda usar el método *availableProcessors* para esto). Usa la constante del fichero Config que ya usaste para Observables para poder configurar cómo se van a lanzar los threads, si con Executor, con Observables o de la forma antigua. En tu código preguntarás por ese atributo y dependiendo de su valor (0, 1 ó 2\) los lanzarás de una forma  u otra.   
2) Ahora vamos a cambiar la implementación de la cola que usamos en la clase *CentroControl*. En vez de utilizar una cola normal y usar *wait/notify/notifyAll*, vamos a utilizar una **LinkedBlockingQueue** que evitará el uso de primitivas de bajo nivel como *wait/notify/notifyAll*.  
3) Usa **Callable** para obtener el consumo más alto. Hazlo desde el main, una vez que todos los pedidos hayan terminado de tramitarse y antes de hacer las auditorías. Deberás crear una nueva clase que implemente Callable y recorra la lista de consumos para devolver el más alto. Devolvemos el consumo para imprimir su identificador y el consumo solicitado. Puedes considerar el uso de streams en la implementación del método *call()*.  
4) Ahora usaremos **ForkJoin** para obtener una lista con los consumos que estén por encima de 20kwh. El caso trivial será cuando haya menos de 10 consumos en la lista de consumos. Hazlo en el main a continuación del apartado c)  
5) Crea una alternativa con **CyclicBarrier** a lo que has hecho con semáforos en el Operador de Red para controlar si estaban todos**.** De nuevo crea un atributo en la clase Config para poder elegir de qué forma controlamos que estén todos los operadores de red (semáforos o CyclicBarrier).   
6) Ahora queremos que el Operador que carga las baterías no comience a cargar hasta que todos los operadores de red hayan empezado su ejecución. Usa **CountDownLatch** para solucionarlo. 

# **Versión 9 — gRPC**

En esta versión, nuestro sistema se distribuirá para incorporar los cuatro tipos de comunicación que ofrece gRPC. Esto implica la creación de dos servicios principales, **Monitorización** y **Precios**, ambos ubicados en una máquina B y con sus propios ficheros de definición `.proto` (véanse las Figuras 2 y 3). Estos servicios, representados en color azul en la Figura 1, serán utilizados por dos clientes: un nuevo cliente consultor que desarrollaremos desde cero, y nuestro programa existente MySmartGrid.

A continuación, detallamos los pasos a seguir.

1. Se definirá un **servicio** llamado **Monitorizacion**, que incluirá varios métodos especificados en el fichero `.proto`. Este servicio se alojará en el puerto 9002 de una máquina B.  
2. El método **anotarConsumo** tiene como finalidad registrar la información de los consumos procesados en una estructura de datos interna. Este método recibirá un indicador (`solar`) que señala si el consumo corresponde a energía solar. Al ser invocado, devolverá el consumo total acumulado hasta ese momento, desglosado por cada zona energética, además de la suma global de todas ellas. El operador de red, quien actúa como cliente y realiza la anotación en la cuenta energética local (gestionada por nuestro sistema), será el encargado de invocar este método. Dicha ejecución forma parte del programa `MySmartGrid` y se lleva a cabo en un ordenador A.  
3. El método **demandaSolar** recibirá un identificador de zona y proporcionará los identificadores de todos los consumos que hayan solicitado energía solar dentro de esa zona. Dado que el servicio ya almacena todos los consumos (según el requisito previo), le resultará sencillo determinar qué consumos específicos incluyeron la demanda de energía solar.  
4. Se incluirá un tercer método llamado **consumosDireccion**. Este método recibirá una lista de direcciones y devolverá un número que representa la cantidad de esas direcciones que han registrado una demanda de energía solar.  
5. Para los apartados 3\) y 4), se requiere la creación de un **cliente** independiente (denominado "cliente Consultor" en la Figura 1\) que se ejecutará en la máquina A una vez que el programa `MySmartGrid` haya finalizado. La secuencia de ejecución debe ser: 1\) Servidor con el servicio de Monitorización, 2\) Programa `MySmartGrid`, y 3\) Este nuevo cliente.  
6. Deberás crear un servicio adicional, tal como se especifica en el fichero `.proto` de la figura 3, con el objetivo de calcular los precios de los consumos. Este servicio será invocado por el cliente `MySmartGrid` inmediatamente después de completar las auditorías. El servidor para este nuevo servicio se ubicará en el ordenador B (según la figura 1\) y utilizará el puerto 9004\.

 ![][image1]

 **Figura 1.-** Esquema de clientes y servicios

| syntax \= "proto3";   option java\_package \= "grpc"; option java\_outer\_classname \= "MonitorizacionProto";   package directory;   service Monitorizacion {   rpc anotarConsumo (ConsumoRequest) returns (ConsumoReply);   rpc demandaSolar (DemandaRequest) returns (stream DemandaReply);   rpc consumosDireccion (stream DireccionRequest) returns (DireccionReply); } message ConsumoRequest {            	string idConsumo \= 1;            	int32 idZona \= 2;             	double KWh \= 3;             	string direccion \= 4;             	bool solar \= 5; } message ConsumoReply {            	int32 idZona \= 1;            	double totalZona \= 2;             	double totalRed \= 3; } message DemandaRequest {            	int32 idZona \= 1; } message DemandaReply {            	string idConsumo \= 1; } message DireccionRequest {            	string direccion \= 1; } message DireccionReply {            	int32 total \= 1; } |
| :---- |

**Figura 2.-** Proto de Monitorizacion

 

| syntax \= "proto3"; option java\_package \= "grpc"; option java\_outer\_classname \= "PreciosProto";   service Precios {            	rpc calcularPrecios (stream PreciosRequest ) returns (stream PreciosReply ); } message PreciosRequest {            	string idConsumo \= 1;            	int32 idZona \= 2;            	repeated DemandaRequest demandas \= 3; } message DemandaRequest {            	string idTipo \= 1;            	double KWh \= 2;            	int32 prioridad \= 3; } message PreciosReply {            	string idConsumo \= 1;            	double precio \= 2; } |
| :---- |

**Figura 3.-** Proto de Precios

[image1]: <data:image/png;base64,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>