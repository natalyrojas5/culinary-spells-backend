# 🎃 Halloween Hechizos Culinarios

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)](https://mariadb.org/)
[![Cloudinary](https://img.shields.io/badge/Cloudinary-3448C5?style=for-the-badge&logo=cloudinary&logoColor=white)](https://cloudinary.com/)
[![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)](https://jwt.io/)
[![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)](https://ubuntu.com/)
[![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)](https://www.jenkins.io/)
[![Lombok](https://img.shields.io/badge/Lombok-ff9a00?style=for-the-badge&logo=java&logoColor=white)](https://projectlombok.org/)

Bienvenido al backend de **Hechizos Culinarios** 🎃, donde los usuarios pueden compartir y descubrir recetas temáticas. Este sistema permite a los usuarios registrarse, subir recetas de cocina, dar likes y explorar las recetas de otros usuarios.

## 🚀 Características

- **Registro de usuarios**: Los usuarios pueden registrarse en el sistema.
- **Subir recetas**: Los usuarios pueden compartir recetas de cocina temáticas.
- **Likes en recetas**: Los usuarios pueden dar "me gusta" a las recetas que les gusten.
- **Seguridad**: El sistema está protegido por JWT y Spring Security.
- **Almacenamiento de imágenes**: Las imágenes de las recetas se almacenan en Cloudinary.

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para construir el backend y gestionar dependencias.
- **Spring Security**: Proporciona autenticación y autorización de usuarios.
- **JWT (JSON Web Tokens)**: Usado para la autenticación segura.
- **MariaDB**: Base de datos relacional utilizada para almacenar la información de usuarios y recetas.
- **Cloudinary**: Servicio de almacenamiento de imágenes en la nube.
- **Jenkins**: Automatización de despliegue en el servidor.
- **Lombok**: Utilizado para reducir el código boilerplate en Spring Boot.
- **Ubuntu 22.04**: Servidor donde se despliega el proyecto.

## 🏗️ Arquitectura

Este sistema está implementado utilizando **Spring Boot**. La base de datos es **MariaDB**, y las imágenes subidas por los usuarios se almacenan en **Cloudinary**.

El proyecto utiliza **Spring Security** y **JWT** para manejar la autenticación y autorización. Se incluyen las siguientes capas principales:

- **Controladores (Controllers)**: Gestionan las solicitudes HTTP y envían las respuestas.
- **Servicios (Services)**: Contienen la lógica del negocio.
- **Repositorios (Repositories)**: Gestionan el acceso a la base de datos utilizando JPA.

## 🗂️ Estructura del Proyect

```bash
📁 src/
  ┣ 📂 main/
  ┃ ┣ 📂 java/
  ┃ ┃ ┣ 📂 com/hechizos.culinarios/
  ┃ ┃ ┃ ┣ 📂 Auth/
  ┃ ┃ ┃ ┣ 📂 Config/
  ┃ ┃ ┃ ┣ 📂 Dto/
  ┃ ┃ ┃ ┣ 📂 Exception/
  ┃ ┃ ┃ ┣ 📂 Security/
  ┃ ┃ ┃ ┣ 📂 controllers/
  ┃ ┃ ┃ ┣ 📂 services/
  ┃ ┃ ┃ ┣ 📂 models/
  ┃ ┃ ┃ ┣ 📂 repositories/
  ┃ ┣ 📂 resources/
  ┃ ┃ ┣ 📜 application.properties
```
## ⚙️ Configuración
### 1. Clonar el repositorio

```bash
git clone https://github.com/natalyrojas5/culinary-spells-backend.git
```

### 2. Configurar base de datos

Asegúrate de tener MariaDB instalado y ejecutándose. Luego, crea la base de datos y actualiza el archivo application.properties con tus credenciales:

```bash
spring.datasource.url=jdbc:mariadb://localhost:3306/halloween_recipes
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```
### 3. Configurar Cloudinary
Regístrate en Cloudinary y agrega tus credenciales en application.properties:

```bash
cloudinary.cloud_name=tu_nombre_nube
cloudinary.api_key=tu_api_key
cloudinary.api_secret=tu_api_secret
```
### 4. Ejecutar el proyecto

```bash
mvn clean install
mvn spring-boot:run
```
### 5. Despliegue automatizado
Usamos **Jenkins** para automatizar el despliegue. Cada vez que se realiza un cambio en el repositorio, Jenkins toma el código, lo construye y lo despliega en el servidor **Ubuntu**.
## 🔐 Seguridad
El backend utiliza JWT para manejar la autenticación de usuarios y proteger los endpoints. Los usuarios deben autenticarse para subir recetas y dar likes.
## 🖼️ Almacenamiento de Imágenes
Las imágenes subidas por los usuarios se gestionan y almacenan en Cloudinary. Esto garantiza una gestión eficiente de los recursos multimedia.

