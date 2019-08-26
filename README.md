# Rappi Challenge
Este es un proyecto de evaluación para Rappi que se realizo en 2 dias. El objetivo era construir
 algo utilizando información del API de Zomato. (https://developers.zomato.com/api)

## Overview
Lenguaje: Kotlin <br>
Patrón de diseño: MVVM <br>
Librerias utilizadas: Dagger2, Retrofit + OkHttp, Room, Glide, Mockito, Robolectric, PowerMock. <br>
Herramienta de análisis estático de códgio: detekt

## Arquitectura
![arquitectura](https://github.com/sdeira/RappiChallenge/blob/master/challenge_architecture.png)

## Estructura del proyecto
<pre>
* dagger          Clases requeridas para la injeccion de código con dagger2.
* models          Pojo's para modelar información.
* repository      Repositorio que actua como fuente unica de obtencion de informacion de la applicación.
  * local         DAO's para acceder a datos almacenados localmente utilizando Room.
  * remote        Servicios para obtener datos remotamente.
* utils           Clases utilitarias.
* viewmodels      Implementaciones de los ViewModels utilizados
* views           Implementaciones de las vistas utilizadas.
</pre>

El proyecto fue desarrollado en kotlin utilizando el patrón de diseño MVVM. <br>
Cada una de las capas de arquitectura interactua con otra de forma unilateral "clean architecture", logrando un mayor desacoplamiento de código. <br>
Se chequea lo conectividad del dispositivo para optar si se obtiene la informacion de forma remota o local. Funciona tanto online como offline una vez
obtenido los datos por primera vez.
