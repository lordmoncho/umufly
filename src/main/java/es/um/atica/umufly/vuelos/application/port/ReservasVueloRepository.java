package es.um.atica.umufly.vuelos.application.port;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public interface ReservasVueloRepository {

	/**
	 * @param documentoIdentidad
	 * @param idsVuelo
	 * @return mapa <id_vuelo, id_reserva>
	 */
	Map<UUID, UUID> getReservasVueloByPasajeroAndVuelos( DocumentoIdentidad documentoIdentidad, List<UUID> idsVuelo );

	int countReservasVueloByPasajeroAndIdVuelo( Pasajero pasajero, UUID idVuelo );

	void persistirReservaVuelo( ReservaVuelo reservaVuelo );

	void persistirFormalizacion( UUID id, UUID idReservaFormalizada );

}
