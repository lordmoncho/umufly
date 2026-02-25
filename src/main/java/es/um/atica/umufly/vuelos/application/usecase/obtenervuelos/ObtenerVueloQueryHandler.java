package es.um.atica.umufly.vuelos.application.usecase.obtenervuelos;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.QueryHandler;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.application.mapper.ApplicationMapper;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosRepository;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class ObtenerVueloQueryHandler implements QueryHandler<Optional<VueloAmpliadoDTO>, ObtenerVueloQuery> {

	private final VuelosRepository vuelosRepository;
	private final ReservasVueloRepository reservasVueloRepository;

	public ObtenerVueloQueryHandler( VuelosRepository vuelosRepository, ReservasVueloRepository reservasVueloRepository ) {
		this.vuelosRepository = vuelosRepository;
		this.reservasVueloRepository = reservasVueloRepository;
	}

	@Override
	public Optional<VueloAmpliadoDTO> handle( ObtenerVueloQuery query ) throws Exception {
		Vuelo vuelo = vuelosRepository.findVuelo( query.getIdVuelo() );
		UUID vueloReserva = query.getDocumentoIdentidadPasajero() != null ? reservasVueloRepository.findReservaIdByVueloIdAndPasajero( query.getDocumentoIdentidadPasajero(), vuelo.getId() ) : null;

		return Optional.ofNullable( ApplicationMapper.vueloToDTO( vuelo, vueloReserva ) );
	}

}
