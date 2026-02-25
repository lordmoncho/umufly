package es.um.atica.umufly.vuelos.application.usecase.crearreservas;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class CreaReservaCommand extends SyncCommand<ReservaVuelo> {

	private DocumentoIdentidad documentoIdentidadTitular;
	private UUID idVuelo;
	private ClaseAsientoReserva claseAsiento;
	private Pasajero pasajero;

	private CreaReservaCommand( DocumentoIdentidad documentoIdentidadTitular, UUID idVuelo, ClaseAsientoReserva claseAsiento, Pasajero pasajero ) {
		this.documentoIdentidadTitular = documentoIdentidadTitular;
		this.idVuelo = idVuelo;
		this.claseAsiento = claseAsiento;
		this.pasajero = pasajero;
	}

	/*
	 * // 1. Recuperar el vuelo Vuelo vuelo = vuelosRepository.findVuelo( idVuelo ); // 2. Recuperar el número de reservas
	 * del pasajero en el vuelo int numeroReservasPasajeroEnVuelo =
	 * reservasVueloRepository.countReservasByIdVueloAndPasajero( idVuelo, pasajero ); // 3. Recuperar el número de plazas
	 * disponibles en el avión int numeroPlazasDisponiblesAvion = vuelosRepository.plazasDisponiblesEnVuelo( vuelo ); // 4.
	 * Creamos y persistimos la reserva de vuelo ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva(
	 * documentoIdentidadTitular, pasajero, vuelo, claseAsiento, LocalDateTime.now( clock ), numeroReservasPasajeroEnVuelo,
	 * numeroPlazasDisponiblesAvion ); reservasVueloRepository.persistirReserva( reservaVuelo ); // 5. Formalizamos la
	 * reserva llamando al backoffice para que se haga eco de la nueva reserva que acabamos de crear UUID
	 * idReservaFormalizada = formalizacionReservasVueloPort.formalizarReservaVuelo( reservaVuelo );
	 * reservaVuelo.formalizarReserva(); reservasVueloRepository.persistirFormalizacionReserva( reservaVuelo.getId(),
	 * idReservaFormalizada ); return reservaVuelo;
	 */
}
