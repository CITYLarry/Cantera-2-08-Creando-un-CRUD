package com.sofka.contactos.repository;

import com.sofka.contactos.domain.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for Telefono entity
 *
 * @version 1.0.0 2022-10-28
 * @author Larry Mateo Ramirez C.
 */
public interface TelefonoRepository extends JpaRepository<Telefono, Integer> {

    @Modifying
    @Query(value = "UPDATE Telefono t " +
            "SET t.telTelefono = :telephone, t.telUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE t.id = :id")
    public void updateTelefono(@Param(value = "id") Integer id, @Param(value = "telephone") String telephone);

}