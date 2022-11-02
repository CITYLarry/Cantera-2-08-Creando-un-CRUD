package com.sofka.contactos.controller;

import com.sofka.contactos.domain.Contacto;
import com.sofka.contactos.domain.Telefono;
import com.sofka.contactos.service.LibretaService;
import com.sofka.contactos.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller for the address book
 *
 * @version 1.0.0 2022-11-01
 * @author Larry Mateo Ramirez C.
 */
@Slf4j
@RestController
public class LibretaController {

    /**
     * Service for the address book handle
     */
    @Autowired
    private LibretaService libretaService;

    /**
     * API response variable
     */
    private Response response = new Response();

    /**
     * API HTTP code handle variable
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Handle the system root path
     *
     * @param httpResponse Object fot the redirection handle
     * @return             Response object
     */
    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {return getResponseHome(httpResponse);}

    /**
     * Handle the API root path
     *
     * @param httpResponse Object fot the redirection handle
     * @return             Response object
     */
    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {return getResponseHome(httpResponse);}

    /**
     * Handle the API version root path
     *
     * @param httpResponse Object fot the redirection handle
     * @return             Response object
     */
    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {return getResponseHome(httpResponse);}

    /**
     * Index, return a list of contacts and ist phones numbers
     *
     * @return Response object
     */
    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.message = "Contacts successfully found.";
            response.data = libretaService.getList();
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Return a list object that contains all the contacts in the database,
     * sorted by firstname or lastname, ascendant or descendant
     *
     * @param sortedBy Way to sort the list(firstname or lastname)
     * @param order    Method of sorting (ascendant or descendant)
     * @return         Response object
     */
    @GetMapping(path = "/api/v1/index/{sortedBy}/{order}")
    public ResponseEntity<Response> indexSorted(
            @PathVariable(value = "sortedBy") String sortedBy,
            @PathVariable(value = "order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.message = "Contacts successfully found and sorted.";
            response.data = libretaService.getList(sortedBy, order);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Find contacts by its firstname or lastname and return a list of coincident contacts
     *
     * @param dataToFind Searching filter
     * @return           Response object
     */
    @GetMapping(path = "/api/v1/find/contact/{dataToFind}")
    public ResponseEntity<Response> findContacto(
            @PathVariable(value = "dataToFind") String dataToFind
    ) {
        response.restart();
        try {
            response.message = "Contact(s) successfully found.";
            response.data = libretaService.findContacto(dataToFind);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Creates a new contact
     *
     * @param contact Object that contains the contact to be created
     * @return        Response object
     */
    @PostMapping(path = "/api/v1/contact")
    public ResponseEntity<Response> newContacto (
            //@RequestBody
            Contacto contact
    ) {
        response.restart();
        try {
            log.info("new contact: {}", contact);
            response.message = "Contact successfully created.";
            response.data = libretaService.newContacto(contact);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Creates a new telephone
     *
     * @param telephone Object that contains the telephone to be created
     * @return          Response object
     */
    @PostMapping(path = "/api/v1/telephone")
    public ResponseEntity<Response> newTelefono (
            @RequestBody Telefono telephone
    ) {
        response.restart();
        try {
            log.info("new telephone: {}", telephone);
            response.message = "Telephone successfully created.";
            response.data = libretaService.newTelefono(telephone);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates a contact
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        Response object
     */
    @PutMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> updateContacto(
            @RequestBody Contacto contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.message = "Contact updated";
            response.data = libretaService.updateContacto(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates a contact telephone
     *
     * @param id        Telephone ID to update
     * @param telephone Object that contains the contact to be updated
     * @return          Response object
     */
    @PutMapping(path = "/api/v1/phone/{id}")
    public ResponseEntity<Response> updateTelefono(
            @RequestBody Telefono telephone,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.message = "Telephone updated";
            response.data = libretaService.updateTelefono(id, telephone);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the firstname parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contact  Object that contains the contact to be updated
     * @return         Response object
     */
    @PatchMapping(path = "/api/v1/contact/{id}/name")
    public ResponseEntity<Response> updateContactName(
            @RequestBody Contacto contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.message = "Contact name updated";
            response.data = libretaService.updateFirstName(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the lastname parameter of a contact
     *
     * @param id      Contact ID to update
     * @param contact Object that contains the contact to be updated
     * @return        Response object updated
     */
    @PatchMapping(path = "/api/v1/contact/{id}/lastname")
    public ResponseEntity<Response> updateContactLastname(
            @RequestBody Contacto contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.message = "Contact lastname updated";
            response.data = libretaService.updateLastName(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates ONLY the telephone number of a contact
     *
     * @param id        Telephone ID to update
     * @param telephone Object that contains the contact to be updated
     * @return          Response object updated
     */
    @PatchMapping(path = "/api/v1/telephone/{id}/telephone")
    public ResponseEntity<Response> updateTelephoneNumber(
            @RequestBody Telefono telephone,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.message = "Telephone number updated";
            response.data = libretaService.updateTelefonoNumber(id, telephone);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Deletes a contact from the database by its id
     *
     * @param id Contact ID to delete
     * @return   Response object deleted
     */
    @DeleteMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> deleteContact(
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = libretaService.deleteContacto(id);
            if (response.data == null) {
                response.message = "Contact not found";
                httpStatus = HttpStatus.NOT_FOUND;
                return new ResponseEntity<>(response, httpStatus);
            }
            response.message = "Contact deleted";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Deletes a telephone from the database by its id
     *
     * @param id Telephone ID to delete
     * @return   Response object deleted
     */
    @DeleteMapping(path = "/api/v1/phone/{id}")
    public ResponseEntity<Response> deleteTelephone(
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = libretaService.deleteTelefono(id);
            if (response.data == null) {
                response.message = "Telephone not found";
                httpStatus = HttpStatus.NOT_FOUND;
                return new ResponseEntity<>(response, httpStatus);
            }
            response.message = "Telephone deleted";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Redirects to the index controller
     *
     * @param httpResponse Object fot the redirection handle
     * @return             Response object
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse){
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/index");
        } catch (IOException e) {
            response.error = true;
            response.message = e.getMessage();
            response.data = e.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * System exception handler
     *
     * @param e Object exception
     */
    private void getErrorMessageInternal(Exception e) {
        response.error = true;
        response.message = e.getMessage();
        response.data = e.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Data access exception handler
     *
     * @param e Object exception
     */
    private void getErrorMessageForResponse(DataAccessException e) {
        response.error = true;
        if(e.getRootCause() instanceof SQLException sqlE) {
            var sqlECode = sqlE.getErrorCode();
            switch (sqlECode) {
                case 1062 -> {
                    response.message = "User already exists";
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                case 1452 -> {
                    response.message = "User does not exist";
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                }
                default -> {
                    response.message = e.getMessage();
                    response.data = e.getCause();
                    httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                }
            }
        } else {
            response.message = e.getMessage();
            response.data = e.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
