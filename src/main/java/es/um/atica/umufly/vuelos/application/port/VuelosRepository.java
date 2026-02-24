package es.um.atica.umufly.vuelos.application.port;

import java.util.UUID;

import org.springframework.data.domain.Page;

import es.um.atica.umufly.vuelos.domain.model.Vuelo;

public interface VuelosRepository {

	Vuelo findVuelo( UUID id );

	Page<Vuelo> findVuelos( int pagina, int tamanioPagina );

	int getPlazasDisponiblesAvion( UUID idVuelo );

}
