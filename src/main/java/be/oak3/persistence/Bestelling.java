package be.oak3.persistence;

import be.oak3.model.Berekenbaar;
import be.oak3.model.Product;

import java.util.List;

public interface Bestelling extends Berekenbaar{

    void voegProductToe(Product product);
    void sorteer();
    void sorteerOpMerk();
    void sorteerOpVolume();

//    Hoort bij DEEL 1 en 2 van de opgave:
//    void toonPerMerk(String merk);
//    void toonGoedkopeProducten();
//    void toonParfums();

    List<Product> lijstVanBepaaldMerk(String merk);
    List<Product> lijstVanGoedkopeProducten();
    List<Product> lijstVanParfums();

    Product zoekDuursteProduct();
//    List<Product> zoekDuursteProduct();




}
