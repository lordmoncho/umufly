package es.um.atica.umufly.vuelos.application.usecase.crearreservas;

import java.time.LocalDateTime;
import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloPort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

public class CrearReservaCommandHandler implements SyncCommandHandler<ReservaVuelo, CrearReservaCommand> {

	private final VuelosRepository vuelosRepository;
	private final ReservasVueloRepository reservasVueloRepository;
	private final FormalizacionReservasVueloPort formalizacionReservasVueloPort;

	public CrearReservaCommandHandler( VuelosRepository vuelosRepository, ReservasVueloRepository reservasVueloRepository, FormalizacionReservasVueloPort formalizacionReservasVueloPort ) {
		this.vuelosRepository = vuelosRepository;
		this.reservasVueloRepository = reservasVueloRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
	}

	@Override
	public ReservaVuelo handle( CrearReservaCommand command ) throws Exception {
		// 1. Recuperar el vuelo
		Vuelo vuelo = vuelosRepository.findVuelo( command.getIdVuelo() );

		// 2. Recuperar el número de reservas del pasajero en el vuelo
		int numeroReservasPasajeroEnVuelo = reservasVueloRepository.countReservasByIdVueloAndPasajero( command.getIdVuelo(), command.getPasajero() );

		// 3. Recuperar el número de plazas disponibles en el avión
		int numeroPlazasDisponiblesAvion = vuelosRepository.plazasDisponiblesEnVuelo( vuelo );

		// 4. Creamos y persistimos la reserva de vuelo
		ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( command.getDocumentoIdentidadTitular(), command.getPasajero(), vuelo, command.getClaseAsiento(), LocalDateTime.now(), numeroReservasPasajeroEnVuelo, numeroPlazasDisponiblesAvion );
		reservasVueloRepository.persistirReserva( reservaVuelo );

		// 5. Formalizamos la reserva llamando al backoffice para que se haga eco de la nueva reserva que acabamos de crear
		UUID idReservaFormalizada = formalizacionReservasVueloPort.formalizarReservaVuelo( reservaVuelo );
		reservaVuelo.formalizarReserva();
		reservasVueloRepository.persistirFormalizacionReserva( reservaVuelo.getId(), idReservaFormalizada );

		return reservaVuelo;
	}

}
