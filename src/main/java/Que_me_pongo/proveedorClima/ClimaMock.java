package Que_me_pongo.proveedorClima;

import java.time.LocalDate;

public class ClimaMock implements ProveedorClima {

    public ClimaMock() {
    }

    @Override
    public double getTemp(LocalDate date) {
        return 0;
    }
}