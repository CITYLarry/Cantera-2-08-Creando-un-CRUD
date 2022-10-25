package com.sofka.contactos.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Contact entity
 *
 * @version 1.0.0 2022-10-25
 * @author Larry Mateo Ramirez C.
 */
@Data
@Entity
@Table(name = "contacto")
public class Contacto implements Serializable {

    /**
     * Handle the tuple id
     */
    private static final long serialVersionUID = 1L;

    /**
     * Tuple id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cnt_id", columnDefinition = "INT UNSIGNED not null")
    private Integer id;

    /**
     * Contact firstname
     */
    @Column(name = "cnt_nombre", nullable = false, length = 100)
    private String cntNombre;

    /**
     * Contact lastname
     */
    @Column(name = "cnt_apellido", nullable = false, length = 100)
    private String cntApellido;

    /**
     * Tuple creation date-time
     */
    @Column(name = "cnt_created_at", nullable = false)
    private Instant cntCreatedAt;

    /**
     * Tuple last update date-time
     */
    @Column(name = "cnt_updated_at")
    private Instant cntUpdatedAt;

    /**
     * Entities link between Contacto and Telefono
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Telefono.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "telContacto"
    )
    @JsonManagedReference
    private List<Telefono> telefonos = new ArrayList<>();
}