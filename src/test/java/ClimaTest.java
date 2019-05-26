import Que_me_pongo.ClimaAccuweather;
import Que_me_pongo.ClimaMock;
import Que_me_pongo.ProveedorClima;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClimaTest {

    ProveedorClima pc = new ClimaAccuweather();

    @Test
    public void obtenerTemperatura(){
        double temperatura;

        ProveedorClima pc = new ClimaAccuweather();
        temperatura = pc.getTemp();

        System.out.println("La temperatura es...");
        System.out.println(temperatura);
    }

    @Test
    public void climaMock(){
        ProveedorClima proveedorMock = mock(ClimaMock.class);
        when(proveedorMock.getTemp()).thenReturn(43.0);
        Assert.assertEquals(43.0, proveedorMock.getTemp(), 0);
    }

}
