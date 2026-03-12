package es.um.atica.umufly.envios.adaptors.persistence.jpa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "MUCHOVUELO_PAQUETE_ENVIO", schema = "FORMACION_TICARUM" )
public class PaqueteEnvioEntity {

	@Id
	@Column( name = "ID_PAQUETE", length = 100 )
	private String id;

	@Column( name = "DESCRIPCION", length = 500, nullable = false )
	private String descripcion;

	// Usamos BigDecimal para precisión financiera/peso en bases de datos relacionales
	@Column( name = "PESO_KG", precision = 8, scale = 2, nullable = false )
	private BigDecimal pesoKg;

	@Column( name = "FRAGIL", length = 1, nullable = false )
	private String fragil; // 'S' o 'N'

	public PaqueteEnvioEntity() {}

	public PaqueteEnvioEntity( String id, String descripcion, BigDecimal pesoKg, boolean fragil ) {
		this.id = id;
		this.descripcion = descripcion;
		this.pesoKg = pesoKg;
		this.fragil = fragil ? "S" : "N";
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion( String descripcion ) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg( BigDecimal pesoKg ) {
		this.pesoKg = pesoKg;
	}

	public String getFragil() {
		return fragil;
	}

	public void setFragil( String fragil ) {
		this.fragil = fragil;
	}

}
