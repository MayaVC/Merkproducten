package be.oak3.model;

public class Deodorant extends Product {

    private DeoType soort;

    public enum DeoType{
        VAPO, STICK;
    }

    public Deodorant(int productNummer, String merk, String naam, int volume, double prijs, DeoType soort) {
        super(productNummer, merk, naam, volume, prijs);
        this.soort = soort;
    }

    @Override
    public String toString() {
        return  String.format(super.toString() + "%-5s", soort);
    }
}
