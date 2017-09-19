package be.oak3.model;

public class AfterShave extends Product {

    private Soort soort;

    public enum Soort{
        VAPO, GEL;
    }
//    Een enum is een opsomming van alle soorten die je kan meegeven (een klasse met een beperkt aantal instanties). VAPO en GEL zijn public static final (zoals membervariabelen van een interface).
//    Elke enum stamt af van Enum. Er zijn 2 belangrijke methods voor Enum: name() (geeft Vapo/GEL) terug en ordinal(geeft "index nr" terug, hier 0 of 1)!

    public AfterShave(int productNummer, String merk, String naam, int volume, double prijs, Soort soort) {
        super(productNummer, merk, naam, volume, prijs);
        this.soort = soort;
    }

    @Override
    public String toString() {
        return  String.format(super.toString() + "%-3s", soort);
    }
}
