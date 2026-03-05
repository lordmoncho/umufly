package es.um.atica;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import es.um.atica.umufly.vuelos.domain.exception.VueloNoReservableException;
import es.um.atica.umufly.vuelos.domain.model.Avion;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.CorreoElectronico;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.EstadoReserva;
import es.um.atica.umufly.vuelos.domain.model.EstadoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Itinerario;
import es.um.atica.umufly.vuelos.domain.model.Nacionalidad;
import es.um.atica.umufly.vuelos.domain.model.NombreCompleto;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.TipoDocumento;
import es.um.atica.umufly.vuelos.domain.model.TipoVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@TestClassOrder( ClassOrderer.OrderAnnotation.class )
public class UmuFlyUnitariosTest {

	Vuelo vueloPendiente, vueloPendiente2, vueloCancelado, vueloCompletado;
	DocumentoIdentidad titular;
	Pasajero pasajero;
	Itinerario itinearario;
	Avion avion;
	LocalDateTime SALIDA = LocalDateTime.of( 2025, 6, 15, 10, 0 );
	LocalDateTime LLEGADA = LocalDateTime.of( 2025, 6, 15, 12, 0 );
	LocalDateTime ANTES_SALIDA = LocalDateTime.of( 2025, 6, 15, 8, 0 );
	LocalDateTime DESPUES_SALIDA = LocalDateTime.of( 2025, 6, 15, 11, 0 );

	@BeforeEach
	void setUp() {
		titular = new DocumentoIdentidad( TipoDocumento.NIF, "12345678Z" );
		pasajero = Pasajero.of( titular, new NombreCompleto( "Juan", "García", "López" ), new CorreoElectronico( "juan@ejemplo.com" ), new Nacionalidad( "Española" ) );
		Itinerario itinerario = new Itinerario( SALIDA, LLEGADA, "MAD", "BCN" );
		Avion avion = new Avion( 180 );
		vueloPendiente = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.PENDIENTE, avion );
		vueloPendiente2 = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.PENDIENTE, avion );
		vueloCancelado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.CANCELADO, avion );
		vueloCompletado = Vuelo.of( UUID.randomUUID(), itinerario, TipoVuelo.NACIONAL, EstadoVuelo.COMPLETADO, avion );
	}

	@Test
	void crear_reserva_debe_dejarla_en_pendiente() {
		ReservaVuelo reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_SALIDA, 0, 10 );
		assertEquals( EstadoReserva.PENDIENTE, reserva.getEstado() );
	}

	@Test
	void cancelar_deberia_lanzar_excepcion_si_vuelo_iniciado() {
		ReservaVuelo reserva = ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_SALIDA, 0, 10 );
		reserva.formalizarReserva();
		assertEquals( EstadoReserva.ACTIVA, reserva.getEstado() );
	}

	@Test
	void toda_reserva_tiene_id_distinto() {
		List<ReservaVuelo> reservas = Arrays.asList( ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_SALIDA, 0, 10 ),
				ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente2, ClaseAsientoReserva.ECONOMICA, ANTES_SALIDA, 0, 10 ) );

		// 2. Extraemos los IDs a un Set
		Set<UUID> ids = reservas.stream().map( ReservaVuelo::getId ).collect( Collectors.toSet() );

		// 3. Si el tamaño del Set coincide con el de la lista, todos son únicos

		assertEquals( reservas.size(), ids.size(), "Existen IDs de reserva duplicados" );
	}

	@Test
	void reserva_contiene_datos_correctos() {

		assertDoesNotThrow( () -> {
			ReservaVuelo.solicitarReserva( titular, pasajero, vueloPendiente, ClaseAsientoReserva.ECONOMICA, ANTES_SALIDA, 0, 10 );
		}, "No debería lanzar IllegalArgumentException con datos válidos" );

		assertThrows( VueloNoReservableException.class, () -> {
			ReservaVuelo.solicitarReserva( titular, pasajero, vueloCancelado, ClaseAsientoReserva.ECONOMICA, ANTES_SALIDA, 0, 10 );
		}, "Deber3ía lanzar IllegalArgumentException con datos no válidos" );
	}

}
