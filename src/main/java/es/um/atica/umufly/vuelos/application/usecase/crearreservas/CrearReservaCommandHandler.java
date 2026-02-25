package es.um.atica.umufly.vuelos.application.usecase.crearreservas;

import java.time.LocalDateTime;
import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloWritePort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloWriteRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosReadRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

public class CrearReservaCommandHandler implements SyncCommandHandler<ReservaVuelo, CrearReservaCommand> {

	private final VuelosReadRepository vuelosRepository;
	private final ReservasVueloWriteRepository reservasVueloWriteRepository;
	private final ReservasVueloReadRepository reservasVueloReadRepository;
	private final FormalizacionReservasVueloWritePort formalizacionReservasVueloPort;

	public CrearReservaCommandHandler( VuelosReadRepository vuelosRepository, ReservasVueloWriteRepository reservasVueloRepository, ReservasVueloReadRepository reservasVueloReadRepository, FormalizacionReservasVueloWritePort formalizacionReservasVueloPort ) {
		this.vuelosRepository = vuelosRepository;
		this.reservasVueloWriteRepository = reservasVueloRepository;
		this.reservasVueloReadRepository = reservasVueloReadRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
	}

	@Override
	public ReservaVuelo handle( CrearReservaCommand command ) throws Exception {
		// 1. Recuperar el vuelo
		Vuelo vuelo = vuelosRepository.findVuelo( command.getIdVuelo() );

		// 2. Recuperar el número de reservas del pasajero en el vuelo
		int numeroReservasPasajeroEnVuelo = reservasVueloReadRepository.countReservasByIdVueloAndPasajero( command.getIdVuelo(), command.getPasajero() );

		// 3. Recuperar el número de plazas disponibles en el avión
		int numeroPlazasDisponiblesAvion = vuelosRepository.plazasDisponiblesEnVuelo( vuelo );

		// 4. Creamos y persistimos la reserva de vuelo
		ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( command.getDocumentoIdentidadTitular(), command.getPasajero(), vuelo, command.getClaseAsiento(), LocalDateTime.now(), numeroReservasPasajeroEnVuelo, numeroPlazasDisponiblesAvion );
		reservasVueloWriteRepository.persistirReserva( reservaVuelo );

		// 5. Formalizamos la reserva llamando al backoffice para que se haga eco de la nueva reserva que acabamos de crear
		UUID idReservaFormalizada = formalizacionReservasVueloPort.formalizarReservaVuelo( reservaVuelo );
		reservaVuelo.formalizarReserva();
		reservasVueloWriteRepository.persistirFormalizacionReserva( reservaVuelo.getId(), idReservaFormalizada );

		return reservaVuelo;
	}

}
