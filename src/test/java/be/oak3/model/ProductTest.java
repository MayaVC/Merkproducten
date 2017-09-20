package be.oak3.model;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private static Product product;
    private static AfterShave aftershave;
    private static Deodorant deodorant;

    @Before
    public void init(){
        product = new Parfum(0, "Dolce & Gabbana", "Light Blue", 100, 66.72);
        aftershave = new AfterShave(0, "Yves Saint Laurent", "Jazz", 50, 39.84,
                AfterShave.Soort.VAPO);
        deodorant = new Deodorant(0, "DKNY", "Be Delicious Women", 100, 33.65,
                Deodorant.DeoType.STICK);
    }

    @Test
    public void testProduct(){
        assertEquals(0, product.getProductNummer());
        assertEquals("Dolce & Gabbana", product.getMerk());
        assertEquals("Light Blue", product.getNaam());
        assertEquals(100, product.getVolume());
        assertEquals(66.72, product.getPrijs(),0.000001);
        product.setProductNummer(12);
        product.setMerk("Cacherel");
        product.setNaam("Anais");
        product.setVolume(50);
        product.setPrijs(61.52);
        assertEquals(12, product.getProductNummer());
        assertEquals("Cacherel", product.getMerk());
        assertEquals("Anais", product.getNaam());
        assertEquals(50, product.getVolume());
        assertEquals(61.52, product.getPrijs(),0.000001);
//        assertThat(product).isInstanceOf(Parfum.class);
    }

    @Test
    public void testProductCode(){
        assertEquals("DOLLIG100",product.getProductCode());
        product.setProductNummer(12);
        product.setMerk("Cacherel");
        product.setNaam("Anais");
        product.setVolume(50);
        product.setPrijs(61.52);
        assertEquals("CACANA50",product.getProductCode());
    }

    @Test
    public void testAfterShave() {
        assertThat(aftershave).isInstanceOf(AfterShave.class);
        assertThat(aftershave.toString()).contains("VAPO");
    }

    @Test
    public void testDeodorant() {
        assertThat(deodorant).isInstanceOf(Deodorant.class);
        assertThat(deodorant.toString()).contains("STICK");
    }


}
