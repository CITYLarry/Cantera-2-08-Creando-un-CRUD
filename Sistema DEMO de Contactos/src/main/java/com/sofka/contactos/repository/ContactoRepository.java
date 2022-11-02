package com.sofka.contactos.repository;

import com.sofka.contactos.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for Contacto entity
 *
 * @version 1.0.0 2022-10-28
 * @author Larry Mateo Ramirez C.
 */
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {

    @Query(value = "SELECT c.id " +
            "FROM Contacto  c " +
            "WHERE c.cntNombre LIKE %:data% OR c.cntApellido LIKE %:data% " +
            "ORDER BY c.cntNombre ASC")
    public List<Contacto> findContacto(@Param("data") String data);

    @Modifying
    @Query(value = "UPDATE Contacto c " +
            "SET c.cntNombre = :name, c.cntUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE c.id= :id")
    public void updateFirstName(@Param(value = "id") Integer id, @Param(value = "name") String name);

    @Modifying
    @Query(value = "UPDATE Contacto c " +
            "SET c.cntApellido = :lastname, c.cntUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE c.id = :id")
    public void updateLastname(@Param(value = "id") Integer id, @Param(value = "lastname") String name);

}