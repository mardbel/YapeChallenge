# YapeChallenge
=======

App para el reto de codigo de Yape, entregada el 3/2/23

El proyecto cuenta con clean architecture buscando independencia del codigo, estructuracion, bajo acoplamiento y facilidad al realizar testing.
Para la capa de presentacion se utiliza mvvm.

En cuanto a librerias externas, se utiliza rtrofit para la comunicacion remota.
Siguiendo los principios SOLID, se utiliza el concepto de inyeccion de dependencias para lograr codigo desacoplado y facil de testear. La libreria seleccionada para este fin es Dagger Hilt.

Tambien se utiliza el prioncipio de single Activity, con navegacion entre fragments para facilitar la implementacion de la navegacion y facilitar las transiciones entre pantallas.

Para cargar imagenes se utiliza Glide.

En la pantalla de caraga de mapas se utiliza la libreria de maps proporcionada por google.

Para los test unitarios se utiliza la libreria de google.truth con el fin de facilitar la lectura de los mismos.
