# Exam TP — Analyse de trajets (Streams & ParallelStream)

**Etudiant :** WABI ROISSATH  
**Classe :** IABD  
**Module :** Java Avance — Lambdas & Streams  
**Intervenant :** Kelig Martin  
**Date :** 6 mai 2026

---

## Contexte

Application de mobilite type Uber/Bolt.  
Analyse d'un grand volume de trajets (`Trip`) via les Streams Java afin de :
- comprendre les performances du systeme
- analyser les comportements utilisateurs
- produire des statistiques exploitables
- optimiser les traitements grace au parallelisme

---

## Modele de donnees

```java
public record Trip(
    long id,
    String driverId,
    String userId,
    String city,
    double distanceKm,
    double durationMin,
    double price,
    double rating,
    LocalDateTime startTime,
    LocalDateTime endTime
) {}
```

---

## Tableau recapitulatif des exercices

| # | Methode | Fichier | Lambda utilisee | Description |
|---|---------|---------|-----------------|-------------|
| 1 | `longAndExpensiveTrips` | `Partie1` | `isLongTrip` + `isExpensiveTrip` | Filtre trajets > 10km ET prix > 20€ |
| 2 | `badTrips` | `Partie1` | `isBadTrip` | Filtre trajets avec note < 3 |
| 3 | `recentTrips` | `Partie1` | `isRecentTrip` | Filtre trajets d'aujourd'hui ou hier |
| 4 | `countByCity` | `Partie2` | `getCity` | Nombre de trajets par ville |
| 5 | `revenueByDriver` | `Partie2` | `getDriverId` + `getPrice` | Revenu total par chauffeur |
| 6 | `avgDurationByCity` | `Partie2` | `getCity` + `getDurationMin` | Duree moyenne par ville |
| 7 | `top10ExpensiveTrips` | `Partie3` | `byPrice` | Top 10 trajets les plus chers |
| 8 | `bestTrip` | `Partie3` | `byRating` | Trajet avec la meilleure note |
| 9 | `byPrice` / `byRating` | `Partie3` | Comparators declares | Comparateurs de tri |
| 10 | `totalRevenueSequential` | `Partie4` | `getPrice` | Revenu total via `stream()` |
| 11 | `totalRevenueParallel` | `Partie4` | `getPrice` | Revenu total via `parallelStream()` |
| 12 | `countByCityParallel` | `Partie4` | `getCity` | Trajets par ville en parallele |
| 13 | `premiumTripsParallel` | `Partie4` | `isPremiumPrice` + `isPremiumRating` | Trajets premium (prix > 30 ET note > 4) en parallele |

---

## Structure du projet

```
src/
├── Main.java
├── models/
│   └── Trip.java
├── factory/
│   └── TripFactory.java
└── exo/
    ├── Partie1.java   → Filtrage (Exos 1, 2, 3)
    ├── Partie2.java   → Statistiques (Exos 4, 5, 6)
    ├── Partie3.java   → Tri & recherche (Exos 7, 8, 9)
    └── Partie4.java   → Parallelisme (Exos 10, 11, 12, 13)
```

---

## Choix techniques

### Lambdas declarees en tant que variables (obligatoire)

Comme exige dans le sujet, **toutes les lambdas sont declarees en haut de chaque classe** sous forme de variables (`Predicate`, `Function`, `Comparator`, `ToDoubleFunction`) et **reutilisees dans les streams**.

### Partie 1 — Predicate
- `isLongTrip` et `isExpensiveTrip` sont combines avec `.and()` pour l'exercice 1
- `isRecentTrip` utilise `LocalDate.now()` pour comparer dynamiquement

### Partie 2 — Function + Collectors
- `groupingBy` utilise les `Function` declarees pour extraire la cle
- `summingDouble` et `averagingDouble` utilisent les lambdas via `.apply()`

### Partie 3 — Comparator
- `byPrice.reversed()` pour trier du plus cher au moins cher
- `.max(byRating)` retourne un `Optional<Trip>`

### Partie 4 — parallelStream
- `totalRevenueParallel` : `mapToDouble` + `sum()` — operation associative, safe en parallele
- `countByCityParallel` : utilise `groupingByConcurrent` (thread-safe)
- `premiumTripsParallel` : filtre combine avec `.and()`

---

## Lancer le projet

Ouvrir avec **IntelliJ IDEA** et executer `Main.java`.  
Le `TripFactory` genere automatiquement les donnees de test.
