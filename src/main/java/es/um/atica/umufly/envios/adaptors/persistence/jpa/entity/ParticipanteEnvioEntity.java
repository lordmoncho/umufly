package es.um.atica.umufly.envios.adaptors.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "MUCHOVUELO_PARTICIPANTE_ENVIO", schema = "FORMACION_TICARUM" )
public class ParticipanteEnvioEntity {

	@Id
	@Column( name = "ID_PARTICIPANTE", length = 100 )
	private String id;

	@Column( name = "NOMBRE", length = 100, nullable = false )
	private String nombre;

	@Column( name = "APELLIDOS", length = 150, nullable = false )
	private String apellidos;

	@Column( name = "NUMERO_DOCUMENTO", length = 15, nullable = false )
	private String numeroDocumento;

	@Column( name = "TIPO_DOCUMENTO", length = 2, nullable = false )
	private String tipoDocumento;

	@Column( name = "EMAIL", length = 250, nullable = false )
	private String email;

	@Column( name = "TELEFONO", length = 30 )
	private String telefono;

	@Column( name = "TIPO_PARTICIPANTE", length = 20, nullable = false )
	private String tipoParticipante;

	// Constructor vacío requerido por JPA
	public ParticipanteEnvioEntity() {}

	// Constructor completo
	public ParticipanteEnvioEntity( String id, String nombre, String apellidos, String numeroDocumento, String tipoDocumento, String email, String telefono, String tipoParticipante ) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.numeroDocumento = numeroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.email = email;
		this.telefono = telefono;
		this.tipoParticipante = tipoParticipante;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos( String apellidos ) {
		this.apellidos = apellidos;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento( String numeroDocumento ) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento( String tipoDocumento ) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono( String telefono ) {
		this.telefono = telefono;
	}

	public String getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante( String tipoParticipante ) {
		this.tipoParticipante = tipoParticipante;
	}

}
