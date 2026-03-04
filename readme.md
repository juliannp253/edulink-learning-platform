# EduLink: Gamified English Learning Platform 

**EduLink** es una solución pedagógica integral diseñada para incentivar el aprendizaje del idioma inglés mediante la gamificación. Desarrollado originalmente como un proyecto piloto para el área de Ciencias de la Comunicación de la **Universidad Autónoma del Noroeste (UANE)**, este sistema transforma la enseñanza tradicional en una experiencia interactiva y competitiva.

## Impacto del Proyecto
El proyecto nació de la necesidad de aumentar el compromiso (engagement) estudiantil. Tras su implementación inicial, la plataforma recibió una validación positiva por parte del cuerpo docente de la **UANE**, destacando su efectividad para motivar a los alumnos mediante retos dinámicos.

## Tech Stack & Herramientas
* **Backend:** Java 17+, Spring Boot (MVC), Spring Security.
* **Base de Datos:** PostgreSQL (vía Supabase).
* **Frontend:** Thymeleaf, JavaScript (ES6+), CSS3.
* **Documentación:** Swagger / OpenAPI 3.0.
* **Despliegue:** Render / Cloud Services.

## Arquitectura y Diseño de Software
Lo más destacado de EduLink es su arquitectura escalable y mantenible:
* **Polimorfismo y Herencia:** Implementación de una jerarquía de clases para los retos (`Music`, `Emoji`, `Match`), permitiendo la extensión de nuevas actividades sin modificar la lógica base.
* **Seguridad Robusta:** Validación de respuestas y asignación de puntos procesada exclusivamente en el servidor para prevenir fraudes en el progreso del usuario.
* **API Documentation:** Documentación interactiva completa vía Swagger para facilitar la integración y el testing de endpoints.
* **Data Transfer Objects (DTOs):** Uso de Records de Java para una comunicación limpia y segura entre capas.

## Funcionalidades Principales
* **Music Challenges:** Interpretación de letras de canciones mediante integración multimedia.
* **Emoji Puzzles:** Retos de asociación visual y pensamiento lógico.
* **Match Challenges:** Sistema de emparejamiento conceptual.
* **Global Dashboard:** Visualización de progreso, niveles y puntajes en tiempo real.
