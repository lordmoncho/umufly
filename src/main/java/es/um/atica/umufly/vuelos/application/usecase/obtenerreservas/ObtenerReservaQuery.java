package es.um.atica.umufly.vuelos.application.usecase.obtenerreservas;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class ObtenerReservaQuery extends Query<ReservaVuelo> {

	private final DocumentoIdentidad documentoIdentidadPasajero;
	private final UUID idReserva;

	private ObtenerReservaQuery( DocumentoIdentidad documentoIdentidadPasajero, UUID idReserva ) {
		this.documentoIdentidadPasajero = documentoIdentidadPasajero;
		this.idReserva = idReserva;
	}

	public static ObtenerReservaQuery of( DocumentoIdentidad documentoIdentidadPasajero, UUID idVuelo ) {
		return new ObtenerReservaQuery( documentoIdentidadPasajero, idVuelo );
	}

	public DocumentoIdentidad getDocumentoIdentidadPasajero() {
		return documentoIdentidadPasajero;
	}

	public UUID getIdReserva() {
		return idReserva;
	}

}
