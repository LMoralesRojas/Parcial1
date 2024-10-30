# Detección de ADN Mutante - API REST

Este proyecto implementa una API REST en Java Spring Boot para analizar cadenas de ADN y determinar si una persona es mutante. La API está estructurada en una arquitectura de capas que incluye controladores, servicios y repositorios, y utiliza JPA para interactuar con la base de datos.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Requisitos](#requisitos)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Endpoints](#endpoints)
- [Detalles Técnicos](#detalles-técnicos)
- [Tests](#tests)
- [Documentación Adicional](#documentación-adicional)

## Descripción
La API analiza una secuencia de ADN representada por un array de strings, validando si contiene características de ADN mutante. Una persona es considerada mutante si se encuentran al menos dos secuencias consecutivas de cuatro letras idénticas en la matriz de ADN.

### Estructura del Proyecto
- `PersonaService`: Clase de servicio que maneja la lógica de verificación de ADN mutante y calcula estadísticas.
- `PersonaRepository`: Interfaz de repositorio JPA para la entidad `Persona`.
- `PersonaController`: Controlador REST que expone los endpoints `/mutant/` y `/stats`.

## Requisitos
- **Java 17** o superior
- **Maven** o **Gradle** para gestionar dependencias
- **Spring Boot 3.3.3**
- Base de datos en memoria **H2**

## Instalación y Ejecución

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/primer-parcial-adn-mutante.git
   cd primer-parcial-adn-mutante
   
1. Detección de ADN Mutante
URL: /mutant/
Método: POST
Descripción: Detecta si un humano es mutante a partir de una secuencia de ADN.
Body JSON:
json
Copiar código
{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
Respuestas:
200 OK: Si el ADN corresponde a un mutante.
403 Forbidden: Si el ADN no corresponde a un mutante.
2. Estadísticas
URL: /stats
Método: GET
Descripción: Devuelve estadísticas de las verificaciones de ADN.
Respuesta JSON:
json
Copiar código
{
  "contar_adn_mutante": 40,
  "contar_adn_humano": 100,
  "ratio": 0.4
}
3. Eliminación de Registros
Eliminar ADN específico:
URL: /persona/{id}
Método: DELETE
Descripción: Elimina un registro de ADN específico de la base de datos.
Eliminar todos los registros:
URL: /persona/deleteAll
Método: DELETE
Descripción: Elimina todos los registros de ADN de la base de datos.
Detalles Técnicos
Lógica de Detección
La lógica de detección en PersonaService busca secuencias de cuatro caracteres idénticos en filas, columnas y diagonales en la matriz de ADN. Si encuentra al menos dos secuencias, se considera un ADN mutante.

Almacenamiento de Datos
La base de datos H2 almacena cada registro de ADN junto con el resultado de si pertenece a un mutante o no.

Tests
La aplicación cuenta con pruebas automáticas para:

Verificar la lógica de detección de ADN mutante.
Confirmar las estadísticas.
Asegurar la funcionalidad de los endpoints.
Ejecutar los Tests
bash
Copiar código
# Con Maven
mvn test

# Con Gradle
./gradlew test
Documentación Adicional
Cobertura de Código: Se requiere una cobertura superior al 80%.
Diagrama de Secuencia y Arquitectura: Se incluye un PDF con los diagramas que detallan el flujo de la aplicación.
Para más información sobre este proyecto o ayuda, visita nuestra Documentación en GitHub o consulta el código fuente directamente.

go
Copiar código

Este `README.md` resume las funcionalidades, configuración y métodos
