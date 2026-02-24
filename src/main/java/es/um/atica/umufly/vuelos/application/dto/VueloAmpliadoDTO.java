package es.um.atica.umufly.vuelos.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class VueloAmpliadoDTO {

	private UUID idVuelo;
	private String tipoVuelo;
	private LocalDateTime salidaVuelo;
	private LocalDateTime llegadaVuelo;
	private UUID idReserva;
	private String origen;
	private String destino;
	private String estado;
	private Integer capacidad;

	public VueloAmpliadoDTO( UUID idVuelo, String tipoVuelo, LocalDateTime salidaVuelo, LocalDateTime llegadaVuelo, UUID idReserva, String origen, String destino, String estado, Integer capacidad ) {
		this.idVuelo = idVuelo;
		this.tipoVuelo = tipoVuelo;
		this.salidaVuelo = salidaVuelo;
		this.llegadaVuelo = llegadaVuelo;
		this.idReserva = idReserva;
		this.origen = origen;
		this.destino = destino;
		this.estado = estado;
		this.capacidad = capacidad;
	}

	public UUID getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo( UUID idVuelo ) {
		this.idVuelo = idVuelo;
	}

	public String getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo( String tipoVuelo ) {
		this.tipoVuelo = tipoVuelo;
	}

	public LocalDateTime getSalidaVuelo() {
		return salidaVuelo;
	}

	public void setSalidaVuelo( LocalDateTime salidaVuelo ) {
		this.salidaVuelo = salidaVuelo;
	}

	public LocalDateTime getLlegadaVuelo() {
		return llegadaVuelo;
	}

	public void setLlegadaVuelo( LocalDateTime llegadaVuelo ) {
		this.llegadaVuelo = llegadaVuelo;
	}

	public UUID getIdReserva() {
		return idReserva;
	}

	public void setIdReserva( UUID idReserva ) {
		this.idReserva = idReserva;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen( String origen ) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino( String destino ) {
		this.destino = destino;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado( String estado ) {
		this.estado = estado;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad( Integer capacidad ) {
		this.capacidad = capacidad;
	}

}
