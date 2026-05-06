package exo;

import models.Trip;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Partie3 {

    // Comparators declares en haut du fichier
    Comparator<Trip> byPrice = Comparator.comparingDouble(Trip::price);
    Comparator<Trip> byRating = Comparator.comparingDouble(Trip::rating);

    public List<Trip> top10ExpensiveTrips(List<Trip> trips) {
        return trips.stream()
                .sorted(byPrice.reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public Optional<Trip> bestTrip(List<Trip> trips) {
        return trips.stream()
                .max(byRating);
    }
}
