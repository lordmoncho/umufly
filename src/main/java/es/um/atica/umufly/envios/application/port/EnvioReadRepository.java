package es.um.atica.umufly.envios.application.port;

import java.util.Optional;

import es.um.atica.umufly.envios.domain.model.Envio;
import es.um.atica.umufly.envios.domain.model.NumeroSeguimiento;

public interface EnvioReadRepository {

	Optional<Envio> buscarPorTracking( NumeroSeguimiento numeroSeguimiento );

}
