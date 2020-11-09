package ohtu.ohtuvarasto;

import com.google.errorprone.annotations.Var;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void negatiivinenVarasto()
    {Varasto varavarasto = new Varasto(-69);
    assertEquals(0, varavarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kakkosKonstruktori() {
        Varasto varastoKakkonen = new Varasto(10, 9);
        assertEquals(10, varastoKakkonen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kakkosKonstruktoriNegatiivinen() {
        Varasto varastoKakkonenNegatiivinen = new Varasto(-69, 0);
        assertEquals(0, varastoKakkonenNegatiivinen.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kakkosKonstruktoriNegatiivinenAlkusaldo() {
        Varasto varastoKakkonenNegatiivinenSaldo = new Varasto(10, -69);
        assertEquals(0.0, varastoKakkonenNegatiivinenSaldo.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenTavaranLisaaminen() {
        varasto.lisaaVarastoon(-69);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void tavaranMaaraYlittaaTilavuuden() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenTavaranOttaminen() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(-69);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liianIsoOttaminenAntaaKaiken() {
        varasto.lisaaVarastoon(5);
        double saatuMaara = varasto.otaVarastosta(7);
        assertEquals(5, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liianIsoOttaminenTyhjentaaVaraston() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(8);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTuottaaOikeanMerkkijonon() {
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

}