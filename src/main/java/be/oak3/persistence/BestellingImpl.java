package be.oak3.persistence;

import be.oak3.model.Parfum;
import be.oak3.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BestellingImpl implements Bestelling {
//  De interface berekenbaar wordt ook geïmplementeerd! (Want wordt geïmplementeerd door Bestelling.)

    private List<Product> bestelling = new ArrayList<>();

    private static int productNummer = 1000;


    public BestellingImpl() {
        bestelling = new ArrayList<>();
    }

    public BestellingImpl(List<Product> bestelling) {
        this.bestelling = bestelling;
    }

    @Override
    public void voegProductToe(Product product) {
        product.setProductNummer(productNummer) ;
        bestelling.add(product);
        productNummer++;
    }

    @Override
    public void sorteer() {
        bestelling.sort(Comparator.naturalOrder());
//            bestelling.sort((p1, p2) -> p1.compareTo(p2));
        System.out.println(this);
    }

//    @Override
//    public void sorteer() {
//        List<Product> n = bestelling.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
//        n.forEach(System.out::println);
//    }


    @Override
    public void sorteerOpMerk() {
        bestelling.sort(Product.sorteerOpMerkNaam());
        System.out.println(this);
    }

//    OF:
//    @Override
//    public void sorteerOpMerk() {
//        bestelling.stream().sorted(Product.sorteerOpMerkNaam()).forEach(System.out::println);
//    }


    @Override
    public void sorteerOpVolume() {
        bestelling.sort(Comparator.comparingInt(Product::getVolume));
        System.out.println(this);
    }

//    @Override
//    public void sorteerOpVolume() {
//        bestelling.stream().sorted(Comparator.comparingInt(Product::getVolume)).forEach(System.out::println);
//    }


    @Override
    public void toonPerMerk(String merk) {
        bestelling.stream().filter(product -> product.getMerk().equalsIgnoreCase(merk))
                .sorted(Comparator.comparingInt(Product::getVolume)).forEach(System.out::println);
    }

//    @Override
//    public List<Product> lijstVanBepaaldMerk(String merk) {
//        return bestelling.stream().filter(product -> product.getMerk()
//                .equalsIgnoreCase(merk)).collect(Collectors.toList());
//    }


    @Override
    public void toonGoedkopeProducten() {
        bestelling.stream().filter(product -> product.getPrijs()<50)
                .sorted(Comparator.comparingInt(Product::getVolume)).forEach(System.out::println);
    }

    @Override
    public Product zoekDuursteProduct() {
//        bestelling.sort(Comparator.comparingDouble(Product::getPrijs).reversed());
//        return bestelling.get(0);
//      OF:
      return bestelling.stream().max(Comparator.comparingDouble(Product::getPrijs)).orElseThrow(RuntimeException::new);
  }
//      Probleem: wat als er meerdere duurste producten zijn? Misschien beter om een List<Product> terug te geven?
//
//        @Override
//         public List<Product> zoekDuursteProduct() {
//            Map<Double,List<Product>> productPerPrijs = bestelling.stream().collect(Collectors.groupingBy(Product::getPrijs));
//            ArrayList<Double> lijstPrijzen = (ArrayList<Double>) bestelling.stream().map(Product::getPrijs).sorted().collect(Collectors.toList());
//            Collections.reverse(lijstPrijzen);
//            return productPerPrijs.get(lijstPrijzen.get(0));
//        }

    public void toonParfums(){
        bestelling.stream().filter(product -> product instanceof Parfum)
                .sorted(Comparator.comparingInt(Product::getVolume)).forEach(System.out::println);
    }

//    Deze method moesten we niet in Bestelling zetten.
//    Als we die in de TestApp willen gebruiken, moeten we bestelling dus eerst type casten naar een BestellingImpl.

    @Override
    public double totalePrijs() {
        return  bestelling.stream().mapToDouble(product -> product.getPrijs()).sum();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Product p: bestelling) {
            builder.append(p).append("\n");
        }
        return builder.toString();
    }


}
