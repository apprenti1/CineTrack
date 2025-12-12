# Documentation API - CineTrack

## Configuration

### Variables d'environnement

Les clés API et URLs sont configurées dans le fichier `local.properties` à la racine du projet Android.

**Fichier: `local.properties`**
```properties
tmdb.api.key=YOUR_TMDB_API_KEY_HERE
tmdb.base.url=https://api.themoviedb.org/3/
cinetrack.api.url=http://10.0.2.2:3000
```

**Note:** Le fichier `local.properties` est ignoré par git pour des raisons de sécurité. Utilisez `local.properties.example` comme modèle.

### Accès aux variables dans le code

Les variables sont accessibles via `BuildConfig`:

```kotlin
val tmdbApiKey = BuildConfig.TMDB_API_KEY
val tmdbBaseUrl = BuildConfig.TMDB_BASE_URL
val cinetrackApiUrl = BuildConfig.CINETRACK_API_URL
```

---

## TMDB API (The Movie Database)

### Authentification

Toutes les requêtes nécessitent une clé API valide passée en paramètre `api_key`.

**Obtenir une clé API:**
1. Créer un compte sur https://www.themoviedb.org
2. Aller dans Paramètres > API
3. Demander une clé API

### Base URL

```
https://api.themoviedb.org/3/
```

### Endpoints utilisés

#### 1. Films tendances de la semaine

**GET** `/trending/movie/week`

Récupère les films tendances de la semaine.

**Paramètres:**
- `api_key` (required): Clé API TMDB
- `language` (optional): Code langue (ex: `fr-FR`, `en-US`)
- `page` (optional): Numéro de page (défaut: 1)

**Exemple:**
```
GET https://api.themoviedb.org/3/trending/movie/week?api_key=YOUR_KEY&language=fr-FR
```

**Réponse:**
```json
{
  "page": 1,
  "results": [
    {
      "id": 798645,
      "title": "Titre du film",
      "original_title": "Original Title",
      "overview": "Synopsis du film...",
      "poster_path": "/path/to/poster.jpg",
      "backdrop_path": "/path/to/backdrop.jpg",
      "release_date": "2024-01-15",
      "vote_average": 8.5,
      "vote_count": 1234,
      "popularity": 567.89,
      "genre_ids": [28, 12, 878],
      "adult": false,
      "video": false,
      "original_language": "en"
    }
  ],
  "total_pages": 100,
  "total_results": 2000
}
```

#### 2. Détails d'un film

**GET** `/movie/{movie_id}`

Récupère les détails complets d'un film spécifique.

**Paramètres:**
- `movie_id` (path): ID du film
- `api_key` (required): Clé API TMDB
- `language` (optional): Code langue
- `append_to_response` (optional): Données supplémentaires à inclure (ex: `credits,videos,reviews`)

**Exemple:**
```
GET https://api.themoviedb.org/3/movie/798645?api_key=YOUR_KEY&language=fr-FR&append_to_response=credits,videos,reviews
```

**Réponse:**
```json
{
  "id": 798645,
  "title": "Titre du film",
  "original_title": "Original Title",
  "overview": "Synopsis complet...",
  "poster_path": "/path/to/poster.jpg",
  "backdrop_path": "/path/to/backdrop.jpg",
  "release_date": "2024-01-15",
  "runtime": 148,
  "budget": 200000000,
  "revenue": 500000000,
  "vote_average": 8.5,
  "vote_count": 1234,
  "popularity": 567.89,
  "status": "Released",
  "tagline": "Tagline du film",
  "homepage": "https://example.com",
  "imdb_id": "tt1234567",
  "genres": [
    { "id": 28, "name": "Action" },
    { "id": 12, "name": "Aventure" },
    { "id": 878, "name": "Science-Fiction" }
  ],
  "production_companies": [...],
  "production_countries": [...],
  "spoken_languages": [...],

  "credits": {
    "cast": [
      {
        "id": 12345,
        "name": "Acteur Name",
        "character": "Nom du personnage",
        "profile_path": "/path/to/profile.jpg",
        "order": 0,
        "gender": 2,
        "known_for_department": "Acting"
      }
    ],
    "crew": [...]
  },

  "videos": {
    "results": [
      {
        "id": "abc123",
        "key": "YouTube_Video_ID",
        "name": "Official Trailer",
        "site": "YouTube",
        "type": "Trailer",
        "size": 1080
      }
    ]
  },

  "reviews": {
    "results": [
      {
        "id": "review123",
        "author": "username",
        "content": "Contenu de la review...",
        "created_at": "2024-01-15T12:00:00.000Z",
        "updated_at": "2024-01-15T12:00:00.000Z",
        "url": "https://..."
      }
    ]
  }
}
```

#### 3. Recherche de films

**GET** `/search/movie`

Recherche des films par titre.

**Paramètres:**
- `api_key` (required): Clé API TMDB
- `query` (required): Terme de recherche
- `language` (optional): Code langue
- `page` (optional): Numéro de page
- `include_adult` (optional): Inclure contenu adulte (défaut: false)
- `year` (optional): Filtrer par année
- `primary_release_year` (optional): Année de sortie principale

**Exemple:**
```
GET https://api.themoviedb.org/3/search/movie?api_key=YOUR_KEY&query=Inception&language=fr-FR
```

#### 4. Genres de films

**GET** `/genre/movie/list`

Liste tous les genres de films disponibles.

**Paramètres:**
- `api_key` (required): Clé API TMDB
- `language` (optional): Code langue

**Exemple:**
```
GET https://api.themoviedb.org/3/genre/movie/list?api_key=YOUR_KEY&language=fr-FR
```

**Réponse:**
```json
{
  "genres": [
    { "id": 28, "name": "Action" },
    { "id": 12, "name": "Aventure" },
    { "id": 16, "name": "Animation" },
    { "id": 35, "name": "Comédie" },
    { "id": 80, "name": "Crime" },
    { "id": 99, "name": "Documentaire" }
  ]
}
```

### Images

Les URLs d'images doivent être construites avec la base URL des images TMDB:

**Base URL pour les images:**
```
https://image.tmdb.org/t/p/{size}{path}
```

**Tailles disponibles:**
- **Posters:** `w92`, `w154`, `w185`, `w342`, `w500`, `w780`, `original`
- **Backdrops:** `w300`, `w780`, `w1280`, `original`
- **Profiles:** `w45`, `w185`, `h632`, `original`

**Exemple:**
```kotlin
val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
val backdropUrl = "https://image.tmdb.org/t/p/w1280${movie.backdrop_path}"
val profileUrl = "https://image.tmdb.org/t/p/w185${actor.profile_path}"
```

### Vidéos (Bandes-annonces)

Les vidéos YouTube peuvent être construites avec:

```kotlin
val youtubeUrl = "https://www.youtube.com/watch?v=${video.key}"
val youtubeThumbnail = "https://img.youtube.com/vi/${video.key}/hqdefault.jpg"
```

### Limites et restrictions

- **Limite de requêtes:** 40 requêtes par 10 secondes
- **Authentification:** Clé API requise pour toutes les requêtes
- **Cache:** Recommandé de mettre en cache les résultats pour réduire les appels API

### Codes d'erreur

| Code | Description |
|------|-------------|
| 200  | Succès |
| 401  | Clé API invalide ou manquante |
| 404  | Ressource introuvable |
| 429  | Limite de requêtes dépassée |
| 500  | Erreur serveur TMDB |

---

## CineTrack API (Backend interne)

### Base URL

```
http://10.0.2.2:3000  # Émulateur Android (localhost)
http://localhost:3000 # Web/Desktop
```

### Endpoints

*À documenter une fois l'API backend implémentée*

#### Authentification
- POST `/auth/register` - Inscription
- POST `/auth/login` - Connexion
- POST `/auth/refresh` - Rafraîchir le token
- POST `/auth/logout` - Déconnexion

#### Utilisateur
- GET `/user/profile` - Profil utilisateur
- PUT `/user/profile` - Mettre à jour le profil
- GET `/user/favorites` - Liste des favoris
- GET `/user/watchlist` - Liste à voir
- GET `/user/watched` - Films vus

#### Films
- POST `/movies/{id}/favorite` - Ajouter/retirer des favoris
- POST `/movies/{id}/watchlist` - Ajouter/retirer de la watchlist
- POST `/movies/{id}/watched` - Marquer comme vu/non vu
- POST `/movies/{id}/rate` - Noter un film
- GET `/movies/{id}/rating` - Récupérer la note de l'utilisateur

#### Commentaires
- GET `/movies/{id}/reviews` - Commentaires d'un film
- POST `/movies/{id}/reviews` - Ajouter un commentaire
- PUT `/reviews/{id}` - Modifier un commentaire
- DELETE `/reviews/{id}` - Supprimer un commentaire

---

## Utilisation dans le code

### Configuration Retrofit

```kotlin
object RetrofitClient {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    val tmdbApi: TMDBApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBApiService::class.java)
    }

    val cinetrackApi: CineTrackApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.CINETRACK_API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CineTrackApiService::class.java)
    }
}
```

### Interface TMDB API

```kotlin
interface TMDBApiService {
    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "fr-FR",
        @Query("page") page: Int = 1
    ): Response<TMDBMoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "fr-FR",
        @Query("append_to_response") appendToResponse: String = "credits,videos,reviews"
    ): Response<TMDBMovieDetails>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("query") query: String,
        @Query("language") language: String = "fr-FR",
        @Query("page") page: Int = 1
    ): Response<TMDBMoviesResponse>
}
```

---

## Références

- Documentation officielle TMDB API: https://developers.themoviedb.org/3
- API Key TMDB: https://www.themoviedb.org/settings/api
- Status TMDB: https://status.themoviedb.org/