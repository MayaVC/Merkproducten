package be.oak3.persistence;

import be.oak3.model.Product;

import java.util.List;

public class TestApp {

//  private static  Logger logger  = LogManager.getLogger();

    public static void main(String[] args) {

        System.out.printf("Oplossing van %s %s\n", "Kenneth Van Gijsel ",
                "Java Instructeur");

        Bestelling bestelling = new BestellingImpl();
        List<Product> lijst = Data.getData();

        for (Product artikel : lijst) {
            bestelling.voegProductToe(artikel);
        }

        System.out.println("Lijst gesorteerd op natuurlijke volgorde: ");
        bestelling.sorteer();

        System.out.println("\nLijst gesorteerd op merknaam: ");
        bestelling.sorteerOpMerk();

        System.out.println("\nLijst gesorteerd op volume: ");
        bestelling.sorteerOpVolume();

        System.out.println("\nVan het merk Georgio Armani:");
        bestelling.toonPerMerk("Georgio Armani");

        System.out.println("\nAlle Parfums:");
        bestelling.toonParfums();

//        System.out.println("\nAlle Parfums:");
//        BestellingImpl bestelling1 = (BestellingImpl) bestelling;
//        bestelling1.toonParfums();

        System.out.println("\nAlle producten onder €50; ");
        bestelling.toonGoedkopeProducten();

        Product product = bestelling.zoekDuursteProduct();
        System.out.println("\nDuurste product:");
        BestellingImpl bestelling1 = (BestellingImpl) bestelling;
//        logger.debug(product);
        bestelling1.getLogger().debug(product);

//        Product product = bestelling.zoekDuursteProduct();
//        System.out.println("\nDuurste product:\n" + product);

//        List<Product> duurste = bestelling.zoekDuursteProduct();
//        System.out.println("\nDuurste product:\n" + duurste);

        System.out.printf("\nTotale prijs:\n");
        bestelling1.getLogger().debug( "€" + bestelling.totalePrijs());

//        System.out.printf("\nTotale prijs: €%.2f", bestelling.totalePrijs());

    }
}
