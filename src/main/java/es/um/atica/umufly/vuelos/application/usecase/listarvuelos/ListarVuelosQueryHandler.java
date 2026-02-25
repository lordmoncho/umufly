package es.um.atica.umufly.vuelos.application.usecase.listarvuelos;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.application.mapper.ApplicationMapper;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosRepository;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class ListarVuelosQueryHandler implements QueryHandler<Page<VueloAmpliadoDTO>, ListarVuelosQuery> {

	private final VuelosRepository vuelosRepository;
	private final ReservasVueloRepository reservasVueloRepository;

	public ListarVuelosQueryHandler( VuelosRepository vuelosRepository, ReservasVueloRepository reservasVueloRepository ) {
		this.vuelosRepository = vuelosRepository;
		this.reservasVueloRepository = reservasVueloRepository;
	}

	@Override
	public Page<VueloAmpliadoDTO> handle( ListarVuelosQuery query ) throws Exception {
		Page<Vuelo> vuelos = vuelosRepository.findVuelos( query.getPagina(), query.getTamanioPagina() );
		Map<UUID, UUID> vuelosReserva = query.getDocumentoIdentidadPasajero() != null ? reservasVueloRepository.findReservasIdByVueloIdAndPasajero( query.getDocumentoIdentidadPasajero(), vuelos.map( Vuelo::getId ).getContent() ) : Collections.emptyMap();

		return vuelos.map( v -> ApplicationMapper.vueloToDTO( v, vuelosReserva.get( v.getId() ) ) );
	}

}
