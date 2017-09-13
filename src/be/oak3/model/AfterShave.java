package be.oak3.model;

public class AfterShave extends Product {

    private Soort soort;

    public enum Soort{
        VAPO, GEL;
    }

    public AfterShave(int productNummer, String merk, String naam, int volume, double prijs, Soort soort) {
        super(productNummer, merk, naam, volume, prijs);
        this.soort = soort;
    }

    @Override
    public String toString() {
        return  String.format(super.toString() + "%-5s", soort);
    }
}
