package es.um.atica.umufly.envios.domain.model;

public enum CategoriaCarga {

	ESTANDAR( 2.5 ), FRAGIL( 4.0 );

	private final double precioPorKg;

	CategoriaCarga( double precioPorKg ) {
		this.precioPorKg = precioPorKg;
	}

	public double getPrecioPorKg() {
		return precioPorKg;
	}
}
