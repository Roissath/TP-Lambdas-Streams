package exo;

import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Partie2 {

    // Lambdas declarees en haut du fichier
    Function<Trip, String> getCity = trip -> trip.city();
    Function<Trip, String> getDriverId = trip -> trip.driverId();
    Function<Trip, Double> getPrice = trip -> trip.price();
    Function<Trip, Double> getDurationMin = trip -> trip.durationMin();

    public Map<String, Long> countByCity(List<Trip> trips) {
        return trips.stream()
                .collect(Collectors.groupingBy(getCity, Collectors.counting()));
    }

    public Map<String, Double> revenueByDriver(List<Trip> trips) {
        return trips.stream()
                .collect(Collectors.groupingBy(getDriverId,
                        Collectors.summingDouble(trip -> getPrice.apply(trip))));
    }

    public Map<String, Double> avgDurationByCity(List<Trip> trips) {
        return trips.stream()
                .collect(Collectors.groupingBy(getCity,
                        Collectors.averagingDouble(trip -> getDurationMin.apply(trip))));
    }
}
