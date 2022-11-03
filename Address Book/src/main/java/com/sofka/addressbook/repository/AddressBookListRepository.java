package com.sofka.addressbook.repository;

import com.sofka.addressbook.domain.AddressBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for AdressBookList entity
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
public interface AddressBookListRepository extends JpaRepository<AddressBookList, Integer> {

    @Query(value = "SELECT a.id " +
            "FROM AddressBookList a " +
            "WHERE a.abName LIKE %:data% " +
            "ORDER BY a.abName ASC")
    public List<AddressBookList> search(@Param("data") String data);
}