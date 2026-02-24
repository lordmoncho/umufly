package es.um.atica.umufly.vuelos.adaptors.api.rest.v2;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.vuelos.adaptors.api.rest.Constants;
import es.um.atica.umufly.vuelos.adaptors.api.rest.LinkService;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.dto.VueloDTO;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.mapper.ApiRestMapper;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;

@Component
public class VuelosModelAssemblerV2 implements RepresentationModelAssembler<VueloAmpliadoDTO, VueloDTO> {

	private final LinkService linkService;

	public VuelosModelAssemblerV2( LinkService linkService ) {
		this.linkService = linkService;
	}

	@Override
	public VueloDTO toModel( VueloAmpliadoDTO entity ) {
		VueloDTO vuelo = ApiRestMapper.vueloToDTO( entity );

		if ( entity.getIdReserva() != null ) {
			vuelo.add( getLinkReservaVuelos( entity.getIdReserva().toString() ) );
		}

		return vuelo;

	}

	private Link getLinkReservaVuelos( String idReserva ) {
		return Link.of( linkService.privateApiV2().path( Constants.RESOURCE_RESERVAS_VUELO ).pathSegment( idReserva ).build().toString(), "reserva-vuelo" );
	}

}
