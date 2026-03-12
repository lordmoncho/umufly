package es.um.atica.umufly.envios.application.usecase.facturarpaquete;

import java.util.UUID;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommand;
import es.um.atica.umufly.envios.domain.model.Envio;
import es.um.atica.umufly.envios.domain.model.Paquete;
import es.um.atica.umufly.envios.domain.model.Persona;

public class FacturarPaqueteCommand extends SyncCommand<Envio> {

	private final UUID vueloId;
	private final Persona remitente;
	private final Persona destinatario;
	private final Paquete paquete;

	private FacturarPaqueteCommand( UUID vueloId, Persona remitente, Persona destinatario, Paquete paquete ) {
		this.vueloId = vueloId;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.paquete = paquete;
	}

	public static FacturarPaqueteCommand of( UUID vueloId, Persona remitente, Persona destinatario, Paquete paquete ) {
		return new FacturarPaqueteCommand( vueloId, remitente, destinatario, paquete );
	}

	public UUID getVueloId() {
		return vueloId;
	}

	public Persona getRemitente() {
		return remitente;
	}

	public Persona getDestinatario() {
		return destinatario;
	}

	public Paquete getPaquete() {
		return paquete;
	}

}
