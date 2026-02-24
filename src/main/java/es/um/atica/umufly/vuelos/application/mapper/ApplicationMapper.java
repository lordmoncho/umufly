package es.um.atica.umufly.vuelos.application.mapper;

import java.util.UUID;

import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

public class ApplicationMapper {

	private ApplicationMapper() {
		throw new IllegalStateException( "clase de conversion" );
	}

	public static VueloAmpliadoDTO vueloToDTO( Vuelo vuelo, UUID idResrva ) {
		return new VueloAmpliadoDTO( vuelo.getId(), vuelo.getTipo().toString(), vuelo.getItinerario().salida(), vuelo.getItinerario().llegada(), idResrva, vuelo.getItinerario().origen(), vuelo.getItinerario().destino(), vuelo.getEstado().toString(),
				vuelo.getAvion().capacidad() );

	}

}
