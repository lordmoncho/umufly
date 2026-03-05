package es.um.atica.vuelos.cucumber.steps;

import java.text.MessageFormat;
import java.util.List;

import es.um.atica.umufly.vuelos.adaptors.api.rest.v2.dto.VueloDTO;
import es.um.atica.umufly.vuelos.adaptors.providers.muchovuelo.dto.ReservaVueloDTO;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class ViajeroSteps {

	private String usuario;
	private List<VueloDTO> vuelos;

	private ReservaVueloDTO reserva;

	@Dado( "un viajero con NIF {string}" )
	public void cargo_datos_usuario( String nif ) {
		this.usuario = nif;
		System.out.println( MessageFormat.format( "NIF {0}", nif ) );
	}

	@Cuando( "lista de vuelos con página {int} y tamaño {int}" )
	public void listo_vuelos_disponibles( int page, int size ) {
		System.out.println( MessageFormat.format( "Lista de vuelos pagina {0} y tamaño {1}", page, size ) );
	}

	@Entonces( "la respuesta debe tener status {int}" )
	public void verificar_status( int estadoEsperado ) {
		// response.expectStatus().isEqualTo( estadoEsperado );
		System.out.println( MessageFormat.format( "verificar_status {0}", estadoEsperado ) );
	}

	@Y( "devolver una lista de vuelos rellena o vacia" )
	public void verifica_lista_vuelos() {
		// assertNotNull( vuelos );
		System.out.println( "verifica_lista_vuelos" );

	}

}
