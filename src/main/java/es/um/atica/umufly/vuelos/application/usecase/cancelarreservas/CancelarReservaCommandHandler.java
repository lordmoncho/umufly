package es.um.atica.umufly.vuelos.application.usecase.cancelarreservas;

import java.time.LocalDateTime;
import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloPort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class CancelarReservaCommandHandler implements SyncCommandHandler<ReservaVuelo, CancelarReservaCommand> {

	private final ReservasVueloRepository reservasVueloRepository;
	private final FormalizacionReservasVueloPort formalizacionReservasVueloPort;

	public CancelarReservaCommandHandler( ReservasVueloRepository reservasVueloRepository, FormalizacionReservasVueloPort formalizacionReservasVueloPort ) {
		this.reservasVueloRepository = reservasVueloRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
	}

	@Override
	public ReservaVuelo handle( CancelarReservaCommand command ) throws Exception {
		ReservaVuelo reservaVuelo = reservasVueloRepository.findReservaById( command.getDocumentoIdentidadTitular(), command.getIdReserva() );

		// 2. Cancelamos la reserva en el fronOffice
		reservaVuelo.cancelarReserva( LocalDateTime.now() );
		reservasVueloRepository.cancelReserva( reservaVuelo.getId() );

		// 3. Cancelamos la reserva llamando al backoffice para que se haga eco de la cancelacion
		UUID idReservaFormalizada = reservasVueloRepository.findIdFormalizadaByReservaById( command.getIdReserva() );
		formalizacionReservasVueloPort.cancelarReservaVuelo( command.getDocumentoIdentidadTitular(), idReservaFormalizada );

		return reservaVuelo;
	}

}
