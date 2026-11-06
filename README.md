# Loverboxd

Loverboxd es una aplicación de películas para Android que hice como trabajo práctico del Seminario de Android.

La app usa la API de [The Movie Database (TMDB)](https://www.themoviedb.org/) para mostrar películas populares y consultar sus detalles.

## Funcionalidades

- Lista de películas populares con su título, portada y calificación.
- Pantalla de detalle con título, portada, sinopsis, géneros y calificación.
- Indicadores de carga mientras se consultan los datos.
- Mensajes de error y opción para reintentar si falla una consulta.
- Textos en español e inglés, según el idioma del dispositivo.
- Soporte para modo claro y oscuro.

## Cómo ejecutar el proyecto

1. Clonar este repositorio y abrirlo en Android Studio.
2. Esperar a que Gradle sincronice el proyecto.
3. Obtener un **API Read Access Token** desde [TMDB](https://www.themoviedb.org/settings/api).
4. En el archivo `local.properties`, ubicado en la carpeta principal del proyecto, agregar esta línea:

   ```properties
   TMDB_TOKEN=TU_TOKEN_DE_TMDB
   ```

   Reemplazar `TU_TOKEN_DE_TMDB` por el token de lectura de TMDB, sin comillas ni la palabra `Bearer`. Si el archivo `local.properties` todavía no existe, se puede crear en la raíz del proyecto.
5. Ejecutar la aplicación desde Android Studio en un emulador o dispositivo Android.

**Nota:** `local.properties` está incluido en `.gitignore`, por eso el token no aparece en GitHub y hay que configurarlo al descargar el proyecto.

## Tecnologías utilizadas

- Kotlin y Jetpack Compose para la interfaz.
- MVVM y Repository Pattern para organizar el código.
- Retrofit para las consultas a la API.
- Coil para cargar las imágenes.
- Navigation Compose para pasar de la lista al detalle de una película.
