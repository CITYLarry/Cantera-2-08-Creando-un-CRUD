package com.sofka.addressbook.controller;

import com.sofka.addressbook.domain.AddressBookList;
import com.sofka.addressbook.service.AddressBookListService;
import com.sofka.addressbook.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controller for the address book
 *
 * @version 1.0.0 2022-11-03
 * @author Larry Mateo Ramirez C.
 */
@Slf4j
@RestController
public class AddressBookController {

    /**
     * Service for the address book handle
     */
    @Autowired
    private AddressBookListService addressBookListService;

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
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Handle the API root path
     *
     * @param httpResponse Object fot the redirection handle
     * @return             Response object
     */
    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Handle the API version root path
     *
     * @param httpResponse Object fot the redirection handle
     * @return             Response object
     */
    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index, return a list of contacts from the address book
     *
     * @return Response object
     */
    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = addressBookListService.getList();
            response.message = "Contacts successfully found.";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Return an object that contains the contact with the coincident id
     *
     * @param id Searching filter
     * @return     Response object
     */
    @GetMapping(path = "/api/v1/find/{id}")
    public ResponseEntity<Response> findId(
            @PathVariable(value = "id") Integer id) {
        response.restart();
        try {
            response.data = addressBookListService.findId(id);
            response.message = "Contact successfully found.";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }


    @GetMapping(path = "/api/v1/find/name/{name}")
    public ResponseEntity<Response> findByName(
            @PathVariable(value = "name") String name) {
        response.restart();
        try {
            response.data = addressBookListService.findByName(name);
            response.message = "Contact successfully found.";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Return a list object that contains all the contacts with the coincident filter
     *
     * @param data Searching filter
     * @return     Response object
     */
    @GetMapping(path = "/api/v1/search/{data}")
    public ResponseEntity<Response> search(
            @PathVariable(value = "data") String data) {
        response.restart();
        try {
            response.data = addressBookListService.search(data);
            response.message = "Contact(s) successfully found.";
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
    @PostMapping(path = "/api/v1/address_book")
    public ResponseEntity<Response> newContact (
            AddressBookList contact
    ) {
        response.restart();
        try {
            log.info("new contact: {}", contact);
            response.data = addressBookListService.newContact(contact);
            response.message = "Contact successfully created.";
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
    @PutMapping(path = "/api/v1/address_book/{id}")
    public ResponseEntity<Response> updateContact(
            @RequestBody AddressBookList contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.updateContact(id, contact);
            response.message = "Contact updated";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the name parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contact  Object that contains the contact to be updated
     * @return         Response object
     */
    @PatchMapping(path = "/api/v1/address_book/{id}/name")
    public ResponseEntity<Response> updateName(
            @RequestBody AddressBookList contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.updateName(id, contact);
            response.message = "Contact name updated";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the phone number parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contact  Object that contains the contact to be updated
     * @return         Response object
     */
    @PatchMapping(path = "/api/v1/address_book/{id}/phone")
    public ResponseEntity<Response> updatePhone(
            @RequestBody AddressBookList contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.updatePhone(id, contact);
            response.message = "Contact phone updated";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the mail parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contact  Object that contains the contact to be updated
     * @return         Response object
     */
    @PatchMapping(path = "/api/v1/address_book/{id}/mail")
    public ResponseEntity<Response> updateMail(
            @RequestBody AddressBookList contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.updateMail(id, contact);
            response.message = "Contact mail updated";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the mail parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contact  Object that contains the contact to be updated
     * @return         Response object
     */
    @PatchMapping(path = "/api/v1/address_book/{id}/birthday")
    public ResponseEntity<Response> updateBday(
            @RequestBody AddressBookList contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.updateBday(id, contact);
            response.message = "Contact mail updated";
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException e) {
            getErrorMessageForResponse(e);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Updates the deleted_at parameter of a contact
     *
     * @param id       Contact ID to update
     * @param contact  Object that contains the contact to be updated
     * @return         Response object
     */
    @PatchMapping(path = "/api/v1/address_book/{id}/delete")
    public ResponseEntity<Response> updateDelete(
            @RequestBody AddressBookList contact,
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.updateDelete(id, contact);
            response.message = "Contact deleted successfully";
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
    @DeleteMapping(path = "/api/v1/address_book/{id}")
    public ResponseEntity<Response> delete(
            @PathVariable(value = "id") Integer id
    ) {
        response.restart();
        try {
            response.data = addressBookListService.deleteContact(id);
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
