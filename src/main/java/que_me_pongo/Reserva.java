package que_me_pongo;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

public class Reserva {

    @Id
    @GeneratedValue
    private long id;

    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fecha;
}
