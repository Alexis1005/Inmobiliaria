<h1 align="center">🏠 Inmobiliaria Moreno Galeano</h1>

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=java&logoColor=white" alt="JSP">
  <img src="https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white" alt="Bootstrap">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven">
</div>

<div align="center">
  <img src="https://img.shields.io/badge/Version-1.0.0-blue.svg" alt="Version">
  <img src="https://img.shields.io/badge/License-MIT-green.svg" alt="License">
  <img src="https://img.shields.io/badge/PRs-Welcome-brightgreen.svg" alt="PRs Welcome">
</div>

<div align="center">
  <h3>🚀 Sistema de gestión inmobiliaria desarrollado en Java</h3>
  <p>Una aplicación web robusta para la administración integral de propiedades.</p>
</div>

---

## 📋 Tabla de Contenidos

- [✨ Características](#-características)
- [🛠️ Tecnologías](#️-tecnologías)
- [🚀 Uso](#-uso)
- [📱 Capturas de Pantalla](#-capturas-de-pantalla)
- [📄 Licencia](#-licencia)
- [👨‍💻 Autor](#-autor)

## ✨ Características

### 🏢 Gestión de Propiedades
- **CRUD Completo**: Crear, leer, actualizar y eliminar propiedades
- **Categorización**: Casas, departamentos, terrenos, campos, hacienda general
- **Gestión de Imágenes**: Subida y visualización múltiple de fotos
- **Estados**: Disponible, vendido, alquilado, arrendado
- **Filtros Avanzados**: Búsqueda por tipo de propiedad y modalidad


### 🔐 Seguridad y Acceso
- **Sistema de Login**: Autenticación segura con sesiones
- **Roles de Usuario**: Administrador, Cliente

## 🛠️ Tecnologías

### Backend
```
☕ Java 11+ (JDK)
🌐 JSP (JavaServer Pages)
🎯 Servlets
🔗 JSTL (JSP Standard Tag Library)
📦 Maven (Gestión de dependencias)
```

### Frontend
```
🎨 Bootstrap 5.0.2
📱 CSS3 & HTML5
⚡ JavaScript ES6+
🖼️ Font Awesome (Iconos)
```

### Base de Datos
```
🗄️ MySQL 8.0+
🔗 JDBC Driver
💾 Connection Pooling
🔄 Transacciones
```

### Herramientas de Desarrollo
```
🛠️ Apache Tomcat 10+
🔧 Maven 3.8+
💻 IDE utilizado: Netbeans
🔍 MySQL Workbench
```

## 🚀 Uso

### Flujo de Trabajo Principal

1. **Iniciar Sesión** como administrador 
2. **Registrar Propiedades** con todas las características
3. **Subir Imágenes** de cada propiedad


### Usuarios del Sistema

| Rol | Permisos | Funcionalidades |
|-----|----------|----------------|
| **Administrador** | Todos | Gestión completa|
| **Cliente** | Básicos | Ver propiedades, contacto|

## 📱 Capturas de Pantalla

<div align="center">
  <table>
    <tr>
      <td align="center">
        <img src="https://via.placeholder.com/400x250/007396/FFFFFF?text=Login+System" alt="Login">
        <br>
        <em>🔐 Sistema de Autenticación</em>
      </td>
      <td align="center">
        <img src="https://via.placeholder.com/400x250/563D7C/FFFFFF?text=Dashboard" alt="Dashboard">
        <br>
        <em>📊 Dashboard Principal</em>
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="https://via.placeholder.com/400x250/ED8B00/FFFFFF?text=Propiedades" alt="Propiedades">
        <br>
        <em>🏠 Gestión de Propiedades</em>
      </td>
      <td align="center">
        <img src="https://via.placeholder.com/400x250/005C84/FFFFFF?text=Reportes" alt="Reportes">
        <br>
        <em>📈 Reportes y Estadísticas</em>
      </td>
    </tr>
  </table>
</div>


## 📚 Estructura del Proyecto

```
inmobiliaria/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── inmobiliaria/
│   │   │           ├── controllers/     # Servlets
│   │   │           │   ├── PropiedadController.java
│   │   │           │   ├── ClienteController.java
│   │   │           │   └── UsuarioController.java
│   │   │           ├── models/          # POJOs/Entities
│   │   │           │   ├── Propiedad.java
│   │   │           │   ├── Cliente.java
│   │   │           │   └── Usuario.java
│   │   │           ├── dao/             # Data Access Objects
│   │   │           │   ├── PropiedadDAO.java
│   │   │           │   ├── ClienteDAO.java
│   │   │           │   └── UsuarioDAO.java
│   │   │           ├── utils/           # Utilidades
│   │   │           │   ├── DatabaseConnection.java
│   │   │           │   ├── PasswordUtils.java
│   │   │           │   └── FileUploadUtils.java
│   │   │           └── filters/         # Filtros de seguridad
│   │   │               └── AuthenticationFilter.java
│   │   ├── resources/
│   │   │   ├── db.properties           # Configuración BD
│   │   │   └── log4j.properties        # Configuración logs
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── web.xml             # Configuración web
│   │       │   └── lib/                # Librerías JAR
│   │       ├── assets/                 # Recursos estáticos
│   │       │   ├── css/
│   │       │   ├── js/
│   │       │   └── images/
│   │       ├── jsp/                    # Páginas JSP
│   │       │   ├── admin/
│   │       │   ├── cliente/
│   │       │   └── propiedades/
│   │       └── uploads/                # Archivos subidos
├── database/
│   ├── schema.sql                      # Estructura BD
│   └── sample_data.sql                 # Datos de prueba
├── pom.xml                            # Configuración Maven
└── README.md
```

## 📊 Características Técnicas

### Performance
- **Connection Pooling**: Gestión eficiente de conexiones BD
- **Prepared Statements**: Prevención de SQL Injection
- **Lazy Loading**: Carga de imágenes bajo demanda
- **Paginación**: Listados optimizados para grandes volúmenes

### Seguridad
- **Session Management**: Control de sesiones seguro
- **Input Validation**: Validación server-side completa
- **File Upload Security**: Validación de tipos y tamaños

### Escalabilidad
- **Arquitectura MVC**: Separación clara de responsabilidades
- **DAO Pattern**: Abstracción de acceso a datos
- **Modular Design**: Componentes independientes y reutilizables


## 📄 Licencia

Este proyecto está bajo la **Licencia MIT** - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## 👨‍💻 Autor

**Rubén** - *Analista y desarrollador de software*
**Andrés** - *Analista y desarrollador de software*
**Alexis** - *Analista y desarrollador de software*

- 📧 Email: [vespaalexis@gmail.com]
- 📧 Email: [rag1973@gmail.com]
- 📧 Email: [andreskorell@gmail.com]

---

<div align="center">
  
  <p>Desarrollado con ☕ y ❤️ por <a href="https://github.com/Alexis1005">Alexis</a></p> 
  
  
  <img src="https://img.shields.io/github/stars/Alexis1005/Inmobiliaria?style=social" alt="GitHub stars">
  <img src="https://img.shields.io/github/forks/Alexis1005/Inmobiliaria?style=social" alt="GitHub forks">
  <img src="https://img.shields.io/github/watchers/Alexis1005/Inmobiliaria?style=social" alt="GitHub watchers">
</div>
