package es.um.atica.umufly.envios.domain.model;

public record Paquete( String descripcion, double pesoKg, CategoriaCarga categoria ) {

	public Paquete {
		if ( pesoKg <= 0 ) {
			throw new IllegalArgumentException( "El peso debe ser positivo" );
		}
	}

	public double calcularPrecioTotal() {
		return pesoKg * categoria.getPrecioPorKg();
	}
}
