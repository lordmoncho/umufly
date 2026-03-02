package es.um.atica.umufly.parking.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

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

	public ReservaParking( UUID id, DocumentoIdentidad identificadorPasajero, TipoReserva tipo, Periodo periodoEstacionamiento, Importe importeInicial, boolean tieneReservaVuelo ) {
		this.id = id;
		this.identificadorPasajero = identificadorPasajero;
		this.tipo = tipo;
		this.periodoEstacionamiento = periodoEstacionamiento;
		this.fechaReserva = LocalDateTime.now();
		this.estado = EstadoReserva.ACTIVA;

		// Regla de negocio: Descuento por reserva de vuelo
		this.importe = calcularImporteInicial( importeInicial, tieneReservaVuelo );
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
