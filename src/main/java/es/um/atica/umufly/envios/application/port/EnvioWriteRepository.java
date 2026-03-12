package es.um.atica.umufly.envios.application.port;

import es.um.atica.umufly.envios.domain.model.Envio;

public interface EnvioWriteRepository {

	void guardarEnvio( Envio envio );
}
