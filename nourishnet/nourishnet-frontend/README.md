# 🥗 NourishNet

A full-stack web application that provides personalized healthy recipe content based on dietary preferences and cuisine types.

![NourishNet Banner](screenshots/diet-selection.png)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Screenshots](#screenshots)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)
- [License](#license)

## 🎯 Overview

NourishNet is a recipe discovery platform that helps users find healthy recipes tailored to their dietary needs. Users can:
- Select from 4 diet types (Vegetarian, Pescatarian, Keto, Alkaline)
- Choose from 4 cuisine styles (Indian, Thai, Japanese, African)
- View personalized content including images, videos, recipes, and ingredient lists

The application features a **Spring Boot REST API backend** with an **H2 in-memory database** and a **React frontend** with **Tailwind CSS** styling.

## ✨ Features

### Current Features
- **Diet Selection**: Choose from 4 different dietary approaches
- **Cuisine Filtering**: Explore 4 global cuisine types
- **Content Display**: View 32 unique content items (2 per diet-cuisine combination)
- **Content Types**:
  - 🖼️ Images with captions
  - 🎥 Video tutorials
  - 📖 Recipes with instructions
  - 🛒 Ingredient lists with shopping tips
- **Responsive Design**: Works on desktop, tablet, and mobile
- **Smooth Navigation**: Back buttons and clean routing

### Data Model
- **4 Diets**: Vegetarian, Pescatarian, Keto, Alkaline
- **4 Cuisines**: Indian, Thai, Japanese, African
- **32 Content Items**: Automatically seeded on startup
- **7 Database Tables**: Diets, Cuisines, Content (with 4 subtypes)

## 🛠️ Technologies Used

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.5.9** - Application framework
- **Spring Data JPA** - Database persistence
- **Hibernate** - ORM (Object-Relational Mapping)
- **H2 Database** - In-memory database
- **Maven** - Build tool and dependency management

### Frontend
- **React 18** - UI library
- **React Router DOM** - Client-side routing
- **Axios** - HTTP client for API calls
- **Tailwind CSS** - Utility-first CSS framework
- **JavaScript ES6+** - Programming language

### Development Tools
- **VS Code** - Code editor
- **Node.js 22 LTS** - JavaScript runtime
- **npm** - Package manager
- **Git** - Version control

## 📸 Screenshots

### Diet Selection Page
![Diet Selection](screenshots/diet-selection.png)
*Choose your dietary preference from 4 colorful options*

### Cuisine Selection Page
![Cuisine Selection](screenshots/cuisine-selection.png)
*Select your favorite cuisine style*

### Content Display Page
![Content Display](screenshots/content-display.png)
*View personalized recipes and content*

## 🚀 Getting Started

### Prerequisites

Before running this project, make sure you have:
- **Java 17** or higher ([Download](https://adoptium.net/))
- **Node.js 20+** ([Download](https://nodejs.org/))
- **Maven 3.9+** (or use included Maven wrapper)
- **Git** (for cloning)

### Installation & Setup

#### 1. Clone the Repository
```bash
git clone https://github.com/YOUR_USERNAME/nourishnet.git
cd nourishnet
```

#### 2. Set Up the Backend
```bash
# Navigate to backend folder
cd nourishnet

# Run the Spring Boot application
./mvnw spring-boot:run

# On Windows, use:
mvnw.cmd spring-boot:run
```

The backend will start on **http://localhost:8080**

✅ You should see: "Started NourishnetApplication in X.XXX seconds"

✅ Database will be auto-seeded with 32 content items

#### 3. Set Up the Frontend

Open a **new terminal window** and:
```bash
# Navigate to frontend folder
cd nourishnet-frontend

# Install dependencies
npm install

# Start the development server
npm start
```

The frontend will start on **http://localhost:3000**

✅ Browser should automatically open to http://localhost:3000

✅ You should see the NourishNet diet selection page

### Running the Application

**Make sure BOTH servers are running simultaneously:**

| Server | Command | Port | URL |
|--------|---------|------|-----|
| Backend | `./mvnw spring-boot:run` | 8080 | http://localhost:8080 |
| Frontend | `npm start` | 3000 | http://localhost:3000 |

## 📡 API Endpoints

### Diets

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/diets` | Get all diets |
| GET | `/api/diets/{id}` | Get diet by ID |

### Cuisines

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/cuisines` | Get all cuisines |
| GET | `/api/cuisines/{id}` | Get cuisine by ID |

### Content

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/content` | Get all content |
| GET | `/api/content?dietId={id}&cuisineId={id}` | Get content by diet and cuisine |
| GET | `/api/content/{id}` | Get content by ID |

### H2 Database Console

Access the H2 console at: **http://localhost:8080/h2-console**

**Login Credentials:**
- JDBC URL: `jdbc:h2:mem:nourishnet`
- Username: `sa`
- Password: *(leave blank)*

## 📁 Project Structure
```
nourishnet/
├── nourishnet/                    # Backend (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/nourishnet/
│   │   │   │   ├── config/        # CORS & DataSeeder
│   │   │   │   ├── controller/    # REST Controllers
│   │   │   │   ├── entity/        # JPA Entities
│   │   │   │   ├── repository/    # Data Access Layer
│   │   │   │   ├── service/       # Business Logic
│   │   │   │   └── NourishnetApplication.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml                    # Maven dependencies
│
└── nourishnet-frontend/           # Frontend (React)
    ├── public/
    ├── src/
    │   ├── pages/                 # Page components
    │   │   ├── DietSelection.js
    │   │   ├── CuisineSelection.js
    │   │   └── ContentDisplay.js
    │   ├── services/
    │   │   └── api.js             # API calls
    │   ├── App.js                 # Routes
    │   └── index.js
    ├── package.json
    └── tailwind.config.js
```

## 🎨 Architecture

### Backend Architecture (3-Layer Pattern)
```
Controller Layer (REST API)
        ↓
Service Layer (Business Logic)
        ↓
Repository Layer (Data Access)
        ↓
Database (H2)
```

### Frontend Architecture
```
User → Pages → API Service → Backend
```

## 🔮 Future Enhancements

### Planned Features
- [ ] **Search & Filter**: Search content by title, filter by content type
- [ ] **Admin Panel**: Create, edit, and delete content items
- [ ] **User Authentication**: User login and personalized favorites
- [ ] **Image Display**: Show actual recipe images
- [ ] **Video Embedding**: Play videos directly in the app
- [ ] **Shopping List**: Generate shopping lists from recipes
- [ ] **Nutrition Info**: Add calorie and macro tracking
- [ ] **User Reviews**: Rate and review recipes
- [ ] **Deployment**: Deploy to cloud (Vercel + Railway/Render)

### Technical Improvements
- [ ] Add JUnit tests for backend
- [ ] Add Jest tests for frontend
- [ ] Switch to PostgreSQL for production
- [ ] Add ESLint & Prettier
- [ ] Add loading animations
- [ ] Implement caching
- [ ] Add error boundaries

## 🧪 Testing

### Manual Testing

1. **Test all diet combinations**: Try all 16 diet-cuisine pairs
2. **Verify data**: Check that 32 content items are displayed
3. **Test navigation**: Ensure back buttons work correctly
4. **Check console**: Verify no errors in browser console (F12)

### Automated Testing (Coming Soon)

- Backend: JUnit + Mockito
- Frontend: Jest + React Testing Library

## 🤝 Contributing

This is a personal learning project. Feel free to fork and experiment!

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your Name](https://linkedin.com/in/yourprofile)

## 🙏 Acknowledgments

- Built as a full-stack learning project
- Inspired by healthy eating and global cuisines
- Thanks to the Spring Boot and React communities

---

**⭐ If you found this project helpful, please give it a star!**