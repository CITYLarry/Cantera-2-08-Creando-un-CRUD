package com.sofka.addressbook.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.io.Serializable;
import java.time.Instant;

/**
 * AddrressBookList entity
 *
 * @version 1.0.0 2022-11-3
 * @author Larry Mateo Ramirez C.
 */
@Data
@Entity
@Table(name = "address_book_list")
public class AddressBookList implements Serializable {

    /**
     * Handle the tuple id
     */
    private static final long serialVersionUID = 1L;

    /**
     * Tuple ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ab_id", nullable = false)
    private Integer id;

    /**
     * Contact full name
     */
    @Column(name = "ab_name", nullable = false, length = 80)
    private String abName;

    /**
     * Contact phone number
     */
    @Column(name = "ab_phone", length = 45)
    private String abPhone;

    /**
     * Contact email
     */
    @Column(name = "ab_mail", length = 120)
    private String abMail;

    /**
     * Contact birth date
     */
    @Column(name = "ab_bday")
    private String abBday;

    /**
     * Tuple creation date-time
     */
    @Column(name = "ab_created_at", nullable = false, updatable = false)
    private Instant abCreatedAt;

    /**
     * Tuple last update date-time
     */
    @Column(name = "ab_updated_at")
    private Instant abUpdatedAt;

    /**
     * Tuple logic delete date-time
     */
    @Column(name = "ab_deleted_at")
    private Instant abDeletedAt;
}