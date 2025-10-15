# CineTrack

<p align="center">
  <img src="./doc/icon.png" alt="CineTrack Icon" width="200"/>
</p>

Application mobile de gestion et de suivi de films et séries.

## Technologies

### Backend (API)
- **NestJS** - Framework Node.js
- **Prisma** - ORM pour PostgreSQL
- **PostgreSQL** - Base de données
- **Docker** - Conteneurisation
- **Swagger** - Documentation API

### Frontend (Mobile)
- **Kotlin** - Langage de programmation
- **Jetpack Compose** - UI moderne Android
- **Material Design 3** - Design system

## Prérequis

- Docker Desktop
- Node.js 18+
- Android Studio
- JDK 17+

## Installation

### 1. Cloner le projet

```bash
git clone https://github.com/apprenti1/CineTrack.git
cd CineTrack
```

### 2. Configuration API

Créer un fichier `.env` dans le dossier `api/` :

```env
POSTGRES_USER=cinetrack
POSTGRES_PASSWORD=cinetrack123
POSTGRES_DB=cinetrack
POSTGRES_PORT=5432
API_PORT=3000
DATABASE_URL=postgresql://cinetrack:cinetrack123@localhost:5432/cinetrack
```

Lancer les conteneurs Docker :

```bash
cd api
docker compose up -d
```

Installer les dépendances :

```bash
npm install
```

Générer le client Prisma :

```bash
npx prisma generate
npx prisma migrate dev
```

Démarrer l'API :

```bash
npm run start:dev
```

### 3. Configuration Android

Créer un fichier `app/local.properties` :

```properties
tmdb.api.key=VOTRE_CLE_API_TMDB
tmdb.base.url=https://api.themoviedb.org/3/
cinetrack.api.url=http://10.0.2.2:3000
```

Ouvrir le projet dans Android Studio et lancer l'application.

## API Endpoints

- **Documentation Swagger** : `http://localhost:3000/doc`
- **Health Check** : `http://localhost:3000/health`
- **API Routes** : `http://localhost:3000/api/*`

## Structure du projet

```
CineTrack/
├── api/                    # Backend NestJS
│   ├── src/
│   │   ├── health/        # Health check
│   │   ├── prisma/        # Service Prisma
│   │   └── main.ts        # Point d'entrée
│   ├── prisma/            # Schémas et migrations
│   └── docker-compose.yml
│
└── app/                    # Application Android
    └── app/src/main/java/fr/hainu/cinetrack/
        ├── ui/
        │   ├── screens/   # Écrans
        │   ├── components/# Composants réutilisables
        │   └── theme/     # Thème et styles
        └── MainActivity.kt
```

## Licence

Propriétaire
