package es.um.atica.umufly.vuelos.adaptors.api.rest.v1;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.vuelos.adaptors.api.rest.Constants;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.dto.VueloDTO;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.application.usecase.vuelos.GestionarVuelosUseCase;

@RestController
public class VuelosEndpoint {

	private final GestionarVuelosUseCase gestionarVuelosUseCase;
	private final VuelosModelAssembler vuelosModelAssembler;
	private final PagedResourcesAssembler<VueloAmpliadoDTO> pagedResourcesAssembler;

	public VuelosEndpoint( GestionarVuelosUseCase gestionarVuelosUseCase, VuelosModelAssembler vuelosModelAssembler, PagedResourcesAssembler<VueloAmpliadoDTO> pagedResourcesAssembler ) {
		this.gestionarVuelosUseCase = gestionarVuelosUseCase;
		this.vuelosModelAssembler = vuelosModelAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
	}

	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_1 + Constants.RESOURCE_VUELOS )
	public CollectionModel<VueloDTO> getVuelos( @RequestParam( name = "page", defaultValue = "0" ) int page, @RequestParam( name = "size", defaultValue = "25" ) int size ) {
		return pagedResourcesAssembler.toModel( gestionarVuelosUseCase.listarVuelos( page, size, null ), vuelosModelAssembler );
	}
}
