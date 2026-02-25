package es.um.atica.umufly.vuelos.application.usecase.listarreservas;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

@Component
public class ListarReservasQueryHandler implements QueryHandler<Page<ReservaVuelo>, ListarReservasQuery> {

	private final ReservasVueloRepository reservasVueloRepository;

	public ListarReservasQueryHandler( ReservasVueloRepository reservasVueloRepository ) {
		this.reservasVueloRepository = reservasVueloRepository;
	}

	@Override
	public Page<ReservaVuelo> handle( ListarReservasQuery query ) throws Exception {
		return reservasVueloRepository.findReservas( query.getDocumentoIdentidadPasajero(), query.getPagina(), query.getTamanioPagina() );
	}

}
