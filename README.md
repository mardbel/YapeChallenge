# YapeChallenge

App para el reto de codigo de Yape, entregada el 3/2/23.

El projecto fue desarrollado siguiendo los principios de Clean Architecture, dividiendo el mismo en diferentes capas, con el objetivo de reducir el acoplamiento de clases y favorecer el testing. Para la capa de presentacion se implementa MVVM.

Se utiliza el principio de single Activity, con navegacion entre fragments para facilitar la implementacion de la navegacion y transiciones entre pantallas.


Siguiendo los principios SOLID, se utiliza el concepto de inyeccion de dependencias para lograr codigo desacoplado y facil de testear.

## Dependencias
El projecto usa diferentes librerias de Jetpack, quiza las que mas valen la pena destacar es `navigation` usada para la navegacion entre pantallas y `Hilt` para la injeccion de dependencias.

Para la comunicacion con la API se utiliza `retrofit`, mientras que `Glide` es utilizado para la carga de imagenes remotas. `Google Maps` fue la libreria elegida para renderizar el mapa.

Para los test unitarios se utiliza `Google Truth` para las aserciones y `Mockito` como mock framework.
