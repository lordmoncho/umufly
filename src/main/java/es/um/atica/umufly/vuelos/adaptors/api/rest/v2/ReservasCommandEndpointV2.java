package es.um.atica.umufly.vuelos.adaptors.api.rest.v2;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.umufly.vuelos.adaptors.api.rest.AuthService;
import es.um.atica.umufly.vuelos.adaptors.api.rest.Constants;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v2.dto.ReservaVueloDTO;
import es.um.atica.umufly.vuelos.adaptors.api.rest.v2.mapper.ApiRestV2Mapper;
import es.um.atica.umufly.vuelos.application.usecase.cancelarreservas.CancelarReservaCommand;
import es.um.atica.umufly.vuelos.application.usecase.cancelarreservas.CancelarReservaCommandHandler;
import es.um.atica.umufly.vuelos.application.usecase.crearreservas.CrearReservaCommand;
import es.um.atica.umufly.vuelos.application.usecase.crearreservas.CrearReservaCommandHandler;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag( name = "VuelosV2", description = "Operaciones sobre vuelos version2" )
public class ReservasCommandEndpointV2 {

	private final CrearReservaCommandHandler crearReservaCommandHandler;
	private final CancelarReservaCommandHandler cancelarReservaCommandHandler;
	private final ReservasModelAssemblerV2 reservasModelAssembler;
	private final AuthService authService;

	public ReservasCommandEndpointV2( CrearReservaCommandHandler crearReservaCommandHandler, CancelarReservaCommandHandler cancelarReservaCommandHandler, ReservasModelAssemblerV2 reservasModelAssembler, AuthService authService ) {
		this.crearReservaCommandHandler = crearReservaCommandHandler;
		this.cancelarReservaCommandHandler = cancelarReservaCommandHandler;
		this.reservasModelAssembler = reservasModelAssembler;
		this.authService = authService;
	}

	@Operation( summary = "Crear reserva", description = "Crea una reserva en un vuelo" )
	@PostMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_2 + Constants.RESOURCE_RESERVAS_VUELO )
	public ReservaVueloDTO creaReserva( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @RequestBody @Valid ReservaVueloDTO nuevaReservaVuelo ) throws Exception {
		return reservasModelAssembler.toModel( crearReservaCommandHandler.handle( CrearReservaCommand.of( authService.parseUserHeader( usuario ), nuevaReservaVuelo.getVuelo().getId(),
				ClaseAsientoReserva.valueOf( nuevaReservaVuelo.getClaseAsiento().toString() ), ApiRestV2Mapper.pasajeroToModel( nuevaReservaVuelo.getPasajero() ) ) ) );
	}

	@Operation( summary = "Cancelar reserva", description = "Operación que permite cancelar una reserva" )
	@DeleteMapping( Constants.PRIVATE_PREFIX + Constants.API_VERSION_2 + Constants.RESOURCE_RESERVAS_VUELO + Constants.ID_RESERVA )
	public ReservaVueloDTO cancelarReserva2( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva3( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva4( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva5( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva6( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva7( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva8( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva9( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva10( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva11( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva12( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva13( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva14( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva15( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva16( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva17( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva18( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva19( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva20( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}

	public ReservaVueloDTO cancelarReserva21( @RequestHeader( name = "UMU-Usuario", required = true ) String usuario, @PathVariable( "idReserva" ) UUID idReserva ) throws Exception {
		return reservasModelAssembler.toModel( cancelarReservaCommandHandler.handle( CancelarReservaCommand.of( authService.parseUserHeader( usuario ), idReserva ) ) );
	}
}
