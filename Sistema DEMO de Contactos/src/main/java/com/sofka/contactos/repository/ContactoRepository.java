package com.sofka.contactos.repository;

import com.sofka.contactos.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Contacto entity
 *
 * @version 1.0.0 2022-10-28
 * @author Larry Mateo Ramirez C.
 */
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}