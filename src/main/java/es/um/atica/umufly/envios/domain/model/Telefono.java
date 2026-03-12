package es.um.atica.umufly.envios.domain.model;

import java.util.regex.Pattern;

public record Telefono( String valor ) {

	private static final String PATRON_TELEFONO = "^(\\+\\d{1,3}[- ]?)?\\d{9,12}$";

	public Telefono {
		if ( valor == null || valor.isBlank() ) {
			throw new IllegalArgumentException( "El telefono no puede ser nulo" );
		}
		if ( !Pattern.matches( PATRON_TELEFONO, valor ) ) {
			throw new IllegalArgumentException( "El teléfono no tiene un formato válido" );
		}
	}
}
