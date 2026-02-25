package es.um.atica.umufly.vuelos.application.usecase.listarreservas;

import org.springframework.data.domain.Page;

import es.um.atica.fundewebjs.umubus.domain.cqrs.Query;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class ListarReservasQuery extends Query<Page<ReservaVuelo>> {

	private DocumentoIdentidad documentoIdentidadPasajero;
	private int pagina;
	private int tamanioPagina;

	private ListarReservasQuery( DocumentoIdentidad documentoIdentidadPasajero, int pagina, int tamanioPagina ) {
		this.documentoIdentidadPasajero = documentoIdentidadPasajero;
		this.pagina = pagina;
		this.tamanioPagina = tamanioPagina;

	}

	public static ListarReservasQuery of( DocumentoIdentidad documentoIdentidadPasajero, int pagina, int tamanioPagina ) {
		return new ListarReservasQuery( documentoIdentidadPasajero, pagina, tamanioPagina );
	}

	public DocumentoIdentidad getDocumentoIdentidadPasajero() {
		return documentoIdentidadPasajero;
	}

	public int getPagina() {
		return pagina;
	}

	public int getTamanioPagina() {
		return tamanioPagina;
	}

}
