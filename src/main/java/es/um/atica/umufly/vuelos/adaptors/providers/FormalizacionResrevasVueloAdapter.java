package es.um.atica.umufly.vuelos.adaptors.providers;

import java.util.UUID;

import es.um.atica.umufly.vuelos.adaptors.providers.muchovuelo.MuchoVueloClient;
import es.um.atica.umufly.vuelos.adaptors.providers.muchovuelo.dto.ReservaVueloDTO;
import es.um.atica.umufly.vuelos.adaptors.providers.muchovuelo.mapper.MuchoVueloMapper;
import es.um.atica.umufly.vuelos.application.port.FormalizacionReservaPort;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

public class FormalizacionResrevasVueloAdapter implements FormalizacionReservaPort {

	private MuchoVueloClient muchoVueloClient;

	@Override
	public UUID formalizarReservaVuelo( ReservaVuelo reservaVuelo ) {
		ReservaVueloDTO resrevaVueloDTO = muchoVueloClient.creaReservaVuelo( MuchoVueloMapper.reservaToDTO( reservaVuelo ) );
		return resrevaVueloDTO.getId();
	}

}
