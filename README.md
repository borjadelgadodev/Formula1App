# Formula1App

## Descripción

Formula1App es una aplicación Android que muestra información sobre los pilotos de Fórmula 1. La aplicación utiliza Retrofit para conectarse a una API y obtener datos actualizados sobre los pilotos.

## Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes:

- **data**: Contiene las clases relacionadas con la obtención de datos desde la API.
  - **api**: Interfaz de Retrofit para la comunicación con la API.
  - **model**: Clases de datos (DTO) que representan los datos obtenidos de la API.
- **domain**: Contiene la lógica de negocio del proyecto.
  - **models**: Clases de dominio utilizadas en la aplicación.
  - **repository**: Interfaces y sus implementaciones para el acceso a los datos.
  - **usecase**: Casos de uso que encapsulan las operaciones de la aplicación.
  - **mapper**: Clases de mapeo entre DTOs y modelos de dominio.
- **presentation**: Contiene las clases relacionadas con la presentación de los datos.
  - **view**: Clases de las vistas y componentes UI.
  - **viewmodel**: Clases ViewModel para la gestión de la UI y datos.
  - **navigation**: Clases para la navegación en la aplicación.

## Instalación

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/borjadelgadodev/Formula1App.git
