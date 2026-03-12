package es.um.atica.umufly.vuelos.application.port;

import java.util.UUID;

import es.um.atica.umufly.vuelos.domain.model.Vuelo;

public interface VuelosRepositoryPort {

	Vuelo findVuelo( UUID idVuelo );
}
