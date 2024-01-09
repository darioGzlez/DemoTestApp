Proyecto Android escrito con Kotlin, Jetpack compose y MVI.

La aplicación muestra un listado de contactos obtenidos de la API de [Random User](https://randomuser.me). Se puede navegar desde cada elemento de la lista para ver más datos, además la lista está paginada y permite la búsqueda por nombre e email. De forma adcional se muestran mensajes de error en caso de que se produzca uno durante la llamada al servici u otras condiciones como la falta de conexión a internet.

Se sigue una estructura de paquetes CLEAN, en la que se divide el proyecto en entitades, data sources, use case, viewmodels y modelos. La interfaz de ha realizado haciendo uso de Jetpack Compose prestando especial atención a la reutilización y limpieza del código. 

Se usan las siguientes librerías
- Jetpack Paging: Se usa para controlar el pagina del listado de contactos.
- Coil: Para cargar asíncrocamente las imágenes.
- HILT: Se usa para la inyección de dependencias.
- Retrofit: Para la ejecución de llamas HTTP a la API.
- Moshi: Para el parseado a clasos Koltin de los JSON recibidos.
- Compose Destinations: Remplaza la navegación nativa de Compose con un sistema mucho más flexible e intuitivo.
