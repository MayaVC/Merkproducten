package be.oak3.model;

@FunctionalInterface
// door dit er bij te zetten, controleert Java of er wel degelijk maar 1 abstracte methode is (die wordt dan gebruikt in de lambdas)
public interface Berekenbaar {

    double totalePrijs();


}
