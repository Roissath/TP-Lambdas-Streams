import factory.TripFactory;
import models.Trip;

void main() {
    List<Trip> trips = TripFactory.generateTrips(10000000);

    // appeler les méthodes des exos ici
    // pour tester si ça marche bien, générer une liste de 10 éléments et afficher le résultat
}