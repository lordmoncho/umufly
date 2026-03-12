package es.um.atica.umufly.envios.application.usecase.facturarpaquete;

import org.springframework.stereotype.Component;

import es.um.atica.fundewebjs.umubus.domain.cqrs.SyncCommandHandler;
import es.um.atica.umufly.envios.application.port.EnvioWriteRepository;
import es.um.atica.umufly.envios.domain.model.Envio;

@Component
public class FacturarPaqueteCommandHandler implements SyncCommandHandler<Envio, FacturarPaqueteCommand> {

	private final EnvioWriteRepository envioWriteRepository;

	public FacturarPaqueteCommandHandler( EnvioWriteRepository envioWriteRepository ) {
		this.envioWriteRepository = envioWriteRepository;
	}

	@Override
	public Envio handle( FacturarPaqueteCommand command ) throws Exception {
		// 1. Recuperamos la reserva del vuelo
		command.getVueloId();

		Envio facturarPaquete = Envio.facturarPaquete( command.getVueloId(), command.getRemitente(), command.getDestinatario(), command.getPaquete() );

		envioWriteRepository.guardarEnvio( facturarPaquete );

		return facturarPaquete;
	}

}
