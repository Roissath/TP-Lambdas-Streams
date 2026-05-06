package models;

import java.time.LocalDateTime;

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