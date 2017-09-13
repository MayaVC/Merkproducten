package be.oak3.model;

import java.util.Comparator;

public abstract class Product implements Comparable<Product>{
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
        return (merk.substring(0,3) + naam.substring(0,3) + (String.valueOf(volume)))
                                .toUpperCase().replace(" ", "_");
    }

    public static Comparator<Product> sorteerOpMerkNaam(){
        return (o1,o2) -> o1.getMerk().compareTo(o2.getMerk());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productNummer == product.productNummer;
    }

    @Override
    public int hashCode() {
        return productNummer;
    }

    @Override
    public int compareTo(Product p) {
        return  productNummer - p.productNummer;
    }

    @Override
    public String toString() {
        return String.format("%-10d Merk: %-10s Naam: %-10s Volume: %5dml Prijs: %-5d  Code: %-10s", productNummer, merk, naam, volume, prijs, getProductCode());

//        return productNummer + " " + "Merk: " + merk + "\t" +
//                "Naam: " + naam + "\t" +
//                "Volume: " + volume + "\t" +
//                "Prijs: " + prijs + " " + "Code: " + getProductCode();
    }
}
