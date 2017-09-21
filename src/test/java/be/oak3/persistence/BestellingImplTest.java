package be.oak3.persistence;

import be.oak3.model.Parfum;
import be.oak3.model.Product;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class BestellingImplTest {

    private static Bestelling bestellingImpl1;
    private static List<Product> lijst = Data.getData();
    private static int grootte;

    @BeforeClass
    public static void init() {
        bestellingImpl1 = new BestellingImpl();
        for (Product artikel : lijst) {
            bestellingImpl1.voegProductToe(artikel);
        }
        grootte = bestellingImpl1.getBestelling().size();
    }

//    Test voor constructor, getter en voegProductToe()?
    @Test
    public void testBestellingImpl() {
        assertThat(bestellingImpl1.getBestelling()).isInstanceOf(List.class);
        for(int i=0; i<grootte-1; i++) {
            assertThat(bestellingImpl1.getBestelling().get(i)).isInstanceOf(Product.class);
        }
        assertThat(grootte).isEqualTo(11);
    }

//    Test voor voegProductToe()
    @Test
    public void testVoegProductToe(){
        Product cocoChanel = new Parfum(0, "Chanel", "Coco Mademoiselle", 100, 00.00);
        bestellingImpl1.voegProductToe(cocoChanel);
        assertThat(bestellingImpl1.getBestelling()).contains(cocoChanel);
    }

//    Test voor sorteer()
    @Test
    public void testSorteer(){
        bestellingImpl1.sorteer();
        assertThat(bestellingImpl1.getBestelling().get(0).getProductNummer()).isEqualTo(1000);
//        Zelf geschreven:
        for(int i=0; i<grootte-1; i++){
            assertThat(bestellingImpl1.getBestelling().get(i+1).getProductNummer())
                    .isEqualTo(bestellingImpl1.getBestelling().get(i).getProductNummer()+1);
        }
//        Gebruik makend van methods van AssertJ:
        assertThat(bestellingImpl1.getBestelling()).isSorted();
    }

//    Test voor sorteerOpMerk()
    @Test
    public void testSorteerOpMerk(){
        bestellingImpl1.sorteerOpMerk();
//        Zelf geschreven:
        for(int i=0; i< grootte-1; i++){
            assertTrue(bestellingImpl1.getBestelling().get(i).getMerk().
                    compareTo(bestellingImpl1.getBestelling().get(i+1).getMerk())<=0);
//            assertThat(bestellingImpl1.getBestelling().get(i).getMerk()).
//                    isLessThanOrEqualTo(bestellingImpl1.getBestelling().get(i+1).getMerk());
        }
//        Gebruik makend van methods van AssertJ:
        assertThat(bestellingImpl1.getBestelling()).isSortedAccordingTo(Comparator.comparing(Product::getMerk));
    }

//    Test voor sorteerOpVolume()
    @Test
    public void testSorteerOpVolume(){
        bestellingImpl1.sorteerOpVolume();
//        Zelf geschreven:
        for(int i=0; i< grootte-1; i++){
            assertTrue(bestellingImpl1.getBestelling().get(i).getVolume()<=
                    bestellingImpl1.getBestelling().get(i+1).getVolume());
        }
//        Gebruik makend van methods van AssertJ:
        assertThat(bestellingImpl1.getBestelling()).isSortedAccordingTo(Comparator.comparingInt(Product::getVolume));
    }

//    Test voor testLijstVanBepaaldMerk()
    @Test
    public void testLijstVanBepaaldMerk(){
        List<Product> lijstGA = bestellingImpl1.lijstVanBepaaldMerk("Georgio Armani");
        assertThat(lijstGA).isNotNull();
        assertThat(lijstGA).isNotEmpty();
        int grootteLGA = lijstGA.size();

//        Aantal Giorgio Armani-producten tellen in de volledige lijst:
        int aantalGAInVolledigeLijst = (int) bestellingImpl1.getBestelling().stream()
                .filter(product -> product.getMerk().equalsIgnoreCase("Georgio Armani")).count();

//        Aantal Giorgio Armani-producten in volledige lijst vergelijk met aantal producten in lijstGA:
        assertThat(aantalGAInVolledigeLijst).isEqualTo(grootteLGA);

//        Checken of elk product in lijstGA effectief van Giorgio Armani is:
        for(int i=0; i<grootteLGA-1; i++){
        assertThat(bestellingImpl1.lijstVanBepaaldMerk("Georgio Armani").get(i).getMerk()).isEqualToIgnoringCase("Georgio Armani");
        }
    }

//    Test voor testLijstVanGoedkopeProducten()
    @Test
    public void testLijstVanGoedkopeProducten(){
        List<Product> lijstVGP = bestellingImpl1.lijstVanGoedkopeProducten();
        assertThat(lijstVGP).isNotNull();
        assertThat(lijstVGP).isNotEmpty();
        int grootteLVGP = lijstVGP.size();

//        Aantal goedkope producten tellen in de volledige lijst:
        int aantalGPInVolledigeLijst = (int) bestellingImpl1.getBestelling().stream()
                .filter(product -> product.getPrijs()<50).count();

//        Aantal Giorgio Armani-producten in volledige lijst vergelijk met aantal producten in lijstVGP:
        assertThat(aantalGPInVolledigeLijst).isEqualTo(grootteLVGP);


        for(int i=0; i<grootteLVGP-1; i++){
            assertThat(bestellingImpl1.lijstVanGoedkopeProducten().get(i).getPrijs()).isLessThan(50);
        }
    }

//    Test voor List<Product> lijstVanParfums()
    @Test
    public void testLijstVanParfums(){
        List<Product> lijstParfums = bestellingImpl1.lijstVanParfums();
        assertThat(lijstParfums).isNotNull();
        assertThat(lijstParfums).isNotEmpty();
        int grootteParfums = lijstParfums.size();

//        Aantal Parfums tellen in de volledige lijst:
        int aantalParfumsInVolledigeLijst = (int) bestellingImpl1.getBestelling().stream()
                .filter(product -> product instanceof Parfum).count();

//        Aantal Parfums in volledige lijst vergelijk met aantal producten in lijstVGP:
        assertThat(aantalParfumsInVolledigeLijst).isEqualTo(grootteParfums);

        for(int i=0; i<grootteParfums-1; i++){
            assertThat(bestellingImpl1.lijstVanParfums().get(i)).isInstanceOf(Parfum.class) ;
        }
    }

//        Test voor zoekDuursteProduct()
    @Test
    public void testZoekDuursteProduct(){
        assertThat(bestellingImpl1.zoekDuursteProduct()).isInstanceOf(Product.class);
        Product duursteProduct = bestellingImpl1.zoekDuursteProduct();
        double hoogstePrijs = duursteProduct.getPrijs();

        for(int i=0; i<grootte-1; i++) {
            assertThat(bestellingImpl1.getBestelling().get(i).getPrijs()).isLessThanOrEqualTo(hoogstePrijs);
        }

//      Of?
        assertThat(bestellingImpl1.zoekDuursteProduct().getPrijs()).isEqualTo(76.00);
    }

//        Test voor totalePrijs()
    @Test
    public void testTotalePrijs(){
        assertThat(bestellingImpl1.totalePrijs()).isEqualTo(579.05);
    }


}

