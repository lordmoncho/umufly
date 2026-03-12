package es.um.atica.umufly.envios.adaptors.persistence.jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table( name = "MUCHOVUELO_ENVIO", schema = "FORMACION_TICARUM" )
public class EnvioEntity {

	@Id
	@Column( name = "ID_ENVIO", length = 100 )
	private String id;

	// Relaciones OneToOne para composición
	@OneToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "ID_REMITENTE", referencedColumnName = "ID_PARTICIPANTE", nullable = false )
	private ParticipanteEnvioEntity remitente;

	@OneToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "ID_DESTINATARIO", referencedColumnName = "ID_PARTICIPANTE", nullable = false )
	private ParticipanteEnvioEntity destinatario;

	@OneToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "ID_PAQUETE", referencedColumnName = "ID_PAQUETE", nullable = false )
	private PaqueteEnvioEntity paquete;

	@Column( name = "ID_VUELO", length = 50, nullable = false )
	private String idVuelo;

	@Column( name = "ID_SEGUIMIENTO", length = 50, nullable = false, unique = true )
	private String idSeguimiento;

	@Column( name = "IMPORTE_ENVIO", precision = 10, scale = 2, nullable = false )
	private BigDecimal importeEnvio;

	@Column( name = "ESTADO", length = 20, nullable = false )
	private String estado;

	public EnvioEntity() {}

	public EnvioEntity( String id, ParticipanteEnvioEntity remitente, ParticipanteEnvioEntity destinatario, PaqueteEnvioEntity paquete, String idVuelo, String idSeguimiento, BigDecimal importeEnvio, String estado ) {
		this.id = id;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.paquete = paquete;
		this.idVuelo = idVuelo;
		this.idSeguimiento = idSeguimiento;
		this.importeEnvio = importeEnvio;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public ParticipanteEnvioEntity getRemitente() {
		return remitente;
	}

	public void setRemitente( ParticipanteEnvioEntity remitente ) {
		this.remitente = remitente;
	}

	public ParticipanteEnvioEntity getDestinatario() {
		return destinatario;
	}

	public void setDestinatario( ParticipanteEnvioEntity destinatario ) {
		this.destinatario = destinatario;
	}

	public PaqueteEnvioEntity getPaquete() {
		return paquete;
	}

	public void setPaquete( PaqueteEnvioEntity paquete ) {
		this.paquete = paquete;
	}

	public String getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo( String idVuelo ) {
		this.idVuelo = idVuelo;
	}

	public String getIdSeguimiento() {
		return idSeguimiento;
	}

	public void setIdSeguimiento( String idSeguimiento ) {
		this.idSeguimiento = idSeguimiento;
	}

	public BigDecimal getImporteEnvio() {
		return importeEnvio;
	}

	public void setImporteEnvio( BigDecimal importeEnvio ) {
		this.importeEnvio = importeEnvio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado( String estado ) {
		this.estado = estado;
	}

}
