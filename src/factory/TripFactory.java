package factory;

import models.Trip;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TripFactory {

    private static final String[] CITIES = {
            "Paris", "Lyon", "Marseille", "Lille", "Bordeaux"
    };


    public static List<Trip> generateTrips(int size) {

        return IntStream.range(0, size)
                .mapToObj(TripFactory::generateOne)
                .toList();
    }

    public static List<Trip> generateTripsParallel(int size) {

        return IntStream.range(0, size)
                .parallel()
                .mapToObj(TripFactory::generateOne)
                .toList();
    }


    private static Trip generateOne(int i) {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        double distance = random.nextDouble(1, 30); // km
        double duration = distance * random.nextDouble(1.5, 3); // minutes

        double price = distance * random.nextDouble(1.5, 3.5);

        double rating = random.nextDouble(1, 5);

        LocalDateTime start =
                LocalDateTime.now()
                        .minusDays(random.nextInt(0, 10))
                        .minusMinutes(random.nextInt(0, 1440));

        LocalDateTime end = start.plusMinutes((long) duration);

        return new Trip(
                i,
                "DRIVER_" + random.nextInt(1, 100),
                "USER_" + random.nextInt(1, 1000),
                CITIES[random.nextInt(CITIES.length)],
                distance,
                duration,
                price,
                rating,
                start,
                end
        );
    }
}
