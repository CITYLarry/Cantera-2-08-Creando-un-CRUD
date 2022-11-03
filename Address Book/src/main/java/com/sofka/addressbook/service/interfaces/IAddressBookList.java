package com.sofka.addressbook.service.interfaces;

import com.sofka.addressbook.domain.AddressBookList;

import java.util.List;

/**
 * AdreesBookList service interface
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
public interface IAddressBookList {

    /**
     * Return a list object that contains all the contacts in the database
     *
     * @return List of contacts
     */
    public List<AddressBookList> getList();

    /**
     * Return a list object that contains all the contacts white coincident filter
     *
     * @param data Seaching filter
     * @return     List of contacts
     */
    public List<AddressBookList> search(String data);

    /**
     * Creates a new contact
     *
     * @param contact Object that contains the contact to be created
     * @return        AddressBookList object created
     */
    public AddressBookList newContact(AddressBookList contact);

    /**
     * Updates a contact
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        AddressBookList object updated
     */
    public AddressBookList updateContact(Integer id, AddressBookList contact);

    /**
     * Updates a contact name
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        AddressBookList object updated
     */
    public AddressBookList updateName(Integer id, AddressBookList contact);

    /**
     * Updates a contact phone number
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        AddressBookList object updated
     */
    public AddressBookList updatePhone(Integer id, AddressBookList contact);

    /**
     * Updates a contact email
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        AddressBookList object updated
     */
    public AddressBookList updateMail(Integer id, AddressBookList contact);

    /**
     * Logic delete of a contact
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        AddressBookList object updated
     */
    public AddressBookList updateDelete(Integer id, AddressBookList contact);

    /**
     * Deletes a contact
     *
     * @param id      Contact ID to delete
     * @return        AddressBookList object deleted
     */
    public AddressBookList deleteContact(Integer id);
}
