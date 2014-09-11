HOW-TO Hibernate en Español!
============================
 
#Capítulo 2
 
Este artículo explicara cómo (HOW-TO) usar el ORM Hibernate en un proyecto de Java.
Para esto, debes tener mapeadas las clases y su base de datos correspondiente. Sino lo hiciste previamente, mira el capítulo anterior.
 
A continuación mostraré cómo crear un DAO y usarlo correctamente en las Controladoras y todo utilizando el ORM Hibernate. No es la única forma, pero es mi preferida.
 
##Por donde comienzo?
 
Abriendo el proyecto que ya tiene los archivos de configuración Hibernate, las clases y sus archivos de mapeos XML, y todo para que se pueda empezar a utilizar estas herramientas.
 
##Codificar DAO
 
Para todas las clases codificaremos el CRUD (Create Read Update Delete) que es el método de Agregar un objeto (guardarlo en la base de datos), Leerlo (traerlo de la base de datos), Actualizarlo y Eliminarlo, efectivamente, de la base de datos.
 
Ya que el CRUD será implementado en *todas* las clases, generaremos un DAO genérico que realice estas cuatro operaciones. De esta forma no repetimos el *mismo código* para todas las clases.
 
Antes de continuar:
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Java", seleccionar "Paquete Java" y presiona <kbd>Siguiente</kbd>
3. Escribe `registro.dao` para el nombre de paquete. Luego presiona <kbd>Finalizar</kbd>
 
Todas las clases del DAO serán almacenadas en el paquete `registro.dao`. Asegúrate de que así sea ¬¬
 
###Codificar DAO Genérico
 
__La Interfaz__
 
[`GenericDao`](https://raw.githubusercontent.com/lucio-martinez/registro/master/Registro/src/registro/dao/GenericDao.java)
 
`T`: Tipo de dato a mapear, o sea la clase
`PK`: Tipo de dato de la clave primaria del objeto a mapear, lo más común es `int`
 
__La clase concreta__
 
[`GenericDao`](https://raw.githubusercontent.com/lucio-martinez/registro/master/Registro/src/registro/dao/GenericDaoImpl.java)
 
`T` y `PK` siguen siendo lo mismo.
 
No voy a dar clases de programación, por lo que espero que el código hable por sí mismo.
 
Puedes utilizar este DAO genérico para cualquier clase a la hora de realizar cualquiera de las cuatro operaciones básicas (CRUD).
 
###Codificar DAO de una clase
 
Muchas veces el CRUD es suficiente y se necesitan métodos más específicos, como buscar elementos por un atributo o traerlos todos. Para esto se escribe un DAO específico a la clase que se desea agregar métodos.
 
__La Interfaz__
 
[`UsuariosDao`](https://raw.githubusercontent.com/lucio-martinez/registro/master/Registro/src/registro/dao/UsuariosDao.java)
 
Ahora los métodos de este DAO serán específicos para la clase `Usuarios`.
 
__La clase concreta__
 
[`UsuariosDao`](https://raw.githubusercontent.com/lucio-martinez/registro/master/Registro/src/registro/dao/UsuariosDaoImpl.java)
 
Puedes observar que los queries no son MySQL sino HQL (Hibernate Query Language). Esto permite que podamos cambiar el motor de base de datos sin modificar el código fuente del DAO.
 
##Codificar Controladora
 
Las controladoras usaran el DAO para almacenar, modificar y traer información. Estas manejaran las sesiones Hibernate para que sean utilizadas por el DAO.
 
Antes de continuar:
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Java", seleccionar "Paquete Java" y presiona <kbd>Siguiente</kbd>
3. Escribe `registro.controller` para el nombre de paquete. Luego presiona <kbd>Finalizar</kbd>
 
Todas las controladoras serán almacenadas en el paquete `registro.controller`. Asegúrate de que así sea ¬¬
 
__Ejemplo de Controladora__
 
[`AdministrarUsuariosController`](https://raw.githubusercontent.com/lucio-martinez/registro/master/Registro/src/registro/controller/AdministrarUsuariosController.java)
 
Se puede observar que la sesión es generada en el método de la controladora, se inicia la transacción y luego se la pasa por parámetro al DAO.
 
La controladora es la encargada de manejar las excepciones, haciendo *rollback* sobre la transacción y cerrando la sesión.
 
##Probar el funcionamiento
 
En este punto está completo el mapeo, están los modelos, el DAO, la controladora y todo lo necesario para hacer funcionar el proyecto.
 
Para probar el proyecto necesitamos correr código, y para esto voy a utilizar el `main` del proyecto donde se demuestre el correcto funcionamiento de Hibernate.
 
###Super Main
 
[`main`](https://github.com/lucio-martinez/registro/raw/master/Registro/src/registro/Registro.java)
 
Ejecuta!
 
![screenshoot del exito](http://i.imgur.com/3fh1pk5.png)
 
##Festejar
 
![champagne para lucio](http://betanews.com/wp-content/uploads/2013/05/champagne-541x600.jpg)
 
Corre, salta, grita, festeja, tienes un mapeo de Hibernate, un DAO funcional, una controladora ---pobre--- funcional y un *main* para seguir jugando!
 
\o/
 
Puedes acceder al repositorio completo [aquí](https://github.com/lucio-martinez/registro).
