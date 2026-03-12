package es.um.atica.umufly.envios.adaptors.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.um.atica.umufly.envios.adaptors.persistence.jpa.entity.EnvioEntity;

public interface JpaEnvioRepository extends JpaRepository<EnvioEntity, String> {

}
