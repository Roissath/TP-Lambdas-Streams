package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class Partie4 {

    // Lambdas declarees en haut du fichier
    ToDoubleFunction<Trip> getPrice = trip -> trip.price();
    Predicate<Trip> isPremiumPrice = trip -> trip.price() > 30;
    Predicate<Trip> isPremiumRating = trip -> trip.rating() > 4;
    Function<Trip, String> getCity = trip -> trip.city();

    public double totalRevenueSequential(List<Trip> trips) {
        return trips.stream()
                .mapToDouble(getPrice)
                .sum();
    }

    public double totalRevenueParallel(List<Trip> trips) {
        return trips.parallelStream()
                .mapToDouble(getPrice)
                .sum();
    }

    public Map<String, Long> countByCityParallel(List<Trip> trips) {
        return trips.parallelStream()
                .collect(Collectors.groupingByConcurrent(getCity, Collectors.counting()));
    }

    public List<Trip> premiumTripsParallel(List<Trip> trips) {
        return trips.parallelStream()
                .filter(isPremiumPrice.and(isPremiumRating))
                .collect(Collectors.toList());
    }
}
