package es.um.atica.umufly.vuelos.adaptors.persistence.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.entity.EstadoReservaVueloEnum;
import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.entity.ReservaVueloViewEntity;
import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.mapper.JpaPersistenceMapper;
import es.um.atica.umufly.vuelos.adaptors.persistence.jpa.repository.JpaReservaVueloViewRepository;
import es.um.atica.umufly.vuelos.application.port.ReservasVueloRepository;
import es.um.atica.umufly.vuelos.domain.model.DocumentoIdentidad;
import es.um.atica.umufly.vuelos.domain.model.Pasajero;
import es.um.atica.umufly.vuelos.domain.model.ReservaVuelo;

@Component
public class ReservasVueloPersistenceAdapter implements ReservasVueloRepository {

	private final JpaReservaVueloViewRepository jpaReservaVueloViewRepository;

	public ReservasVueloPersistenceAdapter( JpaReservaVueloViewRepository jpaReservaVueloViewRepository ) {
		this.jpaReservaVueloViewRepository = jpaReservaVueloViewRepository;
	}

	@Override
	public Map<UUID, UUID> getReservasVueloByPasajeroAndVuelos( DocumentoIdentidad documentoIdentidad, List<UUID> idsVuelo ) {
		List<ReservaVueloViewEntity> reservas = this.jpaReservaVueloViewRepository.findByPasajerosTipoDocumentoAndPasajerosNumeroDocumentoAndIdVueloInAndEstadoReservaIn( JpaPersistenceMapper.tipoDocumentoToEntity( documentoIdentidad.tipo() ),
				documentoIdentidad.identificador(), idsVuelo.stream().map( UUID::toString ).toList(), Arrays.asList( EstadoReservaVueloEnum.A, EstadoReservaVueloEnum.P ) );
		return reservas.stream().collect( Collectors.toMap( r -> UUID.fromString( r.getIdVuelo() ), r -> UUID.fromString( r.getId() ) ) );

	}

	@Override
	public int countReservasByIdVueloAndPasajero( Pasajero pasajero, UUID idVuelo ) {
		return jpaReservaVueloViewRepository.countReservasByIdVueloAndPasajero( idVuelo.toString(), JpaPersistenceMapper.tipoDocumentoToEntity( pasajero.getIdentificador().tipo() ).toString(), pasajero.getIdentificador().identificador() );
	}

	@Override
	public void persistirReservaVuelo( ReservaVuelo reservaVuelo ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persistirFormalizacion( UUID id, UUID idReservaFormalizada ) {
		// TODO Auto-generated method stub

	}

}
