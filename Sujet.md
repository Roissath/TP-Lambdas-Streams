# 🧪 TP — Analyse de trajets (Streams & ParallelStream)

---

## 📌 Contexte

Vous travaillez pour une application de mobilité (type Uber / Bolt).

Votre objectif est d’analyser un grand volume de trajets afin de :

* comprendre les performances du système
* analyser les comportements utilisateurs
* produire des statistiques exploitables
* optimiser les traitements grâce au parallélisme

---

## 📦 Modèle fourni

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

## 🎯 Objectifs

* Manipuler les Streams Java
* Utiliser Predicate, Function, Comparator
* Comprendre le parallelStream
* Éviter les effets de bord
* Travailler sur un volume de données important

---

## 🔍 PARTIE 1 — Filtrage (30 min)

### Exercice 1

```java
public List<Trip> longAndExpensiveTrips(List<Trip> trips)
```

Conditions :

* distance > 10 km
* prix > 20 €

---

### Exercice 2

```java
public List<Trip> badTrips(List<Trip> trips)
```

Condition :

* rating < 3

---

### Exercice 3

```java
public List<Trip> recentTrips(List<Trip> trips)
```

Conditions :

* trajets d’aujourd’hui ou d’hier

---

## 📊 PARTIE 2 — Analyse & statistiques (40 min)

### Exercice 4

```java
public Map<String, Long> countByCity(List<Trip> trips)
```

Nombre de trajets par ville

---

### Exercice 5

```java
public Map<String, Double> revenueByDriver(List<Trip> trips)
```

Revenu total par chauffeur

---

### Exercice 6

```java
public Map<String, Double> avgDurationByCity(List<Trip> trips)
```

Durée moyenne par ville

---

## 🧠 PARTIE 3 — Tri & recherche (30 min)

### Exercice 7

```java
public List<Trip> top10ExpensiveTrips(List<Trip> trips)
```

Top 10 des trajets les plus chers

---

### Exercice 8

```java
public Optional<Trip> bestTrip(List<Trip> trips)
```

Trajet avec la meilleure note

---

### Exercice 9

Créer les comparators :

```java
Comparator<Trip> byPrice
Comparator<Trip> byRating
```

---

## ⚡ PARTIE 4 — Traitement parallèle (40 min)

### Exercice 10

```java
public double totalRevenueSequential(List<Trip> trips)
```

---

### Exercice 11

```java
public double totalRevenueParallel(List<Trip> trips)
```

---

### Exercice 12

```java
public Map<String, Long> countByCityParallel(List<Trip> trips)
```

---

### Exercice 13

```java
public List<Trip> premiumTripsParallel(List<Trip> trips)
```

Conditions :

* prix > 30
* rating > 4


---
### Notation

Chaque méthode correctement dévelopée rapportera 1 point, pour un total de 13 points.

Chaque méthode doit être correctement développée à l'aide de streams mais aussi de lambdas, qui sont déclarées en haut de chaque fichier, mais pas encore développées, vous allez donc devoir développer les lambdas ET les streams.

Si les lambdas fournies (en tant que variables) ne sont pas utilisées dans les streams, mais que vous les utilisez à la volée (ex: `list.stream().filter(p -> p.stock() > 0)`) seule la moitié des points sera accordée.

### Rendu

Le rendu doit-être fait via github, dont le lien du repository doit être envoyé par mail à l'adresse suivante : kmartin16@myges.fr avec le format suivant : NOM - Prénom - CLASSE - TP Lambdas & Streams

exemple : MARTIN - Kelig - IABD1 - Exam Lambdas et Streams