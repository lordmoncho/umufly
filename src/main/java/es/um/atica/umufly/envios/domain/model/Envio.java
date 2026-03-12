package es.um.atica.umufly.envios.domain.model;

import java.util.UUID;

// Agregado raíz
public class Envio {

	private UUID id;
	private UUID vueloId;
	private NumeroSeguimiento numeroSeguimiento;
	private Persona remitente;
	private Persona destinatario;
	private Paquete paquete;
	private EstadoEnvio estado;
	private double precioTotal;

	private Envio( UUID id, UUID vueloId, NumeroSeguimiento numeroSeguimiento, Persona remitente, Persona destinatario, Paquete paquete, EstadoEnvio estado, double precioTotal ) {
		this.id = id;
		this.vueloId = vueloId;
		this.numeroSeguimiento = numeroSeguimiento;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.paquete = paquete;
		this.estado = estado;
		this.precioTotal = precioTotal;
	}

	public static Envio of( UUID id, NumeroSeguimiento numeroSeguimiento, UUID vueloId, Persona remitente, Persona destinatario, Paquete paquete, EstadoEnvio estado ) {
		if ( id == null ) {
			throw new IllegalArgumentException( "El id del envio no puede ser nulo" );
		}
		if ( numeroSeguimiento == null ) {
			throw new IllegalArgumentException( "El trackingId del envio no puede ser nulo" );
		}
		if ( vueloId == null ) {
			throw new IllegalArgumentException( "El id del envio no puede ser nulo" );
		}
		if ( remitente == null ) {
			throw new IllegalArgumentException( "El remitente del envio no puede ser nulo" );
		}
		if ( destinatario == null ) {
			throw new IllegalArgumentException( "El destinatario del envio no puede ser nulo" );
		}
		if ( paquete == null ) {
			throw new IllegalArgumentException( "El paquete del envio no puede ser nulo" );
		}
		if ( estado == null ) {
			throw new IllegalArgumentException( "El estado del envio no puede ser nulo" );
		}

		return new Envio( id, vueloId, numeroSeguimiento, remitente, destinatario, paquete, estado, paquete.calcularPrecioTotal() );
	}

	// --- Lógica de Negocio ---

	public static Envio facturarPaquete( UUID vueloId, Persona remitente, Persona destinatario, Paquete paquete ) {
		return of( UUID.randomUUID(), NumeroSeguimiento.generate(), vueloId, remitente, destinatario, paquete, EstadoEnvio.FACTURADO );
	}

	public void entregarPaquete() {
		if ( this.estado != EstadoEnvio.EN_TRANSITO ) {
			throw new IllegalStateException( "El envío debe estar en tránsito para ser entregado en taquilla." );
		}
		this.estado = EstadoEnvio.ENTREGADO;
	}

	public UUID getId() {
		return id;
	}

	public UUID getVueloId() {
		return vueloId;
	}

	public NumeroSeguimiento getTrackingId() {
		return numeroSeguimiento;
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

	public EstadoEnvio getEstado() {
		return estado;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public static void main( String[] args ) {
		NumeroSeguimiento f = NumeroSeguimiento.generate();
		System.out.println( f );
	}
}
