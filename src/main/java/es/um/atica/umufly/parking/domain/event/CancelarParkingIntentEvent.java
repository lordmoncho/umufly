package es.um.atica.umufly.parking.domain.event;

import es.um.atica.fundewebjs.umubus.domain.events.Event;

public class CancelarParkingIntentEvent extends Event {

	private final String id;
	private final String ideReservaFormalizada;
	private final String tipoIdentificadorCliente;
	private final String numeroIdentificadorCliente;

	private CancelarParkingIntentEvent( String id, String ideReservaFormalizada, String tipoIdentificadorCliente, String numeroIdentificadorCliente ) {
		this.id = id;
		this.ideReservaFormalizada = ideReservaFormalizada;
		this.tipoIdentificadorCliente = tipoIdentificadorCliente;
		this.numeroIdentificadorCliente = numeroIdentificadorCliente;
	}

	public static CancelarParkingIntentEvent of( String id, String ideReservaFormalizada, String tipoIdentificadorCliente, String numeroIdentificadorCliente ) {
		return new CancelarParkingIntentEvent( id, ideReservaFormalizada, tipoIdentificadorCliente, numeroIdentificadorCliente );
	}

	public String getId() {
		return id;
	}

	public String getIdeReservaFormalizada() {
		return ideReservaFormalizada;
	}

	public String getTipoIdentificadorCliente() {
		return tipoIdentificadorCliente;
	}

	public String getNumeroIdentificadorCliente() {
		return numeroIdentificadorCliente;
	}

}
