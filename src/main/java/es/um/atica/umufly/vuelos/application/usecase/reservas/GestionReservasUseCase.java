package es.um.atica.umufly.vuelos.application.usecase.reservas;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import es.um.atica.umufly.vuelos.application.port.FormalizacionReservaPort;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.application.port.VuelosRepository;
import es.um.atica.umufly.vuelos.domain.model.ClaseAsientoReserva;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class GestionReservasUseCase {

	private final VuelosRepository vuelosRepository;
	private final ReservasVueloRepository reservasVueloRepository;
	private final FormalizacionReservaPort formalizacionResrevaPort;
	private final Clock clock;

	public GestionReservasUseCase( VuelosRepository vuelosRepository, ReservasVueloRepository reservasVueloRepository, FormalizacionReservaPort formalizacionResrevaPort, Clock clock ) {
		this.vuelosRepository = vuelosRepository;
		this.reservasVueloRepository = reservasVueloRepository;
		this.formalizacionResrevaPort = formalizacionResrevaPort;
		this.clock = clock;
	}

	public ReservaVuelo creaReservaVuelo( DocumentoIdentidad documentoIdentidadTitular, UUID idVuelo, ClaseAsientoReserva claseAsiento, Pasajero pasajero ) {

		Vuelo vuelo = vuelosRepository.findVuelo( idVuelo );
		int numeroReservasVuelo = reservasVueloRepository.countReservasVueloByPasajeroAndIdVuelo( pasajero, idVuelo );
		int numeroPlazasDisponibles = vuelosRepository.getPlazasDisponiblesAvion( idVuelo );

		ReservaVuelo reservaVuelo = ReservaVuelo.solicitarReserva( documentoIdentidadTitular, pasajero, vuelo, claseAsiento, LocalDateTime.now( clock ), numeroReservasVuelo, numeroPlazasDisponibles );

		reservasVueloRepository.persistirReservaVuelo( reservaVuelo );

		UUID idReservaFormalizada = formalizacionResrevaPort.formalizarReservaVuelo( reservaVuelo );

		reservaVuelo.formalizarReserva();

		reservasVueloRepository.persistirFormalizacion( reservaVuelo.getId(), idReservaFormalizada );

		return reservaVuelo;
	}

}
