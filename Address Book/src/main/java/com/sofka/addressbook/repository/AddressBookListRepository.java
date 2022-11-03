package com.sofka.addressbook.repository;

import com.sofka.addressbook.domain.AddressBookList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for AdressBookList entity
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
public interface AddressBookListRepository extends JpaRepository<AddressBookList, Integer> {
}