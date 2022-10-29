package com.sofka.contactos.repository;

import com.sofka.contactos.domain.Telefono;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Telefono entity
 *
 * @version 1.0.0 2022-10-28
 * @author Larry Mateo Ramirez C.
 */
public interface TelefonoRepository extends CrudRepository<Telefono, Integer> {
}