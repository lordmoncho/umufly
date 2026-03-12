package es.um.atica.umufly.envios.domain.model;

import java.security.SecureRandom;
import java.util.stream.Collectors;

public record NumeroSeguimiento( String value ) {

	private static final String NUMERO_SEGUIMEINTO_PREFIJO = "TICARUM-";
	private static final int NUMERO_SEGUIMEINTO_LONGITUD_NUM = 10;

	public NumeroSeguimiento {
		if ( !value.matches( "^" + NUMERO_SEGUIMEINTO_PREFIJO + "\\d{" + NUMERO_SEGUIMEINTO_LONGITUD_NUM + "}$" ) )

		{
			throw new IllegalArgumentException( "Formato de seguimiento inválido" );
		}
	}

	// Generador (Factory Method)
	public static NumeroSeguimiento generate() {
		return new NumeroSeguimiento( NUMERO_SEGUIMEINTO_PREFIJO + generateSecureCodes( NUMERO_SEGUIMEINTO_LONGITUD_NUM ) );
	}

	private static String generateSecureCodes( int tam ) {
		SecureRandom secureRandom = new SecureRandom();
		return secureRandom.ints( tam, 0, 10 ).mapToObj( String::valueOf ).collect( Collectors.joining() );
	}
}
