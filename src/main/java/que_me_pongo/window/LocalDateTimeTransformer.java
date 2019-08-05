package que_me_pongo.window;

import org.apache.commons.collections15.Transformer;

import java.time.LocalDateTime;

public class LocalDateTimeTransformer implements Transformer<LocalDateTime, String> {
    @Override
    public String transform(LocalDateTime fecha) {
        return fecha.toLocalDate().toString();
    }
}
