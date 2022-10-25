package com.sofka.contactos.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.time.Instant;

/**
 * Telefono entity
 *
 * @version 1.0.0 2022-10-25
 * @author Larry Mateo Ramirez C.
 */
@Data
@Entity
@Table(name = "telefono")
public class Telefono implements Serializable {

    /**
     * Handle the tuple id
     */
    private static final long serialVersionUID = 1L;

    /**
     * Tuple id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tel_id", columnDefinition = "INT UNSIGNED not null")
    private Integer id;

    /**
     * Entities link between Contacto and Telefono
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Contacto.class, optional = false)
    @JoinColumn(name = "tel_contacto_id", nullable = false)
    @JsonBackReference
    private Contacto telContacto;

    /**
     * Telephone number
     */
    @Column(name = "tel_telefono", nullable = false, length = 30)
    private String telTelefono;

    /**
     * Tuple creation date-time
     */
    @Column(name = "tel_created_at", nullable = false, updatable = false)
    private Instant telCreatedAt;

    /**
     * Tuple last update date-time
     */
    @Column(name = "tel_updated_at")
    private Instant telUpdatedAt;

}