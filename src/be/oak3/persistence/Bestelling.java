package be.oak3.persistence;

import be.oak3.model.Berekenbaar;
import be.oak3.model.Product;

public interface Bestelling extends Berekenbaar{

    void voegProductToe(Product product);
    void sorteer();
    void sorteerOpMerk();
    void sorteerOpVolume();
    void toonPerMerk(String merk);
    void toonGoedkopeProducten();
    Product zoekDuursteProduct();
//    List<Product> zoekDuursteProduct();
    void toonParfums();


}
