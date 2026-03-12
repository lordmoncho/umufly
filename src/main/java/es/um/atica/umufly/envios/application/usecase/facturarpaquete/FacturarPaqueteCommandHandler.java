package es.um.atica.umufly.envios.application.usecase.facturarpaquete;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.envios.application.port.EnvioWriteRepository;
import es.um.atica.umufly.envios.domain.model.Envio;
import es.um.atica.umufly.vuelos.application.port.VuelosRepositoryPort;
import es.um.atica.umufly.vuelos.domain.model.Vuelo;

@Component
public class FacturarPaqueteCommandHandler implements SyncCommandHandler<Envio, FacturarPaqueteCommand> {

	private final EnvioWriteRepository envioWriteRepository;
	private final VuelosRepositoryPort vueloRepositoryPort;

	public FacturarPaqueteCommandHandler( EnvioWriteRepository envioWriteRepository, VuelosRepositoryPort vueloRepositoryPort ) {
		this.envioWriteRepository = envioWriteRepository;
		this.vueloRepositoryPort = vueloRepositoryPort;
	}

	@Override
	public Envio handle( FacturarPaqueteCommand command ) throws Exception {
		// 1. Recuperamos la reserva del vuelo
		Vuelo vuelo = vueloRepositoryPort.findVuelo( command.getVueloId() );

		Envio facturarPaquete = Envio.facturarPaquete( command.getRemitente(), command.getDestinatario(), command.getPaquete(), vuelo );

		envioWriteRepository.guardarEnvio( facturarPaquete );

		return facturarPaquete;
	}

}
