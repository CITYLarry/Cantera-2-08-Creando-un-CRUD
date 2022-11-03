package com.sofka.addressbook.repository;

import com.sofka.addressbook.domain.AddressBookList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for AdressBookList entity
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
public interface AddressBookListRepository extends JpaRepository<AddressBookList, Integer> {

    /**
     * Return a lis of contacts that contains the searching filter in its name
     *
     * @param data Data to search
     * @return     List of contacts that contain the search filter
     */
    @Query(value = "SELECT a.id " +
            "FROM AddressBookList a " +
            "WHERE a.abName LIKE %:data% " +
            "ORDER BY a.abName ASC")
    public List<AddressBookList> search(@Param("data") String data);

    /**
     * Updates the contact name in the database
     *
     * @param id   Contact ID to update
     * @param name Name to update
     */
    @Modifying
    @Query(value = "UPDATE AddressBookList a " +
            "SET a.abName = :name, a.abUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE a.id = :id")
    public void updateName(@Param(value = "id") Integer id, @Param(value = "name") String name);

    /**
     * Updates the contact phone number in the database
     *
     * @param id    Contact ID to update
     * @param phone Phone number to update
     */
    @Modifying
    @Query(value = "UPDATE AddressBookList a " +
            "SET a.abPhone = :phone, a.abUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE a.id = :id")
    public void updatePhone(@Param(value = "id") Integer id, @Param(value = "phone") String phone);

    /**
     * Updates the contact mail in the database
     *
     * @param id   Contact ID to update
     * @param mail Mail to update
     */
    @Modifying
    @Query(value = "UPDATE AddressBookList a " +
            "SET a.abMail = :mail, a.abUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE a.id = :id")
    public void updateMail(@Param(value = "id") Integer id, @Param(value = "mail") String mail);

    /**
     * Updates the contact birthday in the database
     *
     * @param id   Contact ID to update
     * @param bday Date to update
     */
    @Modifying
    @Query(value = "UPDATE AddressBookList a " +
            "SET a.abBday = :bday, a.abUpdatedAt = CURRENT_TIMESTAMP " +
            "WHERE a.id = :id")
    public void updateBday(@Param(value = "id") Integer id, @Param(value = "bday") LocalDate bday);

    /**
     * Updates the contact to be deleted in the database
     *
     * @param id Contact ID to delete
     */
    @Modifying
    @Query(value = "UPDATE AddressBookList a " +
            "SET a.abDeletedAt = CURRENT_TIMESTAMP " +
            "WHERE a.id = :id")
    public void updateDelete(@Param(value = "id") Integer id);
}