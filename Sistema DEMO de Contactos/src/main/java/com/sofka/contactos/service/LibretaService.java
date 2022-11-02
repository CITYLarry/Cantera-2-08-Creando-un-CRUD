package com.sofka.contactos.service;

import com.sofka.contactos.domain.Contacto;
import com.sofka.contactos.domain.Telefono;
import com.sofka.contactos.repository.ContactoRepository;
import com.sofka.contactos.repository.TelefonoRepository;
import com.sofka.contactos.service.interfaces.ILibreta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Service implementation for the contact list Libreta
 *
 * @version 1.0.0 2022-10-28
 * @author Larry Mateo Ramirez C.
 */
@Service
public class LibretaService implements ILibreta {

    /**
     * Contacto repository
     */
    @Autowired
    private ContactoRepository contactoRepository;

    /**
     * Telefono repository
     */
    @Autowired
    private TelefonoRepository telefonoRepository;

    /**
     * Return a list object that contains all the contacts in the database
     *
     * @return List of contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getList() {
        return (List<Contacto>) contactoRepository.findAll();
    }

    /**
     * Return a list object that contains all the contacts in the database,
     * sorted by firstname or lastname, ascendant or descendant
     *
     * @param sortedBy Way to sort the list(firstname or lastname)
     * @param order    Method of sorting (ascendant or descendant)
     * @return         List of contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> getList(String sortedBy, Sort.Direction order) {
        return (List<Contacto>) contactoRepository.findAll(Sort.by(order, sortedBy));
    }

    /**
     * Find contacts by its firstname or lastname and return a list of coincident contacts
     *
     * @param dataToFind Seaching filter
     * @return           List of contacts
     */
    @Override
    @Transactional(readOnly = true)
    public List<Contacto> findContacto(String dataToFind) {
        return contactoRepository.findContacto(dataToFind);
    }

    /**
     * Creates a new contact
     *
     * @param contacto Object that contains the contact to be created
     * @return         Contacto object created
     */
    @Override
    @Transactional
    public Contacto newContacto(Contacto contacto) {
        contacto.setCntCreatedAt(Instant.now());
        return contactoRepository.save(contacto);
    }

    /**
     * Updates a contact
     *
     * @param id       Contact ID to update
     * @param contacto Object that contains the contact to be updated
     * @return         Contacto object updated
     */
    @Override
    @Transactional
    public Contacto updateContacto(Integer id, Contacto contacto) {
        contacto.setId(id);
        contacto.setCntUpdatedAt(Instant.now());
        contactoRepository.save(contacto);
        return contacto;
    }

    /**
     * Updates the firstname parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contacto Object that contains the contact to be updated
     * @return         Contacto object updated
     */
    @Override
    @Transactional
    public Contacto updateFirstName(Integer id, Contacto contacto) {
        contacto.setId(id);
        contacto.setCntUpdatedAt(Instant.now());
        contactoRepository.updateFirstName(id, contacto.getCntNombre());
        return contacto;
    }

    /**
     * Updates the lastname parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contacto Object that contains the contact to be updated
     * @return         Contacto object updated
     */
    @Override
    @Transactional
    public Contacto updateLastName(Integer id, Contacto contacto) {
        contacto.setId(id);
        contacto.setCntUpdatedAt(Instant.now());
        contactoRepository.updateLastname(id, contacto.getCntApellido());
        return contacto;
    }

    /**
     * Deletes a contact from the database by its id
     *
     * @param id Contact ID to delete
     * @return   Contacto object deleted
     */
    @Override
    @Transactional
    public Contacto deleteContacto(Integer id) {
        var contacto = contactoRepository.findById(id);
        if (contacto.isEmpty()){
            return null;
        }
        contactoRepository.delete(contacto.get());
        return contacto.get();
    }

    /**
     * Creates a new telephone
     *
     * @param telefono Object that contains the telephone to be created
     * @return         Telefono object created
     */
    @Override
    @Transactional
    public Telefono newTelefono(Telefono telefono) {
        telefono.setTelCreatedAt(Instant.now());
        return telefonoRepository.save(telefono);
    }

    /**
     * Updates a contact telephone
     *
     * @param id       Telephone ID to update
     * @param telefono Object that contains the contact to be updated
     * @return         Telefono object updated
     */
    @Override
    @Transactional
    public Telefono updateTelefono(Integer id, Telefono telefono) {
        telefono.setId(id);
        telefono.setTelUpdatedAt(Instant.now());
        telefonoRepository.save(telefono);
        return telefono;
    }

    /**
     * Updates ONLY the telephone number of a contact
     *
     * @param id       Telephone ID to update
     * @param telefono Object that contains the contact to be updated
     * @return         Telefono object updated
     */
    @Override
    @Transactional
    public Telefono updateTelefonoNumber(Integer id, Telefono telefono) {
        telefono.setId(id);
        telefono.setTelUpdatedAt(Instant.now());
        telefonoRepository.updateTelefono(id, telefono.getTelTelefono());
        return telefono;
    }

    /**
     * Deletes a telephone from the database by its id
     *
     * @param id Telephone ID to delete
     * @return   Telefono object deleted
     */
    @Override
    @Transactional
    public Telefono deleteTelefono(Integer id) {
        var telefono = telefonoRepository.findById(id);
        if (telefono.isEmpty()){
            return null;
        }
        telefonoRepository.delete(telefono.get());
        return telefono.get();
    }
}
