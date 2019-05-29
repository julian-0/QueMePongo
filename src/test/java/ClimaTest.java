import Que_me_pongo.ClimaMock;
import Que_me_pongo.ClimaOpenWeather;
import Que_me_pongo.ProveedorClima;
import Que_me_pongo.RangoDiasException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClimaTest {
    

    @Test
    public void obtenerTemperatura(){
        double temperatura;
        String date = "2019-05-30";

        ProveedorClima pc = new ClimaOpenWeather();
        temperatura = pc.getTemp(date);

        System.out.println("La temperatura es...");
        System.out.println(temperatura);
    }

    @Test
    public void climaMock(){
        String date = "2019-05-28";
        ProveedorClima proveedorMock = mock(ClimaMock.class);
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
        pc.getTemp(now.plusDays(6).toString());
        Assert.fail();
    }

}
