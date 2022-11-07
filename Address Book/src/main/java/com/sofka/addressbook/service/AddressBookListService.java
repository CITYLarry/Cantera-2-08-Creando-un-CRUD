package com.sofka.addressbook.service;

import com.sofka.addressbook.domain.AddressBookList;
import com.sofka.addressbook.repository.AddressBookListRepository;
import com.sofka.addressbook.service.interfaces.IAddressBookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Service implementation for the contact list AddressBookList
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
@Service
public class AddressBookListService implements IAddressBookList{

    /**
     * AdressBookList repository
     */
    @Autowired
    private AddressBookListRepository addressBookListRepository;

    /**
     * Return a list object that contains all the contacts in the database
     *
     * @return List of contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddressBookList> getList() {
        return addressBookListRepository.findAll();
    }

    /**
     * Return an object that contains the contact with the coincident id
     *
     * @param id Object to retun id
     * @return   List of contacts
     */
    @Override
    public AddressBookList findId(Integer id) {
        return addressBookListRepository.findId(id);
    }

    /**
     * Return a list object that contains all the contacts with the coincident filter
     *
     * @param data Searching filter
     * @return     List of contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddressBookList> search(String data) {
        return addressBookListRepository.search(data);
    }

    /**
     * Creates a new contact
     *
     * @param contact Object that contains the contact to be created
     * @return        AddressBookList object created
     */
    @Override
    @Transactional
    public AddressBookList newContact(AddressBookList contact) {
        contact.setAbCreatedAt(Instant.now());
        addressBookListRepository.save(contact);
        return contact;
    }

    /**
     * Updates a contact
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return AddressBookList object updated
     */
    @Override
    @Transactional
    public AddressBookList updateContact(Integer id, AddressBookList contact) {
        contact.setId(id);
        contact.setAbUpdatedAt(Instant.now());
        addressBookListRepository.save(contact);
        return contact;
    }

    /**
     * Updates a contact name
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return AddressBookList object updated
     */
    @Override
    @Transactional
    public AddressBookList updateName(Integer id, AddressBookList contact) {
        contact.setId(id);
        contact.setAbUpdatedAt(Instant.now());
        addressBookListRepository.updateName(id, contact.getAbName());
        return contact;
    }

    /**
     * Updates a contact phone number
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return AddressBookList object updated
     */
    @Override
    @Transactional
    public AddressBookList updatePhone(Integer id, AddressBookList contact) {
        contact.setId(id);
        contact.setAbUpdatedAt(Instant.now());
        addressBookListRepository.updatePhone(id, contact.getAbPhone());
        return contact;
    }

    /**
     * Updates a contact email
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return AddressBookList object updated
     */
    @Override
    @Transactional
    public AddressBookList updateMail(Integer id, AddressBookList contact) {
        contact.setId(id);
        contact.setAbUpdatedAt(Instant.now());
        addressBookListRepository.updateMail(id, contact.getAbMail());
        return contact;
    }

    /**
     * Updates a contact birthday
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        AddressBookList object updated
     */
    @Override
    @Transactional
    public AddressBookList updateBday(Integer id, AddressBookList contact) {
        contact.setId(id);
        contact.setAbUpdatedAt(Instant.now());
        addressBookListRepository.updateBday(id, contact.getAbBday());
        return contact;
    }

    /**
     * Logic delete of a contact
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return AddressBookList object updated
     */
    @Override
    @Transactional
    public AddressBookList updateDelete(Integer id, AddressBookList contact) {
        contact.setId(id);
        contact.setAbUpdatedAt(Instant.now());
        contact.setAbDeletedAt(Instant.now());
        addressBookListRepository.updateDelete(id);
        return contact;
    }

    /**
     * Deletes a contact
     *
     * @param id Contact ID to delete
     * @return AddressBookList object deleted
     */
    @Override
    @Transactional
    public AddressBookList deleteContact(Integer id) {
        var contact = addressBookListRepository.findById(id);
        if(contact.isEmpty()) {
            return null;
        }
        addressBookListRepository.delete(contact.get());
        return contact.get();
    }
}
