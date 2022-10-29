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

@Slf4j
@RestController
public class LibretaController {

    @Autowired
    private LibretaService libretaService;

    private Response response = new Response();

    private HttpStatus httpStatus = HttpStatus.OK;

    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {}

    @GetMapping(path = "/api/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {}

    @GetMapping(path = "/api/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {}

    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index() {}

    @GetMapping(path = "/api/v1/index/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(
            @PathVariable(value = "orderBy") String orderBy,
            @PathVariable(value = "order") Sort.Direction order
    ) {}

    @GetMapping(path = "/api/v1/find/contact/{dataToFind}")
    public ResponseEntity<Response> findContacto(
            @PathVariable(value = "dataToFind") String dataToFind
    ) {}

    @PostMapping(path = "/api/v1/contact")
    public ResponseEntity<Response> newContacto (
            @RequestBody Contacto contact
    ) {}

    @PostMapping(path = "/api/v1/telephone")
    public ResponseEntity<Response> newContacto (
            @RequestBody Telefono telephone
    ) {}

    @PutMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> updateContacto(
            @RequestBody Contacto contact,
            @PathVariable(value = "id") Integer id
    ) {}

    @PutMapping(path = "/api/v1/phone/{id}")
    public ResponseEntity<Response> updateTelefono(
            @RequestBody Telefono telefono,
            @PathVariable(value="id") Integer id
    ) { }

    @PatchMapping(path = "/api/v1/contact/{id}/name")
    public ResponseEntity<Response> updateContactoName(
            @RequestBody Contacto contact,
            @PathVariable(value = "id") Integer id
    ) {}

    @PatchMapping(path = "/api/v1/contact/{id}/lastname")
    public ResponseEntity<Response> updateContactoLastname(
            @RequestBody Contacto contact,
            @PathVariable(value = "id") Integer id
    ) {}

    @PatchMapping(path = "/api/v1/telephone/{id}/telephone")
    public ResponseEntity<Response> updateTelefonoNumber(
            @RequestBody Telefono telefono,
            @PathVariable(value = "id") Integer id
    ) {}

    @DeleteMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> deleteContacto(
            @PathVariable(value = "id") Integer id
    ) {}

    @DeleteMapping(path = "/api/v1/phone/{id}")
    public ResponseEntity<Response> deleteTelefono(
            @PathVariable(value="id") Integer id
    ) {}

    public ResponseEntity<Response> getResponseHome(
            HttpServletResponse httpResponse
    ) {}

    private void getErrorMessageInternal(Exception e) {}

    private void getErrorMessageForResponse(DataAccessException e) {}
}
