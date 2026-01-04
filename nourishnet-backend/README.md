# 🍃 NourishNet Backend API

RESTful API backend for NourishNet - A healthy recipe discovery platform built with Spring Boot.

## 📋 Table of Contents

- [Overview](#overview)
- [Technologies](#technologies)
- [Architecture](#architecture)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Data Model](#data-model)
- [Project Structure](#project-structure)

## 🎯 Overview

The NourishNet backend provides a REST API for managing and retrieving healthy recipe content based on dietary preferences and cuisines. The API serves:
- **4 Diet Types**: Vegetarian, Pescatarian, Keto, Alkaline
- **4 Cuisine Types**: Indian, Thai, Japanese, African
- **32 Content Items**: Images, Videos, Recipes, Ingredient Lists

## 🛠️ Technologies

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 3.5.9 | Application Framework |
| Spring Web | 3.5.9 | REST API |
| Spring Data JPA | 3.5.9 | Database Persistence |
| Hibernate | 6.6.39 | ORM Implementation |
| H2 Database | 2.3.232 | In-Memory Database |
| Maven | 3.9+ | Build Tool |

## 🏗️ Architecture

### Three-Layer Architecture
```
Controllers (API Layer)
       ↓
Services (Business Logic)
       ↓
Repositories (Data Access)
       ↓
H2 Database
```

## 🗄️ Database Schema

### Tables

**DIETS**
- id (BIGINT, PRIMARY KEY)
- name (VARCHAR, UNIQUE)
- description, guidelines, health_benefits, restrictions

**CUISINES**
- id (BIGINT, PRIMARY KEY)
- name (VARCHAR, UNIQUE)
- description, region, characteristics

**CONTENT** (Single Table Inheritance)
- id (BIGINT, PRIMARY KEY)
- content_type (Discriminator: IMAGE, VIDEO, RECIPE, INGREDIENT_LIST)
- diet_id, cuisine_id (FOREIGN KEYS)
- title, description, url
- Type-specific fields (ingredients, instructions, duration, etc.)

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Diets
- `GET /api/diets` - Get all diets
- `GET /api/diets/{id}` - Get diet by ID

### Cuisines
- `GET /api/cuisines` - Get all cuisines
- `GET /api/cuisines/{id}` - Get cuisine by ID

### Content
- `GET /api/content` - Get all content
- `GET /api/content?dietId={id}&cuisineId={id}` - Filter by diet and cuisine
- `GET /api/content/{id}` - Get content by ID

### H2 Console
- `GET /h2-console` - Access database console
  - JDBC URL: `jdbc:h2:mem:nourishnet`
  - Username: `sa`
  - Password: (empty)

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.9+ (or use included wrapper)

### Run the Application
```bash
# Build and run
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

The API will start on **http://localhost:8080**

### Verify Setup
```bash
# Test endpoints
curl http://localhost:8080/api/diets
curl http://localhost:8080/api/cuisines
curl "http://localhost:8080/api/content?dietId=1&cuisineId=1"
```

## ⚙️ Configuration

**application.properties** location: `src/main/resources/application.properties`

Key configurations:
- Server port: 8080
- Database: H2 in-memory (`jdbc:h2:mem:nourishnet`)
- Auto-seeding: Enabled (32 content items on startup)
- H2 Console: Enabled at `/h2-console`
- CORS: Enabled for `http://localhost:3000`

## 📊 Data Model

### Entity Hierarchy
```
Content (Abstract)
    ↑
    ├── Image
    ├── Video
    ├── Recipe
    └── IngredientList
```

**Inheritance Strategy:** Single Table Inheritance
- All content types in one table
- Discriminator column: `content_type`

## 📁 Project Structure
```
nourishnet/
├── src/
│   ├── main/
│   │   ├── java/nourishnet/
│   │   │   ├── config/         # CORS, DataSeeder
│   │   │   ├── controller/     # REST endpoints
│   │   │   ├── entity/         # JPA entities
│   │   │   ├── repository/     # Data access
│   │   │   ├── service/        # Business logic
│   │   │   └── NourishnetApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🧪 Testing
```bash
# Run tests
./mvnw test
```

## 🔐 Security Notes

**Development Setup:**
- H2 Console enabled (disable in production)
- No authentication
- CORS allows localhost:3000

**Production Recommendations:**
- Add Spring Security
- Implement JWT authentication
- Switch to PostgreSQL
- Disable H2 console
- Use environment variables

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)

---

**Built with Spring Boot 3.5.9**
```

---

### **Step 4: Save the Backend README**

Press **`Ctrl+S`** (or `Cmd+S` on Mac) to save.

---

## **✅ Final Verification**

After completing these steps, your structure should be:
```
NOURISHNET/
├── nourishnet/                    ← BACKEND
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── target/
│   ├── .gitignore
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── README.md                  ✅ CREATE THIS!
│
└── nourishnet-frontend/           ← FRONTEND
    ├── src/
    │   ├── components/
    │   ├── pages/                 ✅ Has 3 files
    │   │   ├── DietSelection.js
    │   │   ├── CuisineSelection.js
    │   │   └── ContentDisplay.js
    │   ├── services/
    │   │   └── api.js
    │   ├── App.js
    │   └── index.css
    ├── package.json
    └── README.md                  ✅ Already exists