package es.um.atica.umufly.vuelos.application.usecase.cancelarreservas;

import java.time.LocalDateTime;
import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloWritePort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloWriteRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class CancelarReservaCommandHandler implements SyncCommandHandler<ReservaVuelo, CancelarReservaCommand> {

	private final ReservasVueloReadRepository reservasVueloReadRepository;
	private final ReservasVueloWriteRepository reservasVueloWriteRepository;
	private final FormalizacionReservasVueloWritePort formalizacionReservasVueloPort;

	public CancelarReservaCommandHandler( ReservasVueloReadRepository reservasVueloReadRepository, ReservasVueloWriteRepository reservasVueloWriteRepository, FormalizacionReservasVueloWritePort formalizacionReservasVueloPort ) {
		this.reservasVueloReadRepository = reservasVueloReadRepository;
		this.reservasVueloWriteRepository = reservasVueloWriteRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
	}

	@Override
	public ReservaVuelo handle( CancelarReservaCommand command ) throws Exception {
		ReservaVuelo reservaVuelo = reservasVueloReadRepository.findReservaById( command.getDocumentoIdentidadTitular(), command.getIdReserva() );

		// 2. Cancelamos la reserva en el fronOffice
		reservaVuelo.cancelarReserva( LocalDateTime.now() );
		reservasVueloWriteRepository.cancelReserva( reservaVuelo.getId() );

		// 3. Cancelamos la reserva llamando al backoffice para que se haga eco de la cancelacion
		UUID idReservaFormalizada = reservasVueloReadRepository.findIdFormalizadaByReservaById( command.getIdReserva() );
		formalizacionReservasVueloPort.cancelarReservaVuelo( command.getDocumentoIdentidadTitular(), idReservaFormalizada );

		return reservaVuelo;
	}

}
