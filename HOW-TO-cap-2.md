HOW-TO Hibernate en Español!
============================
 
#Capítulo 2
 
Este artículo explicara cómo (HOW-TO) usar el ORM Hibernate en un proyecto de Java.
Para esto, debes tener mapeadas las clases y su base de datos correspondiente. Si no lo hiciste previamente, mira el [capítulo previo](https://github.com/lucio-martinez/hibernate/blob/master/HOW-TO-cap-1.md).
 
A continuación mostraré cómo crear un DAO y usarlo correctamente en las Controladoras y todo utilizando el ORM Hibernate. No es la única forma, pero es mi preferida.

##Requisitos
 
- Netbeans >= 8.0
- Hibernate >= 4.X
- MySQL >= 5.x

![no cumplo con los requisitos](http://i.imgur.com/FEiI5Nt.jpg)

Si usas versiones anteriores lo mejor que puedo hacer por tí es ofrecerte el [link de descarga a la version más reciente de Netbeans](https://netbeans.org/downloads/index.html)
 
NOTA: en este artículo he elegido MySQL pero se puede reemplazar por cualquier otro motor de base de datos que soporte Hibernate.
 
##Por dónde comienzo?
 
Abriendo el proyecto utilizado previamente llamado `registro` que ya tiene los archivos de configuración Hibernate, las clases y sus archivos de mapeos XML, y todo para que se pueda empezar a utilizar estas herramientas.
 
##Codificar DAO
 
Para todas las clases codificaremos el CRUD (Create Read Update Delete) que son los métodos para *agregar* un objeto (guardarlo en la base de datos), *leerlo* (traerlo de la base de datos), *actualizarlo* y *eliminarlo*, efectivamente, de la base de datos.
 
Ya que el CRUD será implementado en *todas* las clases, generaremos un DAO genérico que realice estas cuatro operaciones. De esta forma no repetimos el *mismo código* para todas las clases.
 
Antes de continuar:
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Java", seleccionar "Paquete Java" y presiona <kbd>Siguiente</kbd>
3. Escribe `registro.dao` para el nombre de paquete. Luego presiona <kbd>Finalizar</kbd>
 
Todas las clases del DAO serán almacenadas en el paquete `registro.dao`. Asegúrate de que así sea ¬¬
 
###Codificar DAO Genérico
 
__La Interfaz__
 
[`GenericDao`](https://raw.githubusercontent.com/lucio-martinez/hibernate/master/Registro/src/registro/dao/GenericDao.java)
 
`T`: Tipo de dato a mapear, o sea la clase  
`PK`: Tipo de dato de la clave primaria del objeto a mapear, lo más común es `int`
 
__La clase concreta__
 
[`GenericDaoImpl`](https://raw.githubusercontent.com/lucio-martinez/hibernate/master/Registro/src/registro/dao/GenericDaoImpl.java)
 
`T` y `PK` siguen siendo lo mismo.
 
No voy a dar clases de programación, por lo que espero que el código hable por sí mismo.
 
---Puedes utilizar este DAO genérico para cualquier clase a la hora de realizar cualquiera de las cuatro operaciones básicas (CRUD).--- (ya no porque es abstracta)  
Para evitar pasar por parámetro el tipo de clase cada vez que se usa el DAO, se utiliza la siguiente línea larga.

    (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]

Para saber cómo funciona, puedes leer [ésta explicación en Inglés](http://stackoverflow.com/a/11068057/1505348).
 
###Codificar DAO de una clase
 
Muchas veces el CRUD es suficiente y se necesitan métodos más específicos, como buscar elementos por un atributo o traerlos todos. Para esto se escribe un DAO específico a la clase que se desea agregar métodos.
 
__La Interfaz__
 
[`UsuariosDao`](https://raw.githubusercontent.com/lucio-martinez/hibernate/master/Registro/src/registro/dao/UsuariosDao.java)
 
Ahora los métodos de este DAO serán específicos para la clase `Usuarios`.
 
__La clase concreta__
 
[`UsuariosDaoImpl`](https://raw.githubusercontent.com/lucio-martinez/hibernate/master/Registro/src/registro/dao/UsuariosDaoImpl.java)
 
Puedes observar que los queries no son MySQL sino HQL (Hibernate Query Language). Esto permite que podamos cambiar el motor de base de datos sin modificar el código fuente del DAO.
 
##Codificar Controladora
 
Las controladoras usaran el DAO para almacenar, modificar y traer información. Estas manejaran las sesiones Hibernate para que sean utilizadas por el DAO.
 
Antes de continuar:
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Java", seleccionar "Paquete Java" y presiona <kbd>Siguiente</kbd>
3. Escribe `registro.controller` para el nombre de paquete. Luego presiona <kbd>Finalizar</kbd>
 
Todas las controladoras serán almacenadas en el paquete `registro.controller`. Asegúrate de que así sea ¬¬
 
__Ejemplo de Controladora__
 
[`AdministrarUsuariosController`](https://raw.githubusercontent.com/lucio-martinez/hibernate/master/Registro/src/registro/controller/AdministrarUsuariosController.java)
 
Se puede observar que la sesión es generada en el método de la controladora, se inicia la transacción y luego se la pasa por parámetro al DAO.
 
La controladora es la encargada de manejar las excepciones, haciendo *rollback* sobre la transacción y cerrando la sesión.
 
##Probar el funcionamiento
 
En este punto está completo el mapeo, están los modelos, el DAO, la controladora y todo lo necesario para hacer funcionar el proyecto.
 
Para probar el proyecto necesitamos correr código, y para esto voy a utilizar el `main` del proyecto donde se demuestre el correcto funcionamiento de Hibernate.
 
###Super Main
 
[`main`](https://github.com/lucio-martinez/hibernate/raw/master/Registro/src/registro/Registro.java)
 
Ejecuta!
 
![screenshoot del exito](http://i.imgur.com/3fh1pk5.png)
 
##Festejar
 
![champagne para lucio](http://upload.wikimedia.org/wikipedia/commons/f/ff/Champagne_tower.jpg)
 
Corre, salta, grita, festeja, tienes un mapeo de Hibernate, un DAO funcional, una controladora ---pobre--- funcional y un *main* para seguir jugando!
 
\o/
 
Puedes acceder al repositorio completo (sí, con el código fuente incluido) [aquí](https://github.com/lucio-martinez/hibernate).

---

Champagne Tower: picture by [kenichi nobusue](https://www.flickr.com/photos/nobusue/), license [CC BY 2.0](https://creativecommons.org/licenses/by/2.0/)
