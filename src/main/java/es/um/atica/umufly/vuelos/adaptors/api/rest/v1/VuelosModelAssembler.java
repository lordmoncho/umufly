package es.um.atica.umufly.vuelos.adaptors.api.rest.v1;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.dto.VueloDTO;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.mapper.ApiRestMapper;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;

@Component
public class VuelosModelAssembler implements RepresentationModelAssembler<VueloAmpliadoDTO, VueloDTO> {

	@Override
	public VueloDTO toModel( VueloAmpliadoDTO entity ) {
		return ApiRestMapper.vueloToDTO( entity );
	}

}
