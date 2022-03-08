package com.personal.taller.controller;

import com.personal.taller.dto.ProveedorDto;
import com.personal.taller.repository.ProveedorRepository;
import com.personal.taller.response.ProveedorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("proveedor")
public class ProveedorController {
    @Autowired
    ProveedorRepository proveedorRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody ResponseEntity getAllProveedor() {
        System.out.println("getAll");
        ProveedorResponse proveedorResponse = new ProveedorResponse();
        proveedorResponse.setProveedorDtoList(proveedorRepository.findAll());
        return new ResponseEntity<>(proveedorResponse,HttpStatus.OK);
    }

    @GetMapping(value = "/{rut}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ProveedorDto getProveedor(@PathVariable String rut) {
        return proveedorRepository.findByRut(rut);
    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> create(@RequestBody ProveedorDto newProveedor) {

        //ProveedorDto proveedor = proveedorRepository.save(newProveedor);
        ProveedorDto proveedorResponse;
        try {
            proveedorResponse = proveedorRepository.save(newProveedor);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(proveedorResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> update(@RequestBody ProveedorDto newProveedor) {
        ProveedorDto proveedorResponse;
        try {
            proveedorResponse = proveedorRepository.save(newProveedor);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(proveedorResponse, HttpStatus.CREATED);
        /*ProveedorDto proveedor = proveedorRepository.save(newProveedor);
        if (proveedor == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
        }*/
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<ProveedorDto> delete(@RequestBody ProveedorDto newProveedor) {
        newProveedor.setHabilitado(0);
        ProveedorDto proveedorResponse;
        try {
            proveedorResponse = proveedorRepository.save(newProveedor);
        }catch (Exception e){
            throw e;
        }
        return new ResponseEntity<>(proveedorResponse, HttpStatus.CREATED);

        /*ProveedorDto proveedor = proveedorRepository.save(newProveedor);
        if (proveedor == null) {
            throw new RuntimeException();
        } else {
            return new ResponseEntity<>(proveedor, HttpStatus.CREATED);
        }*/
    }
}
