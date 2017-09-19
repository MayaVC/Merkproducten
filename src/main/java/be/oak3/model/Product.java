package be.oak3.model;

import java.util.Comparator;
import static org.apache.commons.lang3.StringUtils.*;

public abstract class Product implements Comparable<Product>{
    //Comparator wordt zelden geïmplementeerd omdat het een zelf gekozen volgorde maakt
    private int productNummer;
    private String merk;
    private String naam;
    private int volume;
    private double prijs;

    public Product(int productNummer, String merk, String naam, int volume, double prijs) {
        this.productNummer = productNummer;
        this.merk = merk;
        this.naam = naam;
        this.volume = volume;
        this.prijs = prijs;
    }

//    Merk op: we hebben geen constructor zonder parameters. Dus, in de subclasses moet verplicht verwezen worden naar onze enige constructor (met 5 parameters).

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getMerk() {
        return merk;
    }

    public String getNaam() {
        return naam;
    }

    public int getVolume() {
        return volume;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getProductCode(){
        return replaceChars(upperCase(join(left(merk,3), left(naam,3),(String.valueOf(volume))))," ","_");
    }

    public static Comparator<Product> sorteerOpMerkNaam(){
        return Comparator.comparing(Product::getMerk);
//          Ongebonden method reference?
//        of: return (o1,o2) -> o1.getMerk().compareTo(o2.getMerk());
//          Comparator<Product> comparator = new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return o1.getMerk().compareTo(o2.getMerk());
//            }
//          };
//          return comparator;

//      Deze laatste is de manier die altijd gebruikt wordt voor Java 8! Het is belangrijk om die goed te begrijpen, want lambdas zijn verkorte versies hiervan/
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Product)) return false;
//
//        Product product = (Product) o;
//
//        return getProductCode().equals(product.getProductCode());
//    }
//    Deze equals is wel juist, maar niet compatibel met de compareTo (en de compareTo is zo gemaakt, om op een juiste manier te sorteren).

    @Override
    public int hashCode() {
        return getNaam().hashCode();
    }

    @Override
    public int compareTo(Product p) {
        return  productNummer - p.productNummer;
    }

    @Override
    public String toString() {
        return String.format("%-5d Merk: %-25s Naam: %-25s Volume: %3dml \t Prijs: %5.2f \t Code: %-15s", productNummer, merk, naam, volume, prijs, getProductCode());
//       of: return String.format("%d %s %-20s %10s %-24s %10s %3sml %8s %4.2f %5s %s", productNummer, "Merk:", merk, "Naam", naam, "Volume:", volume, "Prijs:", prijs, "Code:", getProductCode());
//       return productNummer + " " + "Merk: " + merk + "\t" +
//                "Naam: " + naam + "\t" +
//                "Volume: " + volume + "\t" +
//                "Prijs: " + prijs + " " + "Code: " + getProductCode();
    }
}
