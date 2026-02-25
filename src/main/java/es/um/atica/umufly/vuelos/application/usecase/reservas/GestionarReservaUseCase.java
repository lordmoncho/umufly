package es.um.atica.umufly.vuelos.application.usecase.reservas;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import es.um.atica.umufly.vuelos.application.port.FormalizacionReservasVueloWritePort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloReadRepository;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloWriteRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosReadRepository;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class GestionarReservaUseCase {

	private final VuelosReadRepository vuelosReadRepository;
	private final ReservasVueloReadRepository reservasVueloReadRepository;
	private final ReservasVueloWriteRepository reservasVueloWriteRepository;
	private final FormalizacionReservasVueloWritePort formalizacionReservasVueloPort;
	private final Clock clock;

	public GestionarReservaUseCase( VuelosReadRepository vuelosRepository, ReservasVueloReadRepository reservasVueloRepository, ReservasVueloWriteRepository reservasVueloWriteRepository, FormalizacionReservasVueloWritePort formalizacionReservasVueloPort,
			Clock clock ) {
		this.vuelosReadRepository = vuelosRepository;
		this.reservasVueloReadRepository = reservasVueloRepository;
		this.formalizacionReservasVueloPort = formalizacionReservasVueloPort;
		this.reservasVueloWriteRepository = reservasVueloWriteRepository;
		this.clock = clock;
	}

	public ReservaVuelo creaReserva( DocumentoIdentidad documentoIdentidadTitular, UUID idVuelo, ClaseAsientoReserva claseAsiento, Pasajero pasajero ) {
		// 1. Recuperar el vuelo
		Vuelo vuelo = vuelosReadRepository.findVuelo( idVuelo );

		// 2. Recuperar el número de reservas del pasajero en el vuelo
		int numeroReservasPasajeroEnVuelo = reservasVueloReadRepository.countReservasByIdVueloAndPasajero( idVuelo, pasajero );

		// 3. Recuperar el número de plazas disponibles en el avión
		int numeroPlazasDisponiblesAvion = vuelosReadRepository.plazasDisponiblesEnVuelo( vuelo );

		// 4. Creamos y persistimos la reserva de vuelo
		ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( documentoIdentidadTitular, pasajero, vuelo, claseAsiento, LocalDateTime.now( clock ), numeroReservasPasajeroEnVuelo, numeroPlazasDisponiblesAvion );
		reservasVueloWriteRepository.persistirReserva( reservaVuelo );

		// 5. Formalizamos la reserva llamando al backoffice para que se haga eco de la nueva reserva que acabamos de crear
		UUID idReservaFormalizada = formalizacionReservasVueloPort.formalizarReservaVuelo( reservaVuelo );
		reservaVuelo.formalizarReserva();
		reservasVueloWriteRepository.persistirFormalizacionReserva( reservaVuelo.getId(), idReservaFormalizada );

		return reservaVuelo;
	}

	public ReservaVuelo cancelarReserva( DocumentoIdentidad documentoIdentidadTitular, UUID idReserva ) {
		// 1. Recuperamos la reserva
		ReservaVuelo reservaVuelo = reservasVueloReadRepository.findReservaById( documentoIdentidadTitular, idReserva );

		// 2. Cancelamos la reserva en el fronOffice
		reservaVuelo.cancelarReserva( LocalDateTime.now( clock ) );
		reservasVueloWriteRepository.cancelReserva( reservaVuelo.getId() );

		// 3. Cancelamos la reserva llamando al backoffice para que se haga eco de la cancelacion
		UUID idReservaFormalizada = reservasVueloReadRepository.findIdFormalizadaByReservaById( idReserva );
		formalizacionReservasVueloPort.cancelarReservaVuelo( documentoIdentidadTitular, idReservaFormalizada );

		return reservaVuelo;
	}

	public Page<ReservaVuelo> listarReservas( DocumentoIdentidad documentoIdentidad, int pagina, int tamanioPagina ) {
		return reservasVueloReadRepository.findReservas( documentoIdentidad, pagina, tamanioPagina );

	}

	public ReservaVuelo obtenerReserva( DocumentoIdentidad documentoIdentidad, UUID idReserva ) {
		return reservasVueloReadRepository.findReservaById( documentoIdentidad, idReserva );
	}

}
