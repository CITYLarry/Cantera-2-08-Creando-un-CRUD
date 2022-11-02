package com.sofka.contactos.service.interfaces;

import com.sofka.contactos.domain.Contacto;
import com.sofka.contactos.domain.Telefono;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Libreta service interface
 *
 * @version 1.0.0 2022-10-27
 * @author Larry Mateo Ramirez C.
 */
public interface ILibreta {

    /**
     * Return a list object that contains all the contacts in the database
     * 
     * @return List of contacts
     */
    public List<Contacto> getList();
    
    /**
     * Return a list object that contains all the contacts in the database,
     * sorted by firstname or lastname, ascendant or descendant
     * 
     * @param sortedBy Way to sort the list(firstname or lastname)
     * @param order    Method of sorting (ascendant or descendant)
     * @return         List of contacts
     */
    public List <Contacto> getList(String sortedBy, Sort.Direction order);

    /**
     * Find contacts by its firstname or lastname and return a list of coincident contacts
     *
     * @param dataToFind Seaching filter
     * @return           List of contacts
     */
    public List <Contacto> findContacto(String dataToFind);

    /**
     * Creates a new contact
     *
     * @param contacto Object that contains the contact to be created
     * @return         Contacto object created
     */
    public Contacto newContacto(Contacto contacto);

    /**
     * Updates a contact
     *
     * @param id       Contact ID to update
     * @param contacto Object that contains the contact to be updated
     * @return         Contacto object updated
     */
    public Contacto updateContacto(Integer id, Contacto contacto);

    /**
     * Updates the firstname parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contacto Object that contains the contact to be updated
     * @return         Contacto object updated
     */
    public Contacto updateFirstName(Integer id, Contacto contacto);

    /**
     * Updates the lastname parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contacto Object that contains the contact to be updated
     * @return         Contacto object updated
     */
    public Contacto updateLastName(Integer id, Contacto contacto);

    /**
     * Deletes a contact from the database by its id
     *
     * @param id Contact ID to delete
     * @return   Contacto object deleted
     */
    public Contacto deleteContacto(Integer id);

    /**
     * Creates a new telephone
     *
     * @param telefono Object that contains the telephone to be created
     * @return         Telefono object created
     */
    public Telefono newTelefono(Telefono telefono);

    /**
     * Updates a contact telephone
     *
     * @param id       Telephone ID to update
     * @param telefono Object that contains the contact to be updated
     * @return         Telefono object updated
     */
    public Telefono updateTelefono(Integer id, Telefono telefono);

    /**
     * Updates ONLY the telephone number of a contact
     *
     * @param id       Telephone ID to update
     * @param telefono Object that contains the contact to be updated
     * @return         Telefono object updated
     */
    public Telefono updateTelefonoNumber(Integer id, Telefono telefono);

    /**
     * Deletes a telephone from the database by its id
     *
     * @param id Telephone ID to delete
     * @return   Telefono object deleted
     */
    public Telefono deleteTelefono(Integer id);
}
