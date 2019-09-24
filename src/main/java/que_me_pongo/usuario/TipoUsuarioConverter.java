package que_me_pongo.usuario;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, String> {
    @Override
    public String convertToDatabaseColumn(TipoUsuario tipoUsuario) {
        return tipoUsuario.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public TipoUsuario convertToEntityAttribute(String s) {
        if (s.equals("gratuito")) {
            return new Gratuito();
        } else if (s.equals("premium")) {
            return new Premium();
        }
        return null;
    }
}
