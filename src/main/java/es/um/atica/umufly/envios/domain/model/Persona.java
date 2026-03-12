package es.um.atica.umufly.envios.domain.model;

public class Persona {

	private DocumentoIdentidad identificador;
	private NombreCompleto nombre;
	private CorreoElectronico correo;
	private Telefono telefonoContacto;

	private Persona( DocumentoIdentidad identificador, NombreCompleto nombre, CorreoElectronico correo, Telefono telefonoContacto ) {
		this.identificador = identificador;
		this.nombre = nombre;
		this.correo = correo;
	}

	public static Persona of( DocumentoIdentidad identificador, NombreCompleto nombre, CorreoElectronico correo, Telefono telefonoContacto ) {
		if ( identificador == null ) {
			throw new IllegalArgumentException( "El identificador no puede ser nulo" );
		}
		if ( nombre == null ) {
			throw new IllegalArgumentException( "El nombre no puede ser nulo" );
		}
		if ( correo == null ) {
			throw new IllegalArgumentException( "El correo no puede ser nulo" );
		}
		if ( telefonoContacto == null ) {
			throw new IllegalArgumentException( "El telefono de contacto no puede ser nulo" );
		}
		return new Persona( identificador, nombre, correo, telefonoContacto );
	}

	public DocumentoIdentidad getIdentificador() {
		return identificador;
	}

	public NombreCompleto getNombre() {
		return nombre;
	}

	public CorreoElectronico getCorreo() {
		return correo;
	}

	public Telefono getTelefonoContacto() {
		return telefonoContacto;
	}

}
