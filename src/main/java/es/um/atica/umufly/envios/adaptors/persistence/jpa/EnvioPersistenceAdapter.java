package es.um.atica.umufly.envios.adaptors.persistence.jpa;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.um.atica.umufly.envios.application.port.EnvioReadRepository;
import es.um.atica.umufly.envios.application.port.EnvioWriteRepository;
import es.um.atica.umufly.envios.domain.model.Envio;
import es.um.atica.umufly.envios.domain.model.NumeroSeguimiento;

@Component
public class EnvioPersistenceAdapter implements EnvioWriteRepository, EnvioReadRepository {

	@Override
	public Optional<Envio> buscarPorTracking( NumeroSeguimiento numeroSeguimiento ) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void guardarEnvio( Envio envio ) {
		// TODO Auto-generated method stub

	}

}
