package es.um.atica.umufly.vuelos.adaptors.api.rest.v2;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.vuelos.adaptors.api.rest.AuthService;
import es.um.atica.umufly.vuelos.adaptors.api.rest.Constants;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v1.dto.VueloDTO;
import es.um.atica.umufly.vuelos.application.dto.VueloAmpliadoDTO;
import es.um.atica.umufly.vuelos.application.usecase.vuelos.GestionarVuelosUseCase;

@RestController
public class VuelosEndpointV2 {

	private final GestionarVuelosUseCase gestionarVuelosUseCase;
	private final VuelosModelAssemblerV2 vuelosModelAssembler;
	private final PagedResourcesAssembler<VueloAmpliadoDTO> pagedResourcesAssembler;
	private final AuthService authService;

	public VuelosEndpointV2( GestionarVuelosUseCase gestionarVuelosUseCase, VuelosModelAssemblerV2 vuelosModelAssembler, PagedResourcesAssembler<VueloAmpliadoDTO> pagedResourcesAssembler, AuthService authService ) {
		this.gestionarVuelosUseCase = gestionarVuelosUseCase;
		this.vuelosModelAssembler = vuelosModelAssembler;
		this.pagedResourcesAssembler = pagedResourcesAssembler;
		this.authService = authService;
	}

	@GetMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_2 + Constants.RESOURCE_VUELOS )
	public CollectionModel<VueloDTO> getVuelos( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @RequestParam( name = "page", defaultValue = "0" ) int page, @RequestParam( name = "size", defaultValue = "25" ) int size ) {
		return pagedResourcesAssembler.toModel( gestionarVuelosUseCase.listarVuelos( page, size, authService.parseUserHeader( usuario ) ), vuelosModelAssembler );
	}
}
