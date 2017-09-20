package be.oak3.persistence;

import be.oak3.model.Parfum;
import be.oak3.model.Product;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BestellingImpl implements Bestelling {
//  De interface berekenbaar wordt ook geïmplementeerd! (Want wordt geïmplementeerd door Bestelling.)

    private List<Product> bestelling = Lists.newArrayList();

    private static int productNummer = 1000;

    private static Logger logger = LogManager.getLogger();
//    OF: private static Logger logger = Logger.getLogger(TestApp.class);





    public BestellingImpl() {
        bestelling = Lists.newArrayList();
    }

    public BestellingImpl(List<Product> bestelling) {
        this.bestelling = bestelling;
    }

    public static Logger getLogger() {
        return logger;
    }

    public List<Product> getBestelling() {
        return bestelling;
    }

    @Override
    public void voegProductToe(Product product) {
        product.setProductNummer(productNummer) ;
        bestelling.add(product);
        productNummer++;
    }

    @Override
    public void sorteer(){
        bestelling.stream().sorted(Comparator.naturalOrder()).forEach(logger::debug);
    }
//    OF (maar dan werd het moeilijk als ik de System.out.println moest vervangen door Loggers):
//    @Override
//    public void sorteer() {
//        bestelling.sort(Comparator.naturalOrder());
//            bestelling.sort((p1, p2) -> p1.compareTo(p2));
//        System.out.println(this);
//    }

//    OF:
//    @Override
//    public void sorteer() {
//        List<Product> n = bestelling.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
//        n.forEach(System.out::println);
//    }


    @Override
    public void sorteerOpMerk() {
        bestelling.stream().sorted(Product.sorteerOpMerkNaam()).forEach(logger::debug);
    }


//  OF:
//  @Override
//    public void sorteerOpMerk() {
//        bestelling.sort(Product.sorteerOpMerkNaam());
//        System.out.println(this);
//    }


    @Override
    public void sorteerOpVolume() {
        bestelling.stream().sorted(Comparator.comparingInt(Product::getVolume)).forEach(logger::debug);
//        Code van Marco:
//        Collections.sort(bestelling, Comparator.comparing(Product::getVolume));
//        for (Product p: bestelling) {
//            logger.debug(p);
//        }
    }

    // OF:
    // @Override
//    public void sorteerOpVolume() {
//        bestelling.sort(Comparator.comparingInt(Product::getVolume));
//        System.out.println(this);
//    }


//      Hoort bij DEEL 1 en 2 van de opvave:
//   @Override
//    public void toonPerMerk(String merk) {
//        bestelling.stream().filter(product -> product.getMerk().equalsIgnoreCase(merk))
//                .sorted(Comparator.comparingInt(Product::getVolume)).forEach(logger::debug);
//    }

    @Override
    public List<Product> lijstVanBepaaldMerk(String merk) {
        return bestelling.stream().filter(product -> product.getMerk()
                .equalsIgnoreCase(merk)).collect(Collectors.toList());
    }

//      Hoort bij DEEL 1 en 2 van de opvave:
//    @Override
//    public void toonGoedkopeProducten() {
//        bestelling.stream().filter(product -> product.getPrijs()<50)
//                .sorted(Comparator.comparingInt(Product::getVolume)).forEach(logger::debug);
//    }


    @Override
    public List<Product> lijstVanGoedkopeProducten(){
        return bestelling.stream().filter(product -> product.getPrijs()<50).collect(Collectors.toList());
    }


//    Volgende method moesten we niet in Bestelling zetten --> wel doen, anders werkt de test-App niet.
//    Hoort bij DEEL 1 en 2 van de opgave:
//    public void toonParfums(){
//        bestelling.stream().filter(product -> product instanceof Parfum)
//                .sorted(Comparator.comparingInt(Product::getVolume)).forEach(logger::debug);
//    }


    public List<Product> lijstVanParfums(){
        return bestelling.stream().filter(product -> product instanceof Parfum).collect(Collectors.toList());
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
