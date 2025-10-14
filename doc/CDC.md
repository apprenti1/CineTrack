# 🎬 CineTrack - Cahier des Charges Détaillé

## Vue d'ensemble du projet

**CineTrack** est une application Android moderne de découverte et de suivi de films et séries TV. Elle permet aux utilisateurs de parcourir des contenus, gérer leurs watchlists, suivre leur historique de visionnage et obtenir des recommandations personnalisées.

---

## 🎯 Fonctionnalités Principales

### 1. **Authentification & Profil**
- Écran de splash avec animation
- Connexion (simulée localement ou via Firebase Auth)
- Inscription avec validation
- Profil utilisateur avec statistiques personnelles

### 2. **Écrans Principaux (6 écrans)**

#### **A. Home / Accueil** 🏠
**Contenu :**
- Carrousel des films/séries tendances du moment
- Section "Populaires cette semaine"
- Section "Sorties récentes au cinéma"
- Section "Séries du moment"
- Section "Recommandés pour vous" (basé sur l'historique)
- Barre de recherche rapide en haut

**API Endpoints utilisés :**
- `/trending/all/week` - Tendances de la semaine
- `/movie/popular` - Films populaires
- `/movie/now_playing` - Sorties cinéma
- `/tv/popular` - Séries populaires

**Fonctionnalités :**
- Pull-to-refresh pour actualiser
- Navigation horizontale avec LazyRow
- Clic sur une carte → Détails
- Skeleton loading pendant le chargement

---

#### **B. Explore / Découverte** 🔍
**Contenu :**
- Onglets : Films / Séries / Tout
- Filtres avancés :
  - Genre (Action, Comédie, Drame, etc.)
  - Année de sortie
  - Note minimale
  - Langue
  - Durée
- Tri par : Popularité, Note, Date de sortie, Titre
- Grille infinie avec pagination (LazyVerticalGrid)
- Recherche avec autocomplétion

**API Endpoints :**
- `/discover/movie` avec paramètres
- `/discover/tv` avec paramètres
- `/search/multi` pour la recherche
- `/genre/movie/list` pour les genres

**Fonctionnalités :**
- Sauvegarde des filtres en local (DataStore)
- Chargement progressif (infinite scroll)
- Chips pour les filtres actifs
- Animation des transitions

---

#### **C. Détails Film/Série** 📺
**Contenu :**
- Header avec backdrop image (parallax effect)
- Poster, titre, année, durée
- Note TMDB + nombre de votes
- Genres (chips)
- Synopsis complet
- Section "Casting" (acteurs principaux avec photos)
- Section "Équipe technique" (réalisateur, scénariste)
- Bande-annonce (lecture via YouTube/WebView)
- Section "Images" (galerie de photos)
- Section "Films/Séries similaires"
- Boutons d'action :
  - ❤️ Ajouter aux favoris
  - 📋 Ajouter à la watchlist
  - ✅ Marquer comme vu
  - ⭐ Noter (1-5 étoiles)
  - 📤 Partager

**API Endpoints :**
- `/movie/{id}` ou `/tv/{id}` - Détails complets
- `/movie/{id}/credits` - Cast & crew
- `/movie/{id}/videos` - Bandes-annonces
- `/movie/{id}/images` - Galerie
- `/movie/{id}/similar` - Contenus similaires

**Fonctionnalités :**
- Animation d'entrée élégante
- Sticky header avec effet de parallaxe
- Bottom sheet pour les actions
- Lecture de trailer intégrée
- Gestion des saisons pour les séries

---

#### **D. Ma Collection** 📚
**Onglets multiples :**

1. **Watchlist (À voir)**
   - Liste des films/séries à regarder
   - Possibilité de réorganiser (drag & drop)
   - Swipe pour retirer
   - Tri : Ajout récent, Alphabétique, Note

2. **Vus (History)**
   - Historique de visionnage
   - Date de visionnage
   - Note personnelle
   - Filtre par type (film/série)

3. **Favoris**
   - Contenus favoris
   - Organisation par collection personnalisée
   - Export/Import de la liste

4. **Collections personnalisées**
   - Créer des collections thématiques
   - Ex: "Film d'horreur", "Soirée entre amis", etc.

**Fonctionnalités :**
- Toutes les données stockées dans Room DB
- Mode offline complet
- Statistiques : nombre total vu, temps passé, genres préférés
- Export en CSV/JSON

---

#### **E. Statistiques & Profil** 📊
**Contenu :**
- Photo de profil et nom d'utilisateur
- Statistiques globales :
  - Nombre de films vus
  - Nombre de séries vues
  - Temps total de visionnage estimé
  - Note moyenne donnée
  - Genres favoris (graphique circulaire)
  - Acteurs/réalisateurs les plus vus
- Graphiques de progression :
  - Films vus par mois (bar chart)
  - Distribution des notes (histogram)
  - Timeline de visionnage
- Badges/achievements :
  - "Cinéphile débutant" (10 films)
  - "Marathonien" (5 séries complètes)
  - "Critique" (50 notes données)
  - etc.

**Fonctionnalités :**
- Graphiques avec Vico ou Charts (Jetpack Compose)
- Animation des statistiques
- Partage des statistiques sur réseaux sociaux
- Objectifs personnels (ex: voir 100 films cette année)

---

#### **F. Recherche Avancée** 🔎
**Contenu :**
- Barre de recherche avec suggestions en temps réel
- Historique des recherches (local)
- Résultats catégorisés :
  - Films
  - Séries
  - Personnes (acteurs, réalisateurs)
- Filtres rapides
- Recherche vocale (optionnel)

**Fonctionnalités :**
- Debounce sur la recherche (éviter trop d'appels API)
- Cache des résultats récents
- Highlighting des termes recherchés

---

### 3. **Écrans Secondaires**

#### **G. Détails Personne (Acteur/Réalisateur)**
- Biographie
- Photo
- Filmographie complète
- Réseaux sociaux
- Date de naissance

#### **H. Paramètres** ⚙️
- Choix du thème : Clair / Sombre / Automatique
- Langue de l'app
- Notifications (sorties, recommandations)
- Cache et stockage
- À propos / Crédits
- Se déconnecter

---

## 🏗️ Architecture Technique

### Stack Technologique

**Langage :** Kotlin 100%

**UI :** 
- Jetpack Compose (100%, aucun XML)
- Material Design 3
- Animations avec `androidx.compose.animation`

**Architecture :** MVVM
```
app/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   ├── entities/
│   │   └── database/
│   ├── remote/
│   │   ├── api/
│   │   ├── dto/
│   │   └── interceptors/
│   ├── repository/
│   └── mapper/
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
├── presentation/
│   ├── navigation/
│   ├── screens/
│   │   ├── home/
│   │   ├── explore/
│   │   ├── details/
│   │   ├── collection/
│   │   ├── profile/
│   │   └── search/
│   ├── components/ (composables réutilisables)
│   └── theme/
└── di/ (Hilt modules)
```

**Bibliothèques :**

- **Networking :** Retrofit + OkHttp + Gson
- **Async :** Coroutines + Flow
- **Database :** Room DB
- **DI :** Hilt
- **Images :** Coil
- **Navigation :** Jetpack Compose Navigation
- **DataStore :** Preferences DataStore (pour settings)
- **Charts :** Vico ou Compose Charts
- **Testing :** JUnit5, Mockito, Turbine (pour Flow), Compose UI Testing

---

### Modèle de Données Room DB

```kotlin
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: String, // JSON string
    val runtime: Int?,
    val type: String // "movie" or "tv"
)

@Entity(tableName = "watchlist")
data class WatchlistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val addedDate: Long,
    val priority: Int = 0
)

@Entity(tableName = "watched")
data class WatchedEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val movieId: Int,
    val watchedDate: Long,
    val userRating: Float?,
    val notes: String?
)

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val movieId: Int,
    val addedDate: Long
)

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String?,
    val createdDate: Long
)

@Entity(tableName = "collection_items")
data class CollectionItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val collectionId: Int,
    val movieId: Int
)
```

---

## 🎨 Design & UX

### Thème Dark Mode
- **Couleurs principales :**
  - Primary: Deep Purple / Electric Blue
  - Background Dark: #121212
  - Surface: #1E1E1E
  - Accent: Gold/Amber pour les notes

- **Couleurs Light Mode :**
  - Primary: Purple 500
  - Background: #FAFAFA
  - Surface: White

### Composants Réutilisables
- `MovieCard` - Carte de film avec poster
- `PersonCard` - Carte acteur/réalisateur
- `RatingBar` - Barre de notation avec étoiles
- `GenreChip` - Chip de genre
- `LoadingShimmer` - Skeleton loader
- `ErrorScreen` - Écran d'erreur avec retry
- `EmptyState` - État vide avec illustration
- `SearchBar` - Barre de recherche customisée
- `FilterBottomSheet` - Bottom sheet de filtres
- `StatCard` - Carte de statistique animée

### Animations
- Transition entre écrans (slide, fade)
- Animation du splash screen
- Parallax effect sur les détails
- Shimmer loading
- Like animation (heart beat)
- Pull to refresh custom

---

## 🔌 API - TMDB (The Movie Database)

### Configuration
- **Base URL :** `https://api.themoviedb.org/3/`
- **Image Base URL :** `https://image.tmdb.org/t/p/`
- **Clé API :** Gratuite, obtenir sur https://www.themoviedb.org/settings/api

### Endpoints Principaux

**Découverte :**
- `GET /trending/{media_type}/{time_window}` - Tendances
- `GET /discover/movie` - Découvrir films
- `GET /discover/tv` - Découvrir séries
- `GET /movie/popular` - Films populaires
- `GET /movie/top_rated` - Films mieux notés
- `GET /movie/now_playing` - En salle

**Détails :**
- `GET /movie/{movie_id}` - Détails film
- `GET /tv/{tv_id}` - Détails série
- `GET /movie/{movie_id}/credits` - Cast & crew
- `GET /movie/{movie_id}/videos` - Vidéos/trailers
- `GET /movie/{movie_id}/images` - Images
- `GET /movie/{movie_id}/similar` - Similaires
- `GET /movie/{movie_id}/recommendations` - Recommandations

**Recherche :**
- `GET /search/multi` - Recherche globale
- `GET /search/movie` - Recherche films
- `GET /search/tv` - Recherche séries
- `GET /search/person` - Recherche personnes

**Personnes :**
- `GET /person/{person_id}` - Détails personne
- `GET /person/{person_id}/movie_credits` - Filmographie

**Configuration :**
- `GET /genre/movie/list` - Liste des genres
- `GET /configuration` - Config images

### Gestion du Cache
- Cache des images avec Coil (disk + memory)
- Cache des requêtes API avec Room (1 heure de validité)
- Mode offline : affichage des données en cache

---

## 🗄️ Backend API - NestJS + Prisma + PostgreSQL

### Architecture Backend

**Stack Technique :**
- **Framework :** NestJS (TypeScript)
- **ORM :** Prisma
- **Base de données :** PostgreSQL
- **Authentification :** JWT (JSON Web Tokens)
- **Validation :** class-validator
- **Documentation :** Swagger/OpenAPI

**Rôle du Backend :**
Le backend custom gère toutes les données utilisateurs et interactions sociales :
- Profils utilisateurs
- Authentification/Autorisation
- Watchlists personnelles
- Favoris et films vus
- Notes et commentaires
- Collections personnalisées
- Statistiques utilisateurs
- Likes et interactions sociales

### Schéma de Base de Données Prisma

```prisma
// schema.prisma

generator client {
  provider = "prisma-client-js"
}

datasource db {
  provider = "postgresql"
  url      = env("DATABASE_URL")
}

model User {
  id            String       @id @default(uuid())
  email         String       @unique
  username      String       @unique
  password      String       // Hash avec bcrypt
  fullName      String?
  avatar        String?
  bio           String?
  createdAt     DateTime     @default(now())
  updatedAt     DateTime     @updatedAt
  
  // Relations
  watchlist     WatchlistItem[]
  favorites     Favorite[]
  watched       Watched[]
  reviews       Review[]
  collections   Collection[]
  likes         ReviewLike[]
  
  @@map("users")
}

model WatchlistItem {
  id          String    @id @default(uuid())
  userId      String
  movieId     Int       // ID du film TMDB
  movieData   Json      // Cache des données TMDB
  priority    Int       @default(0)
  addedAt     DateTime  @default(now())
  
  user        User      @relation(fields: [userId], references: [id], onDelete: Cascade)
  
  @@unique([userId, movieId])
  @@map("watchlist_items")
}

model Favorite {
  id          String    @id @default(uuid())
  userId      String
  movieId     Int
  movieData   Json
  addedAt     DateTime  @default(now())
  
  user        User      @relation(fields: [userId], references: [id], onDelete: Cascade)
  
  @@unique([userId, movieId])
  @@map("favorites")
}

model Watched {
  id          String    @id @default(uuid())
  userId      String
  movieId     Int
  movieData   Json
  watchedAt   DateTime  @default(now())
  userRating  Float?    // Note personnelle 0-5
  notes       String?   // Notes privées
  
  user        User      @relation(fields: [userId], references: [id], onDelete: Cascade)
  
  @@unique([userId, movieId])
  @@map("watched")
}

model Review {
  id          String       @id @default(uuid())
  userId      String
  movieId     Int
  rating      Float        // 1-5
  comment     String
  createdAt   DateTime     @default(now())
  updatedAt   DateTime     @updatedAt
  
  user        User         @relation(fields: [userId], references: [id], onDelete: Cascade)
  likes       ReviewLike[]
  
  @@unique([userId, movieId])
  @@map("reviews")
}

model ReviewLike {
  id          String    @id @default(uuid())
  userId      String
  reviewId    String
  createdAt   DateTime  @default(now())
  
  user        User      @relation(fields: [userId], references: [id], onDelete: Cascade)
  review      Review    @relation(fields: [reviewId], references: [id], onDelete: Cascade)
  
  @@unique([userId, reviewId])
  @@map("review_likes")
}

model Collection {
  id          String             @id @default(uuid())
  userId      String
  name        String
  description String?
  isPublic    Boolean            @default(false)
  createdAt   DateTime           @default(now())
  updatedAt   DateTime           @updatedAt
  
  user        User               @relation(fields: [userId], references: [id], onDelete: Cascade)
  items       CollectionItem[]
  
  @@map("collections")
}

model CollectionItem {
  id            String      @id @default(uuid())
  collectionId  String
  movieId       Int
  movieData     Json
  addedAt       DateTime    @default(now())
  order         Int         @default(0)
  
  collection    Collection  @relation(fields: [collectionId], references: [id], onDelete: Cascade)
  
  @@unique([collectionId, movieId])
  @@map("collection_items")
}
```

### Endpoints API Backend

**Base URL :** `https://api.cinetrack.com/v1` (ou votre URL de déploiement)

#### **Authentification**

```
POST   /auth/register              - Inscription
POST   /auth/login                 - Connexion (retourne JWT)
POST   /auth/refresh               - Rafraîchir le token
GET    /auth/me                    - Profil utilisateur actuel
PUT    /auth/profile               - Mettre à jour le profil
POST   /auth/logout                - Déconnexion
```

**Exemple Request :**
```typescript
// POST /auth/register
{
  "email": "john@example.com",
  "username": "johndoe",
  "password": "SecurePass123!",
  "fullName": "John Doe"
}

// Response
{
  "user": {
    "id": "uuid",
    "email": "john@example.com",
    "username": "johndoe"
  },
  "accessToken": "eyJhbGc...",
  "refreshToken": "eyJhbGc..."
}
```

#### **Watchlist**

```
GET    /watchlist                  - Récupérer la watchlist
POST   /watchlist                  - Ajouter un film
DELETE /watchlist/:movieId         - Retirer un film
PUT    /watchlist/:movieId/priority - Modifier la priorité
```

**Exemple Request :**
```typescript
// POST /watchlist
{
  "movieId": 693134,
  "movieData": {
    "title": "Dune: Part Two",
    "posterPath": "/path.jpg",
    "releaseDate": "2024-03-01",
    "voteAverage": 8.5
  },
  "priority": 1
}
```

#### **Favoris**

```
GET    /favorites                  - Récupérer les favoris
POST   /favorites                  - Ajouter aux favoris
DELETE /favorites/:movieId         - Retirer des favoris
```

#### **Films vus**

```
GET    /watched                    - Récupérer l'historique
POST   /watched                    - Marquer comme vu
PUT    /watched/:movieId           - Modifier note/notes
DELETE /watched/:movieId           - Retirer de l'historique
```

**Exemple Request :**
```typescript
// POST /watched
{
  "movieId": 693134,
  "movieData": { ... },
  "userRating": 4.5,
  "notes": "Excellente suite, visuellement époustouflant"
}
```

#### **Avis & Commentaires**

```
GET    /reviews/movie/:movieId     - Avis d'un film
POST   /reviews                    - Créer un avis
PUT    /reviews/:reviewId          - Modifier un avis
DELETE /reviews/:reviewId          - Supprimer un avis
POST   /reviews/:reviewId/like     - Liker un avis
DELETE /reviews/:reviewId/like     - Retirer le like
```

**Exemple Request :**
```typescript
// POST /reviews
{
  "movieId": 693134,
  "rating": 5,
  "comment": "Un chef-d'œuvre absolu ! Denis Villeneuve..."
}

// Response
{
  "id": "uuid",
  "user": {
    "id": "uuid",
    "username": "johndoe",
    "avatar": "url"
  },
  "movieId": 693134,
  "rating": 5,
  "comment": "Un chef-d'œuvre absolu...",
  "likesCount": 0,
  "isLikedByCurrentUser": false,
  "createdAt": "2024-03-15T10:30:00Z"
}
```

#### **Collections**

```
GET    /collections                - Récupérer mes collections
POST   /collections                - Créer une collection
PUT    /collections/:id            - Modifier une collection
DELETE /collections/:id            - Supprimer une collection
POST   /collections/:id/items      - Ajouter un film
DELETE /collections/:id/items/:movieId - Retirer un film
PUT    /collections/:id/items/reorder - Réorganiser les films
```

#### **Statistiques**

```
GET    /stats/me                   - Mes statistiques globales
GET    /stats/genres               - Répartition par genres
GET    /stats/timeline             - Timeline de visionnage
GET    /stats/achievements         - Badges obtenus
```

**Exemple Response :**
```typescript
// GET /stats/me
{
  "moviesWatched": 156,
  "seriesWatched": 42,
  "totalWatchTime": 325, // heures
  "averageRating": 7.8,
  "moviesThisYear": 48,
  "favoriteGenres": [
    { "genre": "Sci-Fi", "count": 45, "percentage": 28.8 },
    { "genre": "Action", "count": 32, "percentage": 20.5 }
  ],
  "achievements": [
    { "id": "100_movies", "name": "100 Films", "unlockedAt": "2024-01-15" }
  ]
}
```

### Architecture Backend NestJS

```
backend/
├── src/
│   ├── auth/
│   │   ├── auth.controller.ts
│   │   ├── auth.service.ts
│   │   ├── auth.module.ts
│   │   ├── jwt.strategy.ts
│   │   └── dto/
│   │       ├── register.dto.ts
│   │       └── login.dto.ts
│   │
│   ├── users/
│   │   ├── users.controller.ts
│   │   ├── users.service.ts
│   │   └── users.module.ts
│   │
│   ├── watchlist/
│   │   ├── watchlist.controller.ts
│   │   ├── watchlist.service.ts
│   │   ├── watchlist.module.ts
│   │   └── dto/
│   │
│   ├── favorites/
│   ├── watched/
│   ├── reviews/
│   ├── collections/
│   ├── stats/
│   │
│   ├── prisma/
│   │   ├── prisma.service.ts
│   │   └── prisma.module.ts
│   │
│   ├── common/
│   │   ├── guards/
│   │   │   └── jwt-auth.guard.ts
│   │   ├── decorators/
│   │   │   └── current-user.decorator.ts
│   │   └── filters/
│   │
│   ├── app.module.ts
│   └── main.ts
│
├── prisma/
│   ├── schema.prisma
│   └── migrations/
│
├── .env
├── package.json
└── tsconfig.json
```

### Exemple de Service NestJS

```typescript
// watchlist/watchlist.service.ts

@Injectable()
export class WatchlistService {
  constructor(private prisma: PrismaService) {}

  async getWatchlist(userId: string) {
    return this.prisma.watchlistItem.findMany({
      where: { userId },
      orderBy: [
        { priority: 'desc' },
        { addedAt: 'desc' }
      ]
    });
  }

  async addToWatchlist(userId: string, dto: AddToWatchlistDto) {
    return this.prisma.watchlistItem.create({
      data: {
        userId,
        movieId: dto.movieId,
        movieData: dto.movieData,
        priority: dto.priority ?? 0
      }
    });
  }

  async removeFromWatchlist(userId: string, movieId: number) {
    return this.prisma.watchlistItem.delete({
      where: {
        userId_movieId: {
          userId,
          movieId
        }
      }
    });
  }
}
```

### Intégration Android → Backend

**Dans l'app Android :**

```kotlin
// data/remote/api/CineTrackApiService.kt

interface CineTrackApiService {
    
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>
    
    @GET("watchlist")
    suspend fun getWatchlist(
        @Header("Authorization") token: String
    ): Response<List<WatchlistItem>>
    
    @POST("watchlist")
    suspend fun addToWatchlist(
        @Header("Authorization") token: String,
        @Body request: AddWatchlistRequest
    ): Response<WatchlistItem>
    
    @GET("reviews/movie/{movieId}")
    suspend fun getMovieReviews(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10
    ): Response<ReviewsResponse>
    
    @POST("reviews")
    suspend fun submitReview(
        @Header("Authorization") token: String,
        @Body request: SubmitReviewRequest
    ): Response<Review>
    
    @GET("stats/me")
    suspend fun getMyStats(
        @Header("Authorization") token: String
    ): Response<UserStats>
}
```

### Flow Complet de Données

```
┌─────────────────────────────────────────────────────┐
│              Android App (Kotlin)                    │
│                                                       │
│  ┌─────────────┐      ┌──────────────┐              │
│  │  ViewModel  │ ───> │  Repository  │              │
│  └─────────────┘      └──────────────┘              │
│                              │                        │
│                    ┌─────────┴─────────┐            │
│                    │                   │            │
│             ┌──────▼──────┐    ┌──────▼──────┐     │
│             │ TMDB API    │    │ Backend API │     │
│             │ (Films)     │    │ (Utilisateur)│     │
│             └──────┬──────┘    └──────┬──────┘     │
│                    │                   │            │
│             ┌──────▼──────┐    ┌──────▼──────┐     │
│             │  Room Cache │    │  DataStore  │     │
│             │  (Films)    │    │  (Auth)     │     │
│             └─────────────┘    └─────────────┘     │
└─────────────────────────────────────────────────────┘
                       │                   │
                       └───────┬───────────┘
                               │
                    ┌──────────▼───────────┐
                    │   Backend NestJS     │
                    │                      │
                    │   ┌──────────────┐   │
                    │   │   Prisma     │   │
                    │   └──────┬───────┘   │
                    │          │           │
                    │   ┌──────▼───────┐   │
                    │   │ PostgreSQL   │   │
                    │   └──────────────┘   │
                    └──────────────────────┘
```

### Variables d'Environnement Backend

```env
# .env

# Database
DATABASE_URL="postgresql://user:password@localhost:5432/cinetrack?schema=public"

# JWT
JWT_SECRET="your-super-secret-jwt-key-change-in-production"
JWT_EXPIRES_IN="7d"
JWT_REFRESH_SECRET="your-refresh-secret"
JWT_REFRESH_EXPIRES_IN="30d"

# Server
PORT=3000
NODE_ENV="development"

# CORS
CORS_ORIGIN="http://localhost:3000,https://your-app.com"

# Rate Limiting
RATE_LIMIT_TTL=60
RATE_LIMIT_MAX=100
```

### Déploiement Backend

**Options recommandées :**

1. **Heroku** (Simple, gratuit tier disponible)
2. **Railway** (Moderne, facile)
3. **Render** (Free tier PostgreSQL inclus)
4. **AWS/GCP** (Plus professionnel mais complexe)
5. **Docker + VPS** (DigitalOcean, Linode)

**Commandes de déploiement :**

```bash
# Build
npm run build

# Migrations Prisma
npx prisma migrate deploy

# Start production
npm run start:prod
```

### Sécurité Backend

**Mesures implémentées :**

- ✅ **Authentification JWT** avec refresh tokens
- ✅ **Hashing des mots de passe** (bcrypt)
- ✅ **Validation des données** (class-validator)
- ✅ **Rate limiting** (prévenir spam)
- ✅ **CORS configuré** (origines autorisées)
- ✅ **Helmet** (headers de sécurité HTTP)
- ✅ **Guards NestJS** (protection des routes)
- ✅ **Variables d'environnement** sécurisées

### Tests Backend

```typescript
// watchlist/watchlist.service.spec.ts

describe('WatchlistService', () => {
  let service: WatchlistService;
  let prisma: PrismaService;

  beforeEach(async () => {
    const module = await Test.createTestingModule({
      providers: [WatchlistService, PrismaService],
    }).compile();

    service = module.get<WatchlistService>(WatchlistService);
    prisma = module.get<PrismaService>(PrismaService);
  });

  it('should add movie to watchlist', async () => {
    const userId = 'user-123';
    const movieId = 693134;
    
    const result = await service.addToWatchlist(userId, {
      movieId,
      movieData: { title: 'Dune' },
    });

    expect(result.movieId).toBe(movieId);
  });
});
```

---

## 📊 Récapitulatif des Données

### Données venant de TMDB API
- ✅ Informations films/séries
- ✅ Cast & crew
- ✅ Images & vidéos
- ✅ Genres
- ✅ Films similaires
- ✅ Recherche

### Données venant du Backend Custom
- ✅ Profils utilisateurs
- ✅ Authentification
- ✅ Watchlists personnelles
- ✅ Favoris
- ✅ Historique de visionnage
- ✅ Notes utilisateurs
- ✅ Commentaires/Avis
- ✅ Collections personnalisées
- ✅ Likes sur avis
- ✅ Statistiques personnelles

### Données en Cache Local (Room)
- ✅ Films TMDB (cache temporaire)
- ✅ Watchlist (sync avec backend)
- ✅ Token JWT
- ✅ Préférences utilisateur

---

## ✅ Tests Unitaires

### Couverture >= 50%

**ViewModels :**
```kotlin
@Test
fun `when fetching trending movies succeeds, state is updated`() = runTest {
    // Given
    val movies = listOf(mockMovie1, mockMovie2)
    whenever(repository.getTrendingMovies()).thenReturn(flowOf(Result.success(movies)))
    
    // When
    viewModel.loadTrendingMovies()
    
    // Then
    assertEquals(movies, viewModel.uiState.value.trendingMovies)
    assertFalse(viewModel.uiState.value.isLoading)
}

@Test
fun `when adding to watchlist, repository is called`() = runTest {
    // Given
    val movieId = 123
    
    // When
    viewModel.addToWatchlist(movieId)
    
    // Then
    verify(repository).addToWatchlist(movieId)
    assertTrue(viewModel.uiState.value.isInWatchlist)
}
```

**Repositories :**
```kotlin
@Test
fun `when api call fails, return cached data`() = runTest {
    // Given
    val cachedMovies = listOf(mockMovie1)
    whenever(localDataSource.getMovies()).thenReturn(cachedMovies)
    whenever(remoteDataSource.getMovies()).thenThrow(IOException())
    
    // When
    val result = repository.getMovies().first()
    
    // Then
    assertTrue(result.isSuccess)
    assertEquals(cachedMovies, result.getOrNull())
}

@Test
fun `when api succeeds, data is saved to cache`() = runTest {
    // Given
    val remoteMovies = listOf(mockMovie1, mockMovie2)
    whenever(remoteDataSource.getMovies()).thenReturn(remoteMovies)
    
    // When
    repository.getMovies().first()
    
    // Then
    verify(localDataSource).saveMovies(remoteMovies)
}
```

**Use Cases :**
```kotlin
@Test
fun `add to watchlist use case saves to database`() = runTest {
    // Given
    val movie = mockMovie1
    
    // When
    val result = addToWatchlistUseCase(movie)
    
    // Then
    assertTrue(result.isSuccess)
    verify(repository).addToWatchlist(movie.id)
}
```

**Composables (UI Tests) :**
```kotlin
@Test
fun `clicking on movie card navigates to details`() {
    var clickedMovieId: Int? = null
    
    composeTestRule.setContent {
        MovieCard(
            movie = mockMovie,
            onClick = { clickedMovieId = it }
        )
    }
    
    composeTestRule.onNodeWithTag("movieCard").performClick()
    assertEquals(mockMovie.id, clickedMovieId)
}

@Test
fun `watchlist screen shows empty state when list is empty`() {
    composeTestRule.setContent {
        WatchlistScreen(movies = emptyList())
    }
    
    composeTestRule.onNodeWithText("Votre watchlist est vide").assertIsDisplayed()
}
```

---

## 🌳 Gestion Git

### Structure des Branches
```
main (production)
├── develop (développement)
    ├── feature/authentication
    ├── feature/home-screen
    ├── feature/movie-details
    ├── feature/watchlist
    ├── feature/search
    ├── feature/profile-stats
    ├── bugfix/image-loading
    └── refactor/repository-layer
```

### Convention de Commits
```
feat: Add movie details screen
fix: Resolve crash on empty watchlist
refactor: Improve ViewModel state management
test: Add unit tests for HomeViewModel
docs: Update README with setup instructions
style: Apply Material 3 theming
chore: Update dependencies
```

### Workflow Git
1. Créer une branche depuis `develop`
2. Développer la feature
3. Commiter régulièrement avec des messages clairs
4. Pusher la branche
5. Créer une Pull Request vers `develop`
6. Code review par un autre membre
7. Merge après validation
8. Supprimer la branche feature

### Pull Request Template
```markdown
## Description
Brief description of changes

## Type of Change
- [ ] New feature
- [ ] Bug fix
- [ ] Refactoring
- [ ] Documentation

## Screenshots (if UI changes)

## Checklist
- [ ] Code compiles
- [ ] Tests pass
- [ ] No XML files added
- [ ] Follows MVVM architecture
- [ ] Dark mode tested
```

---

## 📱 Fonctionnalités Bonus (Optionnelles)

1. **Mode "Soirée Film"** 🎲
   - Sélection aléatoire d'un film de la watchlist
   - Filtres: genre, durée, humeur
   - Animation de roulette

2. **Notifications Push**
   - Rappel watchlist
   - Sorties de films attendus
   - Anniversaire d'acteurs favoris
   - Nouveautés dans les genres préférés

3. **Widget Home Screen**
   - Film du jour
   - Statistiques rapides
   - Accès rapide à la watchlist

4. **Partage Social**
   - Partager un film avec image
   - Partager ses stats annuelles
   - Créer des images "Year in Review"

5. **Mode Groupe**
   - Watchlist partagée entre amis
   - Vote pour choisir le film de la soirée
   - Chat de groupe

6. **Intégration IA**
   - Recommandations intelligentes basées sur l'historique
   - Chat pour trouver un film ("je veux une comédie récente")
   - Analyse de préférences

7. **Chromecast / Streaming**
   - Liens vers Netflix, Amazon Prime, Disney+
   - Vérifier la disponibilité du film
   - Comparaison de prix

8. **Gamification**
   - Badges et achievements
   - Niveaux (Novice → Cinéphile → Expert)
   - Défis mensuels
   - Leaderboard entre amis

---

## 📋 Checklist de Développement

### Phase 1 - Setup (Semaine 1)
- [ ] Initialisation du projet Android Studio
- [ ] Configuration Gradle (Kotlin DSL)
- [ ] Setup Hilt pour DI
- [ ] Configuration TMDB API
- [ ] Architecture de base (création des packages)
- [ ] Thème Material 3 + Dark mode
- [ ] Navigation skeleton avec Compose Navigation
- [ ] Écran Splash avec animation

### Phase 2 - Écrans Principaux (Semaines 2-3)
- [ ] Écran Home avec sections multiples
- [ ] Intégration API pour trending/popular
- [ ] Écran Explore + filtres avancés
- [ ] Écran Détails complet avec parallax
- [ ] Écran Ma Collection (4 onglets)
- [ ] Navigation entre tous les écrans
- [ ] Gestion des états de chargement

### Phase 3 - Base de Données (Semaine 3)
- [ ] Création des Entities Room
- [ ] DAOs pour toutes les opérations
- [ ] Repository pattern avec cache
- [ ] Watchlist CRUD operations
- [ ] Favoris et Watched CRUD
- [ ] Synchronisation API ↔ Room
- [ ] Migration database

### Phase 4 - Features Avancées (Semaine 4)
- [ ] Écran Statistiques avec graphiques
- [ ] Écran Recherche avancée
- [ ] Gestion complète des erreurs
- [ ] Mode offline fonctionnel
- [ ] Optimisation performances (LazyColumn)
- [ ] Debounce sur recherche
- [ ] Pull-to-refresh

### Phase 5 - Polish & Tests (Semaines 5-6)
- [ ] Tests unitaires ViewModels (>50%)
- [ ] Tests unitaires Repositories
- [ ] Tests UI avec Compose Testing
- [ ] Animations et transitions
- [ ] Correction de tous les bugs
- [ ] Optimisation mémoire et batterie
- [ ] Documentation inline
- [ ] README complet
- [ ] Préparation soutenance

---

## 📖 README.md Suggéré

```markdown
# 🎬 CineTrack - Your Personal Movie & TV Tracker

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-blue.svg)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

## 📱 Description
CineTrack est une application Android moderne permettant de découvrir, suivre et gérer ses films et séries préférés. Développée entièrement en Kotlin avec Jetpack Compose, elle offre une expérience utilisateur fluide et intuitive.

## ✨ Fonctionnalités
- 🔍 Découverte de films et séries via TMDB API
- 📋 Gestion de watchlist personnalisée
- ⭐ Notation et système de favoris
- 📊 Statistiques de visionnage détaillées
- 🌙 Dark mode natif
- 📴 Mode offline complet
- 🎨 Interface moderne Material Design 3
- 🔄 Synchronisation en temps réel

## 🛠️ Technologies
- **Langage :** Kotlin 100%
- **UI Framework :** Jetpack Compose
- **Architecture :** MVVM (Model-View-ViewModel)
- **Database :** Room DB
- **Networking :** Retrofit + OkHttp
- **Async :** Coroutines + Flow
- **Dependency Injection :** Hilt
- **Image Loading :** Coil
- **Testing :** JUnit5, Mockito, Turbine

## 📦 Installation

### Prérequis
- Android Studio Hedgehog ou supérieur
- JDK 17
- Android SDK 34
- Clé API TMDB

### Étapes
1. Cloner le repository
```bash
git clone https://github.com/votre-equipe/cinetrack.git
cd cinetrack
```

2. Obtenir une clé API TMDB
   - Créer un compte sur https://www.themoviedb.org
   - Aller dans Settings → API
   - Générer une clé API v3

3. Ajouter la clé dans `local.properties`
```properties
TMDB_API_KEY=votre_clé_api_ici
```

4. Sync Gradle et Build le projet
5. Run sur émulateur ou appareil physique

## 🏗️ Architecture

```
app/
├── data/              # Data layer
│   ├── local/         # Room DB
│   ├── remote/        # API calls
│   └── repository/    # Repository implementations
├── domain/            # Business logic
│   ├── model/         # Domain models
│   ├── repository/    # Repository interfaces
│   └── usecase/       # Use cases
├── presentation/      # UI layer
│   ├── screens/       # Composable screens
│   ├── components/    # Reusable composables
│   ├── navigation/    # Navigation graph
│   └── theme/         # Material theming
└── di/                # Hilt modules
```

## 📸 Screenshots

| Home | Details | Watchlist | Stats |
|------|---------|-----------|-------|
| ![](screenshots/home.png) | ![](screenshots/details.png) | ![](screenshots/watchlist.png) | ![](screenshots/stats.png) |

## 👥 Équipe
- **[Nom 1]** - Feature Home & API Integration
  - Setup Retrofit et API calls
  - Écran d'accueil avec sections
  - Gestion du cache
  
- **[Nom 2]** - Feature Watchlist & Database
  - Configuration Room DB
  - Écran Ma Collection
  - Synchronisation offline
  
- **[Nom 3]** - Feature Details & UI Components
  - Écran détails avec parallax
  - Composables réutilisables
  - Thème et animations
  
- **[Nom 4]** - Feature Search & Statistics
  - Écran de recherche avancée
  - Statistiques et graphiques
  - Tests unitaires

## 📊 Couverture Tests
- **Tests unitaires :** 52%
- **ViewModels :** 70%
- **Repositories :** 60%
- **Use Cases :** 45%

## 🔄 Workflow Git
- `main` : Production stable
- `develop` : Développement actif
- `feature/*` : Nouvelles fonctionnalités
- `bugfix/*` : Corrections de bugs

## 📝 Licence
Ce projet est sous licence MIT. Voir [LICENSE](LICENSE) pour plus de détails.

## 🙏 Remerciements
- [TMDB](https://www.themoviedb.org) pour l'API
- [Material Design](https://m3.material.io) pour les guidelines
- [Jetpack Compose](https://developer.android.com/jetpack/compose) community

## 📞 Contact
Pour toute question, contactez-nous à : votre-email@exemple.com
```

---

## 🎯 Répartition des Tâches (Équipe de 4)

### **Membre 1 - API & Home** 🌐
**Responsabilités :**
- Setup Retrofit et configuration API
- Intercepteurs (logging, authentification)
- DTOs et parsing JSON
- Écran Home complet
- Gestion du cache API (stratégie)
- Pull-to-refresh

**Livrables :**
- Package `data/remote/` complet
- `HomeScreen.kt` et `HomeViewModel.kt`
- Tests des API calls
- Documentation des endpoints

---

### **Membre 2 - Database & Collections** 💾
**Responsabilités :**
- Setup Room Database
- Création de toutes les Entities
- DAOs avec requêtes complexes
- Écran Ma Collection (4 onglets)
- Logique watchlist/favoris/watched
- Synchronisation offline
- Migrations database

**Livrables :**
- Package `data/local/` complet
- `CollectionScreen.kt` et ViewModels associés
- Tests des DAOs
- Scripts de migration

---

### **Membre 3 - UI/UX & Détails** 🎨
**Responsabilités :**
- Écran Détails avec parallax
- Tous les composables réutilisables
- Thème Material 3 (light + dark)
- Animations et transitions
- Gestion des images (Coil)
- Design system

**Livrables :**
- Package `presentation/components/`
- Package `presentation/theme/`
- `DetailsScreen.kt` et ViewModel
- Guide de style (Figma optionnel)

---

### **Membre 4 - Search, Stats & Tests** 🔍
**Responsabilités :**
- Écran Recherche avec filtres
- Écran Statistiques avec graphiques
- Implémentation des charts
- Tests unitaires (tous les modules)
- CI/CD (optionnel)
- Documentation et README

**Livrables :**
- `SearchScreen.kt` et `StatsScreen.kt`
- Package `test/` avec >50% couverture
- README.md complet
- Documentation technique

---

## 📅 Planning Détaillé (6 semaines)

### **Semaine 1 : Setup & Fondations**
**Lundi-Mardi :**
- Réunion de kick-off
- Setup du projet
- Configuration Git (branches, .gitignore)
- Attribution des rôles

**Mercredi-Vendredi :**
- Membre 1 : Setup API TMDB
- Membre 2 : Setup Room DB
- Membre 3 : Setup Material Theme
- Membre 4 : Setup tests framework

**Livrable :** Architecture de base fonctionnelle

---

### **Semaine 2 : Écrans Principaux**
**Objectifs :**
- Home screen fonctionnel
- Détails screen avec données API
- Navigation entre écrans

**Répartition :**
- Membre 1 : Intégration API dans Home
- Membre 2 : Entities et DAOs
- Membre 3 : UI du Details screen
- Membre 4 : Navigation graph

**Livrable :** 2 écrans navigables

---

### **Semaine 3 : Features Core**
**Objectifs :**
- Watchlist fonctionnelle
- Recherche basique
- Offline mode

**Répartition :**
- Membre 1 : Cache API
- Membre 2 : Collection screen complet
- Membre 3 : Composables avancés
- Membre 4 : Search screen

**Livrable :** Application utilisable

---

### **Semaine 4 : Features Avancées**
**Objectifs :**
- Statistiques
- Filtres avancés
- Dark mode complet
- Animations

**Répartition :**
- Membre 1 : Optimisation API (pagination)
- Membre 2 : Collections personnalisées
- Membre 3 : Animations et transitions
- Membre 4 : Stats screen avec graphiques

**Livrable :** Features avancées complètes

---

### **Semaine 5 : Tests & Polish**
**Objectifs :**
- Tests unitaires >50%
- Correction bugs
- Optimisation performances

**Répartition :**
- Membre 1 : Tests API et repositories
- Membre 2 : Tests database
- Membre 3 : Tests UI et polish
- Membre 4 : Consolidation tests et doc

**Livrable :** Application stable et testée

---

### **Semaine 6 : Finalisation & Soutenance**
**Objectifs :**
- Derniers ajustements
- Préparation démo
- Documentation finale

**Tous ensemble :**
- Répétition de la présentation
- Préparation des slides
- README et documentation
- Vidéo démo (optionnel)

**Livrable :** Application prête pour soutenance

---

## 🎤 Guide de Soutenance (15-20 minutes)

### Structure de la Présentation

#### **1. Introduction (2 min)**
- Présentation de l'équipe
- Choix du projet CineTrack
- Objectifs et motivations

#### **2. Démonstration Live (8 min)**
**Scénario utilisateur :**
1. Lancement de l'app (splash screen)
2. Navigation Home → parcours des sections
3. Clic sur un film → écran détails
4. Ajout à la watchlist
5. Navigation vers Ma Collection
6. Marquer comme vu et noter
7. Voir les statistiques
8. Recherche d'un film/série
9. Basculer en dark mode
10. Mode offline (désactiver wifi)

**Points à montrer :**
- Fluidité des animations
- Réactivité de l'UI
- Gestion des états de chargement
- Dark mode
- Mode offline fonctionnel

#### **3. Aspects Techniques (7 min)**

**A. Architecture MVVM (2 min)**
- Schéma de l'architecture
- Séparation des couches
- Flow de données

**B. Contraintes Techniques (3 min)**
- ✅ 6 écrans principaux + secondaires
- ✅ API TMDB intégrée
- ✅ Room DB avec 6 tables
- ✅ Dark mode natif
- ✅ 100% Kotlin + Compose
- ✅ Tests >50%

**C. Défis & Solutions (2 min)**
- Gestion du cache et offline mode
- Optimisation des recompositions
- Pagination et infinite scroll
- Tests des composables

#### **4. Gestion Git (2 min)**
- Montrer l'historique Git (gitk ou GitHub)
- Branches et workflow
- Contributions de chaque membre
- Pull requests et code reviews

#### **5. Questions & Réponses (3 min)**

---

### Slides Recommandées

**Slide 1 :** Page de titre
- Logo CineTrack
- Noms de l'équipe
- Date

**Slide 2 :** Présentation du projet
- Qu'est-ce que CineTrack ?
- Problématique résolue
- Public cible

**Slide 3 :** Fonctionnalités principales
- Liste avec icônes
- Screenshots miniatures

**Slide 4 :** Architecture MVVM
- Diagramme clair
- Flow de données

**Slide 5 :** Stack technique
- Logos des technologies
- Versions utilisées

**Slide 6 :** Structure du code
- Arbre des packages
- Nombres de fichiers/lignes

**Slide 7 :** Room Database
- Schéma des tables
- Relations

**Slide 8 :** Tests
- Graphique de couverture
- Types de tests
- Exemples de tests

**Slide 9 :** Git & Collaboration
- Graphique de commits
- Contributions par membre
- Workflow

**Slide 10 :** Défis rencontrés
- 3-4 défis majeurs
- Solutions apportées

**Slide 11 :** Améliorations futures
- Features bonus possibles
- Évolutions envisagées

**Slide 12 :** Conclusion & Merci
- Bilan du projet
- Apprentissages
- Remerciements

---

## 🎯 Critères d'Évaluation - Auto-checklist

### Respect des Contraintes Techniques (6 pts)

**Écrans (1 pt)**
- [ ] Au moins 4-5 écrans principaux
- [ ] Navigation fluide et cohérente
- [ ] Tous les écrans sont fonctionnels

**API (1.5 pts)**
- [ ] Récupération depuis TMDB API
- [ ] Gestion des appels asynchrones (Coroutines)
- [ ] Gestion des erreurs réseau
- [ ] Système de cache implémenté

**Room DB (1.5 pts)**
- [ ] Base de données locale fonctionnelle
- [ ] Plusieurs tables avec relations
- [ ] Offline mode complet
- [ ] Migrations gérées

**Dark Mode (0.5 pt)**
- [ ] Support natif du dark mode
- [ ] Adaptation automatique aux préférences système
- [ ] Tous les écrans compatibles

**Kotlin & Compose (1 pt)**
- [ ] 100% Kotlin (aucun Java)
- [ ] 100% Compose (aucun XML)
- [ ] Code idiomatique Kotlin

**Tests (0.5 pt)**
- [ ] Tests unitaires >50%
- [ ] Tests des ViewModels
- [ ] Tests des Repositories
- [ ] Tests UI (optionnel mais bonus)

---

### Architecture MVVM et Gestion de l'État (4 pts)

**Structure MVVM (2 pts)**
- [ ] Séparation claire Model/View/ViewModel
- [ ] Repositories pour la data layer
- [ ] ViewModels sans référence au Context
- [ ] Use cases si nécessaire

**Gestion des Composables (1 pt)**
- [ ] Composables réutilisables
- [ ] State hoisting appliqué
- [ ] Pas de logique métier dans les composables

**Optimisation (1 pt)**
- [ ] Éviter les recompositions inutiles
- [ ] remember et rememberSaveable utilisés
- [ ] derivedStateOf si nécessaire
- [ ] LazyColumn/Grid pour les listes

---

### Tests Unitaires (3 pts)

**Couverture (1.5 pts)**
- [ ] >50% du code testé
- [ ] Tous les ViewModels testés
- [ ] Repositories testés

**Qualité (1.5 pts)**
- [ ] Tests pertinents et significatifs
- [ ] Utilisation de mocks (Mockito)
- [ ] Tests des cas limites
- [ ] Tests qui passent tous

---

### Gestion Git (2 pts)

**Organisation (1 pt)**
- [ ] Arbre Git propre
- [ ] Branches feature/bugfix
- [ ] Pas de commits directs sur main

**Collaboration (1 pt)**
- [ ] Tous les membres ont contribué
- [ ] Commits clairs et descriptifs
- [ ] Pull requests utilisées
- [ ] Historique cohérent

---

### Fonctionnalités et UX/UI (3 pts)

**Fonctionnalités (1.5 pts)**
- [ ] Application fluide
- [ ] Toutes les features fonctionnent
- [ ] Pas de crashes majeurs

**UX/UI (1.5 pts)**
- [ ] Navigation intuitive
- [ ] Design cohérent et attractif
- [ ] Responsive (orientations)
- [ ] Animations appropriées

---

### Soutenance Orale (2 pts)

**Présentation (1 pt)**
- [ ] Clarté et structure
- [ ] Respect du timing (15-20 min)
- [ ] Support visuel (slides)

**Démonstration (1 pt)**
- [ ] Démo live fonctionnelle
- [ ] Explications claires
- [ ] Réponses aux questions

---

## 📚 Ressources et Documentation

### Documentation Officielle
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [TMDB API Docs](https://developers.themoviedb.org/3)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Hilt DI](https://developer.android.com/training/dependency-injection/hilt-android)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Material Design 3](https://m3.material.io/)

### Tutoriels Recommandés
- [Compose Pathway](https://developer.android.com/courses/pathways/compose)
- [MVVM Architecture](https://developer.android.com/topic/architecture)
- [Testing Compose](https://developer.android.com/jetpack/compose/testing)

### Bibliothèques Utiles
```gradle
// build.gradle.kts (app level)

dependencies {
    // Compose
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.activity:activity-compose:1.8.1")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    
    // Coil (Image Loading)
    implementation("io.coil-kt:coil-compose:2.5.0")
    
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    
    // Charts (optionnel)
    implementation("com.patrykandpatrick.vico:compose:1.13.1")
    implementation("com.patrykandpatrick.vico:compose-m3:1.13.1")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("app.cash.turbine:turbine:1.0.0")
    
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")
}
```

---

## 🐛 Debugging & Troubleshooting

### Problèmes Courants

#### **1. Recompositions Infinies**
**Symptôme :** L'app freeze ou ralentit
**Solution :**
```kotlin
// ❌ Mauvais
@Composable
fun MyScreen() {
    val data = viewModel.getData() // Appelé à chaque recomposition
}

// ✅ Bon
@Composable
fun MyScreen(viewModel: MyViewModel = hiltViewModel()) {
    val data by viewModel.data.collectAsState()
}
```

#### **2. Memory Leaks avec Flow**
**Symptôme :** Consommation mémoire augmente
**Solution :**
```kotlin
// ❌ Mauvais
viewModel.flow.collect { }

// ✅ Bon
LaunchedEffect(Unit) {
    viewModel.flow.collect { }
}
```

#### **3. Images qui ne se chargent pas**
**Symptôme :** Placeholders seulement
**Solution :**
- Vérifier les permissions INTERNET
- Vérifier l'URL de l'image (prefix avec base URL)
- Ajouter error placeholder

#### **4. Room Database Crashes**
**Symptôme :** IllegalStateException
**Solution :**
- Ne jamais faire d'opérations DB sur le main thread
- Toujours utiliser suspend functions
- Vérifier les migrations

#### **5. API Timeout**
**Symptôme :** Erreurs réseau fréquentes
**Solution :**
```kotlin
val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .build()
```

---

## 💡 Best Practices

### Compose
```kotlin
// State hoisting
@Composable
fun ParentScreen() {
    var text by remember { mutableStateOf("") }
    ChildInput(
        text = text,
        onTextChange = { text = it }
    )
}

@Composable
fun ChildInput(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(value = text, onValueChange = onTextChange)
}
```

### ViewModel
```kotlin
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadMovies()
    }
    
    private fun loadMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getTrendingMovies()
                .catch { e -> 
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = e.message
                    )}
                }
                .collect { movies ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        movies = movies
                    )}
                }
        }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String? = null
)
```

### Repository
```kotlin
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    
    override fun getTrendingMovies(): Flow<List<Movie>> = flow {
        // Try cache first
        val cached = localDataSource.getTrendingMovies()
        if (cached.isNotEmpty()) {
            emit(cached)
        }
        
        // Fetch from API
        try {
            val remote = remoteDataSource.getTrendingMovies()
            localDataSource.saveTrendingMovies(remote)
            emit(remote)
        } catch (e: Exception) {
            if (cached.isEmpty()) throw e
        }
    }
}
```

---

## 🎨 Exemples de Code UI

### MovieCard Composable
```kotlin
@Composable
fun MovieCard(
    movie: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFD700),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = String.format("%.1f", movie.rating),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
```

### Loading Shimmer
```kotlin
@Composable
fun ShimmerMovieCard(
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .shimmerEffect()
            )
            
            Column(modifier = Modifier.padding(12.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(16.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition(label = "shimmer")
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "shimmer"
    )
    
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned {
        size = it.size
    }
}
```

---

## 🚀 Améliorations Post-Soutenance

Si vous souhaitez continuer le projet après la soutenance :

### Court terme
1. **Authentification réelle** (Firebase Auth)
2. **Synchronisation cloud** (Firestore)
3. **Notifications push** (FCM)
4. **Widget Android**
5. **Partage social** natif

### Moyen terme
6. **Mode hors ligne avancé** (sync bidirectionnelle)
7. **Liste partagée** entre utilisateurs
8. **Recommandations IA**
9. **Intégration streaming** (JustWatch API)
10. **Export données** (PDF, Excel)

### Long terme
11. **Version iOS** (Kotlin Multiplatform)
12. **Version Web** (Compose for Web)
13. **Backend custom** (Ktor)
14. **Machine Learning** (recommandations)
15. **Monétisation** (version premium)

---

## 📞 Support et Questions

### Pendant le Développement
- Utilisez les issues GitHub pour tracker les bugs
- Créez un channel Slack/Discord pour l'équipe
- Meetings hebdomadaires (standup)
- Code reviews obligatoires

### Resources d'Aide
- [Stack Overflow](https://stackoverflow.com/questions/tagged/jetpack-compose)
- [Reddit r/androiddev](https://reddit.com/r/androiddev)
- [Kotlin Slack](https://kotlinlang.slack.com)
- Documentation officielle Android

---

## 🎓 Conclusion

CineTrack est un projet complet qui vous permettra de maîtriser :
- ✅ Jetpack Compose et Material Design 3
- ✅ Architecture MVVM propre et scalable
- ✅ Gestion d'état moderne avec Flow
- ✅ Intégration API REST
- ✅ Persistence locale avec Room
- ✅ Tests unitaires et UI
- ✅ Collaboration Git en équipe

**Bon développement et bonne chance pour votre soutenance ! 🎬✨**