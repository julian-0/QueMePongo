import que_me_pongo.proveedorClima.ClimaAccuWeather;
import que_me_pongo.proveedorClima.ClimaOpenWeather;
import que_me_pongo.proveedorClima.ProveedorClima;
import que_me_pongo.proveedorClima.RangoDiasException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClimaTest {
    

    @Test
    @Ignore
    public void obtenerTemperatura(){
        double temperatura;
        LocalDate date = LocalDate.now();

        ProveedorClima pc = new ClimaOpenWeather();
        temperatura = pc.getTemp(date);

        System.out.println("La temperatura es...");
        System.out.println(temperatura);
    }
    
    @Test
    @Ignore
    public void probarAW()
    {
    	double temperatura = new ClimaAccuWeather().getTemp(LocalDate.now());
    	System.out.println(temperatura);
    }

    @Test
    public void climaMock(){
    	LocalDate date = LocalDate.parse("2019-05-28");
        ProveedorClima proveedorMock = mock(ProveedorClima.class);
        when(proveedorMock.getTemp(date)).thenReturn(43.0);
        Assert.assertEquals(43.0, proveedorMock.getTemp(date), 0);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void fechaMayorALoPronosticado(){
        expectedEx.expect(RangoDiasException.class);

        LocalDate now = LocalDate.now();

        ProveedorClima pc = new ClimaOpenWeather();
        pc.getTemp(now.plusDays(6));
    }

}
