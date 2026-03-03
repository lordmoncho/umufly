package es.um.atica.umufly.parking.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;

public class ReservaParking {

	private final UUID id;
	private final DocumentoIdentidad identificadorPasajero;
	private final TipoReserva tipo;
	private final Periodo periodoEstacionamiento;
	private final LocalDateTime fechaReserva;
	private Importe importe;
	private EstadoReserva estado;

	public enum TipoReserva {
		CORTA_DURACION, LARGA_DURACION
	}

	public enum EstadoReserva {
		ACTIVA, CANCELADA
	}

	private ReservaParking( UUID id, DocumentoIdentidad identificadorPasajero, TipoReserva tipo, Periodo periodoEstacionamiento, Importe importeInicial, boolean tieneReservaVuelo ) {
		this.id = id;
		this.identificadorPasajero = identificadorPasajero;
		this.tipo = tipo;
		this.periodoEstacionamiento = periodoEstacionamiento;
		this.fechaReserva = LocalDateTime.now();
		this.estado = EstadoReserva.ACTIVA;

		// Regla de negocio: Descuento por reserva de vuelo
		this.importe = calcularImporteInicial( importeInicial, tieneReservaVuelo );
	}

	public static ReservaParking of( UUID id, DocumentoIdentidad identificadorPasajero, TipoReserva tipo, Periodo periodoEstacionamiento, Importe importeInicial, Boolean tieneReservaVuelo ) {
		if ( id == null ) {
			throw new IllegalArgumentException( "El id de la reserva no puede ser nulo" );
		}
		if ( identificadorPasajero == null ) {
			throw new IllegalArgumentException( "El titular de la reserva no puede ser nulo" );
		}
		if ( tipo == null ) {
			throw new IllegalArgumentException( "El tipo de reserva no puede estar nulo" );
		}
		if ( periodoEstacionamiento == null ) {
			throw new IllegalArgumentException( "El periodo de estacionamiento no puede ser nulo" );
		}
		if ( importeInicial == null ) {
			throw new IllegalArgumentException( "El importe inicial no puede ser nulo" );
		}
		if ( tieneReservaVuelo == null ) {
			throw new IllegalArgumentException( "tieneReservaVuelo no puede ser nulo" );
		}

		return new ReservaParking( id, identificadorPasajero, tipo, periodoEstacionamiento, importeInicial, tieneReservaVuelo );
	}

	private Importe calcularImporteInicial( Importe base, boolean tieneReservaVuelo ) {
		if ( tieneReservaVuelo ) {
			return new Importe( base.valor() * 0.75 );
		}
		return base;
	}

	public void cancelar() {
		// Regla de negocio: puede cancelar antes del inicio del periodo
		if ( LocalDateTime.now().isAfter( periodoEstacionamiento.inicio() ) ) {
			throw new IllegalStateException( "No se puede cancelar una reserva una vez iniciado el periodo" );
		}
		this.estado = EstadoReserva.CANCELADA;
	}

	// Getters
	public UUID getId() {
		return id;
	}

	public DocumentoIdentidad getIdentificadorPasajero() {
		return identificadorPasajero;
	}

	public TipoReserva getTipo() {
		return tipo;
	}

	public Periodo getPeriodoEstacionamiento() {
		return periodoEstacionamiento;
	}

	public Importe getImporte() {
		return importe;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public EstadoReserva getEstado() {
		return estado;
	}
}
