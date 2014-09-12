HOW-TO Hibernate en Español!
============================
 
#Capítulo 1
 
Este artículo explicara cómo (HOW-TO) implementar el ORM Hibernate en un proyecto de Java.
El ejemplo que voy a utilizar es el de una aplicación Java de línea de comandos, pero bien podría ser utilizado para Desktop, Servlets o cualquier proyecto siempre que el mismo sea en la plataforma Java.
 
Hibernate puede mapear objetos utilizando una de las siguientes formas (al menos de mi conocimiento):
 
1. Annotations + XML: definir cómo mapear los objetos en un archivo de configuración XML para cada clase a mapear, dicha clase debe ya existir >.<
2. DB: con una base de datos que tenga las tablas previamente definidas, se hace Ingeniería Inversa (C) y se generan las clases, sus archivos de configuración XML y todas las relaciones automáticamente!
 
Este artículo cubre el último método, para todo lo demás existe [DuckDuckGo](https://duckduckgo.com/).
 
##Requisitos
 
- Netbeans > 8.0
- Hibernate > 4
- MySQL > 5.x
 
NOTA: en este artículo he elegido MySQL pero se puede reemplazar por cualquier otro motor de base de datos que soporte Hibernate.
 
##Por donde comienzo?
 
Genera la base de datos en MySQL. Para este ejemplo he diseñado la base más simple posible, que es la siguiente.
 
[`install.sql`](https://raw.githubusercontent.com/lucio-martinez/registro/master/Registro/install.sql)
 
Corre el script como quieras para generar la base de datos `Registro` con la tabla `usuarios`. *Sólo asegúrate de utilizar exactamente los mismos nombres y prestar atención a la mayúsculas.*
 
##Por donde continúo?
 
Crea un nuevo Proyecto de "Aplicación Java", donde utilizaremos la línea de comando como interfaz gráfica. El nombre del proyecto será `Registro`, donde habrá un registro de *usuarios* con *nombre* y *apellido*, sín contraseña para hacer todo lo más simple posible.
 
Por conveniencia y evitar confusiones te recomiendo utilizar *exactamente* los mismos nombres que yo utilizo aquí de ahora en adelante.
 
__Agregar Hibernate al proyecto__
 
Una forma de agregar Hibernate al proyecto es:
 
1. En la ventana "Proyectos", click derecho sobre "Bibliotecas" y seleccionar "Agregar Biblioteca..."
2. En la nueva ventana "Agregar Biblioteca", presiona en <kbd>Importar...</kbd>
3. En la nueva ventana "Importar Biblioteca", selecciona "Hibernate" presiona en <kbd>Importar Biblioteca</kbd>
4. Finalmente presiona <kbd>Agregar Biblioteca</kbd>
 
Perfecto! Ahora continua con el siguiente paso.
 
##Agregar el conector MySQL
 
Para que funcione Hibernate con la base de datos MySQL en el proyecto, debemos tener el conector de MySQL en el mismo.
 
Antes de agregarlo, búscalo en las "Bibliotecas" del proyecto. Por ejemplo, en la siguiente imagen puedes ver que este se encuentra añadido (al estar en inglés puedes observar "Libraries" en lugar de "Bibliotecas") por lo que puedes saltear este paso.
 
![screenshoot de ayuda](https://netbeans.org/images_www/articles/80/java/hibernate-j2se/hib-libraries-config.png)
 
Sino se encuentra actualmente en "Bibliotecas", añádelo siguiendo los pasos a continuación.
 
1. En la ventana "Proyectos", click derecho sobre "Bibliotecas" y seleccionar "Agregar Biblioteca..."
2. En la nueva ventana "Agregar Biblioteca", presiona en <kbd>Importar...</kbd>
3. En la nueva ventana "Importar Biblioteca", selecciona "MySQL JDBC Driver" presiona en <kbd>Importar Biblioteca</kbd>
4. Finalmente presiona <kbd>Agregar Biblioteca</kbd>
 
##Generar la conexión a la base de datos en Netbeans
 
Tienes dos formas para generar la conexión a la base de datos.
 
__Conectar la base de datos y generar la conexión automáticamente__
 
1. En la ventana "Servicios", expande el nodo "Base de datos"
2. Click derecho sobre la base de datos MySQL y selecciona "Propiedades..."
 
  Te debe aparecer la ventana "Propiedades del Servidor MySQL" con los datos de conexión del servidor MySQL. Asegúrate que los valores sean correctos, sino modifícalos. Por ejemplo, si el usuario *root* de MySQL tiene contraseña, ingrésala! Luego presiona <kbd>OK</kbd>.
 
3. Nuevamente click derecho sobre la base de datos MySQL, selecciona "Conectar"
4. Abre el nodo de MySQL y se debe ver las bases de datos disponibles
5. Click derecho sobre la base de datos a mapear, en nuestro caso `registro`, y selecciona "Conectar..."
 
Si te aparece la ventana "Conexión", ingresa los datos del usuario MySQL. Luego presiona <kbd>OK</kbd>.
 
__Generar la conexión manualmente__
 
1. En la ventana "Servicios", click derecho sobre el nodo "Base de datos" y selecciona "Nueva Conexión..."
2. Para indicar el driver a utilizar, selecciona el driver de MySQL en la lista o agrégalo manualmente (buena suerte con eso!). Luego presiona <kbd>Siguiente</kbd>
3. Aparecerá en la ventana la posibilidad de personalizar la conexión

  En el campo "Base de datos" ingresa `registro` que es el nombre de nuestra base de datos a mapear.
 
  Nuevamente, verifica que las propiedades de la conexión sean las correctas, sino modifícalas. **Asegúrate de que el valor del campo `Base de datos` tenga el nombre de la base de datos que deseas mapear, no `mysql` que es la predeterminada y pertenece al sistema MySQL!** Es muy común olvidarse de este detalle (me pasó detallando este paso). Puedes usar el botón <kbd>Probar conexión</kbd> para, bueno, probar la conexión.. Luego presiona <kbd>Siguiente</kbd>
 
4. Aparecerá la posibilidad de seleccionar el esquema de base de datos, en mi caso no he seleccionado ninguno porque Netbeans no me dejó.. Luego presiona <kbd>Siguiente</kbd>
5. Por último, se mostrará en pantalla el valor de la conexión a utilizar. Aquí tienes la posibilidad de editarlo si lo deseas.. Luego presiona <kbd>Finalizar</kbd>
 
Sin importar cuál de ambas formas se ha utilizado, y si todo salio exitosamente, se habrá generado la conexión a la base de datos. Puedes ver los datos de la base de datos expandiendo el nodo de la misma dentro del nodo "Base de datos", como tablas y vistas.
 
![base_de_datos -> registro_url](http://i.imgur.com/jje6IGy.png)
 
##Generar el archivo de configuración Hibernate
 
El archivo `hibernate.cfg.xml` será utilizado para almacenar la información necesaria para conectarse a la base de datos.
 
Puedes generar el archivo a mano o, como hace todo ser humano, siguiendo los pasos a continuación.
 
1. En la ventana "Proyectos", click derecho en "Paquetes de fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Hibernate", seleccionar "Asistente de configuración de Hibernate" y presiona <kbd>Siguiente</kbd>
3. Deja los valores iniciales para el nombre y la ubicación y presiona <kbd>Siguiente</kbd>
4. Ahora en "Conexión a Base de datos" selecciona la URL de la conexión a la base de datos que generaste previamente
 
Asegúrate de que el nombre de la base de datos que deseas mapear aparece en la URL. Por ejemplo, la base de datos `pepe` tendrá una URL similar a `muchascosa...localhost:3306/pepe?muchasotrascosas`. Luego presiona <kbd>Finalizar</kbd>
 
Luego de presionar <kbd>Finalizar</kbd> Netbeans abre el archivo `hibernate.cfg.xml`. Este ha sido guardado en el paquete "<paquete predeterminado>"
 
##Modificar el archivo de configuración Hibernate
 
En los siguientes pasos se editaran las propiedades predeterminadas especificadas en el archivo `hibernate.cfg.xml`, habilitando la depuración de sentencias SQL.
 
1. Abre `hibernate.cfg.xml` en la pestaña "Diseño"
2. En la sección "Propiedades Opcionales" -> "Propiedades de Configuración", presiona <kbd>Agregar...</kbd>
 
  En la nueva ventana "Agregar Propiedad Hibernate", selecciona la propiedad `hibernate.show_sql` y establece el valor a `true`. Luego presiona <kbd>OK</kbd>
 
4. En la sección "Propiedades Opcionales" -> "Propiedades Diversas", selecciona <kbd>Agregar...</kbd>
 
  En la nueva ventana "Agregar Propiedad Hibernate", selecciona la propiedad `hibernate.query.factory_class` y establece el valor a `org.hibernate.hql.classic.ClassicQueryTranslatorFactory`. *Este valor será cambiado posteriormente debido a un error de compatibilidad.* Luego presiona <kbd>OK</kbd>
 
5. Guarda los cambios realizados en el archivo!
 
##Generar el archivo de ayuda Hibernate
 
Para manejar las sesiones, Hibernate necesita una clase utilitaria que cargue el archivo de configuración generado previamente y obtenga el objeto de sesión.
 
Puedes generar la clase utilitaria a mano o, como hace todo ser humano, siguiendo los siguientes pasos.
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Hibernate", seleccionar "HibernateUtil.java" y presiona <kbd>Siguiente</kbd>
3. Escribe `HibernateUtil` para el nombre de clase y `registro.util` como nombre de paquete. Luego presiona <kbd>Finalizar</kbd>
 
Luego de presionar <kbd>Finalizar</kbd> Netbeans abre el archivo `HibernateUtil.java`. No lo modificaremos, por lo que puedes cerrarlo.
 
##Generar el archivo de Ingeniería Inversa
 
Para leer la información necesaria de la base de datos para generar las clases, se debe especificar una serie de configuraciones acerca de, sí, la base de datos. Como qué tablas mapear, las relaciones, etc. Es realmente simple, al menos lo garantizo si sigues los siguientes pasos.
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Hibernate", seleccionar "Asistente de Ingeniería Inversa de Hibernate" y presiona <kbd>Siguiente</kbd>
3. Escribe `hibernate.reveng` para el nombre de archivo y deja `src` como valor predeterminado para la ubicación y presiona <kbd>Siguiente</kbd>
4. En la nueva ventana se debería ver las tablas disponibles en la base de datos a mapear, en la cual se debe seleccionar las tablas a mapear
 
Ya que las tablas serán utilizadas para generar los objetos, presiona <kbd>Agregar Todo...</kbd> y pasaran del cuadro izquierdo la derecho. Luego presiona <kbd>Finalizar</kbd>
 
Luego de presionar <kbd>Finalizar</kbd> Netbeans abre el archivo `hibernate.reveng.xml`. No lo modificaremos, por lo que puedes cerrarlo.
 
 
##Generar los archivos Hibernate de mapeo
 
Gracias al archivo de ingeniería inversa generado previamente podemos generar los archivos de mapeo y las clases Java. Cómo? Sigue los pasos a continuación.
 
1. Click derecho en "Paquetes de Fuentes" y elegir "Nuevo" -> "Otro..."
2. En la categoría "Hibernate", seleccionar "Archivos de Mapeo de Hibernate y POJOs de base de datos" y presiona <kbd>Siguiente</kbd>
3. Selecciona `hibernate.cfg.xml` para el archivo de configuración Hibernate
4. Selecciona `hibernate.reveng.xml` para el archivo de ingeniería inversa Hibernate
5. Activa las opciones `Código de Dominio (.java)` y `Mapeos XML Hibernate (.hbm.xml)`
6. Escribe `registro.entity` para el nombre de paquete. Luego presiona <kbd>Finalizar</kbd>
 
Luego de presionar <kbd>Finalizar</kbd> Netbeans genera las clases y su mapeo correspondiente. Hecha un vistazo al paquete `registro.entity` :-)
 
##Solucionar bug con HQL
 
Para continuar se debe solucionar un error de compatibilidad del cual no conozco la causa pero sí una forma de evitarlo:
 
1. Abre el archivo `hibernate.cfg.xml` en la pestaña "Fuente"
2. Modifica la propiedad `hibernate.query.factory_class` de la siguiente forma:
 
  El valor actual es `org.hibernate.hql.classic.ClassicQueryTranslatorFactory` pero debe ser reemplazado por `org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory`.
 
3. Guarda los cambios realizados en el archivo!
 
##Festejar
 
![champagne para lucio](http://betanews.com/wp-content/uploads/2013/05/champagne-541x600.jpg)
 
Corre, salta, grita, festeja, tienes un mapeo de Hibernate en tu proyecto!
 
\o/
 
---
 
Y sí, falta hacer que el mapeo sirva de algo, o sea CODIFICAR. En [el siguiente capítulo](https://github.com/lucio-martinez/registro/blob/master/HOW-TO-cap-2.md) explico cómo hacer que todo esto tenga sentido.

[Leer el siguiente capítulo!](https://github.com/lucio-martinez/registro/blob/master/HOW-TO-cap-2.md)
---
 
Bibliografía: https://netbeans.org/kb/docs/java/hibernate-java-se.html
